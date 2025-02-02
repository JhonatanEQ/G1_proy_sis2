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


public class ItemProduct extends javax.swing.JPanel {
    private Product gProduct; 
    private ArrayList<Product> gProductsList; 
    private Map<JLabel, Product> labelProductMap = new HashMap<>(); 

    /**
     * Constructor de la clase CartProduct.
     *
     * @param lProduct Producto a cargar
     */
    public ItemProduct(Product lProduct) {
        gProduct = lProduct;
        initComponents();
        loadProductData();
        setupStyle();
    }
    
    
    private void setupStyle() {
        this.setBackground(Color.WHITE);
        this.setBorder(new RoundedBorder(8, new Color(240, 240, 240)));
        this.setPreferredSize(new Dimension(300, 45));

        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridLayout(1, 5, 10, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        imagePanel.setBackground(Color.WHITE);
        productImageLabel.setPreferredSize(new Dimension(30, 30));
        imagePanel.add(productImageLabel);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        namePanel.setBackground(Color.WHITE);
        productNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        namePanel.add(productNameLabel);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        pricePanel.setBackground(Color.WHITE);
        currencySymbolLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        priceValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pricePanel.add(currencySymbolLabel);
        pricePanel.add(priceValueLabel);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        statusPanel.setBackground(Color.WHITE);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusPanel.add(statusLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(Color.WHITE);
        addToCartButton.setBorder(new RoundedBorder(12, new Color(230, 230, 230)));
        addToCartButton.setPreferredSize(new Dimension(25, 25));
        buttonPanel.add(addToCartButton);

        mainPanel.add(imagePanel); 
        mainPanel.add(namePanel); 
        mainPanel.add(pricePanel);  
        mainPanel.add(statusPanel);    
        mainPanel.add(buttonPanel);    
    }
    
    /**
     * Carga los datos del producto en los componentes visuales.
     */
    private void loadProductData() {
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
        
        priceValueLabel.setText(String.format("%.2f", gProduct.getUnitPrice()));
        priceValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        currencySymbolLabel.setText("$");
        currencySymbolLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Status styling
        statusLabel.setText(gProduct.getStatus() ? "Inactive" : "Active");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(gProduct.getStatus() ? 
            new Color(231, 76, 60) : new Color(39, 174, 96));
        
        // Add to cart button icon
        ImageIcon addIcon = new ImageIcon(getClass().getResource("/org/images/cc1.png"));
        Image scaledAdd = addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        addToCartButton.setIcon(new ImageIcon(scaledAdd));
        addToCartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción al hacer clic en jLabel2.
     */
    private void addToCartButtonClicked(java.awt.event.MouseEvent evt) {
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
        mainPanel = new javax.swing.JPanel();
        productImageLabel = new javax.swing.JLabel();
        addToCartButton = new javax.swing.JLabel();
        productNameLabel = new javax.swing.JLabel();
        priceValueLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        currencySymbolLabel = new javax.swing.JLabel();

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        // Añadir el listener para el botón de agregar al carrito
        addToCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToCartButtonClicked(evt);
            }
        });
    }
    public JLabel getAddToCartButton() {
        return addToCartButton;
    }

    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;  
    private javax.swing.JLabel jLabel2;  
    private javax.swing.JLabel jLabel3; 
    private javax.swing.JLabel jLabel4;  
    private javax.swing.JLabel jLabel5;  
    private javax.swing.JLabel jLabel6; 
    
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel productImageLabel;  
    private javax.swing.JLabel addToCartButton;  
    private javax.swing.JLabel productNameLabel; 
    private javax.swing.JLabel priceValueLabel;  
    private javax.swing.JLabel statusLabel;  
    private javax.swing.JLabel currencySymbolLabel;
}
