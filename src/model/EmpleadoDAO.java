package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private ConexionBD conexionBD;

    public EmpleadoDAO() {
        conexionBD = new ConexionBD();
    }

    // Método para agregar un empleado
    public boolean agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados(nombre, apellidos, fecha_nacimiento, email, telefono, direccion, ciudad, provincia, puesto, departamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexion = null;
        try {
            conexion = conexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setDate(3, new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            ps.setString(4, empleado.getEmail());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getDireccion());
            ps.setString(7, empleado.getCiudad());
            ps.setString(8, empleado.getProvincia());
            ps.setString(9, empleado.getPuesto());
            ps.setString(10, empleado.getDepartamento());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar empleado: " + e.getMessage());
            return false;
        } finally {
            conexionBD.closeConnection(conexion);
        }
    }

    // Método para actualizar un empleado
    public boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre=?, apellidos=?, fecha_nacimiento=?, email=?, telefono=?, direccion=?, ciudad=?, provincia=?, puesto=?, departamento=? WHERE id=?";
        Connection conexion = null;
        try {
            conexion = conexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setDate(3, new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            ps.setString(4, empleado.getEmail());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getDireccion());
            ps.setString(7, empleado.getCiudad());
            ps.setString(8, empleado.getProvincia());
            ps.setString(9, empleado.getPuesto());
            ps.setString(10, empleado.getDepartamento());
            ps.setInt(11, empleado.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        } finally {
            conexionBD.closeConnection(conexion);
        }
    }

    // Método para eliminar un empleado
    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleados WHERE id=?";
        Connection conexion = null;
        try {
            conexion = conexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        } finally {
            conexionBD.closeConnection(conexion);
        }
    }

    // Método para listar empleados con filtros y orden opcionales
    public List<Empleado> listarEmpleados(String filtro, String orden) {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        if (filtro != null) {
            sql += " WHERE " + filtro;
        }
        if (orden != null) {
            sql += " ORDER BY " + orden;
        }

        Connection conexion = null;
        try {
            conexion = conexionBD.getConnection();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidos(rs.getString("apellidos"));
                empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                empleado.setEmail(rs.getString("email"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setCiudad(rs.getString("ciudad"));
                empleado.setProvincia(rs.getString("provincia"));
                empleado.setPuesto(rs.getString("puesto"));
                empleado.setDepartamento(rs.getString("departamento"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        } finally {
            conexionBD.closeConnection(conexion);
        }
        return empleados;
    }
}