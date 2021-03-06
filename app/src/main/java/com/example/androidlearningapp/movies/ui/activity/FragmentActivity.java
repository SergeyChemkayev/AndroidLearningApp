package com.example.androidlearningapp.movies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.ui.fragments.DashboardFragment;
import com.example.androidlearningapp.movies.ui.fragments.HomeFragment;
import com.example.androidlearningapp.movies.ui.fragments.NotificationsFragment;

public class FragmentActivity extends AppCompatActivity {

    public static void open(Context context) {
        Intent intent = new Intent(context, FragmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        bottomNavigationInit();
        loadFragment(HomeFragment.newInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void bottomNavigationInit() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.fragment_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        loadFragment(HomeFragment.newInstance());
                        return true;
                    case R.id.navigation_dashboard:
                        loadFragment(DashboardFragment.newInstance());
                        return true;
                    case R.id.navigation_notifications:
                        loadFragment(NotificationsFragment.newInstance());
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content_layout, fragment).commit();
    }
}
