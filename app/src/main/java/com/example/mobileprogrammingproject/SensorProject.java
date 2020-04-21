package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SensorProject extends AppCompatActivity {
    TextView tvSensor,tv2;
    SensorManager sensorManager;
    SensorEventListener sensorEventListener;
    Sensor lightSensor,gyroscopeSensor;
    Chronometer simpleChronometer;
    private int counter=0;
    List<Sensor> listSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_project);

        tvSensor=(TextView) findViewById(R.id.tvSensor);
        tvSensor.setVisibility(View.GONE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        simpleChronometer= (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        if (gyroscopeSensor == null) {
            Toast.makeText(this, "Gyroscope is not found.", Toast.LENGTH_LONG).show();
            finish();
        }
        if (lightSensor == null) {
            Toast.makeText(this, "LighSensor is not found.", Toast.LENGTH_LONG).show();
            finish();
        }
        for(int i=0;i<listSensor.size();i++){
            tvSensor.setVisibility(View.VISIBLE);
            tvSensor.append("\n" + listSensor.get(i).getName() + "\n" + listSensor.get(i).getVendor() + "\n");
            tvSensor.append("-----------------------------------------");
        }
        simpleChronometer.start();
        simpleChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                counter++;
            }
        });
         sensorEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType()==Sensor.TYPE_GYROSCOPE){
                    if(event.values[0] != 0 || event.values[1] != 0 || event.values[2] != 0)
                    {
                        counter=0;
                    }
                    if (event.values[0] == 0 && event.values[1] == 0 && event.values[2] == 0) {
                        if(counter==10){
                            finish();
                        }
                    }
                }
                if(event.sensor.getType()==Sensor.TYPE_LIGHT){
                    float value = event.values[0];
                    getSupportActionBar().setTitle("Değer:" + value);
                    if (value > 10) {
                        tvSensor.setBackgroundColor(Color.BLACK);
                        tvSensor.setTextColor(Color.WHITE);
                    }
                    else{
                        tvSensor.setTextColor(Color.BLACK);
                        tvSensor.setBackgroundColor(Color.WHITE);
                    }
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener,lightSensor,SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorEventListener,gyroscopeSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
