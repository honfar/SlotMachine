package com.example.alec.slotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by alec on 3/22/18.
 */

public class Rules extends AppCompatActivity {

    private Button button;
    private TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        points = findViewById(R.id.rules_points);


        points.setText( getIntent().getIntExtra("POINTS", -1)+"");

        if (savedInstanceState !=null) {
            points.setText(savedInstanceState.getString("POINTS"));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("TEST", points.getText().toString());
    }
}
