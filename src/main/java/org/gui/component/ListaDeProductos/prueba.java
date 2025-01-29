package org.gui.component.ListaDeProductos;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.utils.Product;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class prueba extends javax.swing.JPanel {
    private Product gProduct; // Producto actual
    private ArrayList<Product> gProductsList; // Lista de productos
    private Map<JLabel, Product> labelProductMap = new HashMap<>(); // Mapa para asociar etiquetas con productos

    /**
     * Constructor de la clase CartProduct.
     *
     * @param lProduct Producto a cargar
     */
    public prueba(Product lProduct) {
        gProduct = lProduct;
        initComponents();
        loadProductData();
    }
    
    /**
     * Carga los datos del producto en los componentes visuales.
     */
    private void loadProductData() {
        if (gProduct.getImage() != null && !gProduct.getImage().isEmpty()) {
            ImageIcon icon = new ImageIcon(gProduct.getImage());
            java.awt.Image img = icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            jLabel1.setIcon(new ImageIcon(img));
        } else {
            jLabel1.setText("Sin Imagen");
        }

        jLabel3.setText(gProduct.getName());
        jLabel4.setText(String.format("%.2f", gProduct.getUnitPrice()));

        if (!gProduct.getStatus()) {
            jLabel5.setText("Active");
            jLabel5.setForeground(new Color(39, 174, 96));
        } else {
            jLabel5.setText("Inactive");
            jLabel5.setForeground(new Color(231, 76, 60));
        }
    }

    /**
     * Acción al hacer clic en jLabel2.
     */
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Producto: " + gProduct.getName() + "\nPrecio: $" + gProduct.getUnitPrice(),
            "Detalles del Producto", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Inicializa los componentes del formulario.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(138, 99));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("imagen");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -4, 160, 110));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/cc1.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 40, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel3.setText("Nombre");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel4.setText("0.00");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 30, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("active");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 40, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("$");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 10, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }

    // Variables declaration
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration

    public JLabel getCartButton() {
    return jLabel2;  // El JLabel que actúa como botón de carrito
    }
}
