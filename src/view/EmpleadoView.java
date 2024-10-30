package view;

import controller.EmpleadoController;
import model.Empleado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpleadoView extends JFrame {
    private EmpleadoController empleadoController;
    private JTable tableEmpleados;
    private DefaultTableModel tableModel;
    private JButton btnAgregar, btnActualizar, btnEliminar;
    private JComboBox<String> filtroComboBox;
    private JTextField filtroTextField;

    public EmpleadoView() {
        empleadoController = new EmpleadoController();
        
        setTitle("Agenda de Empleados");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de filtros
        configurarPanelFiltros();

        // Configuración de la tabla de empleados
        configurarTabla();

        // Panel de botones
        configurarPanelBotones();

        // Cargar datos iniciales de la base de datos
        cargarEmpleados();

        setVisible(true);
    }

    private void configurarPanelFiltros() {
        // Filtro para departamento o puesto
        filtroComboBox = new JComboBox<>(new String[] {"Departamento", "Puesto"});
        filtroTextField = new JTextField(15);
        JButton btnFiltrar = new JButton("Filtrar");

        // Acción para el botón de filtrar
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String criterio = filtroComboBox.getSelectedItem().toString();
                String valor = filtroTextField.getText();
                String whereClause = criterio.equals("Departamento") ? "departamento = '" + valor + "'" : "puesto = '" + valor + "'";
                cargarEmpleados(whereClause);
            }
        });

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.add(new JLabel("Filtrar por:"));
        panelFiltros.add(filtroComboBox);
        panelFiltros.add(filtroTextField);
        panelFiltros.add(btnFiltrar);

        add(panelFiltros, BorderLayout.NORTH);
    }

    private void configurarTabla() {
        // Definir columnas de la tabla
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {"Nombre", "Apellidos", "Fecha Nacimiento", "Email", "Teléfono", "Dirección", "Ciudad", "Provincia", "Puesto", "Departamento"});
        tableEmpleados = new JTable(tableModel);
        tableEmpleados.setFillsViewportHeight(true);

        // Añadir márgenes a la tabla
        JScrollPane scrollPane = new JScrollPane(tableEmpleados);
        scrollPane.setBorder(new EmptyBorder(10, 15, 10, 15));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void configurarPanelBotones() {
        btnAgregar = new JButton("Agregar Empleado");
        btnActualizar = new JButton("Actualizar Empleado");
        btnEliminar = new JButton("Eliminar Empleado");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormularioEmpleado(EmpleadoView.this, empleadoController, null, EmpleadoView.this::cargarEmpleados);
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableEmpleados.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el empleado desde el controlador con la clave "nombre" y "apellidos"
                    String nombre = tableModel.getValueAt(selectedRow, 0).toString();
                    String apellidos = tableModel.getValueAt(selectedRow, 1).toString();
                    Empleado empleado = empleadoController.listarEmpleados("nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'", null).get(0);
                    new FormularioEmpleado(EmpleadoView.this, empleadoController, empleado, EmpleadoView.this::cargarEmpleados);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un empleado para actualizar.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableEmpleados.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el empleado desde el controlador con la clave "nombre" y "apellidos"
                    String nombre = tableModel.getValueAt(selectedRow, 0).toString();
                    String apellidos = tableModel.getValueAt(selectedRow, 1).toString();
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el empleado seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Empleado empleado = empleadoController.listarEmpleados("nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'", null).get(0);
                        if (empleadoController.eliminarEmpleado(empleado.getId())) {
                            cargarEmpleados();
                            JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un empleado para eliminar.");
                }
            }
        });
    }

    private void cargarEmpleados() {
        cargarEmpleados(null);
    }

    private void cargarEmpleados(String whereClause) {
        tableModel.setRowCount(0);  // Limpiar la tabla
        List<Empleado> empleados = empleadoController.listarEmpleados(whereClause, null);
        for (Empleado empleado : empleados) {
            tableModel.addRow(new Object[] {
                empleado.getNombre(),
                empleado.getApellidos(),
                empleado.getFechaNacimiento(),
                empleado.getEmail(),
                empleado.getTelefono(),
                empleado.getDireccion(),
                empleado.getCiudad(),
                empleado.getProvincia(),
                empleado.getPuesto(),
                empleado.getDepartamento()
            });
        }
    }
}