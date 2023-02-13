package com.example.phc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListPages extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arrowBack:
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
                break;
            case R.id.btnItem1:
                String Item1 = "Runny Nose";
                HistoryDatabaseHelper myDB1 = new HistoryDatabaseHelper(ListPages.this);
                myDB1.insertOnHistory(Item1);
                iUsername = intentUsername.getText().toString();
                Intent b = new Intent(this, Item1.class);
                b.putExtra("username", iUsername);
                startActivity(b);
                break;
            case R.id.btnHeadaches:
                String Headaches = "Head Ache";
                HistoryDatabaseHelper myDB2 = new HistoryDatabaseHelper(ListPages.this);
                myDB2.insertOnHistory(Headaches);
                iUsername = intentUsername.getText().toString();
                Intent c = new Intent(this, ItemHeadache.class);
                c.putExtra("username", iUsername);
                startActivity(c);
                break;
            case R.id.btnCoughing:
                String Coughing = "Coughing";
                HistoryDatabaseHelper myDB3 = new HistoryDatabaseHelper(ListPages.this);
                myDB3.insertOnHistory(Coughing);
                iUsername = intentUsername.getText().toString();
                Intent d = new Intent(this, ItemCoughing.class);
                d.putExtra("username", iUsername);
                startActivity(d);
                break;
            case R.id.btnToothache:
                String Toothache = "Tooth Ache";
                HistoryDatabaseHelper myDB4 = new HistoryDatabaseHelper(ListPages.this);
                myDB4.insertOnHistory(Toothache);
                iUsername = intentUsername.getText().toString();
                Intent e = new Intent(this, Toothache.class);
                e.putExtra("username", iUsername);
                startActivity(e);
                break;
            case R.id.btnNausea:
                String Nausea = "Nausea";
                HistoryDatabaseHelper myDB5 = new HistoryDatabaseHelper(ListPages.this);
                myDB5.insertOnHistory(Nausea);
                iUsername = intentUsername.getText().toString();
                Intent f = new Intent(this, Nausea.class);
                f.putExtra("username", iUsername);
                startActivity(f);
                break;
            case R.id.btnFever:
                String Fever = "Fever";
                HistoryDatabaseHelper myDB6 = new HistoryDatabaseHelper(ListPages.this);
                myDB6.insertOnHistory(Fever);
                iUsername = intentUsername.getText().toString();
                Intent g = new Intent(this, Fever.class);
                g.putExtra("username", iUsername);
                startActivity(g);
                break;
            case R.id.btnBackPain:
                String BackPain = "Back Pain";
                HistoryDatabaseHelper myDB7 = new HistoryDatabaseHelper(ListPages.this);
                myDB7.insertOnHistory(BackPain);
                iUsername = intentUsername.getText().toString();
                Intent h = new Intent(this, BackPain.class);
                h.putExtra("username", iUsername);
                startActivity(h);
                break;
            case R.id.btnMuscleCramp:
                String MuscleCramp = "Muscle Cramp";
                HistoryDatabaseHelper myDB8 = new HistoryDatabaseHelper(ListPages.this);
                myDB8.insertOnHistory(MuscleCramp);
                iUsername = intentUsername.getText().toString();
                Intent i = new Intent(this, MuscleCramp.class);
                i.putExtra("username", iUsername);
                startActivity(i);
                break;
            case R.id.btnCankerSore:
                String CankerSore = "Canker Sore";
                HistoryDatabaseHelper myDB9 = new HistoryDatabaseHelper(ListPages.this);
                myDB9.insertOnHistory(CankerSore);
                iUsername = intentUsername.getText().toString();
                Intent j = new Intent(this, CankerSore.class);
                j.putExtra("username", iUsername);
                startActivity(j);
                break;
            default:
                break;
        }
    }

    private ImageView btnArrowBack;
    private Button btnItem1, btnHeadaches, btnCoughing, btnToothache, btnNausea, btnFever, btnBackPain, btnMuscleCramp, btnCankerSore;
    private TextView intentUsername;
    private String username, iUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pages);



        btnArrowBack = findViewById(R.id.arrowBack);
        btnArrowBack.setOnClickListener(this);

        btnItem1 = findViewById(R.id.btnItem1);
        btnItem1.setOnClickListener(this);
        btnHeadaches = findViewById(R.id.btnHeadaches);
        btnHeadaches.setOnClickListener(this);
        btnCoughing = findViewById(R.id.btnCoughing);
        btnCoughing.setOnClickListener(this);
        btnToothache = findViewById(R.id.btnToothache);
        btnToothache.setOnClickListener(this);
        btnNausea = findViewById(R.id.btnNausea);
        btnNausea.setOnClickListener(this);
        btnFever = findViewById(R.id.btnFever);
        btnFever.setOnClickListener(this);
        btnBackPain = findViewById(R.id.btnBackPain);
        btnBackPain.setOnClickListener(this);
        btnMuscleCramp = findViewById(R.id.btnMuscleCramp);
        btnMuscleCramp.setOnClickListener(this);
        btnCankerSore = findViewById(R.id.btnCankerSore);
        btnCankerSore.setOnClickListener(this);


        intentUsername = findViewById(R.id.intentUsername);
        username = getIntent().getStringExtra("username");
        intentUsername.setText(username);


    }
}