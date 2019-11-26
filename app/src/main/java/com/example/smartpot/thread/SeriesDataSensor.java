package com.example.smartpot.thread;

import android.util.Log;

import com.example.smartpot.entity.PotSensorData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SeriesDataSensor implements Runnable {
    private static final String PATH_FODD_DATOS = "datos";

    @Override
    public void run() {
        SeriesTaylor tempH = new SeriesTaylor(0, 3);
        SeriesTaylor humH = new SeriesTaylor(0, 2);
        SeriesTaylor humA = new SeriesTaylor(0, 1.5);
        SeriesTaylor light = new SeriesTaylor(0, 6);
        do {
            try {
                double temp = tempH.getSeries();
                double humAm = tempH.getSeries();
                double humEa = tempH.getSeries();
                double li = tempH.getSeries();

                PotSensorData potSensorData = new PotSensorData(temp, li, humEa, humAm);
                addDataSensor(potSensorData);

                Log.i("AHQC", potSensorData.toString());


                tempH.setN(tempH.getN() + 1);
                humH.setN(humH.getN() + 1);
                humA.setN(humA.getN() + 1);
                light.setN(light.getN() + 1);

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (true);

    }

    //Crea datos de plantas
    public void addDataSensor(PotSensorData potSensorData){

        //Instancia de la base de Datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Apuntar a PATH_FOOD_DATOS = datos
        //otra rama dentro de la BDD
        DatabaseReference reference = database.getReference(PATH_FODD_DATOS);

        //envia la instancia del objeto
        reference.setValue(potSensorData);
    }
}
