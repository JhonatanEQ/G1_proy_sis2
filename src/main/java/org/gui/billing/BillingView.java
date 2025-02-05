package org.gui.billing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import org.services.utils.Sale;
import org.services.utils.SalesDetail;
import org.model.SalesDetailModel;
import org.model.config.DatabaseConnection;
import java.sql.Connection;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class BillingView extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private Sale currentSale;
    private DatabaseConnection dbConnection;
    private JTextField txtNombreCliente;
    private JTextField txtCINIT;
    private JTextField txtTelefonoCliente;
    private JTextField txtCorreo;
    private JTextField txtFechaFactura;
    private JTextField txtFechaCaducidad;
    private JButton btnPreview;
    private JScrollPane scrollPane;

    public BillingView() {
        dbConnection = new DatabaseConnection();
        initComponents();
    }

    public void setSaleData(Sale sale) {
        this.currentSale = sale;
        if (sale != null) {
            updateBillingWithSaleData();
        }
    }

    private void initComponents() {
        // Establecer layout y tamaño del panel
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(740, 570));
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
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo);

        // Datos del Cliente
        JLabel lblDatosCliente = new JLabel("Datos del Cliente");
        lblDatosCliente.setBounds(300, 130, 200, 20);
        lblDatosCliente.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblDatosCliente);

        // Inicializar campos de texto
        txtNombreCliente = new JTextField();
        txtCINIT = new JTextField();
        txtTelefonoCliente = new JTextField();
        txtCorreo = new JTextField();
        txtFechaFactura = new JTextField();
        txtFechaCaducidad = new JTextField();

        // Cliente fields
        JLabel lblNombreCliente = new JLabel("Nombre/Razón Social:");
        lblNombreCliente.setBounds(20, 160, 200, 20);
        add(lblNombreCliente);
        txtNombreCliente.setBounds(150, 160, 200, 20);
        add(txtNombreCliente);

        JLabel lblCINIT = new JLabel("CI/NIT:");
        lblCINIT.setBounds(20, 190, 200, 20);
        add(lblCINIT);
        txtCINIT.setBounds(150, 190, 200, 20);
        add(txtCINIT);

        JLabel lblTelefonoCliente = new JLabel("Teléfono (opcional):");
        lblTelefonoCliente.setBounds(20, 220, 200, 20);
        add(lblTelefonoCliente);
        txtTelefonoCliente.setBounds(150, 220, 200, 20);
        add(txtTelefonoCliente);

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setBounds(20, 250, 200, 20);
        add(lblCorreo);
        txtCorreo.setBounds(150, 250, 200, 20);
        add(txtCorreo);

        // Fechas de Factura
        JLabel lblFechaFactura = new JLabel("Fecha Factura:");
        lblFechaFactura.setBounds(400, 160, 200, 20);
        add(lblFechaFactura);
        txtFechaFactura.setBounds(520, 160, 150, 20);
        txtFechaFactura.setEditable(false);
        add(txtFechaFactura);

        JLabel lblFechaCaducidad = new JLabel("Fecha C. Factura:");
        lblFechaCaducidad.setBounds(400, 190, 200, 20);
        add(lblFechaCaducidad);
        txtFechaCaducidad.setBounds(520, 190, 150, 20);
        txtFechaCaducidad.setEditable(false);
        add(txtFechaCaducidad);

        // Botón para previsualizar
        btnPreview = new JButton("Previsualizar");
        btnPreview.setBounds(600, 20, 110, 30);
        btnPreview.setBackground(new Color(0, 0, 255));
        btnPreview.setForeground(Color.WHITE);
        btnPreview.addActionListener(e -> previsualizarFactura());
        add(btnPreview);

        // Detalle de Productos
        JLabel lblDetalleProductos = new JLabel("Detalle de Productos");
        lblDetalleProductos.setBounds(300, 290, 200, 20);
        lblDetalleProductos.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblDetalleProductos);

        setupProductTable();
    }

    private void setupProductTable() {
        String[] columnNames = {"Producto", "Descripción", "Cantidad", "Precio Unitario", "Subtotal"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        
        // Deshabilitar el redimensionamiento de columnas
        table.getTableHeader().setResizingAllowed(false);
        
        // Evitar que las columnas sean movidas
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 320, 700, 120);
        add(scrollPane);
    }

    private void updateBillingWithSaleData() {
        if (currentSale == null) return;

        try {
            // Actualizar fechas
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            txtFechaFactura.setText(dateFormat.format(currentSale.getDate()));
            
            // Fecha de caducidad (30 días después)
            Date expirationDate = new Date(currentSale.getDate().getTime() + (30L * 24 * 60 * 60 * 1000));
            txtFechaCaducidad.setText(dateFormat.format(expirationDate));

            // Limpiar tabla
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Cargar detalles de la venta
            Connection conn = dbConnection.getConnection();
            List<SalesDetail> details = SalesDetailModel.getByIdSale(conn, currentSale.getIdSale());

            // Agregar detalles de productos
            for (SalesDetail detail : details) {
                Object[] row = new Object[]{
                    String.valueOf(detail.getIdProduct()),
                    detail.getProductName(),
                    detail.getQuantity(),
                    String.format("$%.2f", detail.getUnitPrice()),
                    String.format("$%.2f", detail.getQuantity() * detail.getUnitPrice())
                };
                model.addRow(row);
            }

            // Crear panel para los totales
            JPanel totalsPanel = new JPanel();
            totalsPanel.setLayout(new GridLayout(4, 2, 10, 5));
            totalsPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
            totalsPanel.setBackground(new Color(249, 250, 251));

            // Estilo para las etiquetas
            Font boldFont = new Font("Arial", Font.BOLD, 12);
            Font normalFont = new Font("Arial", Font.PLAIN, 12);

            // Agregar labels y valores para los totales
            JLabel subtotalLabel = new JLabel("Subtotal:");
            subtotalLabel.setFont(normalFont);
            JLabel subtotalValue = new JLabel(String.format("$%.2f", currentSale.getSubtotal()));
            subtotalValue.setFont(normalFont);
            subtotalValue.setHorizontalAlignment(SwingConstants.RIGHT);

            JLabel ivaLabel = new JLabel("IVA (10%):");
            ivaLabel.setFont(normalFont);
            JLabel ivaValue = new JLabel(String.format("$%.2f", currentSale.getTax()));
            ivaValue.setFont(normalFont);
            ivaValue.setHorizontalAlignment(SwingConstants.RIGHT);

            JLabel descuentoLabel = new JLabel(String.format("Descuento (%.0f%%):", currentSale.getDiscountPercentage()));
            descuentoLabel.setFont(normalFont);
            JLabel descuentoValue = new JLabel(String.format("$%.2f", currentSale.getDiscount()));
            descuentoValue.setFont(normalFont);
            descuentoValue.setHorizontalAlignment(SwingConstants.RIGHT);

            JLabel totalLabel = new JLabel("Total:");
            totalLabel.setFont(boldFont);
            JLabel totalValue = new JLabel(String.format("$%.2f", currentSale.getTotal()));
            totalValue.setFont(boldFont);
            totalValue.setHorizontalAlignment(SwingConstants.RIGHT);

            // Agregar componentes al panel de totales
            totalsPanel.add(subtotalLabel);
            totalsPanel.add(subtotalValue);
            totalsPanel.add(ivaLabel);
            totalsPanel.add(ivaValue);
            totalsPanel.add(descuentoLabel);
            totalsPanel.add(descuentoValue);
            totalsPanel.add(totalLabel);
            totalsPanel.add(totalValue);

            // Remover el panel anterior de totales si existe
            for (Component comp : getComponents()) {
                if (comp instanceof JPanel && "totalsPanel".equals(comp.getName())) {
                    remove(comp);
                }
            }

            // Configurar y agregar el nuevo panel de totales
            totalsPanel.setName("totalsPanel");
            totalsPanel.setBounds(20, 450, 700, 80);
            add(totalsPanel);

            // Actualizar la vista
            revalidate();
            repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar los detalles de la venta: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void previsualizarFactura() {
        // Validar que se haya seleccionado una venta
        if (currentSale == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor, seleccione una venta para previsualizar",
                "Error de Previsualización",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear un diálogo de previsualización
        JDialog previewDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
            "Previsualización de Factura", true);
        previewDialog.setLayout(new BorderLayout());

        // Panel para los detalles de la factura
        JPanel detallesPanel = new JPanel();
        detallesPanel.setLayout(new BoxLayout(detallesPanel, BoxLayout.Y_AXIS));
        detallesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Agregar detalles del cliente
        detallesPanel.add(new JLabel("Nombre/Razón Social: " + txtNombreCliente.getText()));
        detallesPanel.add(new JLabel("CI/NIT: " + txtCINIT.getText()));
        detallesPanel.add(new JLabel("Teléfono: " + txtTelefonoCliente.getText()));
        detallesPanel.add(new JLabel("Correo: " + txtCorreo.getText()));
        detallesPanel.add(new JLabel("Fecha de Factura: " + txtFechaFactura.getText()));
        detallesPanel.add(new JLabel("Fecha de Caducidad: " + txtFechaCaducidad.getText()));
        detallesPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Crear tabla de previsualización
        String[] columnNames = {"Producto", "Descripción", "Cantidad", "Precio Unitario", "Subtotal"};
        DefaultTableModel previewModel = new DefaultTableModel(columnNames, 0);
        
        // Copiar datos de la tabla actual
        for (int i = 0; i < model.getRowCount(); i++) {
            Vector<Object> rowData = new Vector<>();
            for (int j = 0; j < model.getColumnCount(); j++) {
                rowData.add(model.getValueAt(i, j));
            }
            previewModel.addRow(rowData);
        }

        JTable previewTable = new JTable(previewModel);
        JScrollPane scrollPane = new JScrollPane(previewTable);

        // Panel para totales en la previsualización
        JPanel totalsPanelPreview = new JPanel(new GridLayout(4, 2, 10, 5));
        totalsPanelPreview.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        
        // Agregar los totales
        totalsPanelPreview.add(new JLabel("Subtotal:"));
        totalsPanelPreview.add(new JLabel(String.format("$%.2f", currentSale.getSubtotal())));
        
        totalsPanelPreview.add(new JLabel("IVA (10%):"));
        totalsPanelPreview.add(new JLabel(String.format("$%.2f", currentSale.getTax())));
        
        totalsPanelPreview.add(new JLabel(String.format("Descuento (%.0f%%):", currentSale.getDiscountPercentage())));
        totalsPanelPreview.add(new JLabel(String.format("$%.2f", currentSale.getDiscount())));
        
        totalsPanelPreview.add(new JLabel("Total:"));
        totalsPanelPreview.add(new JLabel(String.format("$%.2f", currentSale.getTotal())));

        // Panel para botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Botón de Imprimir (sin funcionalidad real)
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.addActionListener(e -> {
            JOptionPane.showMessageDialog(previewDialog, 
                "Funcionalidad de impresión no implementada.", 
                "Imprimir", 
                JOptionPane.INFORMATION_MESSAGE);
        });

        // Botón de Cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> previewDialog.dispose());

        // Agregar botones al panel
        buttonPanel.add(btnImprimir);
        buttonPanel.add(btnCerrar);

        // Agregar componentes al diálogo
        previewDialog.add(new JScrollPane(detallesPanel), BorderLayout.NORTH);
        previewDialog.add(scrollPane, BorderLayout.CENTER);
        previewDialog.add(totalsPanelPreview, BorderLayout.AFTER_LAST_LINE);
        previewDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Configurar tamaño y mostrar
        previewDialog.setSize(700, 600);
        previewDialog.setLocationRelativeTo(this);
        previewDialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Factura Electrónica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BillingView());
        frame.pack();
        frame.setSize(740, 570);  // Aumentado el alto para acomodar los totales
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}