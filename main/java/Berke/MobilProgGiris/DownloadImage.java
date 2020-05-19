package Berke.MobilProgGiris;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class DownloadImage extends AppCompatActivity {

    MediaPlayer player;
    ProgressBar progressBar;
    Button downloadButton;
    ImageView imageView;
    Random random = new Random();
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_image_screen);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        downloadButton = (Button) findViewById(R.id.downButton);
        imageView = (ImageView) findViewById(R.id.downImage);

        imageView.setVisibility(View.INVISIBLE);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadAsyncTask().execute();
            }
        });
    }

    private class DownloadAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected String doInBackground(Integer... integers) {
            while(progress < 100){
                progress = (random.nextInt(10) + 1) + progress;
                publishProgress(progress);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Resim indirildi.";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            play(DownloadImage.this);
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(DownloadImage.this, s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }

    public void play(DownloadImage view){
        if(player == null){
            player = MediaPlayer.create(DownloadImage.this, R.raw.downloadcompletesong);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if(player != null){
                        player.release();
                        player = null;
                    }
                }
            });
        }
        player.start();
    }
}