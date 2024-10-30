package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_empleados";
    private static final String USER = "userAgendaEmpleados";
    private static final String PASSWORD = "userAgendaEmpleados";

    // M�todo para establecer la conexi�n
    public Connection getConnection() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexi�n exitosa.");
        } catch (SQLException e) {
            System.out.println("Error en la conexi�n a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    // M�todo para cerrar la conexi�n
    public void closeConnection(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexi�n cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexi�n: " + e.getMessage());
        }
    }
}
