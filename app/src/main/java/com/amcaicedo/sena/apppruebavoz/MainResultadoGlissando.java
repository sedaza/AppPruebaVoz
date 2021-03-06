package com.amcaicedo.sena.apppruebavoz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.ArrayList;

public class MainResultadoGlissando extends AppCompatActivity {

    private ArrayList datosAmplitud;
    private ArrayList datosAmplitudmin;
    private  ArrayList datosAmplitudmax;
    private short max;
    private ArrayList datosFrecuencia =new ArrayList();
    private float frecfund;
    GraphView graphViewFT, graphViewIT;
    LineGraphSeries<DataPoint> seriesFT, seriesIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resultado_glissando);

        graphViewFT = (GraphView) findViewById(R.id.graphViewFT);
        graphViewIT = (GraphView) findViewById(R.id.graphViewIT);

        datosAmplitudmin = MainGlissando.dBMin;
        datosAmplitudmax = MainGlissando.dBMax;

        datosAmplitud = MainGlissando.datosAmplitud;
        max =MainGlissando.max;

        Bundle extras = getIntent().getExtras();
        ArrayList frecuenciaresultado = extras.getStringArrayList("algoritmos");

        for (int d=0; d<frecuenciaresultado.size(); d++){
            datosFrecuencia.add(Float.parseFloat(frecuenciaresultado.get(d).toString()));
        }
        frecfund = extras.getFloat("frecuencia");



        Log.e("DATOS AMPLITUD NUEVO", datosAmplitud.toString());
        Log.e("DATOS FRECUENCIA NUEVO", datosFrecuencia.toString());
        Log.e("TAMAÑO DATOS FRECUENCIA", ""+datosFrecuencia.size());
        Log.e("DATOS FRECUENCIAFUND", ""+frecfund);

        seriesIT = new LineGraphSeries<DataPoint>();
        seriesFT = new LineGraphSeries<DataPoint>();
        // Gráfica de Intensidad----------------------------------------
        for (int i = 0; i < datosAmplitud.size(); i++) {//amplitud debido al conversor analogo- digital(16bits)
            //------------------graficar
            //int x = (int) Algoritmos.posicionesMax.get(i);
            int x = i;

            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));
            short z = (short)datosAmplitud.get(i);
            String c = Short.toString(z);
            float y = Float.parseFloat(c);

            seriesIT.appendData(new DataPoint(x, y), true, 600);
            Viewport viewport = graphViewIT.getViewport();
            viewport.setYAxisBoundsManual(true);
            viewport.setMinY(0);
            viewport.setMaxY(Double.parseDouble(String.valueOf(max))+5);
            viewport.setScrollable(true);
            Log.e("grafica MAXXXXX", ""+y);

        }

        graphViewIT.addSeries(seriesIT);   //---------------------
       /* for (int i = 0; i < MainGlissando.dBMax.size(); i++) {//amplitud debido al conversor analogo- digital(16bits)
            //------------------graficar
            int x = i;//FonetogramaProActivity.Frecuenciafoneto[i];
            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));

            short y = (short) datosAmplitudmax.get(i);


            seriesIT.appendData(new DataPoint(x, y), true, 500);
            Viewport viewport = graphViewIT.getViewport();
            viewport.setYAxisBoundsManual(true);
            viewport.setMinY(0);
            viewport.setMaxY(Double.parseDouble(String.valueOf(max)) + 10);
            viewport.setScrollable(true);
            Log.e("grafica MAXXXXX", "" + y);

        }*/


        // Gráfica de Frecuencia
        for (int i = 0; i < datosFrecuencia.size(); i++) {//amplitud debido al conversor analogo- digital(16bits)
            //------------------graficar
            int x = i;
            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));
            float y = (float) datosFrecuencia.get(i);
            seriesFT.appendData(new DataPoint(x, y), true, datosFrecuencia.size());
            Viewport viewport = graphViewFT.getViewport();
            viewport.setYAxisBoundsManual(true);
            viewport.setMinY(0);
            viewport.setMaxY(frecfund*1.5);
            //viewport.setMaxY(Double.parseDouble((datosAmplitud2.get(20).toString()))+80);
            viewport.setXAxisBoundsManual(true);
            viewport.setMinX(0);
            viewport.setMaxX(datosFrecuencia.size()+10);
            viewport.setScrollable(true);

        }
        graphViewFT.addSeries(seriesFT);

    }
}
