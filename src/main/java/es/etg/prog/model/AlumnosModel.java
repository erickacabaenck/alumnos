package es.etg.prog.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** “BD” en memoria */
public class AlumnosModel implements AlumnosRepository {
    private final Map<Long, Alumno> alumnos = new LinkedHashMap<>();
    private long secuencia = 0; // contador para IDs

    /** Inserta un alumno y le asigna un id automático */
    @Override
    public Alumno insertar(Alumno a) {
        long id = ++secuencia;
        a.setId(id);
        alumnos.put(id, a);
        return a;
    }

    /** Devuelve todos los alumnos registrados */
    @Override
    public List<Alumno> listar() {
        return new ArrayList<>(alumnos.values());
    }

    /** Indica si un alumno ya existe (comparando por nombre, apellidos y fecha de nacimiento) */
    @Override
    public boolean existe(Alumno a) {
        return alumnos.values().stream().anyMatch(a::equals);
    }
}
