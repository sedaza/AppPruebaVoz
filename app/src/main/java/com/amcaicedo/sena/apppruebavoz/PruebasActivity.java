package com.amcaicedo.sena.apppruebavoz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PruebasActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ARCHIVO = "ARCHIVO";

    TextView btnGlissando, btnEKaiser, btnFonetograma;
    Intent intent;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);

        btnGlissando = (TextView) findViewById(R.id.btnGlissando);
        btnEKaiser = (TextView) findViewById(R.id.btnEKaiser);
        btnFonetograma = (TextView) findViewById(R.id.btnFonetograma);

        btnGlissando.setOnClickListener(this);
        btnEKaiser.setOnClickListener(this);
        btnFonetograma.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGlissando:
                extras = new Bundle();
                intent = new Intent(PruebasActivity.this, MainGlissando.class);
                extras.putString(ARCHIVO, "glissando");
                intent.putExtras(extras);
                startActivity(intent);
                break;
            case R.id.btnEKaiser:
                extras = new Bundle();
                intent = new Intent(PruebasActivity.this, MainEkaiser.class);
                extras.putString(ARCHIVO, "kaiser");
                intent.putExtras(extras);
                startActivity(intent);
                break;
            case R.id.btnFonetograma:
                extras = new Bundle();
                intent = new Intent(PruebasActivity.this, MainFonetograma.class);
                extras.putString(ARCHIVO, "fonetograma");
                intent.putExtras(extras);
                startActivity(intent);
                break;
        }
    }
}
