package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Matricula {

    // Constantes de la Clase
    public static final int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    private static final String ER_CURSO_ACADEMICO = "\\d{2}-\\d{2}";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";

    // Atributos de la Clase
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas =new Asignatura[MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA]; // El número máximo de asignaturas de una matrícula son 10.

    //Constructor
    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }

    public Matricula(Matricula matricula) {
        this.idMatricula = matricula.idMatricula;
        this.cursoAcademico = matricula.cursoAcademico;
        this.fechaMatriculacion = matricula.fechaMatriculacion;
        this.alumno = matricula.alumno;
        this.coleccionAsignaturas = Arrays.copyOf(matricula.coleccionAsignaturas, matricula.coleccionAsignaturas.length); // Copia del array
    }


    public Alumno getAlumno() {
        return alumno;
    }
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
        this.coleccionAsignaturas = coleccionAsignaturas;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0) {
            throw new IllegalArgumentException("El identificador debe ser un número positivo");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("El curso académico debe tener el formato dd-dd, por ejemplo, 23-24.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {

        LocalDate fechaFormateada;

        if (fechaMatriculacion == null) {
            throw new IllegalArgumentException("La fecha de matriculación no puede ser nula.");
        }

        long diasDeRetraso = ChronoUnit.DAYS.between(fechaMatriculacion, LocalDate.now());
        if (diasDeRetraso < 0 || diasDeRetraso > MAXIMO_DIAS_ANTERIOR_MATRICULA) {
            throw new IllegalArgumentException("La matrícula no puede tener más de " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días de retraso.");
        }

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        fechaFormateada = LocalDate.parse(fechaMatriculacion.format(formatoFecha), formatoFecha);

        this.fechaMatriculacion = fechaFormateada;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion != null && ChronoUnit.MONTHS.between(this.fechaMatriculacion, fechaAnulacion) > MAXIMO_MESES_ANTERIOR_ANULACION) {
            throw new IllegalArgumentException("La fecha de anulación no puede superar los " + MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    private String asignaturasMatricula() {
        StringBuilder sb = new StringBuilder();
        for (Asignatura asignatura : coleccionAsignaturas) {
            sb.append(asignatura.imprimir()).append("\n");
        }
        return sb.toString().trim();
    }

    private boolean superaMaximoNumeroHorasMatricula() {
        int totalHoras = Arrays.stream(coleccionAsignaturas).mapToInt(Asignatura::getHorasAnuales).sum();
        return totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    public String imprimir() {
        return "idMatricula=" + idMatricula + ", curso académico=" + cursoAcademico + ", fecha matriculación=" + fechaMatriculacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", alumno=" + getAlumno();
    }

    @Override
    public String toString() {
        return "Matricula{" + "idMatricula=" + idMatricula + ", cursoAcademico='" + cursoAcademico +  ", fechaMatriculacion=" + fechaMatriculacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", fechaAnulacion=" + fechaAnulacion + ", alumno=" + alumno + ", coleccionAsignaturas=" + Arrays.toString(coleccionAsignaturas) + '}';
    }
}
