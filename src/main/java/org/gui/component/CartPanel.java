/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import org.utils.Product;
import org.utils.CartItem;

public class CartPanel extends JPanel {
    private Map<String, CartItemRow> cartRows;
    private JPanel itemsContainer;
    private JLabel subtotalLabel;
    private JLabel taxLabel;
    private JLabel totalLabel;
    private JButton registerButton;
    private double subtotal;
    private double tax;
    private double total;

    public CartPanel() {
        cartRows = new HashMap<>();
        initializeComponents();
        setupLayout();
        updateTotals();
    }

    private void initializeComponents() {
        // Panel principal
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));

        // Título
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Lista de productos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel cartIcon = new JLabel(new ImageIcon("path/to/cart-icon.png")); // Ajusta la ruta
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(cartIcon, BorderLayout.EAST);

        // Contenedor de items
        itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));
        itemsContainer.setBackground(Color.WHITE);

        // Panel de totales
        JPanel totalsPanel = new JPanel();
        totalsPanel.setLayout(new GridLayout(3, 2, 5, 5));
        totalsPanel.setBackground(Color.WHITE);

        JLabel subtotalTitleLabel = new JLabel("Subtotal");
        subtotalTitleLabel.setForeground(new Color(153, 153, 153));
        subtotalLabel = new JLabel("$0.00");

        JLabel taxTitleLabel = new JLabel("Tax (10%)");
        taxTitleLabel.setForeground(new Color(153, 153, 153));
        taxLabel = new JLabel("$0.00");

        JLabel totalTitleLabel = new JLabel("Total");
        totalTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalLabel = new JLabel("$0.00");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        totalsPanel.add(subtotalTitleLabel);
        totalsPanel.add(subtotalLabel);
        totalsPanel.add(taxTitleLabel);
        totalsPanel.add(taxLabel);
        totalsPanel.add(totalTitleLabel);
        totalsPanel.add(totalLabel);

        // Botón de registro
        registerButton = new JButton("Registrar Venta");
        registerButton.setBackground(new Color(63, 81, 181));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> registerSale());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Scroll para los items
        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Panel para totales y botón
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(new JSeparator());
        bottomPanel.add(Box.createVerticalStrut(10));

        // Agregar componentes al panel principal
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addProduct(Product product) {
        if (cartRows.containsKey(product.getName())) {
            CartItemRow row = cartRows.get(product.getName());
            row.incrementQuantity();
        } else {
            CartItemRow newRow = new CartItemRow(product);
            cartRows.put(product.getName(), newRow);
            itemsContainer.add(newRow);
            itemsContainer.revalidate();
        }
        updateTotals();
    }

    private void updateTotals() {
        subtotal = 0;
        for (CartItemRow row : cartRows.values()) {
            subtotal += row.getTotal();
        }
        
        tax = subtotal * 0.10;
        total = subtotal + tax;

        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", total));
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
                // Aquí iría la lógica para registrar la venta
                clearCart();
                JOptionPane.showMessageDialog(
                    this,
                    "Venta registrada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void clearCart() {
        cartRows.clear();
        itemsContainer.removeAll();
        itemsContainer.revalidate();
        itemsContainer.repaint();
        updateTotals();
    }

    // Clase interna para manejar cada fila del carrito
    private class CartItemRow extends JPanel {
        private final Product product;
        private int quantity;
        private JLabel quantityLabel;
        private JLabel priceLabel;

        public CartItemRow(Product product) {
            this.product = product;
            this.quantity = 1;
            initializeRow();
        }

        private void initializeRow() {
            setLayout(new BorderLayout(10, 0));
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            // Panel izquierdo (nombre y precio)
            JPanel leftPanel = new JPanel(new GridLayout(2, 1));
            leftPanel.setBackground(Color.WHITE);
            
            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            
            priceLabel = new JLabel(String.format("$%.2f", product.getUnitPrice()));
            
            leftPanel.add(nameLabel);
            leftPanel.add(priceLabel);

            // Panel de cantidad
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            quantityPanel.setBackground(Color.WHITE);

            JLabel minusLabel = new JLabel("-");
            minusLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            minusLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (quantity > 1) {
                        decrementQuantity();
                    }
                }
            });

            quantityLabel = new JLabel(String.valueOf(quantity));
            quantityLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

            JLabel plusLabel = new JLabel("+");
            plusLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            plusLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    incrementQuantity();
                }
            });

            quantityPanel.add(minusLabel);
            quantityPanel.add(quantityLabel);
            quantityPanel.add(plusLabel);

            add(leftPanel, BorderLayout.WEST);
            add(quantityPanel, BorderLayout.EAST);
        }

        public void incrementQuantity() {
            quantity++;
            updateQuantityDisplay();
        }

        public void decrementQuantity() {
            if (quantity > 1) {
                quantity--;
                updateQuantityDisplay();
            }
        }

        private void updateQuantityDisplay() {
            quantityLabel.setText(String.valueOf(quantity));
            priceLabel.setText(String.format("$%.2f", getTotal()));
            updateTotals();
        }

        public double getTotal() {
            return product.getUnitPrice() * quantity;
        }
    }
}
