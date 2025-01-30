package org.gui.sales;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import org.gui.component.ListaDeProductos.prueba;
import org.utils.Product;

public class SalesView extends javax.swing.JPanel {

    private ArrayList<Product> gProducts;
    private ArrayList<prueba> gCartProducts;
    private Map<String, CartItem> cartItems;
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
        initializeProducts();
        setupProductsPanel();
        setupCartControls();
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
        // Main panel styling
        this.setBackground(new Color(249, 250, 251));

        // Search panel styling
        jpaBuscador.setBackground(Color.WHITE);
        jpaBuscador.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));

        // Search field styling
        jTextFieldBuscarProducto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jTextFieldBuscarProducto.setFont(new java.awt.Font("Segoe UI", 0, 14));

        // Products panel styling
        jpaProductos.setBackground(Color.WHITE);
        jpaProductos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Cart panel styling
        jPanelAreaListaProductos.setBackground(Color.WHITE);
        jPanelAreaListaProductos.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));

        // Estilo del botón de registro
        jToggleButton1.setBackground(new Color(63, 81, 181));
        jToggleButton1.setForeground(Color.WHITE);
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setBorderPainted(false);
    }

    private void setupProductsPanel() {
        jpaProductos.removeAll();
        jpaProductos.setLayout(new GridLayout(2, 3, 15, 15));
        jpaProductos.setPreferredSize(new Dimension(557, 400));

        for (Product lProduct : gProducts) {
            prueba lCartProduct = new prueba(lProduct);

            // Add click listener to cart button
            lCartProduct.getCartButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addToCart(lProduct);
                }
            });

            gCartProducts.add(lCartProduct);
            jpaProductos.add(lCartProduct);
        }

        jpaProductos.revalidate();
        jpaProductos.repaint();
    }

    private void setupCartControls() {
    // Inicializar los JLabel de productos
    productLabels[0] = jLabel20;
    productLabels[1] = jLabel21;
    productLabels[2] = jLabel22;
    productLabels[3] = jLabel23;
    productLabels[4] = jLabel24;
    productLabels[5] = jLabel25;

    // Inicializar los JLabel de cantidades
    quantityLabels[0] = jLabel13;
    quantityLabels[1] = jLabel26;
    quantityLabels[2] = jLabel27;
    quantityLabels[3] = jLabel28;
    quantityLabels[4] = jLabel29;
    quantityLabels[5] = jLabel30;

    // Inicializar los JLabel de "-" y "+"
    JLabel[] minusLabels = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6};
    JLabel[] plusLabels = {jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12};

    // Asignar eventos a los JLabel de "-"
    for (int i = 0; i < minusLabels.length; i++) {
        final int index = i; // Necesario para usar en el listener
        minusLabels[index].setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor a mano
        minusLabels[index].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String productName = productLabels[index].getText();
                if (!productName.equals("Producto " + (index + 1))) {
                    decrementQuantity(productName);
                }
            }
        });
    }

    // Asignar eventos a los JLabel de "+"
    for (int i = 0; i < plusLabels.length; i++) {
        final int index = i; // Necesario para usar en el listener
        plusLabels[index].setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor a mano
        plusLabels[index].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String productName = productLabels[index].getText();
                if (!productName.equals("Producto " + (index + 1))) {
                    incrementQuantity(productName);
                }
            }
        });
    }

    // Configurar el botón de registrar venta
    jToggleButton1.addActionListener(e -> registerSale());
    }

    private void addToCart(Product product) {
        String productId = product.getName();
        if (cartItems.containsKey(productId)) {
            cartItems.get(productId).incrementQuantity();
        } else {
            cartItems.put(productId, new CartItem(product));
            addCartRow(product);
        }
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

        // Actualizar la cantidad mostrada para este producto
        JLabel quantityLabel = findQuantityLabel(item.getProduct().getName());
        if (quantityLabel != null) {
            quantityLabel.setText(String.valueOf(item.getQuantity()));
        }
    }

    tax = subtotal * 0.10;
    total = subtotal + tax;

    // Actualizar las etiquetas de totales
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
        subtotal = 0;
        tax = 0;
        total = 0;

        // Restablecer todas las filas de productos
        for (int i = 0; i < productLabels.length; i++) {
            productLabels[i].setText("Producto " + (i + 1));
            quantityLabels[i].setText("1");
        }

        // Actualizar los totales
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
        jpaProductos = new javax.swing.JPanel();
        jPanelAreaListaProductos = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

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

        javax.swing.GroupLayout jpaBuscadorLayout = new javax.swing.GroupLayout(jpaBuscador);
        jpaBuscador.setLayout(jpaBuscadorLayout);
        jpaBuscadorLayout.setHorizontalGroup(
            jpaBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaBuscadorLayout.createSequentialGroup()
                .addComponent(jTextFieldBuscarProducto)
                .addContainerGap())
        );
        jpaBuscadorLayout.setVerticalGroup(
            jpaBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaBuscadorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTextFieldBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jpaProductos.setBackground(new java.awt.Color(255, 255, 255));
        jpaProductos.setLayout(new java.awt.GridLayout(1, 0));

        jPanelAreaListaProductos.setBackground(new java.awt.Color(255, 255, 255));

        jToggleButton1.setBackground(new java.awt.Color(0, 102, 255));
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Registrar Venta");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel20.setText("Producto 1");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel21.setText("Producto 2");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel22.setText("Producto 3");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel23.setText("Producto 4");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel24.setText("Producto 5");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel25.setText("Producto 6");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel13.setText("1 ");

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel26.setText("1 ");

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel27.setText("1 ");

        jLabel28.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel28.setText("1 ");

        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel29.setText("1 ");

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel30.setText("1 ");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("Sub Total");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("Tax (10%)");

        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel33.setText("$ 0.00");

        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        jLabel34.setText("$ 0.00");

        jLabel35.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel35.setText("Total");

        jLabel36.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel36.setText("$ 0.00");

        jLabel37.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel37.setText("Lista de productos ");

        jLabel38.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Desktop\\imagenes\\Screenshot 2025-01-27 002357.png")); // NOI18N

        jLabel1.setText("-");

        jLabel2.setText("-");

        jLabel3.setText("-");

        jLabel4.setText("-");

        jLabel5.setText("-");

        jLabel6.setText("-");

        jLabel7.setText("+");

        jLabel8.setText("+");

        jLabel9.setText("+");

        jLabel10.setText("+");

        jLabel11.setText("+");

        jLabel12.setText("+");

        javax.swing.GroupLayout jPanelAreaListaProductosLayout = new javax.swing.GroupLayout(jPanelAreaListaProductos);
        jPanelAreaListaProductos.setLayout(jPanelAreaListaProductosLayout);
        jPanelAreaListaProductosLayout.setHorizontalGroup(
            jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34))
                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAreaListaProductosLayout.createSequentialGroup()
                        .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel36)
                                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanelAreaListaProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7))))
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jLabel33)))
                .addContainerGap())
        );
        jPanelAreaListaProductosLayout.setVerticalGroup(
            jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAreaListaProductosLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel13)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel26)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel27)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel28)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel29)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel30)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34))
                .addGap(26, 26, 26)
                .addGroup(jPanelAreaListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addComponent(jToggleButton1)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpaBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelVentas)
                                .addGap(0, 495, Short.MAX_VALUE))
                            .addComponent(jpaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelAreaListaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabelVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpaBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelAreaListaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelVentas;
    private javax.swing.JPanel jPanelAreaListaProductos;
    private javax.swing.JTextField jTextFieldBuscarProducto;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel jpaBuscador;
    private javax.swing.JPanel jpaProductos;
    // End of variables declaration//GEN-END:variables
}
