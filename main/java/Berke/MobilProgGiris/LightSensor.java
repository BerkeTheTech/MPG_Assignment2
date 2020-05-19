package Berke.MobilProgGiris;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LightSensor extends AppCompatActivity implements SensorEventListener {

    private TextView lightText, textView, counterText;
    private SensorManager sensorManagerLight, sensorManagerAcc;
    private Sensor lightSns, accSns;
    private View root;
    boolean isCounterActive = true;
    int count = 0;
    private Handler eventDelay = new Handler();

    float xAccel, yAccel, zAccel;
    float xPrevAccel, yPrevAccel, zPrevAccel;
    boolean firstupdate = true, isMessageShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_screen);

        Handler eventDelay = new Handler();
        textView = (TextView) this.findViewById(R.id.textView6);
        lightText = (TextView) this.findViewById(R.id.textView7);
        counterText = (TextView) this.findViewById(R.id.textView8);
        root = this.findViewById(R.id.LinearLayout);

        sensorManagerLight = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensorManagerAcc = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        lightSns = sensorManagerLight.getDefaultSensor(Sensor.TYPE_LIGHT);
        accSns = sensorManagerAcc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(isCounterActive){
                    counterText.setText("count=" + count);
                    count++;
                }
            }
        }, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManagerLight.unregisterListener(this);
        sensorManagerAcc.unregisterListener(this);
        isCounterActive = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManagerLight.registerListener(this, lightSns, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManagerAcc.registerListener(this, accSns, SensorManager.SENSOR_DELAY_NORMAL);
        isCounterActive = true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            float value = sensorEvent.values[0];
            lightText.setText("Light(LUX): " + value);
            if(value > 15){//ışık var
                root.setBackgroundColor(Color.rgb(255, 255, 255));
                textView.setTextColor(Color.rgb(0, 0, 0));
                lightText.setTextColor(Color.rgb(0, 0, 0));
                counterText.setTextColor(Color.rgb(0, 0, 0));
            }
            else{//ışık yok
                root.setBackgroundColor(Color.rgb(0, 0, 0));
                textView.setTextColor(Color.rgb(255, 255, 255));
                lightText.setTextColor(Color.rgb(255, 255, 255));
                counterText.setTextColor(Color.rgb(255, 255, 255));
            }
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            updateAccParameters(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            if(isAccChanged()){
                count = 0;
            }
            if(count == 6 && !isMessageShown){
                isCounterActive = false;
                Toast.makeText(LightSensor.this, "Uygulama sonlandırılıyor!", Toast.LENGTH_SHORT).show();
                eventDelay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finishAffinity();
                    }
                }, 2000);
                isMessageShown = true;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void updateAccParameters(float xAcc, float yAcc, float zAcc){
        if(firstupdate){
            xPrevAccel = xAcc;
            yPrevAccel = yAcc;
            zPrevAccel = zAcc;
            firstupdate = false;
        }
        else{
            xPrevAccel = xAccel;
            yPrevAccel = yAccel;
            zPrevAccel = zAccel;
        }

        xAccel = xAcc;
        yAccel = yAcc;
        zAccel = zAcc;
    }

    private boolean isAccChanged(){
        float deltaX = Math.abs(xPrevAccel - xAccel);
        float deltaY = Math.abs(yPrevAccel - yAccel);
        float deltaZ = Math.abs(zPrevAccel - zAccel);

        if(deltaX > 1f || deltaY > 1f || deltaZ > 1f){
            return true;
        }
        else{
            return false;
        }
    }
}
