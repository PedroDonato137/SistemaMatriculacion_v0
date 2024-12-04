package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private Consola() {
        //Constructor privado para no poder instanciarlo
    }

    public static void mostrarMenu(){
        System.out.println("=== Opciones del menú ===");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }
    public static Opcion elegirOpcion(){

        int ordinalOpcion;
        do {
            System.out.print("Selecciona que opción quiere realizar: ");
            ordinalOpcion = Entrada.entero();
        } while (ordinalOpcion < 0 || ordinalOpcion > Opcion.values().length);

        return Opcion.values()[ordinalOpcion];
    }

    public static Alumno leerAlumno(){

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
            System.out.print("Introduce el telefono del Alumno: ");
            telefonoAlumno = Entrada.cadena();
        } while (telefonoAlumno.isEmpty());

        do {
            System.out.print("Introduce la fecha de nacimiento(dd/mm/yyyy): ");
            fechaNacimientoAlumno = Entrada.cadena();

        } while (fechaNacimientoAlumno.isEmpty());

        try {
            return new Alumno(nombreAlumno, dniAlumno, correoAlumno, telefonoAlumno, leerFecha(fechaNacimientoAlumno));
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el alumno con el DNI proporcionado.", e);
        }
    }

    public static Alumno getAlumnoPorDni() {

        String dniAlumno;
        // Crear datos ficticios para el alumno
        String nombreFicticio = "Ficticio";
        String telefonoFicticio = "609822699";
        String correoFicticio = "correo@ficticio.com";
        LocalDate fechaNacimientoFicticio = LocalDate.of(1990, 6, 9); // Fecha ficticia válida (+16)

        do {
            System.out.print("Introduce el DNI del Alumno: ");
            dniAlumno = Entrada.cadena();
        } while (dniAlumno.isEmpty());

        try {
            return new Alumno(nombreFicticio, dniAlumno, correoFicticio, telefonoFicticio, fechaNacimientoFicticio);
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el alumno con el DNI proporcionado.", e);
        }
    }

    public static LocalDate leerFecha(String mensaje){
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

    public static CicloFormativo leerCicloFormativo(){

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
            System.out.print("Introduce el horas del ciclo formativo: ");
            horasCiclo = Entrada.entero();
        } while (horasCiclo < 0 || horasCiclo > 2000);

        nuevoGrado = new CicloFormativo(codigoCiclo, familiaProfesionalCiclo, gradoCiclo, nombreCiclo, horasCiclo);

        return new CicloFormativo(nuevoGrado);

    }

    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos){

        CicloFormativo[] ciclosMostrar = ciclosFormativos.get();

        if (ciclosMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen ciclos formativos para mostrar.");
        }

        for (CicloFormativo cicloFormativo : ciclosMostrar) {
            cicloFormativo.imprimir();
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {

        int codigoCiclo;
        // Crear datos ficticios para el ciclo formativo
        String familiaProfesionalCiclo = "Familia profesional Ficticia";
        Grado gradoCiclo = Grado.GDCFGB;
        String nombreCiclo = "Ficticio";
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

    public static Asignatura leerAsignatura(CiclosFormativos cicloFormativos){

        String codigoAsignatura;
        String nombreAsignatura;
        int horasAnualesAsignatura;
        Curso cursoAsignatura;
        int horasDesdobleAsignatura;
        EspecialidadProfesorado especialidadProfesoradoAsignatura;
        CicloFormativo cicloFormativoAsignatura;

        Asignatura nuevaAsignatura = null;

        //Variables auxiliales
        boolean existeCiclo = false;

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

        //Ciclo Formativo
        cicloFormativoAsignatura = getCicloFormativoPorCodigo(); // Creo un ciclo formativo solo con el codigo
        CicloFormativo[] ciclosExistentes = cicloFormativos.get(); // Recupero los ciclos formativos que existen

        for (CicloFormativo ciclosExistente : ciclosExistentes) {
            if (ciclosExistente == cicloFormativoAsignatura) {
                existeCiclo = true;
            }
        }
        if (existeCiclo) {
            nuevaAsignatura = new Asignatura(codigoAsignatura, nombreAsignatura, horasAnualesAsignatura, cursoAsignatura, horasDesdobleAsignatura, especialidadProfesoradoAsignatura, cicloFormativoAsignatura);
            return new Asignatura(nuevaAsignatura);
        }else {
            return null;
        }
    }

    public static Asignatura getAsignaturaPorCodigo(){

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

    public static void mostrarAsignautras(Asignaturas asignaturas){
        Asignatura[] asignaturaMostrar = asignaturas.get();

        if (asignaturaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen alumnos para mostrar.");
        }

        for (Asignatura asignatura : asignaturaMostrar) {
            asignatura.imprimir();
        }
    }

    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura){

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

    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas Asignaturas) throws OperationNotSupportedException {

        int idMatricula;
        String cursoAcademico;
        String fechaMatriculacion;
        Alumno alumno;
        Asignatura[] coleccionAsignaturas = null;

        Matricula nuevaMatricula = null;

        // Variables auxiliales
        int contadorAsignaturas = 0;
        String nuevaAsignaturas;
        Asignatura asignatura = null;

        do {
            System.out.print("Introduce el ID de la matricula: ");
            idMatricula = Entrada.entero();
        } while (idMatricula == 0);

        do {
            System.out.print("Introduce el Curso academico: ");
            cursoAcademico = Entrada.cadena();
        } while (cursoAcademico.isEmpty());

        do {
            System.out.print("Introduce la fecha de matriculación: ");
            fechaMatriculacion = Entrada.cadena();
        } while (fechaMatriculacion.isEmpty());

        do {
            alumno = getAlumnoPorDni();
        } while(alumnos.buscar(alumno) == alumno);

        do {
            System.out.print("Introduce una asignatura(Escribir salir para terminar): ");
            nuevaAsignaturas = Entrada.cadena();
            asignatura = getAsignaturaPorCodigo();

            if(Asignaturas.buscar(asignatura) == asignatura){
                if (!asignaturaYaMatriculada(coleccionAsignaturas, asignatura)) {
                    coleccionAsignaturas[contadorAsignaturas] = asignatura;
                    contadorAsignaturas++;
                }
            }else{
                throw new IllegalArgumentException("ERROR: La asignatura no existe");
            }

        } while(nuevaAsignaturas.equalsIgnoreCase("Salir"));

        nuevaMatricula = new Matricula(idMatricula, cursoAcademico, LocalDate.parse(fechaMatriculacion), alumno, coleccionAsignaturas );
        return new Matricula(nuevaMatricula);

    }

    public static Matricula getMatriculaPorIdentificador(){

        int idMatricula;
        String cursoAcademico = "24/25";
        String fechaMatriculacion = "04/12/2024";
        Alumno alumno = new Alumno("Pedro", "54119272L", "609822699", "pedrodonatogarcia@gmail.com", LocalDate.of(1990,6,9));
        Asignatura[] coleccionAsignaturas = new Asignatura[10];

        //Datos Ficticios
        CicloFormativo cicloFicticio = new CicloFormativo(5, "Informatica", Grado.GDCFGS, "Informatica", 500);
        Asignatura asignaturaFicticia = new Asignatura("0001", "Base Datos", 1000, Curso.PRIMERO, 6, EspecialidadProfesorado.INFORMATICA, cicloFicticio);
        coleccionAsignaturas[0] = asignaturaFicticia;

        Matricula nuevaMatricula = null;

        do {
            System.out.print("Introduce el ID de la matrícula: ");
            idMatricula = Entrada.entero();
        } while (idMatricula == 0);

        nuevaMatricula = new Matricula(idMatricula, cursoAcademico, LocalDate.parse(fechaMatriculacion), alumno, coleccionAsignaturas );
        return new Matricula(nuevaMatricula);
    }

}
