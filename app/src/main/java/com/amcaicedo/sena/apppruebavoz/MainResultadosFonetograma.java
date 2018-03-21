package com.amcaicedo.sena.apppruebavoz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainResultadosFonetograma extends AppCompatActivity {

    private int[] datosAmplitudmin;
    private int[] datosAmplitudmax;
    private short max;
    private ArrayList datosFrecuencia =new ArrayList();
    private float frecfund;
    GraphView graphViewIF;
    GraphView graphViewIFPrincipal;

    Viewport viewport;
    LineGraphSeries<DataPoint> seriesIF;
    LineGraphSeries<DataPoint> seriesIF2;

    Viewport viewport2;
    LineGraphSeries<DataPoint> seriesIFPrincipal;
    LineGraphSeries<DataPoint> seriesIFPrincipal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resultados_fonetograma);

        graphViewIF = (GraphView) findViewById(R.id.graphViewIF);
        graphViewIFPrincipal = (GraphView) findViewById(R.id.graphViewIFPrincipal);

        datosAmplitudmin = FonetogramaProActivity.dBmin;
        datosAmplitudmax = FonetogramaProActivity.dBmax;
        //max =FonetogramaProActivity.max;

        /*Bundle extras = getIntent().getExtras();
        ArrayList frecuenciaresultado = extras.getStringArrayList("");

        for (int d=0; d<frecuenciaresultado.size(); d++){
            datosFrecuencia.add(Float.parseFloat(frecuenciaresultado.get(d).toString()));
        }
        frecfund = extras.getFloat("frecuencia");


        //Bundle extras = getIntent().getExtras();
        //ArrayList frecuenciaresultado = extras.getStringArrayList("algoritmos");

        for (int d=0; d<frecuenciaresultado.size(); d++){
            datosFrecuencia.add(Float.parseFloat(frecuenciaresultado.get(d).toString()));
        }
        frecfund = extras.getFloat("frecuencia");


*/
        Log.e("DATOS AMPLITUD NUEVO", datosAmplitudmin.toString());
        Log.e("DATOS FRECUENCIA NUEVO", datosFrecuencia.toString());
        Log.e("TAMAÑO DATOS FRECUENCIA", ""+datosFrecuencia.size());
        Log.e("DATOS FRECUENCIAFUND", ""+frecfund);

        seriesIF = new LineGraphSeries<DataPoint>();
        seriesIF2 = new LineGraphSeries<DataPoint>();

        seriesIFPrincipal = new LineGraphSeries<DataPoint>();
        seriesIFPrincipal2 = new LineGraphSeries<DataPoint>();
        // Gráfica de Intensidad
        for (int i = 0; i < FonetogramaProActivity.numeroindice.size(); i++) {//amplitud debido al conversor analogo- digital(16bits)
            //------------------graficar
            int k = (int) FonetogramaProActivity.numeroindice.get(i);
            int x = FonetogramaProActivity.Frecuenciafoneto[k];
            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));

            float y = FonetogramaProActivity.dBmax[k];

            Log.e("grafica MAXXXXX", ""+k);
            Log.e("grafica MAXXXXXxxx", ""+x);
            Log.e("grafica MAXXXXXyyyyy", ""+y);

            seriesIF.appendData(new DataPoint(x, y), true, 500);
            seriesIFPrincipal.appendData(new DataPoint(x, y), true, 500);
            /*viewport = graphViewIF.getViewport();
            viewport.setYAxisBoundsManual(true);
            viewport.setMinY(0);
           // viewport.setMaxY(Double.parseDouble(String.valueOf(max))+10);
            viewport.setMaxY(100);
            viewport.setScrollable(true);
            Log.e("grafica MAXXXXX", ""+y);*/

            int a = FonetogramaProActivity.Frecuenciafoneto[k];
            float b = FonetogramaProActivity.dBmin[k];
            Log.e("grafica MAXXXXXyyyyymin", ""+b);

            seriesIF2.appendData(new DataPoint(a, b), true, 500);
            seriesIFPrincipal2.appendData(new DataPoint(a, b), true, 500);



        }

        graphViewIF.setTitle("ZOOM GRAFICA");
        graphViewIF.getGridLabelRenderer().setVerticalAxisTitle("INTENSIDAD dB's");
        graphViewIF.getGridLabelRenderer().setHorizontalAxisTitle("FRECUENCIA Hz");

        viewport = graphViewIF.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(100);

        viewport.setXAxisBoundsManual(true);
        viewport.setMinX(0);
        //viewport.setMaxX(1FonetogramaProActivity.Frecuenciafoneto[FonetogramaProActivity.numeroindice.size()]);
        viewport.setMaxX(100);

        viewport.setScrollable(true);
        seriesIF2.setColor(Color.argb(255, 255, 60, 60));
        seriesIF.setColor(Color.rgb(0 , 0, 0 ));
        graphViewIF.addSeries(seriesIF);
        graphViewIF.addSeries(seriesIF2);

        viewport2 = graphViewIFPrincipal.getViewport();
        viewport2.setYAxisBoundsManual(true);
        viewport2.setMinY(0);
        viewport2.setMaxY(100);

        viewport2.setXAxisBoundsManual(true);
        viewport2.setMinX(0);
        //viewport.setMaxX(1FonetogramaProActivity.Frecuenciafoneto[FonetogramaProActivity.numeroindice.size()]);
        viewport2.setMaxX(2000);
        graphViewIFPrincipal.addSeries(seriesIFPrincipal);
        graphViewIFPrincipal.addSeries(seriesIFPrincipal2);

        graphViewIFPrincipal.getGridLabelRenderer().setVerticalAxisTitle("INTENSIDAD dB's");
        graphViewIFPrincipal.getGridLabelRenderer().setHorizontalAxisTitle("FRECUENCIA Hz");


    }
}
