package com.example.sensorapplication2024;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.hardware.Sensor.TYPE_LIGHT;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView temp_output;

    TextView hum_output;
    SensorManager sensorManager;
    Sensor tempSensor;

    Sensor humSensor;

    Boolean isHumAvailable;
    Boolean isTempAvailable;

    //Sensor lightSensor;

    //SensorEventListener lightEventListener;

    //View root;

    //float maxValue;
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TEMP
        temp_output = findViewById(R.id.temp_txt);
        //temp_output.setText("Test");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempAvailable = true;
        } else {
            temp_output.setText("Temp is not available!");
            isTempAvailable = false;
        }

        //HUM
        hum_output = findViewById(R.id.hum_txt);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humSensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isHumAvailable = true;
        } else {
            hum_output.setText("Humidity is not available!");
            isHumAvailable = false;
        }
    }
        //LUX
//        root = findViewById(R.id.root);
//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
//
//        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_view);
//
//        if(lightSensor == null){
//            Toast.makeText(this, "No light Sensor" , Toast.LENGTH_SHORT).show();
//            finish();
//        }
//
//        maxValue = lightSensor.getMaximumRange();

//        lightEventListener = new SensorEventListener() {
//            @Override
//            public void onSensorChanged(SensorEvent sensorEvent) {
//                float value = sensorEvent.values[0];
//                toolbar.setTitle("Luminosity : " + value + " lx");
//                int newValue = (int) (255f * value / maxValue);
//                root.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int i) {
//
//            }
//        };






        @Override
        protected void onResume() {
            super.onResume();
            if (isTempAvailable) {
                sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            if (isHumAvailable) {
                sensorManager.registerListener(this, humSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            //super.onResume();
            //sensorManager.registerListener(lightEventListener, lightSensor, sensorManager.SENSOR_DELAY_FASTEST);
        }
        @Override
        protected void onPause() {
            super.onPause();
            if (isTempAvailable) {
                sensorManager.unregisterListener(this);
            }
            if (isHumAvailable) {
                sensorManager.unregisterListener(this);
            }


        }




    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp_output.setText("Temperature: " + sensorEvent.values[0] + " °C");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY)
            hum_output.setText("Humidity: " + sensorEvent.values[0] + " %");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
