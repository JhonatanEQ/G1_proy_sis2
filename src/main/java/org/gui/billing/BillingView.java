/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.gui.billing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BillingView extends javax.swing.JPanel {

    public BillingView() {
        initComponents();
        setupFactura();
    }

    private void setupFactura() {
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel superior para dividir en dos columnas
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(new Dimension(740, 100));

        // Panel izquierdo para PRUEBA, CASA MATRIZ, etc.
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel pruebaLabel = new JLabel("PRUEBA");
        pruebaLabel.setFont(new Font("Calibri", Font.BOLD, 20));

        JLabel casaMatrizLabel = new JLabel("CASA MATRIZ");
        casaMatrizLabel.setFont(new Font("Calibri", Font.BOLD, 16));

        JLabel direccionLabel = new JLabel("Calle Juan Pablo II #54");
        JLabel telefonoLabel = new JLabel("Teléfono: 4707070");
        JLabel ciudadLabel = new JLabel("Cochabamba");

        leftPanel.add(pruebaLabel);
        leftPanel.add(casaMatrizLabel);
        leftPanel.add(direccionLabel);
        leftPanel.add(telefonoLabel);
        leftPanel.add(ciudadLabel);

        // Panel derecho para NIT, FACTURA N°, etc.
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel nitLabel = new JLabel("NIT: 456489012");
        JLabel facturaLabel = new JLabel("FACTURA N°: 100");
        JLabel cufLabel = new JLabel("CUF: B2AFA11610013351564");
        

        rightPanel.add(nitLabel);
        rightPanel.add(facturaLabel);
        rightPanel.add(cufLabel);
        

        topPanel.add(leftPanel);
        topPanel.add(Box.createHorizontalGlue()); // Espacio entre los paneles
        topPanel.add(rightPanel);

        jPanel1.add(topPanel);

        // Separador
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        // Factura
        JLabel facturaTitleLabel = new JLabel("FACTURA");
        facturaTitleLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        facturaTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel derechoCreditoLabel = new JLabel("(Con Derecho a Crédito Fiscal)");
        derechoCreditoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        jPanel1.add(facturaTitleLabel);
        jPanel1.add(derechoCreditoLabel);

        // Separador
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        // Detalles de la factura
        JPanel detallesPanel = new JPanel();
        detallesPanel.setLayout(new GridLayout(2, 4, 5, 5));
        detallesPanel.setMaximumSize(new Dimension(700, 50));

        detallesPanel.add(new JLabel("Fecha:"));
        detallesPanel.add(new JLabel("26/07/2019 11:00 AM"));
        detallesPanel.add(new JLabel("NIT/CICEX:"));
        detallesPanel.add(new JLabel("1548971"));
        detallesPanel.add(new JLabel("Nombre/Razon Social:"));
        detallesPanel.add(new JLabel("Pablo Mamani"));
        detallesPanel.add(new JLabel(""));
        detallesPanel.add(new JLabel(""));

        jPanel1.add(detallesPanel);

        // Separador
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        // Tabla de productos
        String[] columnNames = {"CODIGO PRODUCTO / SERVICIO", "CANTIDAD", "DESCRIPCIÓN", "PRECIO UNITARIO", "DESCUENTO", "SUBTOTAL"};
        Object[][] data = {
            {"JN-131231", 10, "JUGO DE NARANJA EN VASO", "2,50", "0,00", "25,00"},
            {"", "", "", "TOTAL BS", "", "25,00"},
            {"", "", "", "IMPORTE BASE CREDITO FISCAL", "", "25,00"}
        };

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Calibri", Font.PLAIN, 10));
        table.setRowHeight(35);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setMaximumSize(new Dimension(700, 100));
        jPanel1.add(scrollPane);

        // Separador
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        // Nota legal
        JLabel notaLegalLabel = new JLabel("ESTA FACTURA CONTRIBUYE AL DESARROLLO DE NUESTRO PAÍS, EL USO ILÍCITO DE ÉSTA SERÁ SANCIONADO DE ACUERDO A LEY");
        notaLegalLabel.setFont(new Font("Calibri", Font.BOLD, 12));
        notaLegalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel1.add(notaLegalLabel);

        JLabel leyLabel = new JLabel("Ley N° 453: Tienes derecho a recibir información sobre las características y contenidos de los servicios que utilices.");
        leyLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
        leyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel1.add(leyLabel);
    }

    @SuppressWarnings("unchecked")    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
