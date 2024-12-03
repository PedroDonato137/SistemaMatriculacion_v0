package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.utilidades.Entrada;

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

        Alumno nuevoAlumno = null;


        return nuevoAlumno;
    }



}
