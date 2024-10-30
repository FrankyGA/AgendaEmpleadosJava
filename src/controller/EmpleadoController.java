package controller;

import model.Empleado;
import model.EmpleadoDAO;

import java.util.List;

public class EmpleadoController {
	private EmpleadoDAO empleadoDAO;

	public EmpleadoController() {
		empleadoDAO = new EmpleadoDAO();
	}

	public boolean agregarEmpleado(Empleado empleado) {
		if (validarEmpleado(empleado)) {
			return empleadoDAO.agregarEmpleado(empleado);
		}
		System.out.println("Empleado no v�lido");
		return false;
	}

	public boolean actualizarEmpleado(Empleado empleado) {
		if (validarEmpleado(empleado)) {
			return empleadoDAO.actualizarEmpleado(empleado);
		}
		System.out.println("Datos de empleado no v�lidos");
		return false;
	}

	public boolean eliminarEmpleado(int id) {
		if (id > 0) {
			return empleadoDAO.eliminarEmpleado(id);
		}
		System.out.println("ID de empleado no v�lido");
		return false;
	}

	public List<Empleado> listarEmpleados(String filtro, String orden) {
		return empleadoDAO.listarEmpleados(filtro, orden);
	}

	// M�todo para validaciones de datos
	private boolean validarEmpleado(Empleado empleado) {
	    // Validaci�n del nombre
	    if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
	        System.out.println("Nombre no puede estar vac�o.");
	        return false;
	    }
	    
	    // Validaci�n de los apellidos
	    if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
	        System.out.println("Apellidos no pueden estar vac�os.");
	        return false;
	    }
	    
	    // Validaci�n del correo electr�nico
	    if (empleado.getEmail() == null || !empleado.getEmail().contains("@")) {
	        System.out.println("Email inv�lido.");
	        return false;
	    }
	    
	    // Validaci�n del departamento
	    if (empleado.getDepartamento() == null || empleado.getDepartamento().isEmpty()) {
	        System.out.println("Departamento no puede estar vac�o.");
	        return false;
	    }
	    
	    // Validaci�n del n�mero de tel�fono (solo n�meros)
	    if (empleado.getTelefono() == null || !empleado.getTelefono().matches("\\d+")) {
	        System.out.println("El n�mero de tel�fono solo puede contener n�meros.");
	        return false;
	    }

	    // Si todas las validaciones son correctas
	    return true;
	}
}
