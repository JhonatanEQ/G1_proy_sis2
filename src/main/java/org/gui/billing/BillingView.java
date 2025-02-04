/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gui.billing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

public class BillingView extends JPanel {

    public BillingView() {
        initComponents();
    }

    private void initComponents() {

        // Establecer layout y tamaño del panel
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(740, 540));
        setBackground(new Color(249, 250, 251));

        // Datos del Vendedor
        JLabel lblDatosVendedor = new JLabel("Datos del Vendedor");
        lblDatosVendedor.setBounds(20, 20, 200, 20);
        add(lblDatosVendedor);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(20, 50, 200, 20);
        add(lblDireccion);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 80, 200, 20);
        add(lblTelefono);

        // Título centrado
        JLabel lblTitulo = new JLabel("FACTURA ELECTRONICA", SwingConstants.CENTER);
        lblTitulo.setBounds(220, 100, 260, 20);
        add(lblTitulo);

        // Datos del Cliente
        JLabel lblDatosCliente = new JLabel("Datos del Cliente");
        lblDatosCliente.setBounds(300, 130, 200, 20);
        add(lblDatosCliente);

        JLabel lblNombreCliente = new JLabel("Nombre/Razón Social:");
        lblNombreCliente.setBounds(20, 160, 200, 20);
        add(lblNombreCliente);

        JTextField txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(150, 160, 200, 20);
        add(txtNombreCliente);

        JLabel lblCINIT = new JLabel("CI/NIT:");
        lblCINIT.setBounds(20, 190, 200, 20);
        add(lblCINIT);

        JTextField txtCINIT = new JTextField();
        txtCINIT.setBounds(150, 190, 200, 20);
        add(txtCINIT);

        JLabel lblTelefonoCliente = new JLabel("Teléfono (opcional):");
        lblTelefonoCliente.setBounds(20, 220, 200, 20);
        add(lblTelefonoCliente);

        JTextField txtTelefonoCliente = new JTextField();
        txtTelefonoCliente.setBounds(150, 220, 200, 20);
        add(txtTelefonoCliente);

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setBounds(20, 250, 200, 20);
        add(lblCorreo);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 250, 200, 20);
        add(txtCorreo);

        // Fechas de Factura
        JLabel lblFechaFactura = new JLabel("Fecha Factura:");
        lblFechaFactura.setBounds(400, 160, 200, 20);
        add(lblFechaFactura);

        JTextField txtFechaFactura = new JTextField();
        txtFechaFactura.setBounds(520, 160, 150, 20);
        add(txtFechaFactura);

        JLabel lblFechaCaducidad = new JLabel("Fecha C. Factura:");
        lblFechaCaducidad.setBounds(400, 190, 200, 20);
        add(lblFechaCaducidad);

        JTextField txtFechaCaducidad = new JTextField();
        txtFechaCaducidad.setBounds(520, 190, 150, 20);
        add(txtFechaCaducidad);

        // Botón para guardar los datos
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(600, 20, 100, 30);

        // Cambiar el fondo a azul y el texto a blanco
        btnGuardar.setBackground(new Color(0, 0, 255)); // Fondo azul
        btnGuardar.setForeground(Color.WHITE); // Texto blanco

        // Añadir el botón al panel
        add(btnGuardar);


        // Detalle de Productos
        JLabel lblDetalleProductos = new JLabel("Detalle de Productos");
        lblDetalleProductos.setBounds(300, 290, 200, 20);
        lblDetalleProductos.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblDetalleProductos);

        // Crear la tabla para los detalles de los productos
        String[] columnNames = {"Producto", "Descripción", "Cantidad", "Precio Unitario", "Subtotal"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que la tabla no sea editable
                return false;
            }
        };
        JTable table = new JTable(model);

        // Añadir filas de ejemplo
        model.addRow(new Object[]{"Producto 1", "Descripción 1", 2, 50.00, 100.00});
        model.addRow(new Object[]{"Producto 2", "Descripción 2", 1, 30.00, 30.00});
        model.addRow(new Object[]{"Producto 3", "Descripción 3", 3, 20.00, 60.00});

        // Añadir fila para el total
        model.addRow(new Object[]{"", "", "", "Total a pagar:", 190.00});

        // Configurar la tabla
        table.setBounds(100, 320, 600, 200);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);

        // Personalizar la fila del total
        table.getColumnModel().getColumn(4).setCellRenderer(new TotalRenderer());

        // Añadir la tabla a un JScrollPane para que sea desplazable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 320, 600, 200);
        add(scrollPane);
    }

    // Renderizador personalizado para la fila del total
    static class TotalRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Verificar si es la fila del total
            if (row == table.getModel().getRowCount() - 1) {
                c.setFont(new Font("Arial", Font.BOLD, 12)); // Texto en negrita
                c.setBackground(new Color(230, 230, 230)); // Fondo gris claro
            } else {
                c.setBackground(Color.WHITE); // Fondo blanco para las demás filas
            }

            return c;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Factura Electrónica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BillingView());
        frame.pack();
        frame.setSize(740, 540);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}