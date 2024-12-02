package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import javax.naming.OperationNotSupportedException;

public class Matriculas {

    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    //Constructor
    public Matriculas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        setCapacidad(capacidad);
        setTamano(0);
        coleccionMatriculas = new Matricula[capacidad];
    }

    // Constructor copia
    public Matricula[] get() {
        return copiaProfundaMatriculas();
    }

    public Matricula[] get(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un alumno nulo.");
        }
        Matricula[] matriculaPermanencia = new Matricula[capacidad];
        int indice = 0;
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                matriculaPermanencia[indice++] = new Matricula(matricula);
            }
        }

        return matriculaPermanencia;
    }

    public Matricula[] get(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un curso nulo.");
        }
        Matricula[] cursoPermanencia = new Matricula[capacidad];
        int indice = 0;
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getCursoAcademico().equals(cursoAcademico)) {
                cursoPermanencia[indice++] = new Matricula(matricula);
            }
        }

        return cursoPermanencia;
    }

    public Matricula[] get(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un curso nulo.");
        }
        Matricula[] cicloPermanencia = new Matricula[capacidad];
        int indice = 0;
        /* // PA PAÑANA
        for(int i; i < cicloFormativo.getCodigo())
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.) {
                cicloPermanencia[indice++] = new Matricula(matricula);
            }
        }*/

        return cicloPermanencia;
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

    public Matricula[] getColeccionMatriculas() {
        return coleccionMatriculas;
    }

    public void setColeccionMatriculas(Matricula[] coleccionMatriculas) {
        this.coleccionMatriculas = coleccionMatriculas;
    }

    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copiaMatricula = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaMatricula[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatricula;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) throws OperationNotSupportedException {
        if (tamano >= coleccionMatriculas.length) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        return indice >= capacidad;
    }

    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nulo.");
        }
        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Matricula(coleccionMatriculas[indice]);
        }
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula de ese alumno");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i+1];
        }
        coleccionMatriculas[i] = null;
        tamano--;
    }

    private int buscarIndice(Matricula matricula) {
        int indice = 0;
        boolean matriculaEncontrado = false;

        if (matricula==null) {
            throw new NullPointerException("ERROR: No se puede buscar el índice de una matricula nula.");
        }
        while (!tamanoSuperado(indice) && !matriculaEncontrado) {
            if (coleccionMatriculas[indice].equals(matricula)) {
                matriculaEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        if (tamanoSuperado(indice)) {
            coleccionMatriculas[indice] = new Matricula(matricula);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula de este alumno.");
        }
    }

}


