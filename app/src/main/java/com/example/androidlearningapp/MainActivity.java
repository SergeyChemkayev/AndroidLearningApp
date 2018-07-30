package com.example.androidlearningapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        View.OnClickListener ocButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDataActivity(view);
            }
        };
        button.setOnClickListener(ocButton);
    }

    public void goToDataActivity(View view) {
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }


}
