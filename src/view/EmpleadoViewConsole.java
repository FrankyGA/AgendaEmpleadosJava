package view;

import controller.EmpleadoController;
import model.Empleado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmpleadoViewConsole {
    private EmpleadoController empleadoController;
    private Scanner scanner;

    public EmpleadoViewConsole() {
        empleadoController = new EmpleadoController();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            switch (opcion) {
                case 1 -> agregarEmpleado();
                case 2 -> actualizarEmpleado();
                case 3 -> eliminarEmpleado();
                case 4 -> listarEmpleados();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }

    private void mostrarMenu() {
        System.out.println("\n--- Agenda de Empleados ---");
        System.out.println("1. Agregar Empleado");
        System.out.println("2. Actualizar Empleado");
        System.out.println("3. Eliminar Empleado");
        System.out.println("4. Listar Empleados");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void agregarEmpleado() {
        System.out.println("Agregar nuevo empleado");
        Empleado empleado = obtenerDatosEmpleado();
        if (empleadoController.agregarEmpleado(empleado)) {
            System.out.println("Empleado agregado correctamente");
        } else {
            System.out.println("No se pudo agregar el empleado");
        }
    }

    private void actualizarEmpleado() {
        System.out.print("Ingrese el ID del empleado a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Empleado empleado = obtenerDatosEmpleado();
        empleado.setId(id);

        if (empleadoController.actualizarEmpleado(empleado)) {
            System.out.println("Empleado actualizado correctamente");
        } else {
            System.out.println("No se pudo actualizar el empleado");
        }
    }

    private void eliminarEmpleado() {
        System.out.print("Ingrese el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        if (empleadoController.eliminarEmpleado(id)) {
            System.out.println("Empleado eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar el empleado");
        }
    }

    private void listarEmpleados() {
        System.out.print("¿Desea filtrar los empleados? (sí/no): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        String filtro = null;
        String orden = null;

        if (respuesta.equals("sí")) {
            System.out.print("Ingrese el campo de filtro (por ejemplo, ciudad='Madrid'): ");
            filtro = scanner.nextLine();

            System.out.print("Ingrese el campo de orden (por ejemplo, nombre ASC): ");
            orden = scanner.nextLine();
        }

        List<Empleado> empleados = empleadoController.listarEmpleados(filtro, orden);
        if (!empleados.isEmpty()) {
            System.out.println("\n--- Lista de Empleados ---");
            for (Empleado e : empleados) {
                System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre() + ", Apellidos: " + e.getApellidos() +
                        ", Fecha de Nacimiento: " + e.getFechaNacimiento() + ", Email: " + e.getEmail() +
                        ", Teléfono: " + e.getTelefono() + ", Dirección: " + e.getDireccion() + ", Ciudad: " + e.getCiudad() +
                        ", Provincia: " + e.getProvincia() + ", Puesto: " + e.getPuesto() + ", Departamento: " + e.getDepartamento());
            }
        } else {
            System.out.println("No se encontraron empleados");
        }
    }

    private Empleado obtenerDatosEmpleado() {
        Empleado empleado = new Empleado();

        System.out.print("Nombre: ");
        empleado.setNombre(scanner.nextLine());

        System.out.print("Apellidos: ");
        empleado.setApellidos(scanner.nextLine());

        System.out.print("Fecha de Nacimiento (yyyy-MM-dd): ");
        String fecha = scanner.nextLine();
        try {
            Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            empleado.setFechaNacimiento(fechaNacimiento);
        } catch (ParseException e) {
            System.out.println("Fecha inválida, asignando valor por defecto");
            empleado.setFechaNacimiento(new Date());
        }

        System.out.print("Email: ");
        empleado.setEmail(scanner.nextLine());

        System.out.print("Teléfono: ");
        empleado.setTelefono(scanner.nextLine());

        System.out.print("Dirección: ");
        empleado.setDireccion(scanner.nextLine());

        System.out.print("Ciudad: ");
        empleado.setCiudad(scanner.nextLine());

        System.out.print("Provincia: ");
        empleado.setProvincia(scanner.nextLine());

        System.out.print("Puesto: ");
        empleado.setPuesto(scanner.nextLine());

        System.out.print("Departamento: ");
        empleado.setDepartamento(scanner.nextLine());

        return empleado;
    }
}
