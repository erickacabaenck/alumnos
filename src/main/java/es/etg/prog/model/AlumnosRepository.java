// es/etg/prog/model/AlumnosRepository.java
package es.etg.prog.model;

import java.util.List;

public interface AlumnosRepository {
    Alumno insertar(Alumno a);
    List<Alumno> listar();
    boolean existe(Alumno a);
}
