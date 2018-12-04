package project.aut.carato.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.SharedPrefs;
import project.aut.carato.User;

public class ProfileActivity extends AppCompatActivity {

    int uid;
    TextView username;
    EditText fname;
    EditText gender;
    EditText birth;
    EditText email;

    private String pwd;

    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mydb = new DBHelper(this);

        uid = Integer.valueOf(SharedPrefs.getInstance(this).getUserId());

        username = findViewById(R.id.profile_username);
        fname = findViewById(R.id.profile_fname);
        gender = findViewById(R.id.profile_gender);
        birth = findViewById(R.id.profile_birth);
        email = findViewById(R.id.profile_email);

        getUser();
    }

    public void getUser(){

        User mUser = mydb.GetUserById(uid);
        pwd = mUser.getPass();

        username.setText(mUser.getUname());
        fname.setText(mUser.getName());
        gender.setText(mUser.getGender());
        birth.setText(mUser.getBirth());
        email.setText(mUser.getEmail());

    }

    public void UpdateInfo(){
        String newFname = fname.getText().toString();
        String newEmail = email.getText().toString();
        mydb.InfoUpdate(uid, newFname, newEmail);
    }


    public void SaveChanges(View view) {
        UpdateInfo();
        Intent intent = new Intent(this,CarListActivity.class);
        startActivity(intent);
        finish();
    }

    public void ChangePass(View view){
        Intent intent = new Intent(this,ResetPasswordActivity.class);
        intent.putExtra("pwd",pwd);
        startActivity(intent);
    }
}
