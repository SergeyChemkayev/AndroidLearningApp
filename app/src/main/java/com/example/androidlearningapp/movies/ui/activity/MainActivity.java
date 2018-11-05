package com.example.androidlearningapp.movies.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.services.TimeCounterService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private Intent timeCounterServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.main_open_data_activity_button);
        button.setOnClickListener(this);
        startTimeCounterService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimeCounterService();
    }

    private void startTimeCounterService(){
        timeCounterServiceIntent = new Intent(MainActivity.this, TimeCounterService.class);
        startService(timeCounterServiceIntent);
    }

    private void stopTimeCounterService(){
        stopService(timeCounterServiceIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_open_data_activity_button:
                DataActivity.open(this);
                break;
        }
    }
}
