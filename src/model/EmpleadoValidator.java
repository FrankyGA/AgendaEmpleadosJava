package model;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoValidator {

    // M�todo para validaciones de datos
    public static List<String> validarEmpleado(Empleado empleado) {
        List<String> errores = new ArrayList<>();

        if (empleado == null) {
            errores.add("Empleado no puede ser nulo");
            return errores;
        }
        
        // Validaci�n del nombre
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            errores.add("El nombre del empleado no puede estar vac�o");
        }

        // Validaci�n de los apellidos
        if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
            errores.add("Los apellidos no pueden estar vac�os.");
        }

        // Validaci�n del correo electr�nico
        if (empleado.getEmail() == null || !empleado.getEmail().matches("[^@ ]+@[^@ ]+\\.[^@ ]+")) {
            errores.add("El email del empleado no es v�lido");
        }

        // Validaci�n del departamento
        if (empleado.getDepartamento() == null || empleado.getDepartamento().isEmpty()) {
            errores.add("El departamento no puede estar vac�o.");
        }

        // Validaci�n del n�mero de tel�fono (solo n�meros)
        if (empleado.getTelefono() == null || !empleado.getTelefono().matches("\\d+")) {
            errores.add("El n�mero de tel�fono solo puede contener n�meros.");
        }

        return errores; // Retornar la lista de errores
    }
}

