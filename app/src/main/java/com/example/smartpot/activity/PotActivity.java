package com.example.smartpot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.smartpot.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PotActivity extends AppCompatActivity {
    private static final String PATH_FODD_PLANTA = "planta";
    private static final String TAG = "Data";
    private static final String PATH_FODD_DATOS = "datos";

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FODD_DATOS);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
