package model;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoValidator {

    // Método para validaciones de datos
    public static List<String> validarEmpleado(Empleado empleado) {
        List<String> errores = new ArrayList<>();

        if (empleado == null) {
            errores.add("Empleado no puede ser nulo");
            return errores;
        }
        
        // Validación del nombre
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            errores.add("El nombre del empleado no puede estar vacío");
        }

        // Validación de los apellidos
        if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
            errores.add("Los apellidos no pueden estar vacíos.");
        }

        // Validación del correo electrónico
        if (empleado.getEmail() == null || !empleado.getEmail().matches("[^@ ]+@[^@ ]+\\.[^@ ]+")) {
            errores.add("El email del empleado no es válido");
        }

        // Validación del departamento
        if (empleado.getDepartamento() == null || empleado.getDepartamento().isEmpty()) {
            errores.add("El departamento no puede estar vacío.");
        }

        // Validación del número de teléfono (solo números)
        if (empleado.getTelefono() == null || !empleado.getTelefono().matches("\\d+")) {
            errores.add("El número de teléfono solo puede contener números.");
        }

        return errores; // Retornar la lista de errores
    }
}

