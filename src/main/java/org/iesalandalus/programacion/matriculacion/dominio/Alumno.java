package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
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

    // Varribles de la clase
    private String  nombre;
    private String  telefono;
    private String  correo;
    private String  dni;
    private LocalDate fechaNacimiento;
    private String nia;

    //Constructor
    public Alumno() {
        setNombre(this.nombre);
        setDni(this.dni);
        setCorreo(this.correo);
        setTelefono(this.telefono);
        setFechaNacimiento(LocalDate.now());
    }

    // Constructor Copia
    public Alumno(Alumno alumno){
        this.nombre = alumno.nombre;
        this.telefono = alumno.telefono;
        this.correo = alumno.correo;
        this.dni = alumno.dni;
        this.fechaNacimiento = alumno.fechaNacimiento;
    }

    public String getNia() {
        return nia;
    }

    private void setNia() {
        this.nia = nia;
    }

    private void setNia(String nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
            int numerodni = Integer.parseInt(dniValidar.substring(0, 7)); // Separo la parte numérica
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

}
