package Berke.MobilProgGiris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SendLocation extends AppCompatActivity {

    Button buttonGetLocation;
    Button buttonSendLocation;
    TextView textLocation;

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_location_screen);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        buttonGetLocation = (Button) findViewById(R.id.buttonGetLocation);
        buttonSendLocation = (Button) findViewById(R.id.buttonSendLocation);
        textLocation = (TextView) findViewById(R.id.textView5);

        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.getLastLocation().addOnSuccessListener(SendLocation.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location != null){
                            textLocation.setText(location.toString());
                        }
                    }
                });
            }
        });
    }

    private  void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
