package project.aut.carato.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.SharedPrefs;

public class MainActivity extends AppCompatActivity {
    public DBHelper mydb;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        username = findViewById(R.id.editText_username);
    }

    public void Check(String username, String Password){
        boolean logged =  mydb.GetUserCredentials(username, Password);
        if (logged && Admin()){
            String uid = Integer.toString(mydb.GetUserId(username));
            SharedPrefs.getInstance(this).setUserId(uid);
            Intent intent = new Intent(this,LoginTypeActivity.class);
            startActivity(intent);
            finish();
            SharedPrefs.getInstance(this).setLogged(true);

        }else if (logged && !Admin()){
            String uid = Integer.toString(mydb.GetUserId(username));
            SharedPrefs.getInstance(this).setUserId(uid);
            Intent intent = new Intent(this,CarListActivity.class);
            intent.putExtra("uname",username);
            startActivity(intent);
            finish();
            SharedPrefs.getInstance(this).setLogged(true);

        }else {
            Toast.makeText(this, "Wrong credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view) {
        EditText password = findViewById(R.id.editText_password);

        String uname = username.getText().toString();
        String upass = password.getText().toString();
        Check(uname, upass);
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }

    public boolean Admin(){
        return  mydb.AdminAccess(username.getText().toString());
    }
}
