/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.gui.product;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import org.services.product.ProductService;
import org.services.product.CategoryService;
import org.services.supplier.SupplierService;
import org.services.utils.Category;
import org.services.utils.Product;
import org.services.utils.Supplier;

/**
 *
 * @author Encin
 */
public class ProductView extends javax.swing.JPanel {
    
    
    private String gURLImage;
    private ProductService gProductService;
    private CategoryService gCategoryService;
    private List<Category> gCategories;
    private List<Supplier> gSuppliers;
    private SupplierService gSupplierService;

    /**
     * Creates new form Product
     */
    public ProductView() {
        initComponents();
        gProductService = new ProductService();
        gCategoryService = new CategoryService();
        gSupplierService = new SupplierService();
        cargarCategorias();
        jtAddProveedor.setVisible(false);
    }
    private void cargarCategorias() {
        try {
            gCategories= gCategoryService.getAllCategories();
            List<String> categoryNames = new ArrayList<>();
        
            for (Category category : gCategories) {
                categoryNames.add(category.toString());
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(categoryNames.toArray(new String[0]));
            jcCategoria.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las categorías: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarProveedores() {
        try {
            gSuppliers = gSupplierService.getAllSuppliers();
            List<String> supplierNames = new ArrayList<>();

            // Agregar los proveedores existentes
            for (Supplier supplier : gSuppliers) {
                supplierNames.add(supplier.getCompanyName());
            }

            // Agregar la opción para añadir nuevo proveedor
            supplierNames.add("Agregar nuevo proveedor");

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(supplierNames.toArray(new String[0]));
            jcSelectProveedor.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar los proveedores: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpProduct = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlCodeP = new javax.swing.JLabel();
        jtCodeP = new javax.swing.JTextField();
        jlNombreP = new javax.swing.JLabel();
        jtNombreP = new javax.swing.JTextField();
        jlFechaE = new javax.swing.JLabel();
        jdFechaE = new com.toedter.calendar.JDateChooser();
        jlPrecioU = new javax.swing.JLabel();
        jtPrecioU = new javax.swing.JTextField();
        jlCategoria = new javax.swing.JLabel();
        jlCantidad = new javax.swing.JLabel();
        jtCantidad = new javax.swing.JTextField();
        jsDivision = new javax.swing.JSeparator();
        jlProvedorInfo = new javax.swing.JLabel();
        jlNombreE = new javax.swing.JLabel();
        jtAddProveedor = new javax.swing.JTextField();
        jbRegistrarE = new javax.swing.JButton();
        jlFotoP = new javax.swing.JLabel();
        jlFoto = new javax.swing.JLabel();
        jcCategoria = new javax.swing.JComboBox<>();
        jcSelectProveedor = new javax.swing.JComboBox<>();

        jpProduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Registar Entrada de Producto");
        jpProduct.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlCodeP.setText("Codigo del Producto");
        jPanel2.add(jlCodeP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jtCodeP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCodePActionPerformed(evt);
            }
        });
        jPanel2.add(jtCodeP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 290, -1));

        jlNombreP.setText("Nombre del Producto");
        jPanel2.add(jlNombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jtNombreP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNombrePActionPerformed(evt);
            }
        });
        jPanel2.add(jtNombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 290, -1));

        jlFechaE.setText("Fecha de Entrada");
        jPanel2.add(jlFechaE, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));
        jPanel2.add(jdFechaE, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 290, -1));

        jlPrecioU.setText("Precio Unitario");
        jPanel2.add(jlPrecioU, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));
        jPanel2.add(jtPrecioU, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 290, -1));

        jlCategoria.setText("Categoria");
        jPanel2.add(jlCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jlCantidad.setText("Cantidad");
        jPanel2.add(jlCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));
        jPanel2.add(jtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 290, -1));
        jPanel2.add(jsDivision, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 650, 9));

        jlProvedorInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlProvedorInfo.setText("Informacion del Proveedor");
        jPanel2.add(jlProvedorInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jlNombreE.setText("Nombre de la Empresa");
        jPanel2.add(jlNombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jtAddProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtAddProveedorActionPerformed(evt);
            }
        });
        jPanel2.add(jtAddProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 290, -1));

        jbRegistrarE.setBackground(new java.awt.Color(0, 102, 255));
        jbRegistrarE.setForeground(new java.awt.Color(255, 255, 255));
        jbRegistrarE.setText("Registrar Entrada");
        jbRegistrarE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbRegistrarEMouseClicked(evt);
            }
        });
        jbRegistrarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRegistrarEActionPerformed(evt);
            }
        });
        jPanel2.add(jbRegistrarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, -1, -1));

        jlFotoP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlFotoP.setText("Foto del Producto");
        jlFotoP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jlFotoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jlFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlFoto.setText(" imagen");
        jlFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlFoto.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jlFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlFotoMouseClicked(evt);
            }
        });
        jPanel2.add(jlFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 250, 270));

        jcCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jcCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 290, -1));

        jcSelectProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcSelectProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcSelectProveedorActionPerformed(evt);
            }
        });
        jPanel2.add(jcSelectProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 290, -1));

        jpProduct.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 700, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtCodePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCodePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCodePActionPerformed

    private void jtNombrePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNombrePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNombrePActionPerformed

    private void jtAddProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtAddProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtAddProveedorActionPerformed

    private void jbRegistrarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRegistrarEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRegistrarEActionPerformed

    private void jlFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlFotoMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes JPG, PNG", "jpg", "png"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
             String filePath = file.getAbsolutePath().toLowerCase();

            if (filePath.endsWith(".jpg") || filePath.endsWith(".png") || filePath.endsWith(".jpeg")) {
                try {
                    gURLImage = file.getAbsolutePath();

                    ImageIcon originalIcon = new ImageIcon(gURLImage);
                    Image image = originalIcon.getImage().getScaledInstance(jlFoto.getWidth(), jlFoto.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(image);

                    jlFoto.setIcon(scaledIcon);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Formato de archivo no válido. Por favor selecciona una imagen JPG o PNG.");
            }
        }
    }//GEN-LAST:event_jlFotoMouseClicked

    private void jbRegistrarEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRegistrarEMouseClicked
        // TODO add your handling code here:
        if (validarCampos()) {
            
            Product lProduct = new Product();

            lProduct.setId(0);
            lProduct.setCode(jtCodeP.getText());
            lProduct.setName(jtNombreP.getText());
            lProduct.setUnitPrice(Double.parseDouble(jtPrecioU.getText()));
                
            lProduct.setCategoryId(extractCategoryId());
            
            lProduct.setCurrentStock(Integer.parseInt(jtCantidad.getText()));
            lProduct.setMinimumStock(10); 
            lProduct.setEntryDate(obtenerFechaTexto());
            lProduct.setSupplierId(extractSupplinerId());
            lProduct.setImage(gURLImage); 
            lProduct.setStatus(true); 


            

            gProductService.insertOneProduct(lProduct);
            JOptionPane.showMessageDialog(this, "Producto registrado exitosamente.");
        }
    }//GEN-LAST:event_jbRegistrarEMouseClicked

    
    private int extractCategoryId() {
        String selectedCategoryName = (String) jcCategoria.getSelectedItem();

        for (Category category : gCategories) {
            if (category.toString().equals(selectedCategoryName)) {
                return category.getId();
            }
        }

        throw new IllegalArgumentException("Category not found: " + selectedCategoryName);
    }
    
    
    private int extractSupplinerId() {
        String selectedSupplinerName = (String) jcCategoria.getSelectedItem();

        for (Category category : gCategories) {
            if (category.toString().equals(selectedSupplinerName)) {
                return category.getId();
            }
        }

        throw new IllegalArgumentException("Category not found: " + selectedSupplinerName);
    }
    
    
    private void jcSelectProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcSelectProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcSelectProveedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbRegistrarE;
    private javax.swing.JComboBox<String> jcCategoria;
    private javax.swing.JComboBox<String> jcSelectProveedor;
    private com.toedter.calendar.JDateChooser jdFechaE;
    private javax.swing.JLabel jlCantidad;
    private javax.swing.JLabel jlCategoria;
    private javax.swing.JLabel jlCodeP;
    private javax.swing.JLabel jlFechaE;
    private javax.swing.JLabel jlFoto;
    private javax.swing.JLabel jlFotoP;
    private javax.swing.JLabel jlNombreE;
    private javax.swing.JLabel jlNombreP;
    private javax.swing.JLabel jlPrecioU;
    private javax.swing.JLabel jlProvedorInfo;
    private javax.swing.JPanel jpProduct;
    private javax.swing.JSeparator jsDivision;
    private javax.swing.JTextField jtAddProveedor;
    private javax.swing.JTextField jtCantidad;
    private javax.swing.JTextField jtCodeP;
    private javax.swing.JTextField jtNombreP;
    private javax.swing.JTextField jtPrecioU;
    // End of variables declaration//GEN-END:variables

    private boolean validarCampos() {
        String mensajeError = "";

        if (jtCodeP.getText().isEmpty()) {
            mensajeError += "- El código del producto no puede estar vacío.\n";
        }

        if (jtNombreP.getText().isEmpty()) {
            mensajeError += "- El nombre del producto no puede estar vacío.\n";
        }

        try {
            double precio = Double.parseDouble(jtPrecioU.getText());
            if (precio <= 0) {
                mensajeError += "- El precio unitario debe ser mayor a 0.\n";
            }
        } catch (NumberFormatException e) {
            mensajeError += "- El precio unitario debe ser un número válido.\n";
        }

        try {
            int cantidad = Integer.parseInt(jtCantidad.getText());
            if (cantidad < 0) {
                mensajeError += "- La cantidad no puede ser negativa.\n";
            }
        } catch (NumberFormatException e) {
            mensajeError += "- La cantidad debe ser un número entero válido.\n";
        }

        if (jdFechaE.getDate() == null) {
            mensajeError += "- La fecha de entrada no puede estar vacía.\n";
        }

        if (!mensajeError.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Errores en la validación:\n" + mensajeError, "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validación de que la categoría seleccionada no sea nula
        Category selectedCategory = (Category) jcCategoria.getSelectedItem();
        if (selectedCategory == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una categoría.");
            return false; 
        }

        return true;
    }
    private String obtenerFechaTexto() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return jdFechaE.getDate() != null ? sdf.format(jdFechaE.getDate()) : "";
    }
    
}
