package project.aut.carato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
    }

    public void Check(String username, String Password){
        boolean logged =  mydb.GetUserCredentials(username, Password);
        if (logged){
            Intent intent = new Intent(this,CarListActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "Wrong credentials!", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view) {
        EditText username = findViewById(R.id.editText_username);
        EditText password = findViewById(R.id.editText_password);

        String uname = username.getText().toString();
        String upass = password.getText().toString();
        Check(uname, upass);
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }
}
