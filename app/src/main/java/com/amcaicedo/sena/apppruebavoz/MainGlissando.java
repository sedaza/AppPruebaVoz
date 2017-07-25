package com.amcaicedo.sena.apppruebavoz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.FileNameMap;
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

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

public class MainGlissando extends Activity {

    Button bSiguiente33, bAnterior33;
    LineGraphSeries<DataPoint> series;//para graficar

   // private XYPlot myXYPlot;
    private int frecuencia;
    private ArrayList datos = new ArrayList();
    private ArrayList datosMuestra = new ArrayList();

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

    //parametros de audio para hallar fo
    public int LongVentana = 2048;
    public int channelConfiguration = AudioFormat.CHANNEL_IN_MONO;
    public int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    public static short[] buffer; //+-32767
    public static short[] bufferTemporal;
    public static short[] cep_alta;
    public static short[] cep_energia;
    public static final int SAMPPERSEC = 22500;
    int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
    int BytesPerElement = 2; // 2 bytes in 16bit format


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glissando);

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

                Intent intent4 = new Intent(MainGlissando.this, MainResultadoGlissando.class);
                intent4.putExtra("frecuencia", String.valueOf(frecuencia));
                startActivity(intent4);

            }
        });
    }

//-----------------
        //bAnterior33 = (Button)findViewById(R.id.bAnterior3);
        //bAnterior33.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //  public void onClick(View v) {

              //Intent intent5 = new Intent(ActivityCincoAudioRecording.this, ActivityCuatroTExamen.class);
        //        finish();

        //  }
        //});

        //enableButtons(false);
        //}
//-------------------

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
        outputFile = file.getAbsolutePath() + "/" + System.currentTimeMillis() + AUDIO_RECORDER_FILE_EXT_WAV;
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

        startTimer();

        Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
    }


    public void startTimer(){
        new CountDownTimer(2000, 1000) {//genera un timer de 30000 milisegundos y muestra el estado cada 1000 milisegundos

            public void onTick(long millisUntilFinished) {

                textView.setText("segundos restantes: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textView.setText("Terminado!");
                stop ();
            }
        }.start();
    }

    public void btGraficar(View v){
        obtenerMuestra();
        Algoritmos al = new Algoritmos(datosMuestra);
        al.calcular();
        al.negativos();
        System.out.println("qeeeeeeeeeeeeeeeeeeeeeeeeee");
        float[] nuevoArr = al.getArreglo1();//getArreglo (grafica sin normalizar)
        graficar(nuevoArr);

    }

    public void obtenerMuestra(){
        for (int x = (datos.size()/2); x<(datos.size()/2)+2000; x++){
            datosMuestra.add(datos.get(x));

        }
        System.out.println("imeeeeeee" + datosMuestra);
    }

    private void writeAudioDataToFile() {
        byte data[] = new byte[bufferSize];
        short [] dataShort = new short[bufferSize/2];
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



      /*      private void stopRecording() {
                // stops the recording activity
                if (null != recorder) {
                    isRecording = false;
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                    recordingThread = null;
                }
            }
*/

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
                Toast.makeText(MainGlissando.this, "Si no es constante la pronunciación, repita el proceso", Toast.LENGTH_LONG).show();
            }
        });
        ventana4.show();
    }
    /**
     * Calcula la frecuencia de los datos de audio
     * @return devuelve la frecuencia
     */

    private int frequency(){


        while( true )
        {
            if(recorder==null){
                System.out.println("estanuloooooooooooooooooooooo");
            }

            recorder.read(buffer, 0, LongVentana);
            //recorder.getBufferSizeInFrames();

            int Tmin, Tmax;
            int UMBRAL_ENER=20;
            int tam_fft;
            int i_max=0;
            double max=0;
            int i_max2=0;
            double max2=0;
            double mean=0;
            double total=0;
            int T;
            int freq;
            int m = (LongVentana/2) / 2;
            double r;
            double pi = Math.PI;
            double[] w = new double[LongVentana/2];
            double[] ventana = new double[LongVentana/2];
            double[] cep_alta = new double[LongVentana/4];
            double[] cep_energia = new double[LongVentana/4];

            Tmin=(int)Math.round(0.002*SAMPPERSEC);
            Tmax=(int)Math.round(0.015*SAMPPERSEC);
            //fft rapidas
            tam_fft=(int)Math.pow( 2, Math.ceil( Math.log(LongVentana/2) /
                    Math.log(2)));
            //ventana hamming
            r = pi / m;
            for (int n = -m; n < m; n++){
                w[m + n] = 0.54f + 0.46f * Math.cos(n * r);
            }
            //ventana es la voz enventanda usando hamming
            for (int i = 0; i < LongVentana/2; i++){
                ventana[i]=w[i]*buffer[i];
            }
            //cepstrum
            DoubleFFT_1D fftDo = new DoubleFFT_1D(tam_fft);
            double[] fft = new double[tam_fft*2];
            System.arraycopy(ventana, 0, fft, 0, tam_fft);
            fftDo.realForward(fft);
            for (int i = 0; i < tam_fft*2; i++){
                fft[i]=Math.log(Math.abs(fft[i]));

            }
            fftDo.realInverse(fft,true);
            //cep-alta
            for (int i = Tmin; i < tam_fft/4; i++){
                cep_alta[i]=fft[i];
            }
            //cep_energia
            for (int i = Tmin; i < tam_fft/4; i++){
                cep_energia[i]=cep_alta[i]*cep_alta[i];
            }
            //max y i_max
            for (int i = Tmin; i < Tmax-Tmin; i++){
                if (max<cep_energia[i]){
                    max=cep_energia[i];
                    i_max=i;
                }
            }
            //media cep_energia
            for (int i = Tmin; i < tam_fft/4; i++){
                total+=cep_energia[i];
                mean=total/((tam_fft/4)-Tmin);
            }
            //Se buscan máximos anteriores (de índice menor) que tengan amplitud suficiente,
            // quedándonos con el último que encontremos (el primer máximo que supera el umbral).
            if (max>UMBRAL_ENER*mean){
                for (int i = Tmin; i < i_max-Tmin; i++){
                    if (max2<cep_energia[i]){
                        max2=cep_energia[i];
                        i_max2=i;
                    }
                }

                while (max2>UMBRAL_ENER*mean){
                    i_max=i_max2;
                    for (int i = Tmin; i < i_max2-Tmin; i++){
                        if (max2<cep_energia[i]){
                            max2=cep_energia[i];
                            i_max2=i;
                        }
                    }
                }
                // i_max+Tmin-1 es el numero de muestra dentro del vector cepstrum
                T=i_max+Tmin-1;
                if (T!=0){
                    freq=Math.round(SAMPPERSEC/T);
                }

                else{
                    freq=0;
                }
            }

            else{
                freq=0;
            }

            return freq;
        }

    }

    private void graficar( float[] nuevoArr) {

        //contenido para grabar
        double x, y;
        x = 0;

        GraphView graph = (GraphView)findViewById(R.id.graphView);
        series = new LineGraphSeries<DataPoint>();

        for (int i = 0; i<500; i++){//amplitud debido al conversor analogo- digital(16bits)
            x = i;
            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));
            y = (double) nuevoArr[i];
            series.appendData(new DataPoint(x,y), true, 500);
        }
        graph.addSeries(series);

/*
        myXYPlot = (XYPlot)findViewById(R.id.myXYPlot);

        //creamos un array con los diguientes datos
        Number[] Datos1 = new Number[2000]; //Con estos datos se empiezan a calcular los parametros

        int cont =0;
        for (int i = bufferTemporal.length-1999; i< bufferTemporal.length; i++){

            Datos1[cont] = (Number) bufferTemporal[i];
            System.out.println("ddddddddddddddddddddddddddddddddddddd" + Datos1[cont]);
            cont ++;
        }

        //Number [] Datos2 = {1,2,4,6,8,0,9,6,4};
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(Datos1),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,//Solo valores verticales
                "Series1"); //Nombre de primera serie
        //Modificar los colores de la serie
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),//color linea
                Color.rgb(0, 100, 0),//color punto
                //Color.rgb(150, 190, 150), null); //color relleno
                Color.rgb(255,255,255), null);
        myXYPlot.addSeries(series1, series1Format);
        myXYPlot.redraw();*/
    }
}
