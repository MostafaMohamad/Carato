package project.aut.carato.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import project.aut.carato.Car;
import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.SharedPrefs;

public class ReservationActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener dateListener;
    TextView from;
    TextView to;
    Calendar c;
    boolean dateFrom;
    boolean dateTo;
    String Date;
    DBHelper mydb;
    boolean reserved;

    String minDay;
    String minMonth;
    String minYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        mydb = new DBHelper(this);
        Intent intent = getIntent();
        Car car = (Car)intent.getSerializableExtra("reserve");
        TextView carName = findViewById(R.id.textView25);
        carName.setText(car.getType() + " "+ car.getName());
        from = findViewById(R.id.from_txt);
        to = findViewById(R.id.to_txt);

        Reservation reservation = mydb.getCarInfo(car.getId());
        if (reservation != null){
            reserved = true;

            String dateTo = reservation.getTo();
            String[] minDate = dateTo.split("-");

            c = Calendar.getInstance();
            c.set(Integer.parseInt(minDate[2]), Integer.parseInt(minDate[1]) - 1, Integer.parseInt(minDate[0]));

        }else {
            reserved = false;
        }


        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month +1;
                c = Calendar.getInstance();
                c.set(year,month - 1, dayOfMonth);
                Date = dayOfMonth + "-" + month + "-"+ year;

                if (dateFrom){
                    dateFrom = false;
                    from.setText(Date);
                }
                else if (dateTo){
                    dateTo = false;
                    to.setText(Date);
                }
            }
        };

        from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Button toBtn = findViewById(R.id.to_btn);
                toBtn.setEnabled(true);
                toBtn.setAlpha(1f);

            }
        });

    }

    public void DateFrom(View view) {
        CalendarDate(1);
        dateFrom = true;

    }

    public void DateTo(View view) {
        CalendarDate(2);
        dateTo = true;
    }

    public void ConfirmRes(View view) {
        Intent intent = getIntent();
        Car car = (Car)intent.getSerializableExtra("reserve");
        if (mydb.ReserveCar(car.getId(),
                Integer.parseInt(SharedPrefs.getInstance(this).getUserId()),
                from.getText().toString(),
                to.getText().toString()) ){
            Intent intent1 = new Intent(this,CarListActivity.class);
            startActivity(intent1);
            finish();
        }else {
            Toast.makeText(this, "This car cannot be reserved", Toast.LENGTH_SHORT).show();
        }
    }

    public void CalendarDate(int op){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,0);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ReservationActivity.this,
                R.style.DialogTheme, dateListener, year, month, day);
        if (op == 1 && !reserved) {
            datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        } else if (op == 1 && reserved){
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        } else if (op == 2){
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        }
        datePickerDialog.show();
    }

     /*String[] splitedVal = Date.split("-");
        String s1 = splitedVal[0];
        String s2 = splitedVal[1];
        String s3 = splitedVal[2];

        Toast.makeText(this, s3, Toast.LENGTH_SHORT).show();*/
}
