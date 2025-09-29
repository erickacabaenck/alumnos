package es.etg.prog.controller;

import es.etg.prog.model.Alumno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlumnosControllerTest {

    private AlumnosController controller;

    @BeforeEach
    void setUp() {
        controller = new AlumnosController();
    }

    @Test
    void insertarAlumnoNuevoDevuelveTrue() {
        Alumno a = new Alumno("Juan", "Pérez", LocalDate.of(2000, 1, 1));
        assertTrue(controller.insertarAlumno(a));
    }

    @Test
    void insertarAlumnoDuplicadoDevuelveFalse() {
        Alumno a = new Alumno("Ana", "García", LocalDate.of(2001, 2, 2));
        controller.insertarAlumno(a);
        assertFalse(controller.insertarAlumno(a));
    }

    @Test
    void listarDevuelveListaCorrecta() {
        Alumno a1 = new Alumno("Luis", "Martín", LocalDate.of(1999, 3, 3));
        Alumno a2 = new Alumno("Marta", "López", LocalDate.of(2002, 4, 4));
        controller.insertarAlumno(a1);
        controller.insertarAlumno(a2);

        List<Alumno> lista = controller.listarAlumnos();

        assertEquals(2, lista.size());
        assertTrue(lista.contains(a1));
        assertTrue(lista.contains(a2));
    }
}
