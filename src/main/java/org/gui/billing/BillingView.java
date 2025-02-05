package org.gui.billing;

import com.lowagie.text.alignment.HorizontalAlignment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.InputStream;

import org.model.config.DatabaseConnection;
import java.sql.Connection;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.filechooser.FileNameExtensionFilter;


import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.type.ScaleImageEnum;

import org.model.InvoiceData;
import org.services.utils.Sale;
import org.services.utils.SalesDetail;
import org.model.SalesDetailModel;

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
    private JButton btnExport;
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
        
        btnExport = new JButton("Imprimir");
        btnExport.setBounds(600, 60, 110, 30);
        btnExport.setBackground(new Color(0, 100, 0));
        btnExport.setForeground(Color.WHITE);
        btnExport.addActionListener(e -> exportToJasperReport());
        add(btnExport);

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
    
    //Factura PDF
    private void exportToJasperReport() {
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "No hay datos para exportar",
                "Error de Exportación",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Crear lista de items
            List<InvoiceData> invoiceItems = new ArrayList<>();
            double subtotal = 0.0;
            for (int i = 0; i < model.getRowCount(); i++) {
                String producto = String.valueOf(model.getValueAt(i, 0));
                String descripcion = String.valueOf(model.getValueAt(i, 1));
                int cantidad = Integer.parseInt(String.valueOf(model.getValueAt(i, 2)));
                double precioUnitario = Double.parseDouble(String.valueOf(model.getValueAt(i, 3))
                    .replace("$", "").trim());
                double subtotalItem = Double.parseDouble(String.valueOf(model.getValueAt(i, 4))
                    .replace("$", "").trim());

                subtotal += subtotalItem;
                invoiceItems.add(new InvoiceData(
                    producto,
                    descripcion,
                    cantidad,
                    precioUnitario,
                    subtotalItem
                ));
            }

            // Calcular totales
            double iva = subtotal * 0.10;
            double descuento = subtotal * 0.05;
            double total = subtotal + iva - descuento;

            // Crear el diseño del reporte
            JasperDesign jasperDesign = new JasperDesign();
            jasperDesign.setName("Factura");
            jasperDesign.setPageWidth(595);
            jasperDesign.setPageHeight(842);
            jasperDesign.setColumnWidth(515);
            jasperDesign.setColumnSpacing(0);
            jasperDesign.setLeftMargin(40);
            jasperDesign.setRightMargin(40);
            jasperDesign.setTopMargin(50);
            jasperDesign.setBottomMargin(50);

            // Nombre:
            JRDesignParameter nombreClienteParam = new JRDesignParameter();
            nombreClienteParam.setName("nombreCliente");
            nombreClienteParam.setValueClass(String.class);
            jasperDesign.addParameter(nombreClienteParam);

            // Agregar:
            JRDesignParameter ciNitParam = new JRDesignParameter();
            ciNitParam.setName("ciNit");
            ciNitParam.setValueClass(String.class);
            jasperDesign.addParameter(ciNitParam);

            JRDesignParameter telefonoParam = new JRDesignParameter();
            telefonoParam.setName("telefono");
            telefonoParam.setValueClass(String.class);
            jasperDesign.addParameter(telefonoParam);

            JRDesignParameter correoParam = new JRDesignParameter();
            correoParam.setName("correo");
            correoParam.setValueClass(String.class);
            jasperDesign.addParameter(correoParam);

            JRDesignParameter fechaFacturaParam = new JRDesignParameter();
            fechaFacturaParam.setName("fechaFactura");
            fechaFacturaParam.setValueClass(String.class);
            jasperDesign.addParameter(fechaFacturaParam);

            JRDesignParameter fechaCaducidadParam = new JRDesignParameter();
            fechaCaducidadParam.setName("fechaCaducidad");
            fechaCaducidadParam.setValueClass(String.class);
            jasperDesign.addParameter(fechaCaducidadParam);

            // Definir campos necesarios
            JRDesignField productoField = new JRDesignField();
            productoField.setName("producto");
            productoField.setValueClass(String.class);
            jasperDesign.addField(productoField);

            JRDesignField descripcionField = new JRDesignField();
            descripcionField.setName("descripcion");
            descripcionField.setValueClass(String.class);
            jasperDesign.addField(descripcionField);

            JRDesignField cantidadField = new JRDesignField();
            cantidadField.setName("cantidad");
            cantidadField.setValueClass(Integer.class);
            jasperDesign.addField(cantidadField);

            JRDesignField precioField = new JRDesignField();
            precioField.setName("precioUnitario");
            precioField.setValueClass(Double.class);
            jasperDesign.addField(precioField);

            JRDesignField subtotalField = new JRDesignField();
            subtotalField.setName("subtotal");
            subtotalField.setValueClass(Double.class);
            jasperDesign.addField(subtotalField);

            // Crear banda de título
            JRDesignBand titleBand = new JRDesignBand();
            titleBand.setHeight(120);

            // Agregar logo de la empresa como imagen real
            JRDesignImage logoImage = new JRDesignImage(null);
            logoImage.setX(415);
            logoImage.setY(10);
            logoImage.setWidth(100);
            logoImage.setHeight(50);
            logoImage.setScaleImage(ScaleImageEnum.RETAIN_SHAPE);

            // Cargar la imagen del logo
            String logoPath = getClass().getResource("/org/images/logo.png").toString();
            JRDesignExpression imageExpression = new JRDesignExpression();
            imageExpression.setText("\"" + logoPath + "\"");
            logoImage.setExpression(imageExpression);
            titleBand.addElement(logoImage);

            // Agregar título
            JRDesignStaticText titleElement = new JRDesignStaticText();
            titleElement.setX(0);
            titleElement.setY(20);
            titleElement.setWidth(400);
            titleElement.setHeight(30);
            titleElement.setText("FACTURA ELECTRÓNICA");
            titleElement.setHorizontalTextAlign(net.sf.jasperreports.engine.type.HorizontalTextAlignEnum.CENTER);
            titleElement.setFontName("Arial");
            titleElement.setFontSize(20f);
            titleElement.setBold(true);
            titleBand.addElement(titleElement);
            
            
            JRDesignTextField clienteField = new JRDesignTextField();
            clienteField.setX(40);
            clienteField.setY(60);
            clienteField.setWidth(400);
            clienteField.setHeight(20);
            clienteField.setFontName("Arial");
            clienteField.setFontSize(10f);
            clienteField.setExpression(new JRDesignExpression("\"Cliente: \" + ($P{nombreCliente} != null ? $P{nombreCliente} : \"\")"));
            titleBand.addElement(clienteField);

            JRDesignTextField cinitField = new JRDesignTextField();
            cinitField.setX(40);
            cinitField.setY(80);
            cinitField.setWidth(200);
            cinitField.setHeight(20);
            cinitField.setFontName("Arial");
            cinitField.setFontSize(10f);
            cinitField.setExpression(new JRDesignExpression("\"CI/NIT: \" + ($P{ciNit} != null ? $P{ciNit} : \"\")"));
            titleBand.addElement(cinitField);

            JRDesignTextField telefonoField = new JRDesignTextField();
            telefonoField.setX(40);
            telefonoField.setY(100);
            telefonoField.setWidth(200);
            telefonoField.setHeight(20);
            telefonoField.setFontName("Arial");
            telefonoField.setFontSize(10f);
            telefonoField.setExpression(new JRDesignExpression("\"Teléfono: \" + ($P{telefono} != null ? $P{telefono} : \"\")"));
            titleBand.addElement(telefonoField);

            JRDesignTextField correoField = new JRDesignTextField();
            correoField.setX(250);
            correoField.setY(100);
            correoField.setWidth(200);
            correoField.setHeight(20);
            correoField.setFontName("Arial");
            correoField.setFontSize(10f);
            correoField.setExpression(new JRDesignExpression("\"Email: \" + ($P{correo} != null ? $P{correo} : \"\")"));
            titleBand.addElement(correoField);

            // Agregar fechas
            JRDesignTextField fechaFacturaField = new JRDesignTextField();
            fechaFacturaField.setX(250);
            fechaFacturaField.setY(60);
            fechaFacturaField.setWidth(150);
            fechaFacturaField.setHeight(20);
            fechaFacturaField.setFontName("Arial");
            fechaFacturaField.setFontSize(10f);
            fechaFacturaField.setExpression(new JRDesignExpression("\"Fecha: \" + ($P{fechaFactura} != null ? $P{fechaFactura} : \"\")"));
            titleBand.addElement(fechaFacturaField);

            JRDesignTextField fechaCaducidadField = new JRDesignTextField();
            fechaCaducidadField.setX(250);
            fechaCaducidadField.setY(80);
            fechaCaducidadField.setWidth(150);
            fechaCaducidadField.setHeight(20);
            fechaCaducidadField.setFontName("Arial");
            fechaCaducidadField.setFontSize(10f);
            fechaCaducidadField.setExpression(new JRDesignExpression("\"Vence: \" + ($P{fechaCaducidad} != null ? $P{fechaCaducidad} : \"\")"));
            titleBand.addElement(fechaCaducidadField);

            jasperDesign.setTitle(titleBand);

            // Crear banda de columnas con bordes
            JRDesignBand columnHeaderBand = new JRDesignBand();
            columnHeaderBand.setHeight(30);

            // Definir los encabezados de columna con bordes
            String[] headers = {"Producto", "Descripción", "Cantidad", "Precio Unit.", "Subtotal"};
            int[] widths = {100, 175, 80, 80, 80};
            int xPos = 0;

            for (int i = 0; i < headers.length; i++) {
                JRDesignStaticText header = new JRDesignStaticText();
                header.setX(xPos);
                header.setY(0);
                header.setWidth(widths[i]);
                header.setHeight(30);
                header.setText(headers[i]);
                header.setHorizontalTextAlign(net.sf.jasperreports.engine.type.HorizontalTextAlignEnum.CENTER);
                header.setVerticalTextAlign(net.sf.jasperreports.engine.type.VerticalTextAlignEnum.MIDDLE);
                header.setBold(true);
                header.setFontSize(12f);
                header.setFontName("Arial");
                // Agregar bordes a las celdas de encabezado
                header.setMode(net.sf.jasperreports.engine.type.ModeEnum.OPAQUE);
                header.setBackcolor(new java.awt.Color(240, 240, 240));
                header.getLineBox().getTopPen().setLineWidth(1f);
                header.getLineBox().getTopPen().setLineStyle(LineStyleEnum.SOLID);
                header.getLineBox().getLeftPen().setLineWidth(1f);
                header.getLineBox().getLeftPen().setLineStyle(LineStyleEnum.SOLID);
                header.getLineBox().getBottomPen().setLineWidth(1f);
                header.getLineBox().getBottomPen().setLineStyle(LineStyleEnum.SOLID);
                header.getLineBox().getRightPen().setLineWidth(1f);
                header.getLineBox().getRightPen().setLineStyle(LineStyleEnum.SOLID);
                columnHeaderBand.addElement(header);
                xPos += widths[i];
            }

            jasperDesign.setColumnHeader(columnHeaderBand);

            // Crear banda de detalle con bordes
            JRDesignBand detailBand = new JRDesignBand();
            detailBand.setHeight(20);

            xPos = 0;
            String[] fieldNames = {"producto", "descripcion", "cantidad", "precioUnitario", "subtotal"};

            for (int i = 0; i < fieldNames.length; i++) {
                JRDesignTextField field = new JRDesignTextField();
                field.setX(xPos);
                field.setY(0);
                field.setWidth(widths[i]);
                field.setHeight(20);
                field.setExpression(new JRDesignExpression("$F{" + fieldNames[i] + "}"));
                // Agregar bordes a las celdas de datos
                field.getLineBox().getTopPen().setLineWidth(1f);
                field.getLineBox().getTopPen().setLineStyle(LineStyleEnum.SOLID);
                field.getLineBox().getLeftPen().setLineWidth(1f);
                field.getLineBox().getLeftPen().setLineStyle(LineStyleEnum.SOLID);
                field.getLineBox().getBottomPen().setLineWidth(1f);
                field.getLineBox().getBottomPen().setLineStyle(LineStyleEnum.SOLID);
                field.getLineBox().getRightPen().setLineWidth(1f);
                field.getLineBox().getRightPen().setLineStyle(LineStyleEnum.SOLID);
                field.setFontName("Arial");
                field.setFontSize(10f);

                if (i >= 2) { // Para campos numéricos
                    field.setHorizontalTextAlign(net.sf.jasperreports.engine.type.HorizontalTextAlignEnum.RIGHT);
                    if (i >= 3) { // Para campos de precio
                        field.setPattern("$#,##0.00");
                    }
                } else {
                    field.setHorizontalTextAlign(net.sf.jasperreports.engine.type.HorizontalTextAlignEnum.LEFT);
                }

                detailBand.addElement(field);
                xPos += widths[i];
            }

            ((JRDesignSection) jasperDesign.getDetailSection()).addBand(detailBand);

            // Parámetros del reporte
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombreCliente", txtNombreCliente.getText());
            parameters.put("ciNit", txtCINIT.getText());
            parameters.put("telefono", txtTelefonoCliente.getText());
            parameters.put("correo", txtCorreo.getText());
            parameters.put("fechaFactura", txtFechaFactura.getText());
            parameters.put("fechaCaducidad", txtFechaCaducidad.getText());

            // Crear origen de datos y compilar
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceItems);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                parameters,
                dataSource
            );

            // Diálogo para guardar
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Factura PDF");
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
            fileChooser.setSelectedFile(new File("factura.pdf"));

            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().toLowerCase().endsWith(".pdf")) {
                    file = new File(file.getAbsolutePath() + ".pdf");
                }

                // Exportar a PDF
                JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());

                // Abrir el PDF
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }

                JOptionPane.showMessageDialog(this,
                    "PDF exportado exitosamente.",
                    "Exportación Completada",
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarPantalla();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al exportar el PDF: " + e.getMessage(),
                "Error de Exportación",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void limpiarPantalla() {
        // Limpiar campos de texto
        txtNombreCliente.setText("");
        txtCINIT.setText("");
        txtTelefonoCliente.setText("");
        txtCorreo.setText("");
        txtFechaFactura.setText("");
        txtFechaCaducidad.setText("");

        // Limpiar tabla
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        // Reiniciar currentSale
        currentSale = null;

        // Remover el panel de totales si existe
        for (Component comp : getComponents()) {
            if (comp instanceof JPanel && "totalsPanel".equals(comp.getName())) {
                remove(comp);
            }
        }

        // Actualizar la vista
        revalidate();
        repaint();
    }
}