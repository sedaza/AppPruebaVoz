package com.amcaicedo.sena.apppruebavoz;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class CargarGlissandoActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCargar;
    private  final int PICKER=1;
    private int State =1;
    MediaPlayer mediaPlayer= new MediaPlayer();

    String filePathAudio = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_glissando);

        buttonCargar = (Button)findViewById(R.id.buttonCargar);
        buttonCargar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCargar:
                pickfile();
                break;
        }
    }
    private void pickfile (){
        Intent intent = new Intent (Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent,"instale un administrador de archivos."),PICKER);

        }catch (ActivityNotFoundException ex){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PICKER:
                if (resultCode == RESULT_OK){
                    Uri filepath = data.getData();
                    String filePathAudio = getRealPathFromURI(filepath);
                    try {
                        mediaPlayer.setDataSource(filePathAudio);
                        mediaPlayer.prepare();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Audio.Media.DATA};
            cursor = getApplicationContext().getContentResolver().query(contentUri,proj, null, null,null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }finally{
            if (cursor != null)
                cursor.close();

        }

    }

    public void play (View v)throws IOException {
        mediaPlayer.start();
    }

}
