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
        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    // Constructor copia
    public CicloFormativo[] get() {
        return copiaProfundaCicloFormativo();
    }

    private CicloFormativo[] copiaProfundaCicloFormativo() {
        CicloFormativo[] copiaCicloFormativo = new CicloFormativo[tamano];

        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaCicloFormativo[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copiaCicloFormativo;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }
        if (tamanoSuperado(indice)) {
            coleccionCiclosFormativos[indice] = new CicloFormativo(cicloFormativo);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        int indice = 0;
        boolean cicloFormativoEncontrado = false;

        if (cicloFormativo == null)
            throw new NullPointerException("ERROR: No se puede buscar el índice de un ciclo formativo nulo.");

        while (!tamanoSuperado(indice) && !cicloFormativoEncontrado) {
            if (coleccionCiclosFormativos[indice] != null && coleccionCiclosFormativos[indice].equals(cicloFormativo)) {
                cicloFormativoEncontrado = true;
            } else {
                indice++;
            }
        }

        return indice;
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
            return null; // Si el tamaño del indice es mayor a la capacidad del arrray devuelve null (no lo ha encontrado)
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
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; i < tamano - 1; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[tamano - 1] = null;
        tamano--;
    }

}
