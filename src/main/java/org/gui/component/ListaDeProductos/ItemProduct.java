package org.gui.component.ListaDeProductos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.services.utils.Product;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.gui.component.RoundedBorder;
import org.gui.sales.SalesView;

public class ItemProduct extends javax.swing.JPanel {
    private Product gProduct; 
    private ArrayList<Product> gProductsList; 
    private Map<JLabel, Product> labelProductMap = new HashMap<>(); 
    private SalesView salesView; 

    public ItemProduct(Product lProduct, SalesView salesView) {
        this.gProduct = lProduct;
        this.salesView = salesView; 
        initComponents();
        loadProductData();
        setupStyle();
    }
    
    private void setupStyle() {
        this.setBackground(Color.WHITE);
        this.setBorder(new RoundedBorder(8, new Color(240, 240, 240)));
        this.setPreferredSize(new Dimension(400, 60));
        this.setLayout(new BorderLayout(10, 10));

        // Imagen a la izquierda
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        imagePanel.setPreferredSize(new Dimension(50, 50));
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(new RoundedBorder(8, new Color(240, 240, 240)));
        productImageLabel.setPreferredSize(new Dimension(50, 50));
        productImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(productImageLabel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        infoPanel.setBackground(Color.WHITE);

        productNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        productNameLabel.setForeground(new Color(44, 62, 80)); // Azul oscuro

        productPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productPriceLabel.setForeground(new Color(127, 140, 141)); // Gris claro

        infoPanel.add(productNameLabel);
        infoPanel.add(productPriceLabel);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightPanel.setBackground(Color.WHITE);

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rightPanel.add(statusLabel);

        addToCartButton.setPreferredSize(new Dimension(30, 30));
        addToCartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(addToCartButton);

        this.add(imagePanel, BorderLayout.WEST);
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST); 
    }
    
    private void loadProductData() {
        
        boolean status = gProduct.getCurrentStock()<=gProduct.getMinimumStock();
        // Image setup
        if (gProduct.getImage() != null && !gProduct.getImage().isEmpty()) {
            ImageIcon icon = new ImageIcon(gProduct.getImage());
            java.awt.Image img = icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
            productImageLabel.setIcon(new ImageIcon(img));
        }

        // Product details
        productNameLabel.setText(gProduct.getName());
        productNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        productNameLabel.setForeground(new Color(51, 51, 51));
        
        productPriceLabel.setText("$ " + String.format("%.2f", gProduct.getUnitPrice()));
        productPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productPriceLabel.setForeground(new Color(128, 128, 128));
        
        // Status styling
        statusLabel.setText(status ? "Inactive" : "Active");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(status ? new Color(231, 76, 60) : new Color(39, 174, 96));
        
        // Add to cart button icon
        ImageIcon addIcon = new ImageIcon(getClass().getResource("/org/images/plus.png"));
        Image scaledAdd = addIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        addToCartButton.setIcon(new ImageIcon(scaledAdd));
        addToCartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addToCartButtonClicked(java.awt.event.MouseEvent evt) {
        // En lugar de mostrar un mensaje, agregamos el producto al carrito
        salesView.addToCart(gProduct);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        mainPanel = new javax.swing.JPanel();
        productImageLabel = new javax.swing.JLabel();
        addToCartButton = new javax.swing.JLabel();
        productNameLabel = new javax.swing.JLabel();
        productPriceLabel = new javax.swing.JLabel(); 
        statusLabel = new javax.swing.JLabel();

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        addToCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToCartButtonClicked(evt);
            }
        });
    }
    public JLabel getAddToCartButton() {
        return addToCartButton;
    }

    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel productImageLabel;  
    private javax.swing.JLabel addToCartButton;  
    private javax.swing.JLabel productNameLabel; 
    private javax.swing.JLabel productPriceLabel; 
    private javax.swing.JLabel statusLabel;
}