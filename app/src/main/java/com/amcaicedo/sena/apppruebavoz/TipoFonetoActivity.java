package com.amcaicedo.sena.apppruebavoz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TipoFonetoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvFonetoHablado, tvFonetoCantado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_foneto);

        tvFonetoHablado = (TextView) findViewById(R.id.tvFonetoHablado);
        tvFonetoCantado = (TextView) findViewById(R.id.tvFonetoCantado);

        tvFonetoHablado.setOnClickListener(this);
        tvFonetoCantado.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvFonetoHablado:
                Intent intent = new Intent(TipoFonetoActivity.this,MainFonetograma.class);
                startActivity(intent);
                break;
            case R.id.tvFonetoCantado:
                intent = new Intent(TipoFonetoActivity.this,FonetogramaProActivity.class
                );
                startActivity(intent);

        }
    }
}
