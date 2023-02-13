package com.example.phc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class ContentView extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_view);

        toolbar = findViewById(R.id.toolbar);
        txtContent = findViewById(R.id.txtContent);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
                toolbar.setTitle(bundle.getString("phcTitle"));
        }
    }
}