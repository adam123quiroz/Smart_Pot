package com.example.smartpot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartpot.activity.PotActivity;
import com.example.smartpot.adapters.MyAdapter;
import com.example.smartpot.entity.Pot;
import com.example.smartpot.thread.SeriesDataSensor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PATH_FODD_PLANTA = "planta";
    private static final String PATH_FODD_DATOS = "datos";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar myToolbar;

    private List<Pot> potList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {

                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                Log.i("AHQC", String.valueOf(rv.getChildLayoutPosition(child)));
                Intent intent = new Intent(MainActivity.this, PotActivity.class);
                startActivity(intent);
                intent.putExtra("pot", potList.get(rv.getChildLayoutPosition(child)));
                startActivity(intent);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //load list
//        potList = loadList();
        potList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FODD_PLANTA);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                potList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Pot pot =  postSnapshot.getValue(Pot.class);
                    potList.add(pot);
                }
                Log.i("AHQC", "Value is: " + potList);
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true);

                // specify an adapter (see also next example)
                mAdapter = new MyAdapter(potList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AHQC", "Failed to read value.", error.toException());
            }
        });


//        addData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //TODO: SADSAD
                return true;
            case R.id.action_settings:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                builder.setTitle("Agregar Maceta");
                View v = inflater.inflate(R.layout.dialog_add_pot, null);
                final EditText name = v.findViewById(R.id.namePot);
                final EditText descrip = v.findViewById(R.id.descrip);
                final EditText code = v.findViewById(R.id.code);
                builder.setView(v)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Pot pot = new Pot();
                                pot.setName(name.getText().toString());
                                pot.setDescription(descrip.getText().toString());
                                pot.setImage(R.drawable.plant2);
                                addData(pot);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create();
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Crea datos de plantas
    public void addData(Pot pot){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FODD_PLANTA);
        reference.push().setValue(pot);
    }
}
