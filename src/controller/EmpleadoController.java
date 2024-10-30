package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.Empleado;
import model.EmpleadoDAO;
import model.EmpleadoValidator;

public class EmpleadoController {
    private EmpleadoDAO empleadoDAO;

    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }

    public boolean agregarEmpleado(Empleado empleado) {
        List<String> errores = EmpleadoValidator.validarEmpleado(empleado);
        
        if (errores.isEmpty()) {
            boolean resultado = empleadoDAO.agregarEmpleado(empleado);
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el empleado.");
            }
        } else {
            mostrarErrores(errores);
        }
        return false;
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        List<String> errores = EmpleadoValidator.validarEmpleado(empleado);
        
        if (errores.isEmpty()) {
            boolean resultado = empleadoDAO.actualizarEmpleado(empleado);
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el empleado.");
            }
        } else {
            mostrarErrores(errores);
        }
        return false;
    }

    public boolean eliminarEmpleado(int id) {
        if (id > 0) {
            if (empleadoDAO.eliminarEmpleado(id)) {
                JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el empleado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ID de empleado no válido");
        }
        return false;
    }

    public List<Empleado> listarEmpleados(String filtro, String orden) {
        return empleadoDAO.listarEmpleados(filtro, orden);
    }

    // Método para mostrar errores en una ventana emergente
    private void mostrarErrores(List<String> errores) {
        StringBuilder mensaje = new StringBuilder("Errores encontrados:\n");
        for (String error : errores) {
            mensaje.append("- ").append(error).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Errores de Validación", JOptionPane.ERROR_MESSAGE);
    }
}
