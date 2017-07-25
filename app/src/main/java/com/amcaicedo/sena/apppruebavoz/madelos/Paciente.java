package com.amcaicedo.sena.apppruebavoz.madelos;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Windows 10 Pro on 16/06/2017.
 */

public class Paciente extends SugarRecord {

    private String nombre;
    private String apellido;
    private String cedula;
    private int edad;
    //0 para femenino y 1para Masculino
    private int genero;

    public Paciente() {

    }

    public Paciente(String nombre, String apellido, String cedula, int edad, int genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.edad = edad;
        this.genero = genero;
    }

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public void guardarDatos(Paciente paciente){
        paciente.save();
    }

    public static Paciente findPacienteByCedula(String cedula){
        Paciente paciente = null;

        List<Paciente> result = find(Paciente.class, "cedula = ?", cedula);

        if(result.size()>0)
            paciente = result.get(0);

        return paciente;
    }

}
