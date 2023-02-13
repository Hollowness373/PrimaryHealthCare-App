package com.example.phc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BackPain extends AppCompatActivity {

    RecyclerView recyclerView7;
    private ImageView arrowBack;
    private TextView txtUsername;
    private EditText edtComment;
    private RatingBar ratingBar;
    private Button btnComment;
    private String iUsername;
    MyDatabaseBackPainHelper myDBBackPainHelper;
    ArrayList<String> username, comment;
    ArrayList<Float> rating;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_pain);

        recyclerView7 = findViewById(R.id.recyclerView1);
        ratingBar = findViewById(R.id.ratingBar);
        txtUsername = findViewById(R.id.txtUsername);
        edtComment = findViewById(R.id.edtComment);
        btnComment = findViewById(R.id.btnComment);

        myDBBackPainHelper = new MyDatabaseBackPainHelper(BackPain.this);
        username = new ArrayList<>();
        rating = new ArrayList<>();
        comment = new ArrayList<>();

        displayData1();

        customAdapter = new CustomAdapter(BackPain.this, this, username, rating, comment);
        recyclerView7.setAdapter(customAdapter);
        recyclerView7.setLayoutManager(new LinearLayoutManager(BackPain.this));

        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                Intent intent = new Intent(BackPain.this, ListPages.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtUsername = findViewById(R.id.txtUsername);
                if (txtUsername.getText().toString().equals("")){
                    loginMessage();
                }else{
                    MyDatabaseBackPainHelper myDB1 = new MyDatabaseBackPainHelper(BackPain.this);
                    myDB1.insertCommentBackPain(txtUsername.getText().toString(),
                            ratingBar.getRating(),
                            edtComment.getText().toString());
                    String username = txtUsername.getText().toString();
                    Intent intent = new Intent(BackPain.this, ListPages.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
//                    float ratingValue = ratingBar.getRating();
//                    Toast.makeText(Item1.this, ratingBar.getRating() + edtComment.getText().toString() + txtUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        iUsername = getIntent().getStringExtra("username");
        txtUsername.setText(iUsername);

    }

    void displayData1(){
        Cursor cursor = myDBBackPainHelper.readAllDataBackPain();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                username.add(cursor.getString(1));
                rating.add(Float.valueOf(cursor.getString(2)));
                comment.add(cursor.getString(3));
            }
        }
    }
    void loginMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please LogIn");
        builder.setMessage("Login to add a comment");
        builder.setPositiveButton("LogIn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent a = new Intent(BackPain.this, LogInPage.class);
                startActivity(a);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alert11 = builder.create();
        alert11.show();

        Button buttonBackground = alert11.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonBackground.setTextColor(Color.BLACK);

        Button buttonBackground1 = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonBackground1.setTextColor(Color.BLACK);
    }

}