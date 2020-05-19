package Berke.MobilProgGiris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class NotePad extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String note;
    String mText;

    Button saveButton;
    EditText text;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_pad_screen);

        saveButton = (Button) this.findViewById(R.id.saveButton);
        text = (EditText) this.findViewById(R.id.editText);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();

        note = getPreferences(MODE_PRIVATE).getString("not", null);
        text.setText(note);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NotePad.this, "Not başarıyla kaydedildi.", Toast.LENGTH_SHORT).show();
                mText = text.getText().toString();
                editor.putString("not", mText);
                editor.commit();

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, WRITE_EXTERNAL_STORAGE_CODE);
                    }
                    else{
                        saveToTXTFile(mText);
                    }
                }
                else{
                    saveToTXTFile(mText);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    saveToTXTFile(mText);
                }
                else{
                    Toast.makeText(NotePad.this, "Storage permission is required!!!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void saveToTXTFile(String mText){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        try{
            File path = Environment.getExternalStorageDirectory();
            File dir = new File(path + "/My Files/");
            dir.mkdirs();
            String fileName = "MyFile_" + timeStamp + "_Note.txt";

            File file = new File(dir, fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mText);
            bw.close();

            Toast.makeText(NotePad.this, fileName + "şu adrese kaydedildi:\n" + dir, Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(NotePad.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
