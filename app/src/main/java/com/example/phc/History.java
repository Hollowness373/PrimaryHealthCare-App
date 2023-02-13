package com.example.phc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private ImageView arrowBack;
    private TextView txtUsername;
    RecyclerView historyRecyclerView;
    HistoryDatabaseHelper historyDatabaseHelper;
    HistoryAdapter historyAdapter;
    ArrayList<String> title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyRecyclerView = findViewById(R.id.recyclerView1);
        txtUsername = findViewById(R.id.txtUsername);

        historyDatabaseHelper = new HistoryDatabaseHelper(History.this);
        title = new ArrayList<>();

        displayHistory();

        historyAdapter = new HistoryAdapter(History.this, this, title);
        historyRecyclerView.setAdapter(historyAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(History.this));

        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                Intent intent = new Intent(History.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }
    void displayHistory(){
        Cursor cursor = historyDatabaseHelper.readOnHistory();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                title.add(cursor.getString(1));
            }
        }
    }
}