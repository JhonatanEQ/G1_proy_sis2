package org.gui.sales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.gui.component.CustomScrollPane;
import org.gui.component.ListaDeProductos.ItemProduct;
import org.gui.component.ListaDeProductos.ItemSalesProduct;
import org.services.utils.Product;

public class SalesView extends javax.swing.JPanel {

    private ArrayList<Product> gProducts;
    private ArrayList<ItemProduct> gCartProducts;
    private Map<String, CartItem> cartItems;
    private JPanel cartItemsPanel;
    private double subtotal;
    private double tax;
    private double total;

    // Lista de JLabel para los productos en el carrito
    private JLabel[] productLabels = new JLabel[6];
    private JLabel[] quantityLabels = new JLabel[6];

    public SalesView() {
        cartItems = new HashMap<>();
        initComponents();
        setupStyle();
        setupCartPanel();
        initializeProducts();
        setupProductsPanel();
    }

    private void initializeProducts() {
        // Initialize global lists
        gProducts = new ArrayList<>();
        gCartProducts = new ArrayList<>();

        // Add sample products - you can modify this to load from your actual data source
        gProducts.add(new Product("Mouse", 39.99, "C:\\Users\\HP\\Desktop\\imagenes\\Screenshot 2025-01-26 212927.png", false));
        gProducts.add(new Product("Notebook", 12.99, "C:\\Users\\HP\\Desktop\\imagenes\\Screenshot 2025-01-26 213659.png", false));
        gProducts.add(new Product("USB Cable", 8.99, "C:\\Users\\HP\\Desktop\\imagenes\\Screenshot 2025-01-26 213704.png", false));
        gProducts.add(new Product("Keyboard", 29.99, "C:\\Users\\HP\\Desktop\\imagenes\\Screenshot 2025-01-26 213930.png", false));
        gProducts.add(new Product("Laptop", 599.99, "C:\\Users\\HP\\Desktop\\imagenes\\Screenshot 2025-01-26 223435.png", false));
        gProducts.add(new Product("Power Source", 199.99, "C:\\Users\\HP\\Desktop\\imagenes\\Screenshot 2025-01-26 223615.png", false));
    }

    private void setupStyle() {
        this.setBackground(new Color(249, 250, 251));

        jpaBuscador.setBackground(Color.WHITE);
        jpaBuscador.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));

        jTextFieldBuscarProducto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jTextFieldBuscarProducto.setFont(new Font("Segoe UI", 0, 14));

        jpaProductos.setBackground(Color.WHITE);
        jpaProductos.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        CustomScrollPane scrollPane = new CustomScrollPane(contentPanel);
        jpaProductos.add(scrollPane, BorderLayout.CENTER);

        jPanelAreaListaProductos.setBackground(Color.WHITE);
        jPanelAreaListaProductos.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));

        jToggleButton1.setBackground(new Color(63, 81, 181));
        jToggleButton1.setForeground(Color.WHITE);
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setBorderPainted(false);
    }

    private void setupProductsPanel() {
        // Obtener el contentPanel del ScrollPane
        JScrollPane scrollPane = (JScrollPane) jpaProductos.getComponent(0);
        JPanel contentPanel = (JPanel) scrollPane.getViewport().getView();
        contentPanel.removeAll();

        for (Product lProduct : gProducts) {
            ItemProduct lCartProduct = new ItemProduct(lProduct);

            if (gCartProducts.size() > 0) {
                contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            lCartProduct.setMaximumSize(new Dimension(Integer.MAX_VALUE, lCartProduct.getPreferredSize().height));
            lCartProduct.setAlignmentX(Component.LEFT_ALIGNMENT);

            lCartProduct.getAddToCartButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addToCart(lProduct);
                }
            });

            gCartProducts.add(lCartProduct);
            contentPanel.add(lCartProduct);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void setupCartPanel() {
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new CustomScrollPane(cartItemsPanel);
        scrollPane.setBorder(null);

        jpItemSalesProduct.setLayout(new BorderLayout());
        jpItemSalesProduct.setBackground(Color.WHITE);
        jpItemSalesProduct.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void addToCart(Product product) {
        String productId = product.getName();
        if (cartItems.containsKey(productId)) {
            CartItem item = cartItems.get(productId);
            item.incrementQuantity();
            // Buscar y actualizar el ItemSalesProduct existente
            for (Component comp : cartItemsPanel.getComponents()) {
                if (comp instanceof ItemSalesProduct) {
                    ItemSalesProduct itemSales = (ItemSalesProduct) comp;
                    if (itemSales.getProduct().getName().equals(productId)) {
                        itemSales.setQuantity(item.getQuantity());
                        break;
                    }
                }
            }
        } else {
            // Crear nuevo CartItem y ItemSalesProduct
            CartItem cartItem = new CartItem(product);
            cartItems.put(productId, cartItem);
            ItemSalesProduct itemSales = new ItemSalesProduct(product);

            // Agregar listeners para los botones + y -
            itemSales.getMinusButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CartItem item = cartItems.get(productId);
                    if (item.getQuantity() > 1) {
                        item.decrementQuantity();
                        itemSales.setQuantity(item.getQuantity());
                        updateCartDisplay();
                    }
                }
            });

            itemSales.getPlusButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CartItem item = cartItems.get(productId);
                    item.incrementQuantity();
                    itemSales.setQuantity(item.getQuantity());
                    updateCartDisplay();
                }
            });

            // Agregar botón de eliminar
            itemSales.getDeleteButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cartItems.remove(productId);
                    cartItemsPanel.remove(itemSales);
                    cartItemsPanel.revalidate();
                    cartItemsPanel.repaint();
                    updateCartDisplay();
                }
            });

            cartItemsPanel.add(itemSales);
            if (cartItemsPanel.getComponentCount() > 1) {
                cartItemsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
        updateCartDisplay();
    }


    private void addCartRow(Product product) {
        for (int i = 0; i < productLabels.length; i++) {
            if (productLabels[i].getText().equals("Producto " + (i + 1))) {
                // Encontramos una fila vacía, actualizamos con el nuevo producto
                productLabels[i].setText(product.getName());
                quantityLabels[i].setText("1"); // Inicializar con cantidad 1
                break;
            }
        }
    }

    private void updateCartDisplay() {
        subtotal = 0;
        for (CartItem item : cartItems.values()) {
            subtotal += item.getTotal();
        }

        tax = subtotal * 0.10;
        total = subtotal + tax;

        jLabel33.setText(String.format("$%.2f", subtotal));
        jLabel34.setText(String.format("$%.2f", tax));
        jLabel36.setText(String.format("$%.2f", total));
    }

    private void incrementQuantity(String productId) {
        CartItem item = cartItems.get(productId);
        if (item != null) {
            item.incrementQuantity();
            updateCartDisplay();
        }
    }

    private void decrementQuantity(String productId) {
        CartItem item = cartItems.get(productId);
        if (item != null && item.getQuantity() > 1) {
            item.decrementQuantity();
            updateCartDisplay();
        }
    }

    private void registerSale() {
        if (total > 0) {
            int response = JOptionPane.showConfirmDialog(
                this,
                "¿Desea registrar la venta por $" + String.format("%.2f", total) + "?",
                "Confirmar Venta",
                JOptionPane.YES_NO_OPTION
            );

            if (response == JOptionPane.YES_OPTION) {
                clearCart();
                JOptionPane.showMessageDialog(
                    this,
                    "Venta registrada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

            if (response == JOptionPane.NO_OPTION) {
                clearCart();
                JOptionPane.showMessageDialog(
                    this,
                    "Venta no registrada",
                    "",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void clearCart() {
        cartItems.clear();
        cartItemsPanel.removeAll();
        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();

        subtotal = 0;
        tax = 0;
        total = 0;
        updateCartDisplay();
    }

    private JLabel findQuantityLabel(String productName) {
    for (int i = 0; i < productLabels.length; i++) {
        if (productLabels[i].getText().equals(productName)) {
            return quantityLabels[i];
        }
    }
    return null;
    }

    // Inner class to handle cart items
    private class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product) {
            this.product = product;
            this.quantity = 1;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void incrementQuantity() {
            quantity++;
        }

        public void decrementQuantity() {
            if (quantity > 1) {
                quantity--;
            }
        }

        public double getTotal() {
            return product.getUnitPrice() * quantity;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelVentas = new javax.swing.JLabel();
        jpaBuscador = new javax.swing.JPanel();
        jTextFieldBuscarProducto = new javax.swing.JTextField();
        txt_filter = new javax.swing.JButton();
        jpaProductos = new javax.swing.JPanel();
        jPanelAreaListaProductos = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jpItemSalesProduct = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(249, 250, 251));
        setPreferredSize(new java.awt.Dimension(740, 540));

        jLabelVentas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelVentas.setText("Ventas");

        jpaBuscador.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldBuscarProducto.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldBuscarProducto.setText("Buscar Producto...");
        jTextFieldBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarProductoActionPerformed(evt);
            }
        });

        txt_filter.setBackground(new java.awt.Color(0, 102, 255));
        txt_filter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_filter.setForeground(new java.awt.Color(255, 255, 255));
        txt_filter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/filtrar (1).png"))); // NOI18N
        txt_filter.setText("Filter");
        txt_filter.setBorder(null);
        txt_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpaBuscadorLayout = new javax.swing.GroupLayout(jpaBuscador);
        jpaBuscador.setLayout(jpaBuscadorLayout);
        jpaBuscadorLayout.setHorizontalGroup(
            jpaBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpaBuscadorLayout.setVerticalGroup(
            jpaBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_filter, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap(8, Short.MAX_VALUE))
            .addComponent(jTextFieldBuscarProducto)
        );

        jpaProductos.setBackground(new java.awt.Color(255, 255, 255));
        jpaProductos.setLayout(new java.awt.GridLayout(1, 0));

        jPanelAreaListaProductos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAreaListaProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton1.setBackground(new java.awt.Color(0, 102, 255));
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Registrar Venta");
        jPanelAreaListaProductos.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 170, -1));

        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("Sub Total");
        jPanelAreaListaProductos.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("Tax (10%)");
        jPanelAreaListaProductos.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel33.setText("$ 0.00");
        jPanelAreaListaProductos.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel34.setText("$ 0.00");
        jPanelAreaListaProductos.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, -1, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel35.setText("Total");
        jPanelAreaListaProductos.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel36.setText("$ 0.00");
        jPanelAreaListaProductos.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel37.setText("Lista de productos ");
        jPanelAreaListaProductos.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 7, -1, -1));
        jPanelAreaListaProductos.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 7, -1, -1));

        javax.swing.GroupLayout jpItemSalesProductLayout = new javax.swing.GroupLayout(jpItemSalesProduct);
        jpItemSalesProduct.setLayout(jpItemSalesProductLayout);
        jpItemSalesProductLayout.setHorizontalGroup(
            jpItemSalesProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jpItemSalesProductLayout.setVerticalGroup(
            jpItemSalesProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        jPanelAreaListaProductos.add(jpItemSalesProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 190, 310));
        jPanelAreaListaProductos.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpaBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanelAreaListaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(31, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabelVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpaBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jpaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                    .addComponent(jPanelAreaListaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarProductoActionPerformed

    private void txt_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_filterActionPerformed
     //   String filtro = txt_buscar.getText().trim();

      //  if (filtro.isEmpty()) {
         //   filtrarProductos(""); // Pasamos una cadena vacía para indicar que no hay filtro
      //  }else {
         //   filtrarProductos(filtro); // Aplicar el filtro
     //   }

    }//GEN-LAST:event_txt_filterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabelVentas;
    private javax.swing.JPanel jPanelAreaListaProductos;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldBuscarProducto;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel jpItemSalesProduct;
    private javax.swing.JPanel jpaBuscador;
    private javax.swing.JPanel jpaProductos;
    private javax.swing.JButton txt_filter;
    // End of variables declaration//GEN-END:variables
}
