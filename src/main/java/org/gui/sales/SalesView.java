package org.gui.sales;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


import org.gui.component.CustomScrollPane;
import org.gui.component.ListaDeProductos.ItemProduct;
import org.gui.component.ListaDeProductos.ItemSalesProduct;
import org.gui.component.RoundedBorder;
import org.gui.home.Home;
import org.services.utils.Product;
import org.services.product.ProductService;
import org.services.utils.Sale;
import org.services.utils.SalesDetail;
import org.services.sales.SalesServcice;



public class SalesView extends javax.swing.JPanel {
    
    
    private static final Color BACKGROUND_COLOR = new Color(249, 250, 251);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(240, 240, 240);
    private static final Color DARK_BLUE = new Color(63, 81, 181);
    private static final Dimension PANEL_SIZE = new Dimension(740, 540);

   
    private ArrayList<Product> gProducts;
    private ArrayList<ItemProduct> gCartProducts;
    private Map<String, CartItem> cartItems;
    private JPanel cartItemsPanel;
    
    
    private double subtotal;
    private double tax;
    private double total;

     public SalesView() {
        cartItems = new HashMap<>();
        gProducts = new ArrayList<>();  // Inicializar con lista vacía
        gCartProducts = new ArrayList<>();
        
        initComponents();
        setupStyle();
        setupCartPanel();
        loadProducts(); // Nuevo método para cargar productos
    }
     
    private void loadProducts() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        SwingWorker<List<Product>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Product> doInBackground() throws Exception {
                ProductService productService = new ProductService();
                return productService.getAllProducts();
            }
            
            @Override
            protected void done() {
                try {
                    gProducts = new ArrayList<>(get());
                    setupProductsPanel();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(SalesView.this, 
                        "Error al cargar los productos: " + e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        };
        
        worker.execute();
    }

   private void setupProductsPanel() {
        if (gProducts == null) return;  // Protección contra null
        
        JScrollPane scrollPane = (JScrollPane) jpaProductos.getComponent(0);
        JPanel contentPanel = (JPanel) scrollPane.getViewport().getView();
        contentPanel.removeAll();
        gCartProducts.clear();  // Limpiar la lista antes de añadir nuevos items

        for (Product product : gProducts) {
            ItemProduct itemProduct = new ItemProduct(product, this);
            if (!gCartProducts.isEmpty()) {
                contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            itemProduct.setMaximumSize(new Dimension(Integer.MAX_VALUE, itemProduct.getPreferredSize().height));
            itemProduct.setAlignmentX(Component.LEFT_ALIGNMENT);
            gCartProducts.add(itemProduct);
            contentPanel.add(itemProduct);
        }
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Actualizar el método refreshData
    public void refreshData() {
        loadProducts();  // Usar el nuevo método
    }


    private void setupStyle() {
        this.setBackground(BACKGROUND_COLOR);

        jpaBuscador.setBackground(WHITE);
        jpaBuscador.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY));

        jTextFieldBuscarProducto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jTextFieldBuscarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jpaProductos.setBackground(WHITE);
        jpaProductos.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        CustomScrollPane scrollPane = new CustomScrollPane(contentPanel);
        jpaProductos.add(scrollPane, BorderLayout.CENTER);

        jPanelAreaListaProductos.setBackground(WHITE);
        jPanelAreaListaProductos.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY));

        jToggleButton1.setBackground(DARK_BLUE);
        jToggleButton1.setForeground(WHITE);
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setBorderPainted(false);

        txt_filter.setBorder(new RoundedBorder(10, LIGHT_GRAY));
    }

    private void setupCartPanel() {
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(WHITE);

        JScrollPane scrollPane = new CustomScrollPane(cartItemsPanel);
        scrollPane.setBorder(null);

        jpItemSalesProduct.setLayout(new BorderLayout());
        jpItemSalesProduct.setBackground(WHITE);
        jpItemSalesProduct.add(scrollPane, BorderLayout.CENTER);
    }
    
    public void addToCart(Product product) {
        if (product.getCurrentStock() <= product.getMinimumStock()) {
            JOptionPane.showMessageDialog(this,
                "Este producto no está disponible para la venta.",
                "Producto Inactivo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String productId = String.valueOf(product.getId());
        if (cartItems.containsKey(productId)) {
            CartItem item = cartItems.get(productId);
            if (item.getQuantity() < product.getCurrentStock()) {
                item.incrementQuantity();
                updateCartItem(productId, item.getQuantity());
            } else {
                JOptionPane.showMessageDialog(this,
                    "No hay suficiente stock disponible.",
                    "Stock Insuficiente",
                    JOptionPane.WARNING_MESSAGE);
            }
        } else {
            CartItem cartItem = new CartItem(product);
            cartItems.put(productId, cartItem);
            ItemSalesProduct itemSales = new ItemSalesProduct(product);
            setupItemSalesListeners(itemSales, productId);
            cartItemsPanel.add(itemSales);
            if (cartItemsPanel.getComponentCount() > 1) {
                cartItemsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
        updateCartDisplay();
    }

    private void updateCartItem(String productId, int quantity) {
        for (Component comp : cartItemsPanel.getComponents()) {
            if (comp instanceof ItemSalesProduct) {
                ItemSalesProduct itemSales = (ItemSalesProduct) comp;
                if (String.valueOf(itemSales.getProduct().getId()).equals(productId)) {
                    itemSales.setQuantity(quantity);
                    break;
                }
            }
        }
    }
    
    private void updateCartDisplay() {
        subtotal = cartItems.values().stream().mapToDouble(CartItem::getTotal).sum();
        tax = subtotal * 0.10;
        double subtotalWithTax = subtotal + tax;
        double discount = 0.0;

        if (subtotalWithTax > 1500) {
            discount = subtotalWithTax * 0.05;
            jlDescuento.setText("5%");
        } else {
            jlDescuento.setText("0%");
        }

        total = subtotalWithTax - discount;

        jLabel33.setText(String.format("$%.2f", subtotal));
        jLabel34.setText(String.format("$%.2f", tax));
        jLabel36.setText(String.format("$%.2f", total));
    }


    private void registerSale() {
        if (total <= 0) {
            return;
        }

        if (!confirmSale()) {
            return;
        }

        boolean generateInvoice = askForInvoice();
        Sale sale = createSaleWithDetails();

        processSale(sale, generateInvoice);
    }

    private boolean confirmSale() {
        String message = String.format("¿Desea registrar la venta por $%.2f?", total);
        int response = JOptionPane.showConfirmDialog(this, message, 
            "Confirmar Venta", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    private boolean askForInvoice() {
        int response = JOptionPane.showConfirmDialog(this,
            "¿Desea generar factura?",
            "Facturación",
            JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    private Sale createSaleWithDetails() {
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setSubtotal(subtotal);
        sale.setTax(tax);

        applyDiscountIfApplicable(sale);
        sale.setTotal(total);
        sale.setDetails(createSaleDetails());

        return sale;
    }

    private void applyDiscountIfApplicable(Sale sale) {
        double subtotalWithTax = subtotal + tax;
        if (subtotalWithTax > 1500) {
            double discount = subtotalWithTax * 0.05;
            sale.setDiscount(discount);
            sale.setDiscountPercentage(5.0);
        } else {
            sale.setDiscount(0.0);
            sale.setDiscountPercentage(0.0);
        }
    }

    private List<SalesDetail> createSaleDetails() {
        List<SalesDetail> details = new ArrayList<>();
        for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
            CartItem item = entry.getValue();
            details.add(createSaleDetail(item));
        }
        return details;
    }

    private SalesDetail createSaleDetail(CartItem item) {
        Product product = item.getProduct();
        SalesDetail detail = new SalesDetail();
        detail.setIdProduct(product.getId());
        detail.setQuantity(item.getQuantity());
        detail.setUnitPrice(product.getUnitPrice());
        detail.setSubtotal(item.getTotal());
        return detail;
    }

    private void processSale(Sale sale, boolean generateInvoice) {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                SalesServcice saleService = new SalesServcice();
                return saleService.save(sale);
            }

            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        Home home = (Home) SwingUtilities.getWindowAncestor(SalesView.this);
                        if (home != null) {
                            home.requestViewsUpdate();
                        }
                        showSuccessMessage();
                        clearCart();
                    } else {
                        showErrorMessage("Error al registrar la venta");
                    }
                } catch (Exception e) {
                    showErrorMessage("Error: " + e.getMessage());
                } finally {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        };

        worker.execute();
    }
    
    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(this, 
            "Venta registrada exitosamente", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    

    private void openFacturaScreen(Sale sale) {
        // Obtener referencia al JFrame principal
        Home homeFrame = (Home) SwingUtilities.getWindowAncestor(this);

        // Actualizar BillingView con la venta
        //homeFrame.getBillingPanel().setSaleData(sale);

        // Simular clic en jpBilling
        homeFrame.handleMenuClick(
            homeFrame.getBillingButton(), 
            homeFrame.getIconBilling(),
            "/org/images/inv_select.png",
            homeFrame.getLabelBilling(),
            homeFrame.getBillingPanel()
        );
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

    
    private void setupItemSalesListeners(ItemSalesProduct itemSales, String productId) {
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
    }
    
    // Clase interna para manejar los elementos del carrito
    private class CartItem {
        private final Product product;
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
        jlDscu = new javax.swing.JLabel();
        jlDescuento = new javax.swing.JLabel();

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
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanelAreaListaProductos.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 170, -1));

        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("Sub Total");
        jPanelAreaListaProductos.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("Tax (10%)");
        jPanelAreaListaProductos.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel33.setText("$ 0.00");
        jPanelAreaListaProductos.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 60, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel34.setText("$ 0.00");
        jPanelAreaListaProductos.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 60, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel35.setText("Total");
        jPanelAreaListaProductos.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel36.setText("$ 0.00");
        jPanelAreaListaProductos.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 60, -1));

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

        jlDscu.setForeground(new java.awt.Color(153, 153, 153));
        jlDscu.setText("Desc");
        jPanelAreaListaProductos.add(jlDscu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        jlDescuento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlDescuento.setText("0%");
        jPanelAreaListaProductos.add(jlDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, -1, -1));

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
    String filtro = jTextFieldBuscarProducto.getText().trim(); // Obtener el texto del campo de búsqueda
    
        if (filtro.isEmpty()) {
           // Si el filtro está vacío, mostrar todos los productos
          loadProducts(); // Recargar todos los productos
        } else {
          // Si hay un filtro, aplicar la búsqueda
         filtrarProductos(filtro); // Filtrar productos
        }   
    
    setupProductsPanel(); // Actualizar la lista de productos en la interfaz
    }//GEN-LAST:event_txt_filterActionPerformed
    private void filtrarProductos(String filtro) {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker<List<Product>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Product> doInBackground() throws Exception {
                ProductService productService = new ProductService();
                return productService.getFilteredProducts(filtro);
            }

            @Override
            protected void done() {
                try {
                    gProducts = new ArrayList<>(get());
                    setupProductsPanel();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(SalesView.this, 
                        "Error al filtrar productos: " + e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        };

        worker.execute();
    }
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        registerSale();

    }//GEN-LAST:event_jToggleButton1ActionPerformed


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
    private javax.swing.JLabel jlDescuento;
    private javax.swing.JLabel jlDscu;
    private javax.swing.JPanel jpItemSalesProduct;
    private javax.swing.JPanel jpaBuscador;
    private javax.swing.JPanel jpaProductos;
    private javax.swing.JButton txt_filter;
    // End of variables declaration//GEN-END:variables
}
