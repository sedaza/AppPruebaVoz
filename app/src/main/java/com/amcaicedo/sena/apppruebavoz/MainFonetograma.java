package com.amcaicedo.sena.apppruebavoz;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainFonetograma extends AppCompatActivity {
    Button doo, re, mi, fa, sol, la, si;
    private SoundPool sounPool;
    private int sound_do ,sound_re ,sound_mi ,sound_fa ,sound_sol ,sound_la ,sound_si ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fonetograma);

        doo = (Button) findViewById(R.id.doo);
        re = (Button) findViewById(R.id.re);
        mi = (Button) findViewById(R.id.mi);
        fa = (Button) findViewById(R.id.fa);
        sol = (Button) findViewById(R.id.sol);
        la = (Button) findViewById(R.id.la);
        si = (Button) findViewById(R.id.si);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sounPool =new SoundPool.Builder().setMaxStreams(5).build();
        }else {
            sounPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        sound_do = sounPool.load(this, R.raw.doo, 1);
        sound_la = sounPool.load(this, R.raw.la, 1);
        sound_re = sounPool.load(this, R.raw.re, 1);
        sound_mi = sounPool.load(this, R.raw.mi, 1);
        sound_fa = sounPool.load(this, R.raw.fa, 1);
        sound_sol = sounPool.load(this, R.raw.sol, 1);

        sound_si = sounPool.load(this, R.raw.si, 1);



        doo.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
              sounPool.play(sound_do , 1, 1, 0, 0, 1);

          }
        });
       // System.out.println("el objecto estaaaa"+re);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounPool.play(sound_re , 1, 1, 0, 0, 1);


            }
        });
        mi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounPool.play(sound_mi , 1, 1, 0, 0, 1);

            }
        });
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounPool.play(sound_fa , 1, 1, 0, 0, 1);

            }
        });
        sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounPool.play(sound_sol , 1, 1, 0, 0, 1);

            }
        });
        System.out.println("el objecto estaaaa"+la);
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounPool.play(sound_la , 1, 1, 0, 0, 1);

            }
        });
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sounPool.play(sound_si, 1, 1, 0, 0, 1);

            }
        });
    }
}
