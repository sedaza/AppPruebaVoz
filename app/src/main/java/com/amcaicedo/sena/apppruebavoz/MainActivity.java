package com.amcaicedo.sena.apppruebavoz;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amcaicedo.sena.apppruebavoz.AppUtil.AppUtil;
import com.amcaicedo.sena.apppruebavoz.madelos.Paciente;
import com.orm.SugarContext;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    EditText etNombrePaciente, etApellidoPaciente, etCedulaPaciente, etEdadPaciente;
    Button btnContinuar;

    RadioButton radio_masculino, radio_femenino;

    Paciente paciente;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    // Permisos
    private final int MY_PERMISSIONS = 100;

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

        if(!mayRequestStoragePermission())
            Toast.makeText(this, "Permisos autorizados", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Permisos NO autorizados", Toast.LENGTH_SHORT).show();


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
        getMenuInflater().inflate(R.menu.buscador, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_buscador);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(searchItem, this);

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

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Buscador activado", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Buscador desactivado", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Paciente paciente = null;
        if(Paciente.findPacienteByCedula(query) != null){
            paciente = Paciente.findPacienteByCedula(query);

            mostrarDatosPaciente(paciente);
        }else
            Toast.makeText(getApplicationContext(), "No hay datos del paciente: " + query, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    private void mostrarDatosPaciente(final Paciente paciente) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Datos del paciente");

        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(10, 10, 10, 10);
        layout.setOrientation(LinearLayout.VERTICAL);

        final TextView nombrePaciente = new TextView(this);
        nombrePaciente.setPadding(5,5,5,5);
        nombrePaciente.setTextSize(30);
        nombrePaciente.setText("Nombre: " + paciente.getNombre() + " " + paciente.getApellido());
        layout.addView(nombrePaciente);

        final TextView cedulaPaciente = new TextView(this);
        cedulaPaciente.setPadding(5,5,5,5);
        cedulaPaciente.setTextSize(30);
        cedulaPaciente.setText("Cédula: " + paciente.getCedula());
        layout.addView(cedulaPaciente);

        alertDialog.setView(layout);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, PruebasActivity.class);
                intent.putExtra("CEDULA", paciente.getCedula());
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancelar", null);

        //show alert
        alertDialog.show();
    }

    private boolean mayRequestStoragePermission() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(RECORD_AUDIO))){
            Toast.makeText(getApplicationContext(), "Los permisos son necesarios para poder usar la aplicación",
                    Toast.LENGTH_LONG).show();
            /*Snackbar.make(getView(), "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_LONG).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, MY_PERMISSIONS);
                }
            });*/
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, MY_PERMISSIONS);
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, MY_PERMISSIONS);
        }

        return false;
    }
}
