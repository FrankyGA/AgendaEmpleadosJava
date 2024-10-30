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

	private boolean validarEmpleado(Empleado empleado) {
		// Validaci�n b�sica
		if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
			System.out.println("Nombre no puede estar vac�o.");
			return false;
		}
		if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
			System.out.println("Apellidos no pueden estar vac�os.");
			return false;
		}
		if (empleado.getEmail() == null || !empleado.getEmail().contains("@")) {
			System.out.println("Email inv�lido.");
			return false;
		}
		if (empleado.getDepartamento() == null || empleado.getDepartamento().isEmpty()) {
			System.out.println("Departamento no pueden estar vac�o.");
			return false;
		}
		return true;
	}
}
