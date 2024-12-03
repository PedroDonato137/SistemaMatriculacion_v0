package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {

    INSERTAR_ALUMNO("Insertar alumno"),
    BUSCAR_ALUMNO("Buscar alumno"),
    BORRAR_ALUMNO("Borrar alumno"),
    MOSTRAR_ALUMNOS("Mostrar alumnos"),
    INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo"),
    BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo"),
    BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo"),
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos"),
    INSERTAR_ASIGNATURA("Insertar asignatura"),
    BUSCAR_ASIGNATURA("Buscar asignatura"),
    BORRAR_ASIGNATURA("Borrar asignatura"),
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas"),
    INSERTAR_MATRICULA("Insertar matrícula"),
    BUSCAR_MATRICULA("Buscar matrícula"),
    ANULAR_MATRICULA("Anular matrícula"),
    MOSTRAR_MATRICULAS("Mostrar matrículas"),
    MOSTRAR_MATRICULAS_POR_ALUMNO("Mostrar matrículas por alumno"),
    MOSTRAR_MATRICULAS_POR_CICLO_FORMATIVO("Mostrar matrículas por ciclo formativo"),
    MOSTRAR_MATRICULAS_POR_CURSO_ACADEMICO("Mostrar matrículas por curso académico"),
    SALIR("Salir");


    private final String cadenaAMostrar;

    Opcion(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return this.ordinal() + " .- " + cadenaAMostrar;
    }
}
