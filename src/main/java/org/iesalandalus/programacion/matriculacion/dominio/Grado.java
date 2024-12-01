package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB("GDCFGB"),
    GDCFGM("GDCFGM"),
    GDCFGS("GDCFGS");

    private final String cadenaAMostrar;

    Grado(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public static String imprimir(){
        String cadena = "";



        return "";
    }

    @Override
    public String toString() {
        return "Grado{" + "cadenaAMostrar='" + cadenaAMostrar + '\'' + '}';
    }
}
