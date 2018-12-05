package project.aut.carato.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.SharedPrefs;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText oldPass;
    EditText newPass;
    EditText confirmPass;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        oldPass=findViewById(R.id.edit_old_password);
        newPass=findViewById(R.id.edit_new_password);
        confirmPass=findViewById(R.id.edit_confirm_password);
        mydb = new DBHelper(this);

        oldPass.addTextChangedListener(mtextWatcher);
        newPass.addTextChangedListener(mtextWatcher);
        confirmPass.addTextChangedListener(mtextWatcher);


    }
    public  boolean checkPass(){
        String nPass = newPass.getText().toString();
        String cPAss= confirmPass.getText().toString();
        return nPass.equals(cPAss);
    }
    public  boolean checkOldPass(){
        Intent intent = getIntent();
        String oPass =  intent.getStringExtra("pwd");
        return oldPass.getText().toString().equals(oPass);
    }

    public void SetNewPass(View view) {
        if (!checkPass()){
            confirmPass.setError("Password does not match");
        }
        else if(!checkOldPass()){
            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show();
        }
        else if(checkOldPass() && checkPass()){
            int uid = Integer.valueOf(SharedPrefs.getInstance(this).getUserId());
            mydb.changePass(confirmPass.getText().toString(),uid);
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
            finish();


        }


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
            int i = 1;
        }
    };

    private void CheckEmptyValues() {
        Button button = findViewById(R.id.button_change_password);
        String s1 = oldPass.getText().toString();
        String s2 = newPass.getText().toString();
        String s3 = confirmPass.getText().toString();

        if(s1.equals("") || s2.equals("") || s3.equals("")){
            button.setEnabled(false);
            button.setAlpha(0.5f);
        }
        else {
            button.setEnabled(true);
            button.setAlpha(1f);
        }



    }
}
