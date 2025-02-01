/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.gui.inventory;

/**
 *
 * @author Vidal zenzano jonas :v
 */
import org.services.product.ProductService;
import org.services.utils.Product;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InventoryView extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ProductService productService;
        
    

    /**
     * Creates new form Inventory
     */
    public InventoryView() {
        initComponents();
        tableModel = (DefaultTableModel) jTable1.getModel();
        deshabilitarEdicionTabla();
        productService = new ProductService();
        cargarDatosDesdeBD();
}
// Nueva función para deshabilitar edición
private void deshabilitarEdicionTabla(){
    jTable1.setDefaultEditor(Object.class, null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        txt_modificar = new javax.swing.JButton();
        txt_eliminar1 = new javax.swing.JButton();
        j_buscador = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        txt_filter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 730, -1));

        txt_modificar.setBackground(new java.awt.Color(135, 206, 235));
        txt_modificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_modificar.setText("Modificar");
        txt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_modificarActionPerformed(evt);
            }
        });
        add(txt_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 120, 30));

        txt_eliminar1.setBackground(new java.awt.Color(135, 206, 235));
        txt_eliminar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_eliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/eliminar .png"))); // NOI18N
        txt_eliminar1.setText("Eliminar");
        txt_eliminar1.setMaximumSize(new java.awt.Dimension(95, 27));
        txt_eliminar1.setMinimumSize(new java.awt.Dimension(95, 27));
        txt_eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_eliminar1ActionPerformed(evt);
            }
        });
        add(txt_eliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 120, 30));

        j_buscador.setBackground(new java.awt.Color(255, 255, 255));

        txt_buscar.setText("Buscar inventario");
        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });

        txt_filter.setBackground(new java.awt.Color(135, 206, 235));
        txt_filter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_filter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/filtrar (1).png"))); // NOI18N
        txt_filter.setText("Filter");
        txt_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout j_buscadorLayout = new javax.swing.GroupLayout(j_buscador);
        j_buscador.setLayout(j_buscadorLayout);
        j_buscadorLayout.setHorizontalGroup(
            j_buscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(j_buscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        j_buscadorLayout.setVerticalGroup(
            j_buscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(j_buscadorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(j_buscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        add(j_buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 670, 80));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Inventario");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", "", "", null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Codigo", "Nombre", "Precio", "Categoria", "Stock Actual", "Stock Minimo", "Fecha de entrada", "Proveedor", "Estado"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 670, 290));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this,"Funcion de busqueda no implementada.");
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_filterActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this,"Funcion de filtro no implementada.");
    }//GEN-LAST:event_txt_filterActionPerformed

    private void txt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_modificarActionPerformed
        // TODO add your handling code here:                                              
     int selectedRow = jTable1.getSelectedRow();
     if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una fila para modificar.");
        return;
    }
    
    // Obtener los datos de la fila seleccionada
    int productId = (int) jTable1.getValueAt(selectedRow, 0); // Suponiendo que el ID está en la primera columna
    String codigo = String.valueOf(jTable1.getValueAt(selectedRow, 1));
    String nombre = String.valueOf(jTable1.getValueAt(selectedRow, 2));
    double precio = Double.parseDouble(String.valueOf(jTable1.getValueAt(selectedRow, 3)));
    int stockActual = Integer.parseInt(String.valueOf(jTable1.getValueAt(selectedRow, 5)));
    int stockMinimo = Integer.parseInt(String.valueOf(jTable1.getValueAt(selectedRow, 6)));

    // Mostrar cuadro de diálogo para editar
    JTextField txtCodigo = new JTextField(codigo);
    txtCodigo.setEditable(false);
    JTextField txtNombre = new JTextField(nombre);
    txtNombre.setEditable(false);
    JTextField txtPrecio = new JTextField(String.valueOf(precio));
    JTextField txtStock = new JTextField(String.valueOf(stockActual));

    Object[] message = {
        "Código:", txtCodigo,
        "Nombre:", txtNombre,
        "Precio:", txtPrecio,
        "Stock Actual:", txtStock
    };

         int option = JOptionPane.showConfirmDialog(this, message, "Modificar Producto", JOptionPane.OK_CANCEL_OPTION);
         if (option == JOptionPane.OK_OPTION) {
          try {
            // Validar datos numéricos
            double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
            int nuevoStock = Integer.parseInt(txtStock.getText());

            // Obtener fecha y hora actual del sistema
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaHoraActual = sdf.format(new Date());

            // Crear objeto Producto con los nuevos valores
            Product updatedProduct = new Product();
            updatedProduct.setId(productId);
            updatedProduct.setCode(codigo);
            updatedProduct.setName(nombre);
            updatedProduct.setUnitPrice(nuevoPrecio);
            updatedProduct.setCurrentStock(nuevoStock);
            updatedProduct.setEntryDate(fechaHoraActual);

            // Llamar al servicio para actualizar en la BD
            ProductService productService = new ProductService();
            boolean success = productService.updateProductStockAndDate(updatedProduct);

            if (success) {
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");

                // Actualizar la tabla visualmente
                jTable1.setValueAt(nuevoPrecio, selectedRow, 3);
                jTable1.setValueAt(nuevoStock, selectedRow, 5);
                jTable1.setValueAt(fechaHoraActual, selectedRow, 7);

                // Actualizar el estado basado en el stock
                String nuevoEstado = nuevoStock <= stockMinimo ? "Bajo stock" : "En stock";
                jTable1.setValueAt(nuevoEstado, selectedRow, 9);
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: Verifique que el precio y stock sean números válidos", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    }//GEN-LAST:event_txt_modificarActionPerformed

    private void txt_eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_eliminar1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
     if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        return;
    }
    
    // Obtener el ID del producto (asumiendo que el ID está en la primera columna)
    int productId = (int) jTable1.getValueAt(selectedRow, 0);

    // Confirmar eliminación
    int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        ProductService productService = new ProductService();
        boolean success = productService.deleteProduct(productId);

         if (success) {
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
            tableModel.removeRow(selectedRow); // Elimina de la tabla visualmente
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_txt_eliminar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel j_buscador;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JButton txt_eliminar1;
    private javax.swing.JButton txt_filter;
    private javax.swing.JButton txt_modificar;
    // End of variables declaration//GEN-END:variables

private void cargarDatosDesdeBD() {
    try {
        List<Product> productos = productService.getAllProducts();
        tableModel.setRowCount(0); // Limpiar la tabla
        
        for (Product producto : productos) {
            tableModel.addRow(new Object[]{
                producto.getId(),
                producto.getCode(),
                producto.getName(),
                producto.getUnitPrice(),
                producto.getCategoryId(),
                producto.getCurrentStock(),
                producto.getMinimumStock(),
                producto.getEntryDate(),
                producto.getSupplierId(),
                (producto.getCurrentStock() <= producto.getMinimumStock()) ? "Bajo stock" : "En stock"
            });

            // Mostrar alerta si el stock está bajo
            if (producto.getCurrentStock() <= producto.getMinimumStock()) {
                JOptionPane.showMessageDialog(this, 
                    "¡Alerta! El producto " + producto.getName() + " tiene stock bajo.", 
                    "Alerta de Stock", JOptionPane.WARNING_MESSAGE);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}    

