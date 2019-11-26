package com.example.smartpot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartpot.activity.PotActivity;
import com.example.smartpot.adapters.MyAdapter;
import com.example.smartpot.entity.Pot;
import com.example.smartpot.thread.SeriesDataSensor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PATH_FODD_PLANTA = "planta";
    private static final String PATH_FODD_DATOS = "datos";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button button;

    private List<Pot> potList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        button = findViewById(R.id.button_pot);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PotActivity.class);
                startActivity(intent);
            }
        });

        //load list
        potList = loadList();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(potList);
        recyclerView.setAdapter(mAdapter);
        addData();

        //thread
        Thread thread = new Thread(new SeriesDataSensor());
        thread.start();
    }

    private List<Pot> loadList() {
        List<Pot> pots = new ArrayList<>();
        pots.add(new Pot(R.drawable.plant2, "Bugabilla", "Loremsadbsasahidjhadijhsalidjsa aksjdhsakl nl;oxjdljsan"));
        pots.add(new Pot(R.drawable.plant1, "Clavel", "uududuishhuHFUHSISUFH aksdhkasb k uahoil hkaushd"));
        pots.add(new Pot(R.drawable.plant2,"Ilamantiu", "alsdha lasidhoalki hjalishli hjai;lsjdpi jna"));
        return pots;
    }

    //Crea datos de plantas
    public void addData(){
        Pot pot = new Pot(R.drawable.plant1, "Clavel", "ASKJLDhsa aksdhkasb k uahoil hkaushd");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FODD_PLANTA);
        reference.push().setValue(pot);
    }
}
