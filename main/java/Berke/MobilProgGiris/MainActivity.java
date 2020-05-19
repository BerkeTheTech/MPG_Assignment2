package Berke.MobilProgGiris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int failCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        final Handler eventDelay = new Handler();
        final EditText userIn = (EditText) this.findViewById(R.id.User);
        final EditText passIn = (EditText) this.findViewById(R.id.Pass);
        Button buttonIn = (Button) this.findViewById(R.id.LogInButton);

        buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userIn.getText().toString();
                String password = passIn.getText().toString();

                if(username.matches("admin") && password.matches("1234")){
                    Toast.makeText(MainActivity.this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Menu.class));
                }
                else {
                    failCount += 1;
                    if (failCount != 3) {
                        Toast.makeText(MainActivity.this, "Hatalı giriş. Kalan deneme hakkınız: " + (3 - failCount), Toast.LENGTH_SHORT).show();
                    }
                }

                if(failCount == 3){
                    Toast.makeText(MainActivity.this, "Çok fazla sayıda hatalı giriş yaptınız. Uygulama sonlandırılıyor!", Toast.LENGTH_SHORT).show();

                    eventDelay.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finishAffinity();
                        }
                    }, 2500);
                }
            }
        });
    }
}