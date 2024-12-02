package org.iesalandalus.programacion.matriculacion.dominio;

import com.sun.jdi.event.BreakpointEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {

    // Constantes de la Clase
    private static final String ER_TELEFONO = "^(\\+34|0034|34)?[6789]\\d{8}$";
    private static final String ER_CORREO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String ER_DNI = "^(\\d{8})([A-HJ-NP-TV-Z])$";
    private static final String ER_NIA = "^[a-z]{4}\\d{3}$";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final int MIN_EDAD_ALUMNADO = 16; // Edad mínima

    // Atributos de la clase
    private String  nombre;
    private String  telefono;
    private String  correo;
    private String  dni;
    private LocalDate fechaNacimiento;
    private String nia;

    //Constructor
    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setNia();
    }

    // Constructor Copia
    public Alumno(Alumno alumno){
        this.nombre = alumno.nombre;
        this.telefono = alumno.telefono;
        this.correo = alumno.correo;
        this.dni = alumno.dni;
        this.fechaNacimiento = alumno.fechaNacimiento;
        this.nia = alumno.nia; // REVISAR ESTO
    }

    public String getNia() {
        return nia;
    }

    private void setNia() {
        String digitosNombre = "";
        String digitosDni = "";
        String niaFormado = "";
        Pattern patron = Pattern.compile(ER_NIA);
        Matcher Validacion;

        digitosNombre = this.nombre.substring(0, 4);
        digitosDni = this.dni.substring(5, 8);

        niaFormado = digitosNombre.toLowerCase() + digitosDni;

        Validacion = patron.matcher(niaFormado);

            this.nia = niaFormado;
    }

    private void setNia(String nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre del alumno no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre); // Dar formato al nombre
    }

    public void setTelefono(String telefono) {

        if (telefono != null && !telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no es válido.");
        }
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }

        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setDni(String dni) {

        if(comprobarLetraDni(dni)) {
            this.dni = dni;
        }else{
            // LANZAR EXCEPCION
        }
    }

    public String getDni() {
        return dni;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La matricula no puede ser null");
        }
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        LocalDate fechaFormateada = LocalDate.parse(fechaNacimiento.format(formatoFecha), formatoFecha);

        this.fechaNacimiento = fechaFormateada;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }


    // Metodos
    public String formateaNombre( String nombreCompleto){
        String nombreFor = "";
        String[] nombreSin = nombreCompleto.split(" ");

        for (int i = 0; i < nombreSin.length; i++) {

            nombreSin[i] = nombreSin[i].toLowerCase(); // Transforma todo el nombre en minuscula

            char primeraLetra = nombreSin[i].toUpperCase().charAt(0); // Cambia a mayuscula la palabra y coge solo la primera letra
            nombreSin[i] = primeraLetra + nombreSin[i].substring(1); // Concatena la letra con el RESTO de la palabra (el 1 es la posicion desce que empieza, por lo que coge solo de la segunda letra haceia delante (no en 0))

            if(i != (nombreSin.length - 1)) {
                nombreFor = nombreFor + nombreSin[i] + " ";
            }else{
                nombreFor = nombreFor + nombreSin[i];
            }
        }

        return nombreFor;
    }

    public String getIniciales(){
        String inicialesNombre = "";
        String[] iniciales = getNombre().split(" ");

        for (int i = 0; i < iniciales.length; i++) {
            if (!iniciales[i].equals(" "))
                inicialesNombre = inicialesNombre + iniciales[i].charAt(0);
        }

        return inicialesNombre;
    }

    public static Boolean comprobarLetraDni(String dniValidar){
        String letrasDni ="TRWAGMYFPDXBNJZSQVHLCKE"; // Posibles letras en el DNI
        Pattern patron = Pattern.compile(ER_DNI);
        Matcher Validacion;

        Validacion = patron.matcher(dniValidar); // Compruebo que el DNI a validar sea correcto con el patron

        if(Validacion.matches()){ // Si es correcto
            int numerodni = Integer.parseInt(dniValidar.substring(0, 8)); // Separo la parte numérica
            int modulo = numerodni % 23; // Saco el resto de dividerlo entre 23
            char letra = letrasDni.charAt(modulo); // Saco la letra que tendria que tener ese valor numérico

            if(letra == dniValidar.charAt(8)){ // Si coincide con la letra del DNI a validar devuelve verdadero
                return true;
            }else{
                return false;
            }
        }else{ // Si no sigue el patron devulve falso de todos modos
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(nombre, alumno.nombre) && Objects.equals(telefono, alumno.telefono) && Objects.equals(correo, alumno.correo) && Objects.equals(dni, alumno.dni) && Objects.equals(fechaNacimiento, alumno.fechaNacimiento) && Objects.equals(nia, alumno.nia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, telefono, correo, dni, fechaNacimiento, nia);
    }

    @Override
    public String toString() {
        return "Número de Identificación del Alumnado (NIA)=" + nia +  " nombre=" + nombre + " (" + getIniciales() + "), DNI=" + dni + ", correo=" + correo +", teléfono=" + telefono + ", fecha nacimiento=" + fechaNacimiento;
    }

    public String imprimir() {
        return "Número de Identificación del Alumnado (NIA)=" + nia +  " nombre=" + nombre + " (" + getIniciales() + "), DNI=" + dni + ", correo=" + correo +", teléfono=" + telefono + ", fecha nacimiento=" + fechaNacimiento;
    }
}
