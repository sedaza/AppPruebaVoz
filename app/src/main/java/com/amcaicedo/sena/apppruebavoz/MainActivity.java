package com.amcaicedo.sena.apppruebavoz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amcaicedo.sena.apppruebavoz.madelos.Paciente;
import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity {

    EditText etNombrePaciente, etApellidoPaciente, etCedulaPaciente, etEdadPaciente;
    Button btnContinuar;

    Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);

        paciente = new Paciente();

        etNombrePaciente = (EditText) findViewById(R.id.etNombrePaciente);
        etApellidoPaciente = (EditText) findViewById(R.id.etApellidoPaciente);
        etCedulaPaciente = (EditText) findViewById(R.id.etCedulaPaciente);
        etEdadPaciente = (EditText) findViewById(R.id.etEdadPaciente);

        btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos()) {
                    paciente.setNombre(etNombrePaciente.getText().toString());
                    paciente.setApellido(etApellidoPaciente.getText().toString());
                    paciente.setCedula(etCedulaPaciente.getText().toString());
                    paciente.setEdad(Integer.parseInt(etEdadPaciente.getText().toString()));
                    paciente.guardarDatos(paciente);
                    Toast.makeText(MainActivity.this, "Datos guardados correctamente.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, PruebasActivity.class);
                    intent.putExtra("CEDULA", paciente.getCedula());
                    startActivity(intent);
                }else
                    Toast.makeText(MainActivity.this, "No se aceptan campos nulos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validarCampos() {
        if(etNombrePaciente.getText().toString().equals("") || etApellidoPaciente.getText().toString().equals("") || etCedulaPaciente.getText().toString().equals("")
                || etEdadPaciente.getText().toString().equals(""))
            return false;
        else
            return true;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_masculino:
                if (checked)
                    paciente.setGenero(1);

                    break;
            case R.id.radio_femenino:
                if (checked)
                    paciente.setGenero(0);
                    break;
        }
    }
}
