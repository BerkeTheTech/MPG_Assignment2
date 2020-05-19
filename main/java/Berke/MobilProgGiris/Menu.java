package Berke.MobilProgGiris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

        Button buttonEmail = (Button) this.findViewById(R.id.button1);
        Button buttonUsers = (Button) this.findViewById(R.id.button2);
        Button buttonUserSet = (Button) this.findViewById(R.id.button3);
        Button buttonNote = (Button) this.findViewById(R.id.button4);
        Button buttonSensor = (Button) this.findViewById(R.id.button5);
        Button buttonDownload = (Button) this.findViewById(R.id.button6);
        Button buttonLocation = (Button) this.findViewById(R.id.button8);

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, SendEmail.class));
            }
        });

        buttonUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, ShowUsers.class));
            }
        });

        buttonUserSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Menu.this, "User girişi yapamadığım için bu fonksiyon çalışmıyor.", Toast.LENGTH_LONG).show();
            }
        });

        buttonNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, NotePad.class));
            }
        });

        buttonSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, LightSensor.class));
            }
        });

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, DownloadImage.class));
            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, SendLocation.class));
            }
        });
    }
}
