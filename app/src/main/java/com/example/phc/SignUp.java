package com.example.phc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText edtUserName, edtEmail, edtPassword, edtConfirmPassword;
    private Button btnSignUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        DB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString();
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                String cpw = edtConfirmPassword.getText().toString();
                if(!pass.equals(cpw)){
                    Toast.makeText(SignUp.this, "Password is not matched.", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(cpw) && edtUserName.length() == 0 || edtEmail.length() == 0 || edtPassword.length() == 0 || edtConfirmPassword.length() == 0){
                    Toast.makeText(SignUp.this, "Text Field is Missing!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUser = DB.checkUsername(username);
                    if (checkUser==false){
                        Boolean insert = DB.insertData(username, email, pass);
                        if (insert==true){
                            Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUp.this,LogInPage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignUp.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignUp.this, "User already exist!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}