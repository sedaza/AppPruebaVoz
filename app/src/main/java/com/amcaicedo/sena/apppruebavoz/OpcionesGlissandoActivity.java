package com.amcaicedo.sena.apppruebavoz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OpcionesGlissandoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvGGrabar, tvGCargarAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_glissando);
        tvGGrabar = (TextView) findViewById(R.id.tvGGrabar);
        tvGCargarAudio = (TextView) findViewById(R.id.tvGCargarAudio);

        tvGGrabar.setOnClickListener(this);
        tvGCargarAudio.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.tvGGrabar:
                intent = new Intent(OpcionesGlissandoActivity.this,MainGlissando.class);
                startActivity(intent);
                break;
            case R.id.tvGCargarAudio:
                intent = new Intent(OpcionesGlissandoActivity.this,CargarGlissandoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
