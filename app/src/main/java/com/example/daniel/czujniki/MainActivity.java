package com.example.daniel.czujniki;

import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView textView;
    TextView kierunek;
    SensorManager sensorManager;
    String tekst="";
    Sensor orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tekst);
        kierunek = (TextView)findViewById(R.id.kierunek);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this,orientation,SensorManager.SENSOR_DELAY_FASTEST,null);


        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        tekst = sensors.size() + " Sensors detected : \n";

        for ( int i = 0 ; i < sensors.size() ; i++)
        {
            tekst += sensors.get(i).getName()  + "\n";
        }
        textView.setText(tekst);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        String napis="";
        float stopnie = event.values[0];

        if(stopnie >= 340 || stopnie <= 20) {
            napis="Północ (N)";

        }else if(stopnie > 20 && stopnie < 70 ){
            napis="Północny-wschód (NE)";

        }else if(stopnie >= 70 && stopnie <= 110 ) {
            napis="Wschód (E)";

        }else if(stopnie > 110  && stopnie < 160 ){
            napis="Południowy-wschód (SE)";

        }else if(stopnie >= 160 && stopnie <= 200) {
            napis="Południe (S)";

        }else if(stopnie > 200  && stopnie < 250){
            napis="Południowy-zachód (SW)";

        }else if(stopnie >= 250 && stopnie <= 290) {
            napis="Zachód (W)";

        }else if(stopnie > 290 || stopnie < 20) {
            napis= "Północny-zachód (NW)";
        }

        kierunek.setText("Azymut: " + Math.round(event.values[0]) + "\n" + napis );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
