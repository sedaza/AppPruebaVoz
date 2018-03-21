package com.amcaicedo.sena.apppruebavoz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.drawable.Drawable;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.FileNameMap;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

import android.media.AudioFormat;
import android.media.AudioRecord;
//import java.util.function.Function;
//--------------------------------
//import com.androidplot.xy.LineAndPointFormatter;
//import com.androidplot.xy.SimpleXYSeries;
//import com.androidplot.xy.XYPlot;
//import com.androidplot.xy.XYSeries;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

//tarsoso

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//
//import signal.library.*;


public class FonetogramaProActivity extends Activity {

    Button doo2, stdo2, re2,stre2, mi2, fa2,stfa2, sol2 ,stsol2, la2,stla2, si2;
    Button doo3, stdo3, re3,stre3, mi3, fa3,stfa3, sol3 ,stsol3, la3,stla3, si3;
    Button doo4, stdo4, re4,stre4, mi4, fa4,stfa4, sol4 ,stsol4, la4,stla4, si4;
    Button doo5, stdo5, re5,stre5, mi5, fa5,stfa5, sol5 ,stsol5, la5,stla5, si5;
    Button doo6, stdo6, re6,stre6, mi6, fa6,stfa6, sol6 ,stsol6, la6,stla6, si6;

    TextView tvdoo2, tvstdo2, tvre2,tvstre2, tvmi2, tvfa2,tvstfa2, tvsol2 ,tvstsol2, tvla2,tvstla2, tvsi2;
    TextView tvdoo3, tvstdo3, tvre3,tvstre3, tvmi3, tvfa3,tvstfa3, tvsol3 ,tvstsol3, tvla3,tvstla3, tvsi3;
    TextView tvdoo4, tvstdo4, tvre4,tvstre4, tvmi4, tvfa4,tvstfa4, tvsol4 ,tvstsol4, tvla4,tvstla4, tvsi4;
    TextView tvdoo5, tvstdo5, tvre5,tvstre5, tvmi5, tvfa5,tvstfa5, tvsol5 ,tvstsol5, tvla5,tvstla5, tvsi5;
    TextView tvdoo6, tvstdo6, tvre6,tvstre6, tvmi6, tvfa6,tvstfa6, tvsol6 ,tvstsol6,tvla6,tvstla6, tvsi6;


    private SoundPool sounPool;
    private int sound_do2 ,sound_stdo2,sound_re2,sound_stre2 ,sound_mi2 ,sound_fa2, sound_stfa2 ,sound_sol2,sound_stsol2 ,sound_la2 ,sound_stla2  ,sound_si2 ;
    private int sound_do3 ,sound_stdo3,sound_re3,sound_stre3 ,sound_mi3 ,sound_fa3, sound_stfa3 ,sound_sol3,sound_stsol3 ,sound_la3 ,sound_stla3  ,sound_si3 ;
    private int sound_do4 ,sound_stdo4,sound_re4,sound_stre4 ,sound_mi4 ,sound_fa4, sound_stfa4 ,sound_sol4,sound_stsol4 ,sound_la4 ,sound_stla4  ,sound_si4 ;
    private int sound_do5 ,sound_stdo5,sound_re5,sound_stre5 ,sound_mi5 ,sound_fa5, sound_stfa5 ,sound_sol5,sound_stsol5 ,sound_la5 ,sound_stla5  ,sound_si5 ;
    private int sound_do6 ,sound_stdo6,sound_re6,sound_stre6 ,sound_mi6 ,sound_fa6, sound_stfa6 ,sound_sol6,sound_stsol6 ,sound_la6 ,sound_stla6  ,sound_si6 ;
    private int valornota, valornotaini, valornotafin;
    TextView tvf0,tvdBmax,tvdBmin,tvTiempo,tvPrueba;



    Drawable drawable;
    Drawable drawable2;




        Button bSiguiente33, bAnterior33;
        LineGraphSeries<DataPoint> series;//para graficar

        // private XYPlot myXYPlot;
        private int frecuencia;
        private ArrayList datos;
        private ArrayList datosMuestra ;
        private ArrayList datosMuestraini = new ArrayList();
        private ArrayList datosMuestrafin = new ArrayList();
    public static ArrayList dBMin =new ArrayList();
    public static ArrayList dBMax = new ArrayList();

        private Algoritmos al;
    public static int[] dBmax = new int[60];
    public static int[] dBmin  = new int[60];
    public static int[]  Frecuenciafoneto= new int[60];
    public static ArrayList numeroindice =new ArrayList();
    private boolean[] valoracionotas= new boolean[60];
     int indicefoneto;




        private static final int RECORDER_BPP = 16;
        private static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav"; // como guardar con autoincremento
        private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
        private static final String AUDIO_RECORDER_TEMP_FILE = "record_temp-raw";
        private static final int RECORDER_SAMPLERATE = 44100;
        private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
        private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

        public AudioRecord recorder = null;
        private int bufferSize = 0;
        private Thread recordingThread = null;
        private boolean isRecording = false;




        private String outputFile = null;
        private Button start, graficar, play; //metodos para grabar, parar y reproducir audio
        private TextView textView;

        public static ArrayList datosAmplitud = new ArrayList();
        public static ArrayList datosAmplitud2 = new ArrayList();

        //parametros de audio para hallar fo
        public int LongVentana = 2048;
        public int channelConfiguration = AudioFormat.CHANNEL_IN_MONO;
        public int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
        public static short[] buffer; //+-32767
        public static short[] bufferTemporal;
        public static short[] cep_alta;
        public static short[] cep_energia;
        public static final int SAMPPERSEC = 44100;
        public static final int SAMPPERSEC1 = 7000;
        int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
        int BytesPerElement = 2; // 2 bytes in 16bit format
        short [] dataShort;
        short amplitudeMin = 0;
        short amplitudeMax = 0;

        ProgressDialog loading;


//sonidos y configuracion notas-------------------------------------------------------------------------
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fonetograma_pro);
            bSiguiente33 = (Button) findViewById(R.id.bSiguiente3);
            bSiguiente33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent4 = new Intent(FonetogramaProActivity.this, MainResultadosFonetograma.class);
                    /*intent4.putExtra("frecuencia", al.getFrecFund());
                    ArrayList dbmin = new ArrayList<String>();
                    for (int d=0; d<dBMin.size(); d++){
                        dbmin.add(dBMin.get(d).toString());
                    }
                    intent4.putStringArrayListExtra("algoritmos", dbmin);*/
                    startActivity(intent4);
                }
            });

            doo2 = (Button) findViewById(R.id.doo2);
            stdo2 = (Button) findViewById(R.id.stdo2);
            re2 = (Button) findViewById(R.id.re2);
            stre2 = (Button) findViewById(R.id.stre2);
            mi2 = (Button) findViewById(R.id.mi2);
            fa2 = (Button) findViewById(R.id.fa2);
            stfa2 = (Button) findViewById(R.id.stfa2);
            sol2 = (Button) findViewById(R.id.sol2);
            stsol2 = (Button) findViewById(R.id.stsol2);
            la2 = (Button) findViewById(R.id.la2);
            stla2 = (Button) findViewById(R.id.stla2);
            si2 = (Button) findViewById(R.id.si2);


            doo3 = (Button) findViewById(R.id.doo3);
            stdo3 = (Button) findViewById(R.id.stdo3);
            re3 = (Button) findViewById(R.id.re3);
            stre3 = (Button) findViewById(R.id.stre3);
            mi3 = (Button) findViewById(R.id.mi3);
            fa3 = (Button) findViewById(R.id.fa3);
            stfa3 = (Button) findViewById(R.id.stfa3);
            sol3 = (Button) findViewById(R.id.sol3);
            stsol3 = (Button) findViewById(R.id.stsol3);
            la3 = (Button) findViewById(R.id.la3);
            stla3 = (Button) findViewById(R.id.stla3);
            si3 = (Button) findViewById(R.id.si3);

            doo4 = (Button) findViewById(R.id.doo4);
            stdo4 = (Button) findViewById(R.id.stdo4);
            re4 = (Button) findViewById(R.id.re4);
            stre4 = (Button) findViewById(R.id.stre4);
            mi4 = (Button) findViewById(R.id.mi4);
            fa4 = (Button) findViewById(R.id.fa4);
            stfa4 = (Button) findViewById(R.id.stfa4);
            sol4 = (Button) findViewById(R.id.sol4);
            stsol4 = (Button) findViewById(R.id.stsol4);
            la4 = (Button) findViewById(R.id.la4);
            stla4 = (Button) findViewById(R.id.stla4);
            si4 = (Button) findViewById(R.id.si4);

            doo5 = (Button) findViewById(R.id.doo5);
            stdo5 = (Button) findViewById(R.id.stdo5);
            re5 = (Button) findViewById(R.id.re5);
            stre5 = (Button) findViewById(R.id.stre5);
            mi5 = (Button) findViewById(R.id.mi5);
            fa5 = (Button) findViewById(R.id.fa5);
            stfa5 = (Button) findViewById(R.id.stfa5);
            sol5 = (Button) findViewById(R.id.sol5);
            stsol5 = (Button) findViewById(R.id.stsol5);
            la5 = (Button) findViewById(R.id.la5);
            stla5 = (Button) findViewById(R.id.stla5);
            si5 = (Button) findViewById(R.id.si5);

            doo6 = (Button) findViewById(R.id.doo6);
            stdo6 = (Button) findViewById(R.id.stdo6);
            re6 = (Button) findViewById(R.id.re6);
            stre6 = (Button) findViewById(R.id.stre6);
            mi6 = (Button) findViewById(R.id.mi6);
            fa6 = (Button) findViewById(R.id.fa6);
            stfa6 = (Button) findViewById(R.id.stfa6);
            sol6 = (Button) findViewById(R.id.sol6);
            stsol6 = (Button) findViewById(R.id.stsol6);
            la6 = (Button) findViewById(R.id.la6);
            stla6 = (Button) findViewById(R.id.stla6);
            si6 = (Button) findViewById(R.id.si6);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                sounPool =new SoundPool.Builder().setMaxStreams(5).build();
            }else {
                sounPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
            }

            sound_do2 = sounPool.load(this, R.raw.doo2, 1);
            sound_stdo2 = sounPool.load(this, R.raw.stdo2, 1);
            sound_la2 = sounPool.load(this, R.raw.la2, 1);
            sound_stla2 = sounPool.load(this, R.raw.stla2, 1);
            sound_re2 = sounPool.load(this, R.raw.re2, 1);
            sound_stre2 = sounPool.load(this, R.raw.stre2, 1);
            sound_mi2 = sounPool.load(this, R.raw.mi2, 1);
            sound_fa2 = sounPool.load(this, R.raw.fa2, 1);
            sound_stfa2 = sounPool.load(this, R.raw.stfa2, 1);
            sound_sol2 = sounPool.load(this, R.raw.sol2, 1);
            sound_stsol2 = sounPool.load(this, R.raw.stsol2, 1);
            sound_si2 = sounPool.load(this, R.raw.si2, 1);

            sound_do3 = sounPool.load(this, R.raw.doo3, 1);
            sound_stdo3 = sounPool.load(this, R.raw.stdo3, 1);
            sound_la3 = sounPool.load(this, R.raw.la3, 1);
            sound_stla3 = sounPool.load(this, R.raw.stla3, 1);
            sound_re3 = sounPool.load(this, R.raw.re3, 1);
            sound_stre3 = sounPool.load(this, R.raw.stre3, 1);
            sound_mi3 = sounPool.load(this, R.raw.mi3, 1);
            sound_fa3 = sounPool.load(this, R.raw.fa3, 1);
            sound_stfa3 = sounPool.load(this, R.raw.stfa3, 1);
            sound_sol3 = sounPool.load(this, R.raw.sol3, 1);
            sound_stsol3 = sounPool.load(this, R.raw.stsol3, 1);
            sound_si3 = sounPool.load(this, R.raw.si3, 1);

            sound_do4 = sounPool.load(this, R.raw.doo4, 1);
            sound_stdo4 = sounPool.load(this, R.raw.stdo4, 1);
            sound_la4 = sounPool.load(this, R.raw.la4, 1);
            sound_stla4 = sounPool.load(this, R.raw.stla4, 1);
            sound_re4 = sounPool.load(this, R.raw.re4, 1);
            sound_stre4 = sounPool.load(this, R.raw.stre4, 1);
            sound_mi4 = sounPool.load(this, R.raw.mi4, 1);
            sound_fa4 = sounPool.load(this, R.raw.fa4, 1);
            sound_stfa4 = sounPool.load(this, R.raw.stfa4, 1);
            sound_sol4 = sounPool.load(this, R.raw.sol4, 1);
            sound_stsol4 = sounPool.load(this, R.raw.stsol4, 1);
            sound_si4 = sounPool.load(this, R.raw.si4, 1);

            sound_do5 = sounPool.load(this, R.raw.doo5, 1);
            sound_stdo5 = sounPool.load(this, R.raw.stdo5, 1);
            sound_la5 = sounPool.load(this, R.raw.la5, 1);
            sound_stla5 = sounPool.load(this, R.raw.stla5, 1);
            sound_re5 = sounPool.load(this, R.raw.re5, 1);
            sound_stre5 = sounPool.load(this, R.raw.stre5, 1);
            sound_mi5 = sounPool.load(this, R.raw.mi5, 1);
            sound_fa5 = sounPool.load(this, R.raw.fa5, 1);
            sound_stfa5 = sounPool.load(this, R.raw.stfa5, 1);
            sound_sol5 = sounPool.load(this, R.raw.sol5, 1);
            sound_stsol5 = sounPool.load(this, R.raw.stsol5, 1);
            sound_si5 = sounPool.load(this, R.raw.si5, 1);

            sound_do6 = sounPool.load(this, R.raw.doo6, 1);
            sound_stdo6 = sounPool.load(this, R.raw.stdo6, 1);
            sound_la6 = sounPool.load(this, R.raw.la6, 1);
            sound_stla6 = sounPool.load(this, R.raw.stla6, 1);
            sound_re6 = sounPool.load(this, R.raw.re6, 1);
            sound_stre6 = sounPool.load(this, R.raw.stre6, 1);
            sound_mi6 = sounPool.load(this, R.raw.mi6, 1);
            sound_fa6 = sounPool.load(this, R.raw.fa6, 1);
            sound_stfa6 = sounPool.load(this, R.raw.stfa6, 1);
            sound_sol6 = sounPool.load(this, R.raw.sol6, 1);
            sound_stsol6 = sounPool.load(this, R.raw.stsol6, 1);
            sound_si6 = sounPool.load(this, R.raw.si6, 1);


            tvf0 = (TextView) findViewById(R.id.tvF0);
            tvTiempo = (TextView) findViewById(R.id.tvTiempo);
            tvdBmax = (TextView) findViewById(R.id.tvdBmax);
            tvdBmin = (TextView) findViewById(R.id.tvdBmin);
            tvPrueba = (TextView) findViewById(R.id.tvPrueba);




            doo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_do2 , 1, 1, 0, 0, 1);
                    valornotaini= 63;
                    valornota= 65;
                    valornotafin= 67;
                    indicefoneto=0;


                }
            });
            stdo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_stdo2 , 1, 1, 0, 0, 1);
                    valornotaini= 67;
                    valornota= 69;
                    valornotafin= 71;
                    indicefoneto=1;

                }
            });
            // System.out.println("el objecto estaaaa"+re);
            re2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_re2 , 1, 1, 0, 0, 1);
                    valornotaini= 71;
                    valornota= 73;
                    valornotafin= 75;
                    indicefoneto=2;
                }
            });
            stre2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stre2 , 1, 1, 0, 0, 1);
                    valornotaini= 75;
                    valornota= 77;
                    valornotafin= 80;
                    indicefoneto=3;


                }
            });
            mi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_mi2 , 1, 1, 0, 0, 1);
                    valornotaini=80 ;
                    valornota= 82;
                    valornotafin= 84;
                    indicefoneto=4;
                }
            });
            fa2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_fa2 , 1, 1, 0, 0, 1);
                    valornotaini=84 ;
                    valornota= 87;
                    valornotafin= 90;
                    indicefoneto=5;
                }
            });
            stfa2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stfa2 , 1, 1, 0, 0, 1);
                    valornotaini=90 ;
                    valornota= 92;
                    valornotafin=95 ;
                    indicefoneto=6;
                }
            });
            sol2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_sol2 , 1, 1, 0, 0, 1);
                    valornotaini=95 ;
                    valornota=97 ;
                    valornotafin=100;
                    indicefoneto=7;
                }
            });
            stsol2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stsol2 , 1, 1, 0, 0, 1);
                    valornotaini= 100;
                    valornota= 103;
                    valornotafin= 106;
                    indicefoneto=8;
                }
            });

            la2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_la2 , 1, 1, 0, 0, 1);
                    valornotaini=106 ;
                    valornota= 110;
                    valornotafin= 113;
                    indicefoneto=9;
                }
            });
            stla2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stla2 , 1, 1, 0, 0, 1);
                    valornotaini=113 ;
                    valornota= 116;
                    valornotafin= 119;
                    indicefoneto=10;
                }
            });
            si2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_si2, 1, 1, 0, 0, 1);
                    valornotaini=119 ;
                    valornota= 123;
                    valornotafin=128 ;
                    indicefoneto=11;
                }
            });

            //---------------------------------------------------------------------------------

            doo3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_do3 , 1, 1, 0, 0, 1);
                    valornotaini= 128;
                    valornota= 130;
                    valornotafin=134 ;
                    indicefoneto=12;
                }
            });
            stdo3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_stdo3 , 1, 1, 0, 0, 1);
                    valornotaini= 134;
                    valornota= 138;
                    valornotafin= 142;
                    indicefoneto=13;
                }
            });
            // System.out.println("el objecto estaaaa"+re);
            re3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_re3 , 1, 1, 0, 0, 1);
                    valornotaini= 142;
                    valornota= 146;
                    valornotafin= 150;
                    indicefoneto=14;
                }
            });
            stre3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stre3 , 1, 1, 0, 0, 1);
                    valornotaini= 150;
                    valornota= 155;
                    valornotafin= 160;
                    indicefoneto=15;

                }
            });
            mi3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_mi3 , 1, 1, 0, 0, 1);
                    valornotaini= 160;
                    valornota= 164;
                    valornotafin= 169;
                    indicefoneto=16;
                }
            });
            fa3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_fa3 , 1, 1, 0, 0, 1);
                    valornotaini= 169;
                    valornota= 174;
                    valornotafin=179 ;
                    indicefoneto=17;
                }
            });
            stfa3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stfa3 , 1, 1, 0, 0, 1);
                    valornotaini= 179;
                    valornota= 184;
                    valornotafin= 189;
                    indicefoneto=18;
                }
            });
            sol3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_sol3 , 1, 1, 0, 0, 1);
                    valornotaini= 189;
                    valornota= 185;
                    valornotafin= 201;
                    indicefoneto=19;
                }
            });
            stsol3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stsol3 , 1, 1, 0, 0, 1);
                    valornotaini= 201;
                    valornota= 207;
                    valornotafin= 213;
                    indicefoneto=20;
                }
            });

            la3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_la3 , 1, 1, 0, 0, 1);
                    valornotaini= 213;
                    valornota= 220;
                    valornotafin= 227;
                    indicefoneto=21;
                }
            });
            stla3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stla3 , 1, 1, 0, 0, 1);
                    valornotaini=227 ;
                    valornota= 233;
                    valornotafin= 240;
                    indicefoneto=22;
                }
            });
            si3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_si3, 1, 1, 0, 0, 1);
                    valornotaini= 240;
                    valornota= 246;
                    valornotafin= 256;
                    indicefoneto=23;
                }
            });
            //-----------------------------------------------------------------------------------------------------------------


            doo4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_do4 , 1, 1, 0, 0, 1);
                    valornotaini=254 ;
                    valornota= 261;
                    valornotafin= 269;
                    indicefoneto=24;
                }
            });
            stdo4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_stdo4 , 1, 1, 0, 0, 1);
                    valornotaini= 269;
                    valornota= 277;
                    valornotafin= 285;
                    indicefoneto=25;
                }
            });
            // System.out.println("el objecto estaaaa"+re);
            re4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_re4 , 1, 1, 0, 0, 1);
                    valornotaini= 285;
                    valornota= 293;
                    valornotafin= 301;
                    indicefoneto=26;
                }
            });
            stre4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stre4 , 1, 1, 0, 0, 1);
                    valornotaini= 301;
                    valornota= 311;
                    valornotafin= 320;
                    indicefoneto=27;

                }
            });
            mi4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_mi4 , 1, 1, 0, 0, 1);
                    valornotaini=320 ;
                    valornota= 329;
                    valornotafin= 339;
                    indicefoneto=28;
                }
            });
            fa4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_fa4 , 1, 1, 0, 0, 1);
                    valornotaini=339 ;
                    valornota= 349;
                    valornotafin= 359;
                    indicefoneto=29;
                }
            });
            stfa4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stfa4 , 1, 1, 0, 0, 1);
                    valornotaini= 359;
                    valornota= 369;
                    valornotafin= 380;
                    indicefoneto=30;
                }
            });
            sol4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_sol4 , 1, 1, 0, 0, 1);
                    valornotaini=380;
                    valornota= 391;
                    valornotafin= 403;
                    indicefoneto=31;
                }
            });
            stsol4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stsol4 , 1, 1, 0, 0, 1);
                    valornotaini= 403;
                    valornota= 415;
                    valornotafin= 427;
                    indicefoneto=32;
                }
            });

            la4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_la4 , 1, 1, 0, 0, 1);
                    valornotaini= 427;
                    valornota= 440;
                    valornotafin= 453;
                    indicefoneto=33;
                }
            });
            stla4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stla4 , 1, 1, 0, 0, 1);
                    valornotaini= 453;
                    valornota= 466;
                    valornotafin= 480;
                    indicefoneto=34;
                }
            });
            si4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_si4, 1, 1, 0, 0, 1);
                    valornotaini= 480;
                    valornota= 493;
                    valornotafin= 508;
                    indicefoneto=35;
                }
            });

            //---------------------------------------------------------------------------------


            doo5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_do5 , 1, 1, 0, 0, 1);
                    valornotaini= 508;
                    valornota= 523;
                    valornotafin= 538;
                    indicefoneto=36;
                }
            });
            stdo5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_stdo5 , 1, 1, 0, 0, 1);
                    valornotaini= 538;
                    valornota= 554;
                    valornotafin= 570;
                    indicefoneto=37;
                }
            });
            // System.out.println("el objecto estaaaa"+re);
            re5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_re5 , 1, 1, 0, 0, 1);
                    valornotaini= 570;
                    valornota= 587;
                    valornotafin= 604;
                    indicefoneto=38;
                }
            });
            stre5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stre5 , 1, 1, 0, 0, 1);
                    valornotaini=604 ;
                    valornota= 622;
                    valornotafin= 640;
                    indicefoneto=39;

                }
            });
            mi5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_mi5 , 1, 1, 0, 0, 1);
                    valornotaini= 640;
                    valornota= 659;
                    valornotafin= 678;
                    indicefoneto=40;
                }
            });
            fa5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_fa5 , 1, 1, 0, 0, 1);
                    valornotaini= 678;
                    valornota= 698;
                    valornotafin= 718;
                    indicefoneto=41;
                }
            });
            stfa5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stfa5 , 1, 1, 0, 0, 1);
                    valornotaini=718 ;
                    valornota= 739;
                    valornotafin=761 ;
                    indicefoneto=42;
                }
            });
            sol5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_sol5 , 1, 1, 0, 0, 1);
                    valornotaini= 761;
                    valornota= 783;
                    valornotafin=806 ;
                    indicefoneto=43;
                }
            });
            stsol5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stsol5 , 1, 1, 0, 0, 1);
                    valornotaini= 806;
                    valornota= 830;
                    valornotafin= 855;
                    indicefoneto=44;
                }
            });

            la5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_la5 , 1, 1, 0, 0, 1);
                    valornotaini= 855;
                    valornota= 880;
                    valornotafin= 906;
                    indicefoneto=45;
                }
            });
            stla5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stla5 , 1, 1, 0, 0, 1);
                    valornotaini= 906;
                    valornota= 932;
                    valornotafin= 959;
                    indicefoneto=46;
                }
            });
            si5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_si5, 1, 1, 0, 0, 1);
                    valornotaini=959 ;
                    valornota= 987;
                    valornotafin= 1016;
                    indicefoneto=47;
                }
            });

            //---------------------------------------------------------------------------------


            doo6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_do6 , 1, 1, 0, 0, 1);
                    valornotaini=1016 ;
                    valornota= 1046;
                    valornotafin= 1077;
                    indicefoneto=48;
                }
            });
            stdo6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sounPool.play(sound_stdo6 , 1, 1, 0, 0, 1);
                    valornotaini=1077 ;
                    valornota= 1108;
                    valornotafin= 1141;
                    indicefoneto=49;
                }
            });
            // System.out.println("el objecto estaaaa"+re);
            re6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_re6 , 1, 1, 0, 0, 1);
                    valornotaini= 1141;
                    valornota= 1174;
                    valornotafin= 1209;
                    indicefoneto=50;
                }
            });
            stre6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stre6 , 1, 1, 0, 0, 1);
                    valornotaini= 1209;
                    valornota= 1244;
                    valornotafin= 1281;
                    indicefoneto=51;

                }
            });
            mi6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_mi6 , 1, 1, 0, 0, 1);
                    valornotaini= 1281;
                    valornota= 1318;
                    valornotafin= 1357;
                    indicefoneto=52;
                }
            });
            fa6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_fa6, 1, 1, 0, 0, 1);
                    valornotaini= 1357;
                    valornota= 1396;
                    valornotafin= 1437;
                    indicefoneto=53;
                }
            });
            stfa6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stfa6 , 1, 1, 0, 0, 1);
                    valornotaini= 1437;
                    valornota= 1479;
                    valornotafin= 1523;
                    indicefoneto=54;
                }
            });
            sol6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_sol6 , 1, 1, 0, 0, 1);
                    valornotaini= 1523;
                    valornota= 1567;
                    valornotafin= 1614;
                    indicefoneto=55;
                }
            });
            stsol6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stsol6 , 1, 1, 0, 0, 1);
                    valornotaini= 1614;
                    valornota= 1661;
                    valornotafin= 1710;
                    indicefoneto=56;
                }
            });

            la6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_la6 , 1, 1, 0, 0, 1);
                    valornotaini= 1710;
                    valornota= 1760;
                    valornotafin= 1812;
                    indicefoneto=57;
                }
            });
            stla6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_stla6 , 1, 1, 0, 0, 1);
                    valornotaini=1812 ;
                    valornota= 1864;
                    valornotafin= 1919;
                    indicefoneto=58;
                }
            });
            si6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sounPool.play(sound_si6, 1, 1, 0, 0, 1);
                    valornotaini= 1919;
                    valornota= 1975;
                    valornotafin= 2030;
                    indicefoneto=59;
                }
            });
            Log.e("indiceeeeee", ""+indicefoneto);

            //---------------------------------------------------------------------------------
            //---------------------------------------------------------------------------------
            //---------------------------------------------------------------------------------

            //  myXYPlot = (XYPlot)findViewById(R.id.myXYPlot);
            start = (Button) findViewById(R.id.buttonEmpezar);
            graficar = (Button) findViewById(R.id.buttonGraficar);
            play = (Button) findViewById(R.id.buttonReproducir);
            textView = (TextView) findViewById(R.id.textview);


            bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
            buffer = new short[LongVentana];


            bSiguiente33 = (Button) findViewById(R.id.bSiguiente3);
            bSiguiente33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent4 = new Intent(FonetogramaProActivity.this, MainResultadosFonetograma.class);
                    intent4.putExtra("frecuencia", al.getFrecFund());
                    ArrayList frecuenciastring = new ArrayList<String>();
                    for (int d=0; d<al.getDatosFrecuencia().size(); d++){
                        frecuenciastring.add(al.getDatosFrecuencia().get(d).toString());
                    }
                    intent4.putStringArrayListExtra("algoritmos", frecuenciastring);
                    startActivity(intent4);


                }
            });
        }


        private void enableButton(int id, boolean isEnable) {
            ((Button) findViewById(id)).setEnabled(isEnable);
        }

        private void enableButtons(boolean isRecording) {
            enableButton(R.id.buttonEmpezar, !isRecording);
            enableButton(R.id.buttonGraficar, isRecording);
        }

        private  String getFilename(){
            String filepath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(filepath, AUDIO_RECORDER_FOLDER);
            if(!file.exists()){
                file.mkdirs();
            }
            outputFile = file.getAbsolutePath() + "/" + "AudioFonetograma"+ System.currentTimeMillis() + AUDIO_RECORDER_FILE_EXT_WAV;
            return (outputFile);
        }

        private String getTempFilename() {
            String filepath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(filepath, AUDIO_RECORDER_FOLDER);
            if (!file.exists()) {
                file.mkdirs();
            }

            File tempFile = new File(filepath, AUDIO_RECORDER_TEMP_FILE);
            if(tempFile.exists())
                tempFile.delete();
            return (file.getAbsolutePath() + "/" + AUDIO_RECORDER_TEMP_FILE);

        }






        public void startRecording(View v){

            resetVariables();
            recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                    RECORDER_SAMPLERATE, RECORDER_CHANNELS,
                    RECORDER_AUDIO_ENCODING, bufferSize);

            int i = recorder.getState();
            if(i == 1)
                recorder.startRecording();
            isRecording = true;
            recordingThread = new Thread(new Runnable(){

                @Override
                public void run(){
                    writeAudioDataToFile();
                }
            }, "AudioRecorder Theard");
            recordingThread.start();
            Frecuenciafoneto[indicefoneto]=valornota;
            numeroindice.add(indicefoneto);
            Log.e("pocicion frecuencia", ""+Frecuenciafoneto[indicefoneto]);
            startTimer();

            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
        }


        public void startTimer(){
            new CountDownTimer(8000,1000) {//genera un timer de 30000 milisegundos y muestra el estado cada 1000 milisegundos

                public void onTick(long millisUntilFinished) {


                   tvTiempo.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    tvTiempo.setText("Terminado!");
                    stop ();
                    Toast.makeText(FonetogramaProActivity.this, "PRUEBA FINALIZADA", Toast.LENGTH_SHORT).show();
                }
            }.start();
        }

        public void obtenerMuestraini(){
            //for (int x = (0); x < (1000) ; x++){ //
            for (int x =44100; x<308000; x++){
                datosMuestraini.add(datos.get(x));

            }
            System.out.println("imeeeeeee" + datosMuestraini);
        }


        //--------------FALTa----------------

        public void btGraficar(View v){
        /*loading = new ProgressDialog(getApplicationContext());
        loading.setTitle("Gráfica");
        loading.setMessage("Obteniendo gráfica...");
        loading.setCancelable(false);
        loading.show();*/
            obtenerMuestra();
            al = new Algoritmos(datosMuestra);
            al.calcular(0.4f);
            al.negativos();
            System.out.println("qeeeeeeeeeeeeeeeeeeeeeeeeee");
            float[] nuevoArr = al.getArreglo1();//getArreglo (grafica sin normalizar)
           // graficar(nuevoArr);
       /* al = new Algoritmos(datosMuestra);
        al.calcular(0.8f);
        al.negativos();
        //al.maxArray();
        */al.prueba();
            al.buscarPicosNeg();
            al.PosicionPicos();

            al.restaPicos();
            al.frecuencia();


            //Log.e("valor frecuencia fundamental",+al.getFrecFund());

            obtenerMuestraini();

        /*Double amplitude = 20 * Math.log10(maximos(datosMuestraini) / 1);
        Log.e("VALOR dB MAXIMOS", ""+amplitude);*/
           // System.out.println("DATOS AMPLITUD: " + obtenerdB());

            tvf0.setText(""+ al.getFrecFund());

            //loading.dismiss();
           //------------------------------------------------------------------------------------------------------------------------
            // dentro del condicional almacenar maximos y minimos en cada unos en un arreglo diferente  despues de que pase la prueba
            //----------------------------------------------------------------------------------------------------------------------
            if (al.getFrecFund()<(valornotafin) && al.getFrecFund()>(valornotaini) ) {
                tvPrueba.setText("PRUEBA CORRECTA");

                /*---------------------------------------
                asie es como tiene que ser correcto
                obtenerdBMin();
                obtenerdBMax();
                ----------------------------------------*/
                Toast.makeText(this, "correcto", Toast.LENGTH_SHORT).show();
                switch (indicefoneto) {
                    case 0:
                        doo2.setBackgroundResource(R.color.colorok);
                        break;
                    case 1:
                        stdo2.setBackgroundResource(R.color.colorok2);
                        break;
                    case 2:
                        re2.setBackgroundResource(R.color.colorok);
                        break;
                    case  3:
                        stre2.setBackgroundResource(R.color.colorok2);
                        break;
                    case 4:
                        mi2.setBackgroundResource(R.color.colorok);
                        break;
                    case 5:
                        fa2.setBackgroundResource(R.color.colorok);
                        break;

                    case 6:
                        stfa2.setBackgroundResource(R.color.colorok2);

                        break;
                    case 7:
                        sol2.setBackgroundResource(R.color.colorok);
                       break;
                    case 8:
                        stsol2.setBackgroundResource(R.color.colorok2);
                        break;
                    case 9:
                        la2.setBackgroundResource(R.color.colorok);
                        break;

                    case 10:
                        stla2.setBackgroundResource(R.color.colorok2);
                        break;
                    case 11:
                        si2.setBackgroundResource(R.color.colorok);
                        break;



                    case 12:
                        doo3.setBackgroundResource(R.color.colorok);
                        break;
                    case 13:
                        stdo3.setBackgroundResource(R.color.colorok2);
                        break;
                    case 14:
                        re3.setBackgroundResource(R.color.colorok);
                        break;
                    case  15:
                        stre3.setBackgroundResource(R.color.colorok2);
                        break;
                    case 16:
                        mi3.setBackgroundResource(R.color.colorok);
                        break;
                    case 17:
                        fa3.setBackgroundResource(R.color.colorok);
                        break;

                    case 18:
                        stfa3.setBackgroundResource(R.color.colorok2);

                        break;
                    case 19:
                        sol3.setBackgroundResource(R.color.colorok);
                        break;
                    case 20:
                        stsol3.setBackgroundResource(R.color.colorok2);
                        break;
                    case 21:
                        la3.setBackgroundResource(R.color.colorok);
                        break;

                    case 22:
                        stla3.setBackgroundResource(R.color.colorok2);
                        break;
                    case 23:
                        si3.setBackgroundResource(R.color.colorok);
                        break;





                    case 24:
                        doo4.setBackgroundResource(R.color.colorok);
                        break;
                    case 25:
                        stdo4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 26:
                        re4.setBackgroundResource(R.color.colorok);
                        break;
                    case  27:
                        stre4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 28:
                        mi4.setBackgroundResource(R.color.colorok);
                        break;
                    case 29:
                        fa4.setBackgroundResource(R.color.colorok);
                        break;

                    case 30:
                        stfa4.setBackgroundResource(R.color.colorok2);

                        break;
                    case 31:
                        sol4.setBackgroundResource(R.color.colorok);
                        break;
                    case 32:
                        stsol4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 33:
                        la4.setBackgroundResource(R.color.colorok);
                        break;

                    case 34:
                        stla4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 35:
                        si4.setBackgroundResource(R.color.colorok);
                        break;


                    case 36:
                        doo4.setBackgroundResource(R.color.colorok);
                        break;
                    case 37:
                        stdo4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 38:
                        re4.setBackgroundResource(R.color.colorok);
                        break;
                    case  39:
                        stre4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 40:
                        mi4.setBackgroundResource(R.color.colorok);
                        break;
                    case 41:
                        fa4.setBackgroundResource(R.color.colorok);
                        break;
                    case 42:
                        stfa4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 43:
                        sol4.setBackgroundResource(R.color.colorok);
                        break;
                    case 44:
                        stsol4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 45:
                        la4.setBackgroundResource(R.color.colorok);
                        break;
                    case 46:
                        stla4.setBackgroundResource(R.color.colorok2);
                        break;
                    case 47:
                        si4.setBackgroundResource(R.color.colorok);
                        break;



                    case 48:
                        doo5.setBackgroundResource(R.color.colorok);
                        break;
                    case 49:
                        stdo5.setBackgroundResource(R.color.colorok2);
                        break;
                    case 50:
                        re5.setBackgroundResource(R.color.colorok);
                        break;
                    case  51:
                        stre5.setBackgroundResource(R.color.colorok2);
                        break;
                    case 52:
                        mi5.setBackgroundResource(R.color.colorok);
                        break;
                    case 53:
                        fa5.setBackgroundResource(R.color.colorok);
                        break;

                    case 54:
                        stfa5.setBackgroundResource(R.color.colorok2);

                        break;
                    case 55:
                        sol5.setBackgroundResource(R.color.colorok);
                        break;
                    case 56:
                        stsol5.setBackgroundResource(R.color.colorok2);
                        break;
                    case 57:
                        la5.setBackgroundResource(R.color.colorok);
                        break;

                    case 58:
                        stla5.setBackgroundResource(R.color.colorok2);
                        break;
                    case 59:
                        si5.setBackgroundResource(R.color.colorok);
                        break;
                }

            }else{
                obtenerdBMin();
                obtenerdBMax();
                Toast.makeText(this, "no alcanza la nota", Toast.LENGTH_SHORT).show();
               tvPrueba.setText("NO ALCANZO LA NOTA");
                switch (indicefoneto) {

                    case 0:
                        doo2.setBackgroundResource(R.color.colormal);
                        break;
                    case 1:
                        stdo2.setBackgroundResource(R.color.colormal2);
                        break;
                    case 2:
                        re2.setBackgroundResource(R.color.colormal);
                        break;
                    case  3:
                        stre2.setBackgroundResource(R.color.colormal2);
                        break;
                    case 4:
                        mi2.setBackgroundResource(R.color.colormal);
                        break;
                    case 5:
                        fa2.setBackgroundResource(R.color.colormal);
                        break;

                    case 6:
                        stfa2.setBackgroundResource(R.color.colormal2);

                        break;
                    case 7:
                        sol2.setBackgroundResource(R.color.colormal);
                        break;
                    case 8:
                        stsol2.setBackgroundResource(R.color.colormal2);
                        break;
                    case 9:
                        la2.setBackgroundResource(R.color.colormal);
                        break;

                    case 10:
                        stla2.setBackgroundResource(R.color.colormal2);
                        break;
                    case 11:
                        si2.setBackgroundResource(R.color.colormal);
                        break;



                    case 12:
                        doo3.setBackgroundResource(R.color.colormal);
                        break;
                    case 13:
                        stdo3.setBackgroundResource(R.color.colormal2);
                        break;
                    case 14:
                        re3.setBackgroundResource(R.color.colormal);
                        break;
                    case  15:
                        stre3.setBackgroundResource(R.color.colormal2);
                        break;
                    case 16:
                        mi3.setBackgroundResource(R.color.colormal);
                        break;
                    case 17:
                        fa3.setBackgroundResource(R.color.colormal);
                        break;

                    case 18:
                        stfa3.setBackgroundResource(R.color.colormal2);

                        break;
                    case 19:
                        sol3.setBackgroundResource(R.color.colormal);
                        break;
                    case 20:
                        stsol3.setBackgroundResource(R.color.colormal2);
                        break;
                    case 21:
                        la3.setBackgroundResource(R.color.colormal);
                        break;

                    case 22:
                        stla3.setBackgroundResource(R.color.colormal2);
                        break;
                    case 23:
                        si3.setBackgroundResource(R.color.colormal);
                        break;





                    case 24:
                        doo4.setBackgroundResource(R.color.colormal);
                        break;
                    case 25:
                        stdo4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 26:
                        re4.setBackgroundResource(R.color.colormal);
                        break;
                    case  27:
                        stre4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 28:
                        mi4.setBackgroundResource(R.color.colormal);
                        break;
                    case 29:
                        fa4.setBackgroundResource(R.color.colormal);
                        break;

                    case 30:
                        stfa4.setBackgroundResource(R.color.colormal2);

                        break;
                    case 31:
                        sol4.setBackgroundResource(R.color.colormal);
                        break;
                    case 32:
                        stsol4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 33:
                        la4.setBackgroundResource(R.color.colormal);
                        break;

                    case 34:
                        stla4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 35:
                        si4.setBackgroundResource(R.color.colormal);
                        break;


                    case 36:
                        doo4.setBackgroundResource(R.color.colormal);
                        break;
                    case 37:
                        stdo4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 38:
                        re4.setBackgroundResource(R.color.colormal);
                        break;
                    case  39:
                        stre4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 40:
                        mi4.setBackgroundResource(R.color.colormal);
                        break;
                    case 41:
                        fa4.setBackgroundResource(R.color.colormal);
                        break;
                    case 42:
                        stfa4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 43:
                        sol4.setBackgroundResource(R.color.colormal);
                        break;
                    case 44:
                        stsol4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 45:
                        la4.setBackgroundResource(R.color.colormal);
                        break;
                    case 46:
                        stla4.setBackgroundResource(R.color.colormal2);
                        break;
                    case 47:
                        si4.setBackgroundResource(R.color.colormal);
                        break;



                    case 48:
                        doo5.setBackgroundResource(R.color.colormal);
                        break;
                    case 49:
                        stdo5.setBackgroundResource(R.color.colormal2);
                        break;
                    case 50:
                        re5.setBackgroundResource(R.color.colormal);
                        break;
                    case  51:
                        stre5.setBackgroundResource(R.color.colormal2);
                        break;
                    case 52:
                        mi5.setBackgroundResource(R.color.colormal);
                        break;
                    case 53:
                        fa5.setBackgroundResource(R.color.colormal);
                        break;

                    case 54:
                        stfa5.setBackgroundResource(R.color.colormal2);

                        break;
                    case 55:
                        sol5.setBackgroundResource(R.color.colormal);
                        break;
                    case 56:
                        stsol5.setBackgroundResource(R.color.colormal2);
                        break;
                    case 57:
                        la5.setBackgroundResource(R.color.colormal);
                        break;

                    case 58:
                        stla5.setBackgroundResource(R.color.colormal2);
                        break;
                    case 59:
                        si5.setBackgroundResource(R.color.colormal);
                        break;
                }


            }



        }

        //----------------------------
/*
        public ArrayList obtenerdB(){
            short amplitude = 0;
            float promediodBs = 0.0f;
            for(int x = 0; x < datosMuestraini.size(); x++){

                if ((short)datosMuestraini.get(x) > 500){
                    amplitude = (short) (20 * Math.log10((short) datosMuestraini.get(x) / 1));
                    datosAmplitud.add(amplitude);
                    Log.e("VALOR dB MAXIMOS", ""+amplitude);
                }

            }
            for(int x = 0; x < datosAmplitud.size()-1; x++) {

                promediodBs=(Float.parseFloat(String.valueOf(datosAmplitud.get(x)))+Float.parseFloat(String.valueOf(datosAmplitud.get(x+1))))/2;
                datosAmplitud2.add(promediodBs);


            }
            return datosAmplitud2;
        }*/
    public void obtenerdBMax(){
        double promedioMax = 0;
       // short amplitudeMax = 0;
        short max = 0;
        double Min = 0;
        /*for(int x = 0; x < Algoritmos.posicionesMax.size(); x++){
            sumMax = sumMax + (double)Algoritmos.posicionesMax.get(x);
        }
        promedioMax = sumMax/Algoritmos.posicionesMax.size();*/
        try{
            for (int i=0; i<=Algoritmos.posicionesMax.size();i++){
                amplitudeMax = (short) (20 * Math.log10((short) datosMuestraini.get((Integer) Algoritmos.posicionesMax.get(i))));
                Log.e("Amplitud MAX Cambio", ""+amplitudeMax);
                if (max<amplitudeMax){
                    max=amplitudeMax;
                    //Log.e("Amplitud MAX Cambio", ""+max);

                    //dBMax.add(max);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        dBmax[indicefoneto]=max;
        Log.e("indicexxxxxxxxx", ""+indicefoneto);
        Log.e(" MAX Cambio", ""+dBmax[indicefoneto]);
        tvdBmax.setText(""+max);
        imprimdBmax();
        System.out.println("Amplitudddddd man" + amplitudeMax);

      //return dBMax;
    }

     public void imprimdBmax(){
         for(int h=0; h<dBmax.length; h++){

             System.out.print("primdBmax"+dBmax[h]);}

     }
    public void imprimdBmin(){
        for(int h=0; h<dBmin.length; h++){

            System.out.print("primdBmin"+dBmin[h]);}

    }



    public void obtenerdBMin(){
        double promedioMin = 0;
        //short amplitudeMin = 0;
        short min = 200;
        /*for(int x = 0; x < Algoritmos.posicionesMin.size(); x++){
            sumMin = (short) (sumMin + (short)Algoritmos.posicionesMin.get(x));
        }*/
        //promedioMin = sumMin/Algoritmos.posicionesMin.size();
        try{
            for (int i=0; i<=Algoritmos.posicionesMax.size();i++){
                amplitudeMin = (short) (20 * Math.log10((short) datosMuestraini.get((Integer) Algoritmos.posicionesMax.get(i))));
                Log.e("Amplitud Min Cambio", ""+amplitudeMin);
                if (min>amplitudeMin){
                    min=amplitudeMin;
                    //Log.e("Amplitud MIN Cambio", ""+min);
                   // dBMin.add(min);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.e("indicennnnnnnnn", ""+indicefoneto);
        dBmin[indicefoneto]=min;
        tvdBmin.setText(""+min);
        Log.e(" MIN Cambio", ""+dBmin[indicefoneto]);
        imprimdBmin();
        System.out.println("Prommmmmmm min" + promedioMin);
        System.out.println("Amplitudddddd min" + amplitudeMin);
        //System.out.println("dbmin" + dBmin[indicefoneto]);
        //System.out.println("dbminampli" + amplitudeMin);
    }


    //MUESTRAS
        public void obtenerMuestra(){
             datosMuestra = new ArrayList();
            try {
                for (int x = 44100; x<datos.size()-44100; x++){ //inicio ===  int x= 0 ; x=2000            fin ===    x=datos.size()-100 ; x=datos.size()
                    //for (int x = 44100; x<(datos)-44100; x++)
                    datosMuestra.add(datos.get(x));

                }
                System.out.println("imeeeeeee" + datosMuestra);
            }catch (Exception e){
                e.printStackTrace();
            }
        }




        private void writeAudioDataToFile() {
            datos = new ArrayList();
            byte data[] = new byte[bufferSize];
            dataShort = new short[bufferSize/2];
            String filename = getTempFilename();
            FileOutputStream os = null;
            System.out.printf("se  detiene aqui"+filename);


            try {
                os = new FileOutputStream(filename);

            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }

            int read = 0;
            if (null != os) {
                while (isRecording) {
                    read = recorder.read(dataShort, 0, bufferSize/2); // se lee la señal por trozos

                    for(int x = 0; x<dataShort.length; x++){
                        datos.add(dataShort[x]); //almacena la señal entera

                    }
                    data = short2byte(dataShort); // transforma el arreglo de short en arreglo de bytes

                    if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                        try {
                            os.write(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stop (){
            if(null != recorder) {
                isRecording = false;

                int i = recorder.getState();

                if (i == 1){
                    recorder.stop();
                    // System.out.println("dfdfdfdddddddddddddddddfdfdfdfddfdfdf" + recorder.read(bufferTemporal, 0, 44100, AudioRecord.READ_BLOCKING));
                }
                recorder.release();
                //this.frecuencia = frequency();
                recorder = null;
                graficar.setEnabled(true);
                play.setEnabled(true);
                recordingThread = null;
                Toast.makeText(this, "Audio recorded successfully", Toast.LENGTH_SHORT);
            }
        }

        private void deleteTempFile() {
            File file = new File(getTempFilename());
            file.delete();
        }

        private void copyWaveFile(String inFilename, String outFilename) {
            FileInputStream in = null;
            FileOutputStream out = null;
            long totalAudioLen = 0;
            long totalDataLen = totalAudioLen + 36;
            long longSampleRate = RECORDER_SAMPLERATE;
            int channels = 1;
            long byteRate = RECORDER_BPP * RECORDER_SAMPLERATE * channels / 8;
            byte[] data = new byte[bufferSize];

            try {
                in = new FileInputStream(inFilename);
                out = new FileOutputStream(outFilename);
                totalAudioLen = in.getChannel().size();
                totalDataLen = totalAudioLen + 36;
                // AppLog.logString("File size: " + totalDataLen);
                WriteWaveFileHeader(out, totalAudioLen, totalDataLen,
                        longSampleRate, channels, byteRate);
                while (in.read(data) != -1) {
                    out.write(data);
                }

                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void WriteWaveFileHeader(
                FileOutputStream out, long totalAudioLen,
                long totalDataLen, long longSampleRate, int channels,
                long byteRate) throws IOException {

            byte[] header = new byte[44];

            header[0] = 'R'; // RIFF/WAVE header
            header[1] = 'I';
            header[2] = 'F';
            header[3] = 'F';
            header[4] = (byte) (totalDataLen & 0xff);
            header[5] = (byte) ((totalDataLen >> 8) & 0xff);
            header[6] = (byte) ((totalDataLen >> 16) & 0xff);
            header[7] = (byte) ((totalDataLen >> 24) & 0xff);
            header[8] = 'W';
            header[9] = 'A';
            header[10] = 'V';
            header[11] = 'E';
            header[12] = 'f'; // 'fmt ' chunk
            header[13] = 'm';
            header[14] = 't';
            header[15] = ' ';
            header[16] = 16; // 4 bytes: size of 'fmt ' chunk
            header[17] = 0;
            header[18] = 0;
            header[19] = 0;
            header[20] = 1; // format = 1
            header[21] = 0;
            header[22] = (byte) channels;
            header[23] = 0;
            header[24] = (byte) (longSampleRate & 0xff);
            header[25] = (byte) ((longSampleRate >> 8) & 0xff);
            header[26] = (byte) ((longSampleRate >> 16) & 0xff);
            header[27] = (byte) ((longSampleRate >> 24) & 0xff);
            header[28] = (byte) (byteRate & 0xff);
            header[29] = (byte) ((byteRate >> 8) & 0xff);
            header[30] = (byte) ((byteRate >> 16) & 0xff);
            header[31] = (byte) ((byteRate >> 24) & 0xff);
            header[32] = (byte) (2 * 16 / 8); // block align
            header[33] = 0;
            header[34] = RECORDER_BPP; // bits per sample
            header[35] = 0;
            header[36] = 'd';
            header[37] = 'a';
            header[38] = 't';
            header[39] = 'a';
            header[40] = (byte) (totalAudioLen & 0xff);
            header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
            header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
            header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

            out.write(header, 0, 44);
        }


        public void play (View v)throws IOException {
            copyWaveFile(getTempFilename(), getFilename());
            deleteTempFile();
            MediaPlayer m = new MediaPlayer();
            System.out.printf("aaaa"+outputFile);
            m.setDataSource(outputFile);

            m.prepare();
            m.start();
            Toast.makeText(this, "Playing audio", Toast.LENGTH_SHORT);

        }





        //convert short to byte
        private byte[] short2byte(short[] sData) {
            int shortArrsize = sData.length;
            byte[] bytes = new byte[shortArrsize * 2];
            for (int i = 0; i < shortArrsize; i++) {
                bytes[i * 2] = (byte) (sData[i] & 0x00FF);
                bytes[(i * 2) + 1] = (byte) (sData[i] >> 8);
                //  sData[i] = 0;
            }
            return bytes;

        }

        public void nuevoEnsayo(){
            datos = new ArrayList();

            String filepath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(filepath, AUDIO_RECORDER_FOLDER);
            outputFile = file.getAbsolutePath() + "/" + "muestra1" + AUDIO_RECORDER_FILE_EXT_WAV;
            byte[] soundBytes;

            try {
                InputStream inputStream =
                        getContentResolver().openInputStream(Uri.fromFile(new File(outputFile)));

                soundBytes = new byte[inputStream.available()];
                soundBytes = toByteArray(inputStream);

                Toast.makeText(this, "Recordin Finished"+ " " + soundBytes, Toast.LENGTH_LONG).show();

                //señal en short
                short[] shorts = new short[soundBytes.length/2];
                // to turn bytes to shorts as either big endian or little endian.
                ByteBuffer.wrap(soundBytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
                for (int i= 0; i<shorts.length; i++){
                    datos.add(shorts[i]);
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }


        public byte[] toByteArray(InputStream in) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read = 0;
            byte[] buffer = new byte[1024];
            while (read != -1) {
                read = in.read(buffer);
                if (read != -1)
                    out.write(buffer,0,read);
            }
            out.close();
            return out.toByteArray();
        }





        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            }
            return super.onKeyDown(keyCode, event);
        }



        public void Dialog4Clic (View v) {
            AlertDialog.Builder ventana4 = new AlertDialog.Builder(this);
            ventana4.setTitle("Información");
            ventana4.setMessage("Por favor presione el botón Empezar, y luego pronuncie una vocal sotenida por lapso de 5 segundos");
            ventana4.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(FonetogramaProActivity.this, "Si no es constante la pronunciación, repita el proceso", Toast.LENGTH_LONG).show();
                }
            });
            ventana4.show();
        }





        public short maximos(ArrayList arr) {//maximos de cualquier arreglo arr
            int i;
            short max = (short) 50;
            for (i = 0; i < arr.size(); i++) {
                if ((short) arr.get(i) > max) {
                    max = (short) arr.get(i);
                }
            }
            Log.e("VALOR DE MAX", ""+max);
            return max;
        }

        public void resetVariables (){
            amplitudeMin = 0;
            amplitudeMax = 0;
            datosMuestraini = new ArrayList();
            datosMuestra = new ArrayList();
            datos = new ArrayList();
        }



}

