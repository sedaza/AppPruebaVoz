package com.amcaicedo.sena.apppruebavoz.madelos;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import static com.orm.SugarRecord.count;

/**
 * Created by asus on 17/06/2017.
 */

public class Medico extends SugarRecord{

    private String nombre;
    private String apellido;
    private String cedula;

    public Medico() {
    }

    public Medico(String nombre, String apellido, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }

    //region Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    //endregion

    public static void init(Context context){
        SugarContext.init(context);
        if(count(Medico.class)<1) {
            Medico medico = new Medico();
            medico.setNombre("Sebastian");
            medico.setApellido("Sarria");
            medico.setCedula("12345");

            medico.save();
        }
    }

    public static Medico findMedicoByUsrAndPass(String usr, String pass){
        Medico medico = null;

        List<Medico> result = find(Medico.class, "nombre = ? AND cedula = ?", usr, pass);

        if(result.size()>0)
            medico = result.get(0);

        return medico;
    }

}
