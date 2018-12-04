package project.aut.carato.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.SharedPrefs;
import project.aut.carato.User;

public class ProfileActivity extends AppCompatActivity {

    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mydb = new DBHelper(this);


    }

    public void getUser(){
        int uid = Integer.valueOf(SharedPrefs.getInstance(this).getUserId());
        User mUser = mydb.GetUserById(uid);
    }
}
