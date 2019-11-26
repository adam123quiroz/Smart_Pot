package com.example.smartpot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.smartpot.R;

public class PotActivity extends AppCompatActivity {
    private TextView textViewTemperature;
    private TextView textViewHumidityEarth;
    private TextView textViewHumidityAmbient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pot);

        textViewHumidityAmbient = findViewById(R.id.text_view_humidity_ambient);
        textViewHumidityEarth = findViewById(R.id.text_view_humidity_earth);
        textViewTemperature = findViewById(R.id.text_view_temperature);
    }
}
