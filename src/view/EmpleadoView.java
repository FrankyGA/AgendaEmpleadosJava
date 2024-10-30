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

	// Configuración panel de filtros
	private void configurarPanelFiltros() {
		// Obtiene el criterio de filtrado seleccionado
		filtroComboBox = new JComboBox<>(new String[] { "Departamento", "Puesto" });
		filtroTextField = new JTextField(15);
		JButton btnFiltrar = new JButton("Filtrar");

		// Acción para el botón de filtrar
		btnFiltrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtiene el criterio de filtrado seleccionado
				String criterio = filtroComboBox.getSelectedItem().toString();
				// Obtiene el valor ingresado
				String valor = filtroTextField.getText();
				// Crea cláusula WHERE con el criterio y el valor
				String whereClause = criterio.equals("Departamento") ? "departamento = '" + valor + "'"
						: "puesto = '" + valor + "'";
				// Carga empleados usando el filtro aplicado
				cargarEmpleados(whereClause);
			}
		});

		// Componentes del filtro
		JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelFiltros.add(new JLabel("Filtrar por:"));
		panelFiltros.add(filtroComboBox);
		panelFiltros.add(filtroTextField);
		panelFiltros.add(btnFiltrar);

		add(panelFiltros, BorderLayout.NORTH);
	}

	// ----------------- Panel de la tabla ----------------- //

	private void configurarTabla() {
		// Definir columnas de la tabla
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[] { "Nombre", "Apellidos", "Fecha Nacimiento", "Email", "Teléfono",
				"Dirección", "Ciudad", "Provincia", "Puesto", "Departamento" });
		tableEmpleados = new JTable(tableModel);
		tableEmpleados.setFillsViewportHeight(true);

		// Añadir márgenes a la tabla
		JScrollPane scrollPane = new JScrollPane(tableEmpleados);
		scrollPane.setBorder(new EmptyBorder(10, 15, 10, 15));

		add(scrollPane, BorderLayout.CENTER);
	}

	// ----------------- Panel de los botones ----------------- //

	private void configurarPanelBotones() {
		btnAgregar = new JButton("Agregar Empleado");
		btnActualizar = new JButton("Actualizar Empleado");
		btnEliminar = new JButton("Eliminar Empleado");

		JPanel panelBotones = new JPanel();
		panelBotones.add(btnAgregar);
		panelBotones.add(btnActualizar);
		panelBotones.add(btnEliminar);
		add(panelBotones, BorderLayout.SOUTH);

		// ----------------- Eventos de los botones ----------------- //

		// Acción para el botón de Agregar Empleado
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Abre el formulario para agregar un nuevo empleado
				new FormularioEmpleado(EmpleadoView.this, empleadoController, null, EmpleadoView.this::cargarEmpleados);
			}
		});

		// Acción para el botón de Actualizar Empleado
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtiene la fila seleccionada
				int selectedRow = tableEmpleados.getSelectedRow();
				if (selectedRow != -1) {
					// Recupera datos de la fila seleccionada para identificar al empleado
					String nombre = tableModel.getValueAt(selectedRow, 0).toString();
					String apellidos = tableModel.getValueAt(selectedRow, 1).toString();
					// Busca el empleado en la base de datos
					Empleado empleado = empleadoController
							.listarEmpleados("nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'", null)
							.get(0);
					// Abre el formulario con los datos del empleado para actualizarlo
					new FormularioEmpleado(EmpleadoView.this, empleadoController, empleado,
							EmpleadoView.this::cargarEmpleados);
				} else {
					// Muestra mensaje si no hay fila seleccionada
					JOptionPane.showMessageDialog(null, "Seleccione un empleado para actualizar.");
				}
			}
		});

		// Acción para el botón de Eliminar Empleado
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtiene la fila seleccionada
				int selectedRow = tableEmpleados.getSelectedRow();
				if (selectedRow != -1) {
					// Recupera los datos para identificar al empleado
					String nombre = tableModel.getValueAt(selectedRow, 0).toString();
					String apellidos = tableModel.getValueAt(selectedRow, 1).toString();
					// Confirma la eliminación
					int confirm = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de eliminar el empleado seleccionado?", "Confirmación",
							JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						// Obtiene el empleado y lo elimina
						Empleado empleado = empleadoController
								.listarEmpleados("nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'", null)
								.get(0);
						// Realiza la eliminación y muestra un mensaje de éxito o error
						if (empleadoController.eliminarEmpleado(empleado.getId())) {
							cargarEmpleados();
							JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar el empleado.");
						}
					}
				} else {
					// Muestra mensaje si no hay fila seleccionada
					JOptionPane.showMessageDialog(null, "Seleccione un empleado para eliminar.");
				}
			}
		});
	}

	// Carga la lista sin filtros
	private void cargarEmpleados() {
		cargarEmpleados(null);
	}

	// ----------------- Método de carga de empleados filtrados ----------------- //

	private void cargarEmpleados(String whereClause) {
		tableModel.setRowCount(0); // Limpiar la tabla
		// Metemos empleados filtrados en una lista
		List<Empleado> empleados = empleadoController.listarEmpleados(whereClause, null);
		// Cargamos los empleados en la tabla actualizada recorriendo la lista
		for (Empleado empleado : empleados) {
			// Añade filas a la tabla con los datos
			tableModel.addRow(new Object[] { empleado.getNombre(), empleado.getApellidos(),
					empleado.getFechaNacimiento(), empleado.getEmail(), empleado.getTelefono(), empleado.getDireccion(),
					empleado.getCiudad(), empleado.getProvincia(), empleado.getPuesto(), empleado.getDepartamento() });
		}
	}
}