package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EmpleadoController;
import model.Empleado;

public class FormularioEmpleado extends JDialog {
    private EmpleadoController empleadoController;
    private Empleado empleado;
    private JTextField txtNombre, txtApellidos, txtFechaNacimiento, txtEmail, txtTelefono, txtDireccion, txtCiudad, txtProvincia, txtPuesto, txtDepartamento;

    public FormularioEmpleado(Frame owner, EmpleadoController empleadoController, Empleado empleado, Runnable callback) {
        super(owner, true);
        this.empleadoController = empleadoController;
        this.empleado = empleado;

        setTitle(empleado == null ? "Agregar Empleado" : "Actualizar Empleado");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel con márgenes para los campos de entrada
        JPanel panelCampos = new JPanel(new GridLayout(10, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Márgenes

        // Campos de entrada
        txtNombre = new JTextField(empleado != null ? empleado.getNombre() : "");
        txtApellidos = new JTextField(empleado != null ? empleado.getApellidos() : "");
        txtFechaNacimiento = new JTextField(empleado != null ? new SimpleDateFormat("yyyy-MM-dd").format(empleado.getFechaNacimiento()) : "");
        txtEmail = new JTextField(empleado != null ? empleado.getEmail() : "");
        txtTelefono = new JTextField(empleado != null ? empleado.getTelefono() : "");
        txtDireccion = new JTextField(empleado != null ? empleado.getDireccion() : "");
        txtCiudad = new JTextField(empleado != null ? empleado.getCiudad() : "");
        txtProvincia = new JTextField(empleado != null ? empleado.getProvincia() : "");
        txtPuesto = new JTextField(empleado != null ? empleado.getPuesto() : "");
        txtDepartamento = new JTextField(empleado != null ? empleado.getDepartamento() : "");

        // Añadir componentes de campos
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Apellidos:"));
        panelCampos.add(txtApellidos);
        panelCampos.add(new JLabel("Fecha de Nacimiento (yyyy-MM-dd):"));
        panelCampos.add(txtFechaNacimiento);
        panelCampos.add(new JLabel("Email:"));
        panelCampos.add(txtEmail);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(txtTelefono);
        panelCampos.add(new JLabel("Dirección:"));
        panelCampos.add(txtDireccion);
        panelCampos.add(new JLabel("Ciudad:"));
        panelCampos.add(txtCiudad);
        panelCampos.add(new JLabel("Provincia:"));
        panelCampos.add(txtProvincia);
        panelCampos.add(new JLabel("Puesto:"));
        panelCampos.add(txtPuesto);
        panelCampos.add(new JLabel("Departamento:"));
        panelCampos.add(txtDepartamento);

        // Botón guardar centrado en un panel inferior
        JButton btnGuardar = new JButton("Guardar");
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnGuardar);

        // Acción para el botón guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEmpleado();
                callback.run();  // Actualiza la tabla en la vista principal
            }
        });

        add(panelCampos, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    private void guardarEmpleado() {
        if (empleado == null) {
            empleado = new Empleado();
        }

        empleado.setNombre(txtNombre.getText());
        empleado.setApellidos(txtApellidos.getText());
        empleado.setEmail(txtEmail.getText());
        empleado.setTelefono(txtTelefono.getText());
        empleado.setDireccion(txtDireccion.getText());
        empleado.setCiudad(txtCiudad.getText());
        empleado.setProvincia(txtProvincia.getText());
        empleado.setPuesto(txtPuesto.getText());
        empleado.setDepartamento(txtDepartamento.getText());

        try {
            empleado.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(txtFechaNacimiento.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fecha de nacimiento inválida.");
            return;
        }

        if (empleado.getId() == 0) {
            empleadoController.agregarEmpleado(empleado);
        } else {
            empleadoController.actualizarEmpleado(empleado);
        }

        dispose();
    }
}
