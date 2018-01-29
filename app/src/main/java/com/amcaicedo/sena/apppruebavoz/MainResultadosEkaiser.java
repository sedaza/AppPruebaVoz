package com.amcaicedo.sena.apppruebavoz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainResultadosEkaiser extends AppCompatActivity {
    private ArrayList datosAmplitud2;
    GraphView graphViewIT;
    LineGraphSeries<DataPoint>  seriesIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resultados_ekaiser);

        graphViewIT = (GraphView) findViewById(R.id.graphViewKaiserIT);

        datosAmplitud2 = MainEkaiser.datosAmplitud2;

        seriesIT = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < datosAmplitud2.size(); i++) {//amplitud debido al conversor analogo- digital(16bits)
            //------------------graficar
            int x = i;
            //y = Double.parseDouble(Short.toString((short) datos.get((datos.size()/2)+i)));
            float y = (Float) datosAmplitud2.get(i);
            seriesIT.appendData(new DataPoint(x, y), true, 200);
            Viewport viewport = graphViewIT.getViewport();
            viewport.setYAxisBoundsManual(true);
            viewport.setMinY(0);
            viewport.setMaxY(Double.parseDouble((datosAmplitud2.get(60).toString()))+60);
            viewport.setScrollable(true);
        }
        graphViewIT.addSeries(seriesIT);
    }
}
