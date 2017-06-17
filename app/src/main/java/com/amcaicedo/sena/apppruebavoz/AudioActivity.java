package com.amcaicedo.sena.apppruebavoz;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private Button btnStart, btnStop, btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        Bundle extras = getIntent().getExtras();
        String archivo;
        if (extras.get("ARCHIVO").toString().equalsIgnoreCase("glissando"))
            archivo = "/glissando.3gp";
        else if (extras.get("ARCHIVO").toString().equalsIgnoreCase("kaiser"))
            archivo = "/kaiser.3gp";
        else
            archivo = "/fonetograma.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPlay = (Button) findViewById(R.id.btnPlay);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPlay.setOnClickListener(this);

        btnStop.setEnabled(false);
        btnPlay.setEnabled(false);

        outputFile = getExternalCacheDir().getAbsolutePath() + archivo;
        //outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myrec.3gp";

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setOutputFile(outputFile);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();
    }

    public void start(){
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        }catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnStart.setEnabled(false);
        btnStop.setEnabled(true);

        Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();
    }

    public void stop(){
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;

        btnStop.setEnabled(false);
        btnPlay.setEnabled(true);
        Toast.makeText(this, "Audio guardado...", Toast.LENGTH_SHORT).show();
    }

    public void play() throws IOException {
        MediaPlayer m = new MediaPlayer();
        m.setDataSource(outputFile);
        m.prepare();
        m.start();
        Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStart:
                start();
                break;
            case R.id.btnStop:
                stop();
                break;
            case R.id.btnPlay:
                try {
                    play();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
