package es.etg.prog.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import es.etg.prog.controller.AlumnosController;
import es.etg.prog.model.Alumno;

public class App {
    public static void main(String[] args) {
        AlumnosController ctrl = new AlumnosController();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        boolean salir = false;
        while (!salir) {
            System.out.println("""
                    === Gestión de Alumnos ===
                    1) Insertar
                    2) Listar
                    0) Salir
                    """);
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            try {
                switch (op) {
                    case "1" -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine().trim();

                        System.out.print("Apellidos: ");
                        String apellidos = sc.nextLine().trim();

                        System.out.print("Fecha nacimiento (dd/MM/yyyy): ");
                        String fechaStr = sc.nextLine().trim();

                        LocalDate fecha = LocalDate.parse(fechaStr, fmt);

                        Alumno a = new Alumno(nombre, apellidos, fecha);
                        boolean insertado = ctrl.insertarAlumno(a);
                        if (insertado) System.out.println("Insertado: " + a);
                        else System.out.println("El alumno ya existe.");
                    }
                    case "2" -> {
                        var lista = ctrl.listarAlumnos();
                        if (lista.isEmpty()) System.out.println("(sin alumnos)");
                        else lista.forEach(System.out::println);
                    }
                    case "0" -> {
                        salir = true;
                        System.out.println("Saliste");
                    }
                    default -> System.out.println("Opción no válida");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Usa formato dd/MM/yyyy (ej: 07/09/2001).");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        sc.close();
        System.out.println("Has salido");
    }
}
