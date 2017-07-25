package com.amcaicedo.sena.apppruebavoz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amcaicedo.sena.apppruebavoz.madelos.Medico;

public class RegistroActivity extends AppCompatActivity {

    EditText etNombreMedico, etApellidoMedico, etCedulaMedico;
    Button btnRegistrarMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombreMedico = (EditText) findViewById(R.id.etNombreMedico);
        etApellidoMedico = (EditText) findViewById(R.id.etApellidoMedico);
        etCedulaMedico = (EditText) findViewById(R.id.etCedulaMedico);

        btnRegistrarMedico = (Button) findViewById(R.id.btnRegistrarMedico);
        btnRegistrarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNombreMedico.getText().toString().equalsIgnoreCase("") || etApellidoMedico.getText().toString().equalsIgnoreCase("")
                        || etCedulaMedico.getText().toString().equalsIgnoreCase(""))
                    Toast.makeText(RegistroActivity.this, "No se aceptan campos vacíos", Toast.LENGTH_SHORT).show();
                else
                    registrarMedico();
            }
        });

    }

    public void registrarMedico(){
        Medico medico = new Medico();
        medico.setNombre(etNombreMedico.getText().toString());
        medico.setApellido(etApellidoMedico.getText().toString());
        medico.setCedula(etCedulaMedico.getText().toString());
        medico.save();
        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
