package org.iesalandalus.programacion.matriculacion.dominio;

import java.awt.*;
import java.util.Objects;

public class Asignatura {

    // Constantes de la Clase
    public static final int MAX_NUM_HORAS_ANUALES = 300;
    public static final int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "none";

    // Atributos con visibilidad adecuada
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    // Constructor
    public Asignatura(String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo){
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    // Constructor Copia
    public Asignatura(Asignatura asignatura){
        this.codigo = asignatura.codigo;
        this.nombre = asignatura.nombre;
        this.horasAnuales = asignatura.horasAnuales;
        this.curso = asignatura.curso;
        this.horasDesdoble = asignatura.horasDesdoble;
        this.especialidadProfesorado = asignatura.especialidadProfesorado;
        this.cicloFormativo = asignatura.cicloFormativo;
    }

    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío o ser nulo.");
        }
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales < 0 || horasAnuales > MAX_NUM_HORAS_ANUALES) {
            throw new IllegalArgumentException("El número de horas anuales debe estar entre 0 y " + MAX_NUM_HORAS_ANUALES + ".");
        }
        this.horasAnuales = horasAnuales;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble < 0 || horasDesdoble > MAX_NUM_HORAS_DESDOBLES) {
            throw new IllegalArgumentException("El número de horas de desdoble debe estar entre 0 y " + MAX_NUM_HORAS_DESDOBLES + ".");
        }

        this.horasDesdoble = horasDesdoble;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        this.especialidadProfesorado = especialidadProfesorado;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        this.cicloFormativo = cicloFormativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Una asignatura será igual a otra si su identificador es el mismo
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return horasAnuales == that.horasAnuales && horasDesdoble == that.horasDesdoble && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && curso == that.curso && especialidadProfesorado == that.especialidadProfesorado && Objects.equals(cicloFormativo, that.cicloFormativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
    }

    public String imprimir() {
        return "Código asignatura=" +codigo + ", nombre asignatura=" + nombre + ", ciclo formativo=Código ciclo formativo=" + cicloFormativo.getCodigo() +", nombre ciclo formativo=" + cicloFormativo.getNombre() ;
    }

    @Override
    public String toString() {
        return "Código=" + codigo + ", nombre=" + nombre + ", horas anuales=" + horasAnuales + ", curso=" + curso + ", horas desdoble=" + horasDesdoble + ", ciclo formativo=Código ciclo formativo=" + cicloFormativo.getCodigo() + ", nombre ciclo formativo=" + cicloFormativo.getNombre() + ", especialidad profesorado=" + especialidadProfesorado.toString();
    }
}