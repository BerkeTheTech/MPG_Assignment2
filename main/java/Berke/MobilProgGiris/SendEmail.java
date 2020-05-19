package Berke.MobilProgGiris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.net.URI;

public class SendEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_send_screen);

        Button buttonSend = (Button) this.findViewById(R.id.sendButton);
        final EditText toWho = (EditText) this.findViewById(R.id.editText3);
        final EditText subject = (EditText) this.findViewById(R.id.editText4);
        final EditText content = (EditText) this.findViewById(R.id.editText5);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] toEmails = {toWho.getText().toString()};
                Intent sendMail = new Intent(Intent.ACTION_SEND);
                sendMail.setType("text/plain");
                sendMail.putExtra(Intent.EXTRA_EMAIL, toEmails);
                sendMail.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                sendMail.putExtra(Intent.EXTRA_TEXT, content.getText().toString());


                startActivity(Intent.createChooser(sendMail, "Please choose an application to send Email:"));
            }
        });
    }
}