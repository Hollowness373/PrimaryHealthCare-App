package com.example.phc;

import androidx.annotation.Nullable;
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

public class Nausea extends AppCompatActivity {

    RecyclerView recyclerView5;
    private ImageView arrowBack;
    private TextView txtUsername;
    private EditText edtComment;
    private RatingBar ratingBar;
    private Button btnComment;
    private String iUsername;
    MyDatabaseNauseaHelper myDBItemNausea;
    ArrayList<String> username, comment;
    ArrayList<Float> rating;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nausea);


        recyclerView5 = findViewById(R.id.recyclerView1);
        ratingBar = findViewById(R.id.ratingBar);
        txtUsername = findViewById(R.id.txtUsername);
        edtComment = findViewById(R.id.edtComment);
        btnComment = findViewById(R.id.btnComment);

        myDBItemNausea = new MyDatabaseNauseaHelper(Nausea.this);
        username = new ArrayList<>();
        rating = new ArrayList<>();
        comment = new ArrayList<>();

        displayData1();

        customAdapter = new CustomAdapter(Nausea.this, this, username, rating, comment);
        recyclerView5.setAdapter(customAdapter);
        recyclerView5.setLayoutManager(new LinearLayoutManager(Nausea.this));

        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                Intent intent = new Intent(Nausea.this, ListPages.class);
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
                    MyDatabaseNauseaHelper myDB1 = new MyDatabaseNauseaHelper(Nausea.this);
                    myDB1.insertCommentNausea(txtUsername.getText().toString(),
                            ratingBar.getRating(),
                            edtComment.getText().toString());
                    String username = txtUsername.getText().toString();
                    Intent intent = new Intent(Nausea.this, ListPages.class);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void displayData1(){
        Cursor cursor = myDBItemNausea.readAllDataNausea();
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
                Intent a = new Intent(Nausea.this, LogInPage.class);
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