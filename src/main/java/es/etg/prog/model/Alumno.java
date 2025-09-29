package es.etg.prog.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alumno {
    private Long id; // lo pone el modelo
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;

    public Alumno() {}

    // Constructor sin id (el modelo lo generará)
    public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento) {
        this(null, nombre, apellidos, fechaNacimiento);
    }

    public Alumno(Long id, String nombre, String apellidos, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int getEdad() {
        if (fechaNacimiento == null) return 0;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fn = (fechaNacimiento == null) ? "?" : fechaNacimiento.format(fmt);
        String idStr = (id == null) ? "-" : String.valueOf(id);
        return "[id=" + idStr + "] " + nombre + " " + apellidos + " (" + getEdad() + " años, " + fn + ")";
    }

    /** Igualdad lógica: por id si existe; si no, por (nombre, apellidos, fecha) */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;

        if (id == null || alumno.id == null) {
            return Objects.equals(nombre, alumno.nombre)
                && Objects.equals(apellidos, alumno.apellidos)
                && Objects.equals(fechaNacimiento, alumno.fechaNacimiento);
        }
        return Objects.equals(id, alumno.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return Objects.hash(nombre, apellidos, fechaNacimiento);
        }
        return Objects.hash(id);
    }
}
