package project.aut.carato.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import project.aut.carato.Car;
import project.aut.carato.R;

public class ReservationActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener dateListener;
    TextView from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Intent intent = getIntent();
        Car car = (Car)intent.getSerializableExtra("reserve");
        TextView carName = findViewById(R.id.textView25);
        carName.setText(car.getType() + " "+ car.getName());
        from = findViewById(R.id.from_txt);

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String Date = dayOfMonth + "-" + month + "-"+ year;

                from.setText(Date);
            }
        };

    }

    public void DateFrom(View view) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE,0);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(ReservationActivity.this,
                    R.style.DialogTheme, dateListener, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
            datePickerDialog.show();

    }

    public void ConfirmRes(View view) {

    }



    /*
    LocalDate start = LocalDate.of(2016,1,1);
    LocalDate end = LocalDate.of(2016,1,15);
    LocalDate wanted = LocalDate.of(2016,1,16);

    Boolean containsWanted = ( ! wanted.isBefore( start ) ) && ( wanted.isBefore( end ) ) ;

        if (containsWanted)
            Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
            */
}
