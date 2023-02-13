package com.example.phc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnSearchButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView txtStatus, txtUsername;
    private String statusMess, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStatus = findViewById(R.id.txtStatus);
        txtUsername = findViewById(R.id.txtUsername);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();

        btnSearchButton = findViewById(R.id.btnSearchButton);
        btnSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                Intent intent = new Intent(MainActivity.this, ListPages.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });


        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setCheckedItem(R.id.navHome);


        statusMess = getIntent().getStringExtra("status");
        user = getIntent().getStringExtra("username");
        txtUsername.setText(user);
        txtStatus.setText(statusMess);
        Menu menu = navigationView.getMenu();
        if (txtStatus.getText().toString().equals("true")){
            menu.findItem(R.id.navLogIn).setVisible(false);
            menu.findItem(R.id.navLogOut).setVisible(true);
        }else if (txtStatus.getText().toString().equals("")){
            menu.findItem(R.id.navLogIn).setVisible(true);
            menu.findItem(R.id.navLogOut).setVisible(false);
        }


    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
    }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navHistory:
                Intent a = new Intent(MainActivity.this, History.class);
                startActivity(a);
                break;
            case R.id.navPin:
                Intent b = new Intent(MainActivity.this, Pin.class);
                startActivity(b);
                break;
            case R.id.navLogIn:
                Intent c = new Intent(MainActivity.this, LogInPage.class);
                startActivity(c);
                break;
            case R.id.navProfile:
                String username = txtUsername.getText().toString();
                Intent d = new Intent(MainActivity.this, Profile.class);
                d.putExtra("username", username);
                startActivity(d);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void refreshMenu(){
        txtStatus = findViewById(R.id.txtStatus);
        Menu menu = navigationView.getMenu();
        if (txtStatus.getText().toString().equals("true")){
            menu.findItem(R.id.navLogIn).setVisible(false);
            menu.findItem(R.id.navLogOut).setVisible(true);
        }else if (txtStatus.getText().toString().equals("false")){
            menu.findItem(R.id.navLogIn).setVisible(true);
            menu.findItem(R.id.navLogOut).setVisible(false);
        }
    }
}