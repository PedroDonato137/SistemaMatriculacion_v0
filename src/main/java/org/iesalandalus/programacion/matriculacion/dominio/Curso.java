package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    PRIMERO("PRIMERO"),
    SEGUNDO("SEGUNDO");

    private final String cadenaAMostrar;

    Curso(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public static String imprimir(){
        String cadena = "";



        return "";
    }

    @Override
    public String toString() {
        return "Curso{" + "cadenaAMostrar='" + cadenaAMostrar + '\'' + '}';
    }

}
