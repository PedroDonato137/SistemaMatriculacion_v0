package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import javax.naming.OperationNotSupportedException;

public class Asignaturas {
    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    //Constructor
    public Asignaturas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        setCapacidad(capacidad);
        setTamano(0);
        coleccionAsignaturas = new Asignatura[capacidad];
    }

    // Constructor copia
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
        this.coleccionAsignaturas = coleccionAsignaturas;
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copiaAsignaturas = new Asignatura[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAsignaturas[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copiaAsignaturas;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nulo.");
        }
        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Asignatura(coleccionAsignaturas[indice]);
        }
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nulo.");
        }
        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura con ese nombre.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i+1];
        }
        coleccionAsignaturas[i] = null;
        tamano--;
    }

    private int buscarIndice(Asignatura asignatura) {
        int indice = 0;
        boolean asignaturaEncontrada = false;

        if (asignatura==null)
            throw new NullPointerException("ERROR: No se puede buscar el índice de una asignatura nulo.");

        while (!tamanoSuperado(indice) && !asignaturaEncontrada) {
            if (coleccionAsignaturas[indice].equals(asignatura)) {
                asignaturaEncontrada = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nulo.");
        }
        int indice = buscarIndice(asignatura);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
        if (tamanoSuperado(indice)) {
            coleccionAsignaturas[indice] = new Asignatura(asignatura);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese nombre.");
        }
    }
}
