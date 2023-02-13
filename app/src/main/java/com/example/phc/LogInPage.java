package com.example.phc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends AppCompatActivity {

    private EditText edtUserName, edtPassword;
    private Button btnLogIn, btnCreateAccount;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        DB = new DBHelper(this);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(LogInPage.this, SignUp.class);
                startActivity(signup);
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if(edtUserName.length() == 0 || edtPassword.length() == 0){
                    Toast.makeText(LogInPage.this, "Text Field is Missing!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUserPassword = DB.checkUsernamePassword(user, password);
                    if (checkUserPassword==true){
                        Toast.makeText(LogInPage.this, "Successfully Signed In.", Toast.LENGTH_LONG).show();
                        String statusMess = "true";
                        Intent intent = new Intent(LogInPage.this, MainActivity.class);
                        intent.putExtra("status", statusMess);
                        intent.putExtra("username", user);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LogInPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}