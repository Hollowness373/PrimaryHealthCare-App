package com.example.phc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arrowBack:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    private ImageView arrowBack;
    private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);


        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(this);

        lvList = findViewById(R.id.lvList);
        ArrayList<String> phc = new ArrayList<>();
        phc.add("Title 1");
        phc.add("Title 2");
        phc.add("Title 3");
        phc.add("Title 4");
        phc.add("Title 5");
        ArrayAdapter<String> phcAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                phc
        );
        lvList.setAdapter(phcAdapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListPage.this, ContentView.class);
                intent.putExtra("phcTitle", lvList.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

    }
}