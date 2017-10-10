package com.amcaicedo.sena.apppruebavoz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainResultadoGlissando extends AppCompatActivity {

    private ArrayList datosAmplitud, datosFrecuencia;
    GraphView graphViewFT, graphViewIT;
    LineGraphSeries<DataPoint> seriesFT, seriesIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resultado_glissando);

        graphViewFT = (GraphView) findViewById(R.id.graphViewFT);
        graphViewIT = (GraphView) findViewById(R.id.graphViewIT);

        datosAmplitud = MainGlissando.datosAmplitud;
        datosFrecuencia = Algoritmos.datosFrecuencia;
        Log.e("DATOS AMPLITUD NUEVO", datosAmplitud.toString());
        Log.e("DATOS FRECUENCIA NUEVO", datosFrecuencia.toString());
        Log.e("TAMAÑO DATOS FRECUENCIA", ""+datosFrecuencia.size());

        seriesIT = new LineGraphSeries<DataPoint>();
        seriesFT = new LineGraphSeries<DataPoint>();
        // Gráfica de Intensidad
        for (int i = 0; i < datosAmplitud.size(); i++) {//amplitud debido al conversor analogo- digital(16bits)
            //------------------graficar
            int x = i;
            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));
            short y = (short) datosAmplitud.get(i);
            seriesIT.appendData(new DataPoint(x, y), true, 6000);
        }
        graphViewIT.addSeries(seriesIT);

        // Gráfica de Frecuencia
        for (int i = 0; i < datosFrecuencia.size(); i++) {//amplitud debido al conversor analogo- digital(16bits)
            //------------------graficar
            int x = i;
            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));
            float y = (float) datosFrecuencia.get(i);
            seriesFT.appendData(new DataPoint(x, y), true, 6000);
        }
        graphViewFT.addSeries(seriesFT);

    }
}