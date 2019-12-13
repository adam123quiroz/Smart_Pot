package com.example.smartpot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.smartpot.R;
import com.example.smartpot.adapters.MyAdapter;
import com.example.smartpot.entity.Pot;
import com.example.smartpot.entity.PotSensorData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PotActivity extends AppCompatActivity {
    private static final String PATH_FODD_PLANTA = "pot";
    private static final String TAG = "Data";
    private static final String PATH_FODD_DATOS = "datos";

    private TextView textViewTitlePot;
    private ProgressBar progressBar;
    private TextView textViewTemperature;
    private TextView textViewHumidityEarth;
    private TextView textViewHumidityAmbient;

    private Pot pot;
    private PotSensorData potSensorData;

    List<Integer> listHumedadAmbiente;
    List<Integer> listHumedadTierra;
    List<Integer> listTemperaturaAmbiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pot);

        textViewTitlePot = findViewById(R.id.title_pot);
        progressBar = findViewById(R.id.progress_bar);
        textViewHumidityAmbient = findViewById(R.id.text_view_humidity_ambient);
        textViewHumidityEarth = findViewById(R.id.text_view_humidity_earth);
        textViewTemperature = findViewById(R.id.text_view_temperature);

        listHumedadAmbiente = new ArrayList<>();
        listHumedadTierra = new ArrayList<>();
        listTemperaturaAmbiente = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("pot");
        DatabaseReference aa = reference.child("Humedad Ambiente");

        aa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listHumedadAmbiente.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Integer temp =  postSnapshot.getValue(Integer.class);
                    listHumedadAmbiente.add(temp);
                }
                Log.i("AHQC", listHumedadAmbiente.toString());
                textViewHumidityAmbient.setText(String.valueOf(promedioList(listHumedadAmbiente)));
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AHQC", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference reference1 = database.getReference("pot");
        DatabaseReference bb = reference1.child("Humedad Tierra");

        bb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listHumedadTierra.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Integer temp =  postSnapshot.getValue(Integer.class);
                    listHumedadTierra.add(temp);
                }
                Log.i("AHQC", listHumedadTierra.toString());
                textViewHumidityEarth.setText(String.valueOf(promedioList(listHumedadTierra)));
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AHQC", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference reference2 = database.getReference("pot");
        DatabaseReference cc = reference.child("Temperatura Ambiente");

        cc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listTemperaturaAmbiente.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Integer temp =  postSnapshot.getValue(Integer.class);
                    listTemperaturaAmbiente.add(temp);
                }
                Log.i("AHQC", listTemperaturaAmbiente.toString());
                textViewTemperature.setText(String.valueOf(promedioList(listTemperaturaAmbiente)));
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AHQC", "Failed to read value.", error.toException());
            }
        });

        pot = (Pot) getIntent().getSerializableExtra("pot");
        Log.i("AHQC", pot.toString());

        progressBar.drawableHotspotChanged(2.00f, 0.45f);

        textViewTitlePot.setText(pot.getName());

    }

    private double promedioList(List list) {
        double n = 0;
        for (int i = 0; i < list.size(); i++) {
            n += Double.parseDouble(list.get(i).toString());
        }
        return n/list.size();
    }
}
