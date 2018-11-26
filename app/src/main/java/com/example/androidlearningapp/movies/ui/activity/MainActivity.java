package com.example.androidlearningapp.movies.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidlearningapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.main_open_data_activity_button);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
                    Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_SHORT).show();
                }
            }
        };
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
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
