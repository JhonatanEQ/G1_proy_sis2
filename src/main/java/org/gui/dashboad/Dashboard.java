/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.gui.dashboad;

import org.gui.component.JpRecentSales;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import org.gui.component.CartLowStockAlert;
import org.gui.component.CustomScrollPane;
import java.util.List;  // Esta es la que necesitas para List
import java.util.LinkedList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.services.product.ProductService;
import org.services.sales.SalesServcice;
import org.services.utils.Product;
import org.services.utils.Sale;

/**
 *
 * @author Encin
 */
public class Dashboard extends javax.swing.JPanel {
    
    private static final int MAX_ITEMS = 15;
    private LinkedList<JpRecentSales> recentSalesQueue;
    private ProductService productService; 
    private static double previousTotalSales = 0;
private static int previousTotalProducts = 0;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        productService = new ProductService();
        setupRecentSales();   
        setupLowStockAlerts();   
        loadDashboardData();
    }
    
    private void setupRecentSales() {
        recentSalesQueue = new LinkedList<>();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        CustomScrollPane scrollPane = new CustomScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setBorder(null);

        jpRecentSalesContent.removeAll();
        jpRecentSalesContent.setLayout(new BorderLayout());
        jpRecentSalesContent.setBackground(Color.WHITE);
        jpRecentSalesContent.setBorder(null);
        jpRecentSalesContent.add(scrollPane, BorderLayout.CENTER);

        jpRecentSalesContent.putClientProperty("contentPanel", contentPanel);

        jpRecentSales.setBackground(Color.WHITE);
        jpRecentSales.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
    }

    private void setupLowStockAlerts() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        CustomScrollPane scrollPane = new CustomScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setBorder(null);

        jpAlertPanel.removeAll();
        jpAlertPanel.setLayout(new BorderLayout());
        jpAlertPanel.setBackground(Color.WHITE);
        jpAlertPanel.setBorder(null);
        jpAlertPanel.add(scrollPane, BorderLayout.CENTER);

        jpAlertPanel.putClientProperty("contentPanel", contentPanel);

        jpLowStockAlert.setBackground(Color.WHITE);
        jpLowStockAlert.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
    }

    public void addRecentSale(String orderNumber, String timeAgo, double amount) {
        JPanel contentPanel = (JPanel) jpRecentSalesContent.getClientProperty("contentPanel");
        if (contentPanel == null) return;

        JpRecentSales salePanel = new JpRecentSales();
        salePanel.setOrderInfo(orderNumber, timeAgo, amount);
        salePanel.setBackground(Color.WHITE);
        salePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, salePanel.getPreferredSize().height));
        salePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add at the bottom of the panel instead of the top
        contentPanel.add(salePanel);

        // Add vertical strut between sales
        if (contentPanel.getComponentCount() > 1) {
            contentPanel.add(Box.createVerticalStrut(10), contentPanel.getComponentCount() - 1);
        }

        recentSalesQueue.offerLast(salePanel);

        // Control the maximum number of items
        while (recentSalesQueue.size() > MAX_ITEMS) {
            // Remove the first two components (first panel and its separator)
            if (contentPanel.getComponentCount() > 0) {
                contentPanel.remove(0);
                if (contentPanel.getComponentCount() > 0) {
                    contentPanel.remove(0); // Remove the vertical strut
                }
            }
            recentSalesQueue.removeFirst();
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

   public void addLowStockAlert(String productName, int remainingItems) {
        JPanel contentPanel = (JPanel) jpAlertPanel.getClientProperty("contentPanel");
        if (contentPanel == null) return;

        CartLowStockAlert alertPanel = new CartLowStockAlert();
        alertPanel.setAlertInfo(productName, remainingItems);
        alertPanel.addRestockListener(e -> handleRestock(productName));
        alertPanel.setBackground(Color.WHITE);
        alertPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, alertPanel.getPreferredSize().height));
        alertPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (contentPanel.getComponentCount() > 0) {
            contentPanel.add(Box.createVerticalStrut(10));
        }

        contentPanel.add(alertPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void loadDashboardData() {
        try {
            // Fetch all products
            List<Product> products = productService.getAllProducts();
            int lowStockCount = 0;

            JPanel contentPanel = (JPanel) jpAlertPanel.getClientProperty("contentPanel");
            if (contentPanel != null) {
                contentPanel.removeAll();
            }
            double totalSales = calculateTotalSales();
            double salesGrowthPercentage = calculateSalesGrowthPercentage();
            previousTotalSales = totalSales;

            jlTotalSales.setText(String.format("%.2f", totalSales));
            jlTotalPorcentaje.setText(String.format("%.1f", salesGrowthPercentage) + "%");
            
            int totalProducts = calculateTotalProductsStock();
            double productsGrowthPercentage = calculateProductsGrowthPercentage();

            jlTotalProducts.setText(String.valueOf(totalProducts));
            jlTotalPP.setText(String.format("%.1f", productsGrowthPercentage) + "%");

            for (Product producto : products) {
                if (producto.getCurrentStock() <= producto.getMinimumStock()) {
                    addLowStockAlert(producto.getName(), producto.getCurrentStock());
                    lowStockCount++;
                }
            }

            jLabel14.setText(String.valueOf(lowStockCount));

            double percentage = products.isEmpty() ? 0 : 
                ((double) lowStockCount / products.size()) * 100;
            jLabel12.setText(String.format("%.1f", percentage) + "%");

            if (contentPanel != null) {
                contentPanel.revalidate();
                contentPanel.repaint();
            }

            loadRecentSales();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar datos del dashboard: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadRecentSales() throws SQLException {
        JPanel contentPanel = (JPanel) jpRecentSalesContent.getClientProperty("contentPanel");
        if (contentPanel != null) {
            contentPanel.removeAll();
            recentSalesQueue.clear();
        }

        SalesServcice salesService = new SalesServcice();
        List<Sale> recentSales = salesService.getRecentSales(MAX_ITEMS);

        Collections.sort(recentSales, (a, b) -> b.getDate().compareTo(a.getDate()));

        for (Sale sale : recentSales) {
            String orderNumber = String.format("%04d", sale.getIdSale());
            String timeAgo = calculateTimeAgo(sale.getDate());
            addRecentSale(orderNumber, timeAgo, sale.getTotal());
        }

        // Forzar actualización visual
        if (contentPanel != null) {
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    private void handleRestock(String productName) {
        try {
            // Buscar el producto por nombre
            List<Product> products = productService.getAllProducts();
            Product productToRestock = products.stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .orElse(null);

            if (productToRestock != null) {
                String input = JOptionPane.showInputDialog(this,
                    "Ingrese la cantidad a añadir al stock:",
                    "Resurtir " + productName,
                    JOptionPane.QUESTION_MESSAGE);

                if (input != null && !input.trim().isEmpty()) {
                    try {
                        int additionalStock = Integer.parseInt(input.trim());
                        if (additionalStock > 0) {
                            int newStock = productToRestock.getCurrentStock() + additionalStock;
                            productToRestock.setCurrentStock(newStock);
                            productToRestock.setEntryDate(
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .format(new Date()));

                            if (productService.updateProductStockAndDate(productToRestock)) {
                                JOptionPane.showMessageDialog(this,
                                    "Stock actualizado exitosamente");

                                // Recargar los datos del dashboard
                                loadDashboardData();

                                // Actualizar el inventario si está abierto
                                Window[] windows = Window.getWindows();
                                for (Window window : windows) {
                                    if (window instanceof JFrame) {
                                        for (Component comp : ((JFrame) window).getContentPane().getComponents()) {
                                            if (comp instanceof org.gui.inventory.InventoryView) {
                                                ((org.gui.inventory.InventoryView) comp).refreshData();
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this,
                                "La cantidad debe ser mayor a 0",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this,
                            "Por favor ingrese un número válido",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Error al actualizar el stock: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private String calculateTimeAgo(Date date) {
        long diffInMillies = new Date().getTime() - date.getTime();
        long diffInMinutes = diffInMillies / (60 * 1000);

        if (diffInMinutes < 1) {
            return "hace un momento";
        } else if (diffInMinutes < 60) {
            return "hace " + diffInMinutes + " minutos";
        } else if (diffInMinutes < 1440) { // menos de 24 horas
            long hours = diffInMinutes / 60;
            return "hace " + hours + " " + (hours == 1 ? "hora" : "horas");
        } else {
            long days = diffInMinutes / 1440;
            return "hace " + days + " " + (days == 1 ? "día" : "días");
        }
    }
    
    public void refreshDashboard() {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Iniciando refresh del Dashboard"); // Para debugging
                JPanel contentPanel = (JPanel) jpRecentSalesContent.getClientProperty("contentPanel");
                if (contentPanel != null) {
                    contentPanel.removeAll();
                    recentSalesQueue.clear();
                }
                loadDashboardData();
                if (contentPanel != null) {
                    contentPanel.revalidate();
                    contentPanel.repaint();
                }
                System.out.println("Dashboard refrescado completamente"); // Para debugging
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    
    private int calculateTotalProductsStock() throws SQLException {

        return productService.getAllProducts()
                .stream()
                .mapToInt(Product::getCurrentStock)
                .sum();
    }
    private double calculateTotalSales() throws SQLException {
        SalesServcice salesService = new SalesServcice();

        return salesService.getAllSales()
                .stream()
                .mapToDouble(Sale::getTotal)
                .sum();
    }
    
    
    
    private double calculatePreviousDayTotalSales() throws SQLException {
        SalesServcice salesService = new SalesServcice();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date previousDayDate = calendar.getTime();

        return salesService.getSalesByDate(previousDayDate)
                .stream()
                .mapToDouble(Sale::getTotal)
                .sum();
    }

    private double calculateCurrentDayTotalSales() throws SQLException {
        SalesServcice salesService = new SalesServcice();

        Date currentDate = new Date();

        return salesService.getSalesByDate(currentDate)
                .stream()
                .mapToDouble(Sale::getTotal)
                .sum();
    }

    private double calculateSalesGrowthPercentage() throws SQLException {
        double previousDayTotalSales = calculatePreviousDayTotalSales();
        double currentDayTotalSales = calculateCurrentDayTotalSales();

        if (previousDayTotalSales == 0) return 0;
        return ((currentDayTotalSales - previousDayTotalSales) / previousDayTotalSales) * 100;
    }

    private int calculatePreviousDayTotalProductsStock() throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date previousDayDate = calendar.getTime();

        return productService.getProductsByDate(previousDayDate)
                .stream()
                .mapToInt(Product::getCurrentStock)
                .sum();
    }

    private int calculateCurrentDayTotalProductsStock() throws SQLException {
        Date currentDate = new Date();

        return productService.getProductsByDate(currentDate)
                .stream()
                .mapToInt(Product::getCurrentStock)
                .sum();
    }
    

    private double calculateProductsGrowthPercentage() throws SQLException {
        int previousDayTotalProductsStock = calculatePreviousDayTotalProductsStock();
        int currentDayTotalProductsStock = calculateCurrentDayTotalProductsStock();

        if (previousDayTotalProductsStock == 0) return 0;
        return ((currentDayTotalProductsStock - previousDayTotalProductsStock) / (double) previousDayTotalProductsStock) * 100;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpView = new javax.swing.JPanel();
        jlTitleD = new javax.swing.JLabel();
        jpTotalSales = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlTotalSales = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlTotalPorcentaje = new javax.swing.JLabel();
        jpTotalProducts = new javax.swing.JPanel();
        jlTotalProducts = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlTotalPP = new javax.swing.JLabel();
        jpLowStock = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jpRecentSales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpRecentSalesContent = new javax.swing.JPanel();
        jpLowStockAlert = new javax.swing.JPanel();
        jlAleterTitle = new javax.swing.JLabel();
        jpAlertPanel = new javax.swing.JPanel();

        jpView.setBackground(new java.awt.Color(249, 250, 251));
        jpView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlTitleD.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jlTitleD.setText("Dashboard");
        jpView.add(jlTitleD, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 680, -1));

        jpTotalSales.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/dolar.png"))); // NOI18N

        jLabel5.setText("Total de ventas");

        jlTotalSales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlTotalSales.setText("0.0000");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("$");

        jlTotalPorcentaje.setText("9999%");

        javax.swing.GroupLayout jpTotalSalesLayout = new javax.swing.GroupLayout(jpTotalSales);
        jpTotalSales.setLayout(jpTotalSalesLayout);
        jpTotalSalesLayout.setHorizontalGroup(
            jpTotalSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalSalesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpTotalSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTotalSalesLayout.createSequentialGroup()
                        .addGroup(jpTotalSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTotalSalesLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlTotalSales))
                            .addComponent(jLabel5))
                        .addContainerGap(105, Short.MAX_VALUE))
                    .addGroup(jpTotalSalesLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlTotalPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        jpTotalSalesLayout.setVerticalGroup(
            jpTotalSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTotalSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jlTotalPorcentaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addGroup(jpTotalSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTotalSales)
                    .addComponent(jLabel15))
                .addGap(14, 14, 14))
        );

        jpView.add(jpTotalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 50, 200, 130));

        jpTotalProducts.setBackground(new java.awt.Color(255, 255, 255));

        jlTotalProducts.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlTotalProducts.setText("999999");

        jLabel8.setText("Total de Productos ");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/caja-de-entrega.png"))); // NOI18N
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jlTotalPP.setText("999%");

        javax.swing.GroupLayout jpTotalProductsLayout = new javax.swing.GroupLayout(jpTotalProducts);
        jpTotalProducts.setLayout(jpTotalProductsLayout);
        jpTotalProductsLayout.setHorizontalGroup(
            jpTotalProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalProductsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpTotalProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTotalProductsLayout.createSequentialGroup()
                        .addGroup(jpTotalProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jlTotalProducts))
                        .addGap(0, 83, Short.MAX_VALUE))
                    .addGroup(jpTotalProductsLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlTotalPP)
                        .addGap(18, 18, 18))))
        );
        jpTotalProductsLayout.setVerticalGroup(
            jpTotalProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTotalProductsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTotalProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlTotalPP)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(29, 29, 29)
                .addComponent(jlTotalProducts)
                .addGap(16, 16, 16))
        );

        jpView.add(jpTotalProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, 130));

        jpLowStock.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/alerta.png"))); // NOI18N

        jLabel12.setText("-999");

        jLabel13.setText("Productos con bajo stock");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("999");

        javax.swing.GroupLayout jpLowStockLayout = new javax.swing.GroupLayout(jpLowStock);
        jpLowStock.setLayout(jpLowStockLayout);
        jpLowStockLayout.setHorizontalGroup(
            jpLowStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLowStockLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jpLowStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLowStockLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpLowStockLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(jpLowStockLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(21, 21, 21))))
        );
        jpLowStockLayout.setVerticalGroup(
            jpLowStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLowStockLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLowStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18))
        );

        jpView.add(jpLowStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 200, 130));

        jpRecentSales.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Ventas recientes");

        jpRecentSalesContent.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpRecentSalesContentLayout = new javax.swing.GroupLayout(jpRecentSalesContent);
        jpRecentSalesContent.setLayout(jpRecentSalesContentLayout);
        jpRecentSalesContentLayout.setHorizontalGroup(
            jpRecentSalesContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );
        jpRecentSalesContentLayout.setVerticalGroup(
            jpRecentSalesContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpRecentSalesLayout = new javax.swing.GroupLayout(jpRecentSales);
        jpRecentSales.setLayout(jpRecentSalesLayout);
        jpRecentSalesLayout.setHorizontalGroup(
            jpRecentSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRecentSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRecentSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(jpRecentSalesContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpRecentSalesLayout.setVerticalGroup(
            jpRecentSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRecentSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpRecentSalesContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpView.add(jpRecentSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 220, 320, 280));

        jpLowStockAlert.setBackground(new java.awt.Color(255, 255, 255));

        jlAleterTitle.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlAleterTitle.setText("Alertas de Bajo Stock");

        jpAlertPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpAlertPanelLayout = new javax.swing.GroupLayout(jpAlertPanel);
        jpAlertPanel.setLayout(jpAlertPanelLayout);
        jpAlertPanelLayout.setHorizontalGroup(
            jpAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpAlertPanelLayout.setVerticalGroup(
            jpAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpLowStockAlertLayout = new javax.swing.GroupLayout(jpLowStockAlert);
        jpLowStockAlert.setLayout(jpLowStockAlertLayout);
        jpLowStockAlertLayout.setHorizontalGroup(
            jpLowStockAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLowStockAlertLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLowStockAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlAleterTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(jpAlertPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpLowStockAlertLayout.setVerticalGroup(
            jpLowStockAlertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLowStockAlertLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlAleterTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpAlertPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpView.add(jpLowStockAlert, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 320, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpView, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpView, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jlAleterTitle;
    private javax.swing.JLabel jlTitleD;
    private javax.swing.JLabel jlTotalPP;
    private javax.swing.JLabel jlTotalPorcentaje;
    private javax.swing.JLabel jlTotalProducts;
    private javax.swing.JLabel jlTotalSales;
    private javax.swing.JPanel jpAlertPanel;
    private javax.swing.JPanel jpLowStock;
    private javax.swing.JPanel jpLowStockAlert;
    private javax.swing.JPanel jpRecentSales;
    private javax.swing.JPanel jpRecentSalesContent;
    private javax.swing.JPanel jpTotalProducts;
    private javax.swing.JPanel jpTotalSales;
    private javax.swing.JPanel jpView;
    // End of variables declaration//GEN-END:variables
}
