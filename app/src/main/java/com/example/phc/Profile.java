package com.example.phc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    DBHelper usernameDB;
    private ImageView arrowBack, imgUserImage;
    private EditText edtUsername, edtEmail, edtPassword;
    private TextView txtUsername, txtID;
    private Button btnSave;
    private String iUsername;
    int SELECT_IMAGE_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtUsername = findViewById(R.id.txtUsername);
        txtID = findViewById(R.id.txtID);
        edtUsername = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        imgUserImage = findViewById(R.id.imgUserImage);
        btnSave = findViewById(R.id.btnSave);

        usernameDB = new DBHelper(this);

        iUsername = getIntent().getStringExtra("username");
        txtUsername.setText(iUsername);


        imgUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(Profile.this);
                myDB.updateData(txtID.getText().toString(), edtUsername.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });

        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                Intent intent = new Intent(Profile.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        getID();
        displayUsername();
        displayEmail();
        displayPassword();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            Uri uri = data.getData();
            imgUserImage.setImageURI(uri);
        }
    }
    private void getID() {
        txtUsername = findViewById(R.id.txtUsername);
        String user = txtUsername.getText().toString();
        Cursor result = usernameDB.readUsername(user);
        StringBuffer stringBuffer = new StringBuffer();
        if (result!=null && result.getCount()>0){
            while (result.moveToNext()){
                stringBuffer.append(result.getString(0));
            }
            txtID.setText(stringBuffer.toString());
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayUsername() {
        txtUsername = findViewById(R.id.txtUsername);
        String user = txtUsername.getText().toString();
        Cursor result = usernameDB.readUsername(user);
        StringBuffer stringBuffer = new StringBuffer();
        if (result!=null && result.getCount()>0){
            while (result.moveToNext()){
                stringBuffer.append(result.getString(1));
            }
            edtUsername.setText(stringBuffer.toString());
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
    private void displayEmail() {
        txtUsername = findViewById(R.id.txtUsername);
        String user = txtUsername.getText().toString();
        Cursor result = usernameDB.readUsername(user);
        StringBuffer stringBuffer = new StringBuffer();
        if (result!=null && result.getCount()>0){
            while (result.moveToNext()){
                stringBuffer.append(result.getString(2));
            }
            edtEmail.setText(stringBuffer.toString());
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
    private void displayPassword() {
        txtUsername = findViewById(R.id.txtUsername);
        String user = txtUsername.getText().toString();
        Cursor result = usernameDB.readUsername(user);
        StringBuffer stringBuffer = new StringBuffer();
        if (result!=null && result.getCount()>0){
            while (result.moveToNext()){
                stringBuffer.append(result.getString(3));
            }
            edtPassword.setText(stringBuffer.toString());
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}