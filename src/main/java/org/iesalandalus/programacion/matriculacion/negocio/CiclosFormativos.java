package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import javax.naming.OperationNotSupportedException;

public class CiclosFormativos {

    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    //Constructor
    public CiclosFormativos(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        setCapacidad(capacidad);
        setTamano(0);
        coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    // Constructor copia
    public CicloFormativo[] get() {
        return copiaProfundaCicloFormativo();
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

    public CicloFormativo[] getColeccionCiclosFormativos() {
        return coleccionCiclosFormativos;
    }

    public void setColeccionCiclosFormativos(CicloFormativo[] coleccionCiclosFormativos) {
        this.coleccionCiclosFormativos = coleccionCiclosFormativos;
    }

    private CicloFormativo[] copiaProfundaCicloFormativo() {
        CicloFormativo[] copiaCicloFormativo = new CicloFormativo[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaCicloFormativo[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copiaCicloFormativo;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un Ciclo Formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new CicloFormativo(coleccionCiclosFormativos[indice]);
        }
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo con ese nombre.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i+1];
        }
        coleccionCiclosFormativos[i] = null;
        tamano--;
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        int indice = 0;
        boolean cicloFormativoEncontrado = false;

        if (cicloFormativo==null)
            throw new NullPointerException("ERROR: No se puede buscar el índice de un ciclo formativo nulo.");

        while (!tamanoSuperado(indice) && !cicloFormativoEncontrado) {
            if (coleccionCiclosFormativos[indice].equals(cicloFormativo)) {
                cicloFormativoEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclo formativo.");
        }
        if (tamanoSuperado(indice)) {
            coleccionCiclosFormativos[indice] = new CicloFormativo(cicloFormativo);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese nombre.");
        }
    }
}
