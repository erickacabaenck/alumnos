package es.etg.prog.controller;

import java.util.List;

import es.etg.prog.model.Alumno;
import es.etg.prog.model.AlumnosModel;
import es.etg.prog.model.AlumnosRepository;

/** Controlador: valida y delega en el repositorio */
public class AlumnosController {

    private final AlumnosRepository repo;

    /** Usa un repositorio en memoria por defecto */
    public AlumnosController() {
        this(new AlumnosModel());
    }

    /** Inyección de dependencia (útil para tests/mocks) */
    public AlumnosController(AlumnosRepository repo) {
        this.repo = repo;
    }

    /** Inserta si los datos son válidos y no está duplicado */
    public boolean insertarAlumno(Alumno a) {
        validar(a);
        if (repo.existe(a)) return false; 
        repo.insertar(a);
        return true;
    }

    public List<Alumno> listarAlumnos() {
        return repo.listar();
    }

    private void validar(Alumno a) {
        if (a == null) throw new IllegalArgumentException("Alumno nulo");
        if (a.getNombre() == null || a.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");
        if (a.getApellidos() == null || a.getApellidos().isBlank())
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        if (a.getFechaNacimiento() == null)
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
    }
}
