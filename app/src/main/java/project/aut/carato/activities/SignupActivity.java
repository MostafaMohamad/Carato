package project.aut.carato.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.User;

public class SignupActivity extends AppCompatActivity {


    private DatePickerDialog.OnDateSetListener dateListener;
    public DBHelper mydb;
    EditText name;
    EditText username;
    EditText email;
    Spinner gender;
    TextView birth;
    EditText pass;
    EditText cpass;

    String genderType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        mydb = new DBHelper(this);

        name = findViewById(R.id.editText_fname);
        username = findViewById(R.id.editText_uname);
        email = findViewById(R.id.editText_email);
        gender = findViewById(R.id.spinner_gender);
        birth = findViewById(R.id.editText_birth);
        pass = findViewById(R.id.editText_pass);
        cpass = findViewById(R.id.editText_cpass);

        SetGender();

        name.addTextChangedListener(mtextWatcher);
        username.addTextChangedListener(mtextWatcher);
        email.addTextChangedListener(mtextWatcher);
        birth.addTextChangedListener(mtextWatcher);
        pass.addTextChangedListener(mtextWatcher);
        cpass.addTextChangedListener(mtextWatcher);


        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long max = 568024668000L;
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE,0);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this,
                       R.style.DialogTheme, dateListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime() - max);
                datePickerDialog.show();
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String DOB = dayOfMonth + "-" + month + "-"+ year;
                birth.setText(DOB);
            }
        };


    }
    TextWatcher mtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            CheckEmptyValues();
        }
    };


    public void AddNewUser() {
        User user = new User(name.getText().toString(),
                username.getText().toString(),
                email.getText().toString(),
                genderType,
                birth.getText().toString(),
                pass.getText().toString(),
                "0");

        mydb.InsertUser(user);

        Intent intent = new Intent(SignupActivity.this, CarListActivity.class);
        intent.putExtra("uname",username.getText().toString());
        startActivity(intent);
        finish();
    }

    public boolean PassConfirm() {
        String password = pass.getText().toString();
        String confirmed = cpass.getText().toString();

        if (password.equals(confirmed)) {
            return true;
        } else {
            cpass.setError("Password does not match");
            return false;
        }
    }

    public boolean CheckUsername(String uname) {

        boolean notAvailable =  mydb.UsernameAvailability(uname);
        if (notAvailable){
            username.setError("Username is not available");
            return true;
        }else {
            return false;
        }

    }

    public void RegisterUser(View view) {
        if (PassConfirm()) {
            AddNewUser();
        }
    }

    public void CheckEmptyValues() {
        Button reg = findViewById(R.id.register_btn);

        String s1 = name.getText().toString();
        String s2 = username.getText().toString();
        String s3 = email.getText().toString();
        String s4 = birth.getText().toString();
        String s5 = pass.getText().toString();
        String s6 = cpass.getText().toString();

        if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") ||
                s6.equals("") || CheckUsername(s2)){
            reg.setEnabled(false);
            reg.setAlpha(.5f);
        }else {
            reg.setEnabled(true);
            reg.setAlpha(1f);
        }
    }

    public void SetGender(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupActivity.this,
                R.layout.spinner_text,
                getResources().getStringArray(R.array.gender));
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderType = gender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
    }


}
