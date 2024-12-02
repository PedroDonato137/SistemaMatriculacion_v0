package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class CicloFormativo {

    // Constantes de la Clase
    public static final int MAXIMO_NUMERO_HORAS = 2000;

    // Atributos de la Clase
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;



    // Constructor
    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas) throws IllegalArgumentException {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo(CicloFormativo cicloFormativo) {
        this.codigo = cicloFormativo.codigo;
        this.familiaProfesional = cicloFormativo.familiaProfesional;
        this.grado = cicloFormativo.grado;
        this.nombre = cicloFormativo.nombre;
        this.horas = cicloFormativo.horas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("El codigo debe ser un número de cuatro dígitos.");
        }

        this.codigo = codigo;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas < 0 || horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("El número de horas debe estar entre 0 y " + MAXIMO_NUMERO_HORAS);
        }

        this.horas = horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Un ciclo formativo será igual a otro si su identificador es el mismo
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo && horas == that.horas && Objects.equals(familiaProfesional, that.familiaProfesional) && grado == that.grado && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, familiaProfesional, grado, nombre, horas);
    }

    @Override
    public String toString() {
        return "Código ciclo formativo=" + codigo + ", familia profesional=" + familiaProfesional + ", grado=Grado{cadenaAMostrar='" + grado + "'} , nombre ciclo formativo=" + nombre +", horas=" + horas;
    }

    public String imprimir() {
        return "Código ciclo formativo=" + codigo + ", nombre ciclo formativo=" + nombre;
    }
}