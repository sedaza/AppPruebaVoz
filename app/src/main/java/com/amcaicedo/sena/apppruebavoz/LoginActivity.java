package com.amcaicedo.sena.apppruebavoz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amcaicedo.sena.apppruebavoz.AppUtil.AppUtil;
import com.amcaicedo.sena.apppruebavoz.madelos.Medico;

public class LoginActivity extends AppCompatActivity {

    Button login, registrar;
    TextInputLayout usr, pass;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Medico.init(this);

        preferences = getSharedPreferences(AppUtil.PREFERENCES_NAME, MODE_PRIVATE);
        editor = preferences.edit();

        login = (Button) findViewById(R.id.btn_login);
        registrar = (Button) findViewById(R.id.btn_register);

        usr = (TextInputLayout) findViewById(R.id.edit_usr);
        pass = (TextInputLayout) findViewById(R.id.edit_pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateMedico();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validateMedico() {
        String usrTxt = usr.getEditText().getText().toString();
        String passTxt = pass.getEditText().getText().toString();

        Medico medico = Medico.findMedicoByUsrAndPass(usrTxt, passTxt);

        if(medico == null){
            pass.setErrorEnabled(true);
            pass.setError(getString(R.string.login_error));
        }else{

            editor.putBoolean(AppUtil.KEY_LOGIN, true);
            editor.putString(AppUtil.KEY_USR_NAME, medico.getNombre());
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
