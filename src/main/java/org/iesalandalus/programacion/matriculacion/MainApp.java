package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;


public class MainApp {

    // Constantes de la Clase
    public static final int CAPACIDAD = 3;

    //Atributos de la clase
    private static Alumnos alumnos;
    private static Matriculas matriculas;
    private static Asignaturas asignaturas;
    private static CiclosFormativos cicloFormativos;

    public static void main(String[] args) throws OperationNotSupportedException {

        Consola.mostrarMenu();
        Opcion opcionElegida = Consola.elegirOpcion();
        ejecutarOpcion(opcionElegida);

        System.out.println("Hasta luego Lucas!!!!");
    }

    //Metodos
    private static void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {

        boolean salir = false;
        do {
            switch (opcion) {
                //Alumnos
                case INSERTAR_ALUMNO -> insertarAlumno();
                case BUSCAR_ALUMNO -> buscarAlumno();
                case BORRAR_ALUMNO -> borrarAlumno();
                case MOSTRAR_ALUMNOS -> mostarAlumnos();

                //Ciclos Formativos
                case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
                case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
                case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
                case MOSTRAR_CICLOS_FORMATIVOS -> mostarCiclosFormativos();

                //Asignaturas
                case INSERTAR_ASIGNATURA -> insertarAsignatura();
                case BUSCAR_ASIGNATURA -> buscarAsignatura();
                case BORRAR_ASIGNATURA -> borrarAsignatura();
                case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();

                //Matriculas
                case INSERTAR_MATRICULA -> insertarMatricula();
                case BUSCAR_MATRICULA -> buscarMatricula();
                case MOSTRAR_MATRICULAS -> mostrarMatriculas();
                case MOSTRAR_MATRICULAS_POR_ALUMNO -> mostrarMatriculasPorAlumno();
                case MOSTRAR_MATRICULAS_POR_CICLO_FORMATIVO -> mostrarMatriculasPorCicloFormativo();
                case MOSTRAR_MATRICULAS_POR_CURSO_ACADEMICO -> mostrarMatriculasPorCursoAcademico();
                case ANULAR_MATRICULA -> anularMatricula();

                //Salir
                case SALIR -> salir = true;

                case null, default -> throw new IllegalArgumentException("ERROR: Opcion incorrecta");
            }
        } while (!salir);

    }

    private static void insertarAlumno(){

        Alumno alumno = new Alumno(Consola.leerAlumno());

        try {
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void buscarAlumno(){
        Alumno alumno = new Alumno(Consola.getAlumnoPorDni());

        alumnos.buscar(alumno);
        System.out.println("Alumno insertado correctamente");
    }

    private static void borrarAlumno() throws OperationNotSupportedException {
        alumnos.borrar(Consola.getAlumnoPorDni());
    }

    private static void mostarAlumnos(){

        Alumno[] alumnosMostar =  alumnos.get();

        if (alumnosMostar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen alumnos para mostrar.");
        }

        for (Alumno alumno : alumnosMostar) {
            alumno.imprimir();
        }
    }

    private static void insertarAsignatura(){
        if(cicloFormativos == null){
            throw new IllegalArgumentException("ERROR: No existen ningun Ciclo Formativo");
        }

        Asignatura asignatura = new Asignatura(Consola.leerAsignatura(cicloFormativos));

        try {
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void buscarAsignatura(){

        Asignatura asignatura = new Asignatura(Consola.getAsignaturaPorCodigo());

        asignaturas.buscar(asignatura);
        System.out.println("Asignatura insertado correctamente");
    }

    private static void borrarAsignatura() throws OperationNotSupportedException {
        asignaturas.borrar(Consola.getAsignaturaPorCodigo());
    }

    private static void mostrarAsignaturas(){

        Consola.mostrarAsignautras(asignaturas);
    }

    private static void insertarCicloFormativo(){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.leerCicloFormativo());

        try {
            cicloFormativos.insertar(cicloFormativo);
            System.out.println("Ciclo Formativo insertado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void buscarCicloFormativo (){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.getCicloFormativoPorCodigo());

        cicloFormativos.buscar(cicloFormativo);
        System.out.println("Alumno insertado correctamente");
    }

    private static void borrarCicloFormativo() throws OperationNotSupportedException {
        cicloFormativos.borrar(Consola.getCicloFormativoPorCodigo());
    }

    private static void mostarCiclosFormativos(){

        Consola.mostrarCiclosFormativos(cicloFormativos);
    }

    //Matriculas
    private static void insertarMatricula() throws OperationNotSupportedException {

        Matricula matricula = new Matricula( Consola.leerMatricula(alumnos, asignaturas));

        try {
            matriculas.insertar(matricula);
            System.out.println("Matricula insertado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void buscarMatricula(){

        Matricula matricula = new Matricula(Consola.getMatriculaPorIdentificador());

        matriculas.buscar(matricula);
        System.out.println("Matricula insertado correctamente");
    }

    private static void anularMatricula(){

        int idMatriculaAnular = 0;
        String fechaAnulado;
        Matricula[] matriculaMostrar = matriculas.get();

        if (matriculaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen matriculas para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            matricula.imprimir();
        }

        System.out.print("Introduzca ID de matricula que quiere anular: ");
        idMatriculaAnular = Entrada.entero();

        for(int i = 0; i < matriculaMostrar.length; i++){
            if (matriculaMostrar[i].getIdMatricula() == idMatriculaAnular){
                System.out.print("Introduzca fecha de anulación: ");
                fechaAnulado = Entrada.cadena();
                matriculaMostrar[i].setFechaAnulacion(LocalDate.parse(fechaAnulado));
                System.out.print("Matricula anulada");
            }
        }
    }

    private static void mostrarMatriculas(){

        Matricula[] matriculaMostrar = matriculas.get();

        if (matriculaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen matriculas para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            matricula.imprimir();
        }
    }

    private static void mostrarMatriculasPorAlumno(){

        Alumno alumnoBuscado = Consola.getAlumnoPorDni();
        Matricula[] matriculaMostrar = matriculas.get(alumnoBuscado);

        if (matriculaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen alumnos para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            matricula.imprimir();
        }
    }

    private static void mostrarMatriculasPorCicloFormativo(){

        CicloFormativo cicloFormativoMostrar = Consola.getCicloFormativoPorCodigo();
        Matricula[] matriculaMostrar = matriculas.get(cicloFormativoMostrar);

        if (matriculaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen matriculas con ese ciclo formativo para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            matricula.imprimir();
        }
    }

    private static void mostrarMatriculasPorCursoAcademico(){

        Curso curso =  Consola.leerCurso();
        Matricula[] matriculaMostrar = matriculas.get(String.valueOf(curso));

        if (matriculaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen matriculas con ese curso academico para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            matricula.imprimir();
        }
    }
}
