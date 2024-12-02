package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {

    INFORMATICA("INFORMATICA"),
    SISTEMAS("SISTEMAS"),
    FOL("FOL");

    private String cadenaAMostrar;

    EspecialidadProfesorado(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir(){
        return this.ordinal() + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "EspeciacidadProfesorado{" + "cadenaAMostrar='" + cadenaAMostrar + '\'' + '}';
    }
}