package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private Consola() {
        //Constructor privado para no poder instanciarlo
    }

    public void mostrarMenu(){
        System.out.println("=== Opciones del menú ===");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
        System.out.print("Seleccione una opción: ");
    }
    public Opcion elegirOpcion(){

        int ordinalOpcion;
        do {
            System.out.print("Selecciona que opción quiere realizar: ");
            ordinalOpcion = Entrada.entero();
            if (ordinalOpcion < 0 || ordinalOpcion > Opcion.values().length){
                System.out.println("ERROR: Opción incorrecta");
            }
        } while (ordinalOpcion >= 0 && ordinalOpcion <= Opcion.values().length - 1);

        return Opcion.values()[ordinalOpcion];
    }

    public Alumno leerAlumno(){

        String nombreAlumno;
        String dniAlumno;
        String correoAlumno;
        String telefonoAlumno;
        String fechaNacimientoAlumno; // Luego se pasa a tipo LocalDate

        Alumno nuevoAlumno = null;

        do {
            System.out.print("Introduce el nombre del Alumno: ");
            nombreAlumno = Entrada.cadena();
        } while (nombreAlumno.isEmpty());

        do {
            System.out.print("Introduce el DNI del Alumno: ");
            dniAlumno = Entrada.cadena();
        } while (dniAlumno.isEmpty());

        do {
            System.out.print("Introduce el correo del Alumno: ");
            correoAlumno = Entrada.cadena();
        } while (correoAlumno.isEmpty());

        do {
            System.out.print("Introduce el correo del Alumno: ");
            telefonoAlumno = Entrada.cadena();
        } while (telefonoAlumno.isEmpty());

        do {
            System.out.print("Introduce la fecha de nacimiento(dd/mm/yyyy): ");
            fechaNacimientoAlumno = Entrada.cadena();

        } while (fechaNacimientoAlumno.isEmpty());

        nuevoAlumno = new Alumno(nombreAlumno, dniAlumno, correoAlumno, telefonoAlumno, LocalDate.parse(fechaNacimientoAlumno));

        return new Alumno(nuevoAlumno);
    }

    public Alumno getAlumnoPorDni() {

        String dniAlumno;
        // Crear datos ficticios para el alumno
        String nombreFicticio = "Alumno Ficticio";
        String telefonoFicticio = "telefono Ficticio";
        String correoFicticio = "correo@ficticio.com";
        LocalDate fechaNacimientoFicticio = LocalDate.of(1990, 6, 9); // Fecha ficticia válida (+16)

        do {
            System.out.print("Introduce el DNI del Alumno: ");
            dniAlumno = Entrada.cadena();
        } while (dniAlumno.isEmpty());

        // Validar que el DNI no sea nulo ni vacío
        if (dniAlumno == null || dniAlumno.isBlank()) {
            throw new IllegalArgumentException("ERROR: El DNI no puede ser nulo o vacío.");
        }

        // Validar que el formato del DNI sea correcto
        if (!dniAlumno.matches("\\d{8}[A-Z]")) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
        }

        try {
            return new Alumno(nombreFicticio, dniAlumno, correoFicticio, telefonoFicticio, fechaNacimientoFicticio );
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el alumno con el DNI proporcionado.", e);
        }
    }

    public LocalDate leerFecha(String mensaje){
        LocalDate fecha = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (fecha == null) {
            System.out.print("Introduce una fecha en el formato dd/MM/yyyy: ");
            String entrada = Entrada.cadena();

            try {
                fecha = LocalDate.parse(entrada, formatoFecha);
            } catch (DateTimeParseException e) {
                System.out.println(mensaje);
            }
        }

        return fecha;
    }

    public static Grado leerGrado(){

        int opcionGrado;
        System.out.println("Seleccione un grado de la lista:");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionGrado = Entrada.entero();
        } while (opcionGrado < 0 || opcionGrado > Grado.values().length);

        return Grado.values()[opcionGrado];
    }

    public CicloFormativo leerCicloFormativo(){

        int codigoCiclo;
        String familiaProfesionalCiclo;
        Grado gradoCiclo;
        String nombreCiclo;
        int horasCiclo;

        CicloFormativo nuevoGrado = null;

        do {
            System.out.print("Introduce el código del ciclo formativo: ");
            codigoCiclo = Entrada.entero();
        } while (codigoCiclo < 1000 || codigoCiclo > 9999);

        do {
            System.out.print("Introduce la familia profesional: ");
            familiaProfesionalCiclo = Entrada.cadena();
        } while (familiaProfesionalCiclo.isEmpty());

        gradoCiclo = Consola.leerGrado();

        do {
            System.out.print("Introduce el nombre del ciclo formativo: ");
            nombreCiclo = Entrada.cadena();
        } while (nombreCiclo.isEmpty());

        do {
            System.out.print("Introduce el código del ciclo formativo: ");
            horasCiclo = Entrada.entero();
        } while (horasCiclo < 0 || horasCiclo > 2000);

        nuevoGrado = new CicloFormativo(codigoCiclo, familiaProfesionalCiclo, gradoCiclo, nombreCiclo, horasCiclo);

        return new CicloFormativo(nuevoGrado);

    }

    public void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos){

        ciclosFormativos.getColeccionCiclosFormativos();  // REVISAR
    }

    public CicloFormativo getCicloFormativoPorCodigo() {

        int codigoCiclo;
        // Crear datos ficticios para el ciclo formativo
        String familiaProfesionalCiclo = "Familia profesional Ficticia";
        Grado gradoCiclo = Grado.GDCFGB;
        String nombreCiclo = "Nombre Ficticio";
        int horasCiclo = 25;

        do {
            System.out.print("Introduce el Codigo del ciclo formativo: ");
            codigoCiclo = Entrada.entero();
        } while (codigoCiclo == 0);

        try {
            return new CicloFormativo(codigoCiclo, familiaProfesionalCiclo, gradoCiclo, nombreCiclo, horasCiclo );
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el ciclo formativo con ese código", e);
        }
    }

    public static Curso leerCurso(){

        int opcionCurso;
        System.out.println("Seleccione un curso de la lista:");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionCurso = Entrada.entero();
        } while (opcionCurso < 0 || opcionCurso > Curso.values().length);

        return Curso.values()[opcionCurso];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado(){

        int opcionEspecialidad;
        System.out.println("Seleccione una especialidad de la lista:");
        for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
            System.out.println(especialidadProfesorado.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionEspecialidad = Entrada.entero();
        } while (opcionEspecialidad < 0 || opcionEspecialidad > EspecialidadProfesorado.values().length);

        return EspecialidadProfesorado.values()[opcionEspecialidad];
    }

    public Asignatura leerAsignatura(CicloFormativo cicloFormativo){

        String codigoAsignatura;
        String nombreAsignatura;
        int horasAnualesAsignatura;
        Curso cursoAsignatura;
        int horasDesdobleAsignatura;
        EspecialidadProfesorado especialidadProfesoradoAsignatura;

        Asignatura nuevaAsignatura = null;

        do {
            System.out.print("Introduce el Codigo de la asignatura: ");
            codigoAsignatura = Entrada.cadena();
        } while (codigoAsignatura.isEmpty());

        do {
            System.out.print("Introduce el nombre de la asignatura: ");
            nombreAsignatura = Entrada.cadena();
        } while (nombreAsignatura.isEmpty());

        do {
            System.out.print("Introduce las horas anuales de la asignatura: ");
            horasAnualesAsignatura = Entrada.entero();
        } while (horasAnualesAsignatura == 0);

        cursoAsignatura = Consola.leerCurso();

        do {
            System.out.print("Introduce las horas de desdoble de la asignatura: ");
            horasDesdobleAsignatura = Entrada.entero();
        } while (horasDesdobleAsignatura == 0);

        especialidadProfesoradoAsignatura = Consola.leerEspecialidadProfesorado();

        nuevaAsignatura = new Asignatura(codigoAsignatura, nombreAsignatura, horasAnualesAsignatura, cursoAsignatura, horasDesdobleAsignatura, especialidadProfesoradoAsignatura, cicloFormativo);

        return new Asignatura(nuevaAsignatura);

    }

    public Asignatura getAsignaturaPorCodigo(){

        String codigoAsignatura;
        String nombreAsignatura = "Base de datos Ficticia";
        int horasAnualesAsignatura = 28;
        Curso cursoAsignatura = Curso.PRIMERO;
        int horasDesdobleAsignatura = 30;
        EspecialidadProfesorado especialidadProfesoradoAsignatura = EspecialidadProfesorado.INFORMATICA;
        CicloFormativo cicloFormativoAsignatura = new CicloFormativo(5, "Informática y Comunicaciones", Grado.GDCFGB, "DAW", 500 );

        do {
            System.out.print("Introduce el Codigo de la asignatura: ");
            codigoAsignatura = Entrada.cadena();
        } while (codigoAsignatura.isEmpty());

        try {
            return new Asignatura(codigoAsignatura, nombreAsignatura, horasAnualesAsignatura, cursoAsignatura, horasDesdobleAsignatura, especialidadProfesoradoAsignatura, cicloFormativoAsignatura);
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear la asignatura con ese código", e);
        }

    }

    private void mostrarAsignautras(Asignaturas asignaturas){
        asignaturas.getColeccionAsignaturas();
    }

    private boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura){

        if (asignaturasMatricula == null || asignatura == null) {
            throw new IllegalArgumentException("ERROR: Ni la lista ni la asignatura pueden ser nulas.");
        }

        for (Asignatura asignaturaEnLista : asignaturasMatricula) {
            if (asignaturaEnLista != null && asignaturaEnLista.equals(asignatura)) {
                return true; // La asignatura ya está en la lista.
            }
        }

        return false; // La asignatura no está en la lista.
    }






}
