package com.example.attendencemarkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_dashboard) {
                startActivity(new Intent(this, Dashboard.class));
            }
            else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            }
            else if (id == R.id.nav_attendance_history) {
                //startActivity(new Intent(this, AttendanceHistoryActivity.class));
            }
            else if (id == R.id.nav_salary) {
                //startActivity(new Intent(this, SalarySlipActivity.class));
            }
            else if (id == R.id.nav_location) {
                //startActivity(new Intent(this, AnnouncementActivity.class));
            }
            else if (id == R.id.nav_settings) {
                //startActivity(new Intent(this, SettingsActivity.class));
            }
            else if (id == R.id.nav_logout) {
                //logoutUser();
            }

            // Close drawer after click
            DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });



    }
}
