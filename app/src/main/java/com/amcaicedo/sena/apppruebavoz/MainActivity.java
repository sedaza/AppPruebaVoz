package com.amcaicedo.sena.apppruebavoz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amcaicedo.sena.apppruebavoz.AppUtil.AppUtil;
import com.amcaicedo.sena.apppruebavoz.madelos.Paciente;
import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity {

    EditText etNombrePaciente, etApellidoPaciente, etCedulaPaciente, etEdadPaciente;
    Button btnContinuar;

    RadioButton radio_masculino, radio_femenino;

    Paciente paciente;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);

        paciente = new Paciente();

        preferences = getSharedPreferences(AppUtil.PREFERENCES_NAME, MODE_PRIVATE);
        editor = preferences.edit();

        etNombrePaciente = (EditText) findViewById(R.id.etNombrePaciente);
        etApellidoPaciente = (EditText) findViewById(R.id.etApellidoPaciente);
        etCedulaPaciente = (EditText) findViewById(R.id.etCedulaPaciente);
        etEdadPaciente = (EditText) findViewById(R.id.etEdadPaciente);

        radio_masculino = (RadioButton) findViewById(R.id.radio_masculino);
        radio_femenino = (RadioButton) findViewById(R.id.radio_femenino);

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
                || etEdadPaciente.getText().toString().equals("") || (!radio_masculino.isChecked() && !radio_femenino.isChecked()))
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cerrar:
                editor.putBoolean(AppUtil.KEY_LOGIN, false);
                editor.commit();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
