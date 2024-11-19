package com.example.schoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Home home;
    Assignment assignment;
    Fees fees;
    Library library;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize fragments
        home = new Home();
        assignment = new Assignment();
        fees = new Fees();
        library = new Library();

        // Set up toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SchoolApplication");
        setSupportActionBar(toolbar);

        // Set up DrawerLayout and NavigationView
        nav = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set default fragment
        setFragment(home);

        // Set up BottomNavigationView
        bottomNavigationView = findViewById(R.id.bootambar);

        // NavigationView item selection listener with if-else statements
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment tempFragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.homes) {
                    tempFragment = new navHome();
                } else if (id == R.id.academicsession) {
                    tempFragment = new Academicsessio();
                } else if (id == R.id.rate) {
                    tempFragment = new Rating();
                } else if (id == R.id.detail) {
                    tempFragment = new Detail();
                }
                else if (id == R.id.contact) {
                    tempFragment = new contact();
                } else if (id == R.id.compalin) {
                    tempFragment = new compalin();
                }else if (id == R.id.about) {
                    tempFragment = new about();
                }

                // Replace fragment and close drawer
                if (tempFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, tempFragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });

        // BottomNavigationView item selection listener with if-else statements
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    setFragment(home);
                } else if (id == R.id.assignment) {
                    setFragment(assignment);
                } else if (id == R.id.fees) {
                    setFragment(fees);
                } else if (id == R.id.library) {
                    setFragment(library);
                } else {
                    return false;
                }
                return true;
            }
        });
    }

    // Method to set the selected fragment in the container
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
