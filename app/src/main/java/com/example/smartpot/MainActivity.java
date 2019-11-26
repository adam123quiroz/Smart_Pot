package com.example.smartpot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartpot.adapters.MyAdapter;
import com.example.smartpot.entity.Pot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Pot> potList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);

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
    }

    private List<Pot> loadList() {
        List<Pot> pots = new ArrayList<>();
        pots.add(new Pot(R.drawable.plant2, "Bugabilla", "Loremsadbsasahidjhadijhsalidjsa aksjdhsakl nl;oxjdljsan"));
        pots.add(new Pot(R.drawable.plant1, "Clavel", "ASKJLDhsa aksdhkasb k uahoil hkaushd"));
        pots.add(new Pot(R.drawable.plant2,"Ilamantiu", "alsdha lasidhoalki hjalishli hjai;lsjdpi jna"));

        return pots;
    }


    public void addData(){
        Pot pot = new Pot(R.drawable.plant2, "Bugabilla", "Loremsadbsasahidjhadijhsalidjsa aksjdhsakl nl;oxjdljsan");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

    }

}
