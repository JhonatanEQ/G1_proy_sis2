package org.gui.component.ListaDeProductos;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.gui.component.CircleBorder;
import org.services.utils.Product;

public class ItemSalesProduct extends JPanel {
    private Product product;
    private JLabel nameLabel;
    private JLabel quantityLabel;
    private JLabel minusButton;
    private JLabel plusButton;
    private JLabel deleteButton;
    private int quantity;
    private ImageIcon deleteIcon;
    private ImageIcon deleteHoverIcon;

    public ItemSalesProduct(Product product) {
        this.product = product;
        this.quantity = 1;
        initComponents();
        setupStyle();
        setupListeners();
    }

    private void initComponents() {
        setLayout(new BorderLayout(5, 0));
        setBackground(Color.WHITE);

        // Panel izquierdo para el nombre y botón de eliminar
        JPanel leftPanel = new JPanel(new BorderLayout(8, 0));
        leftPanel.setBackground(Color.WHITE);

        // Botón eliminar
        
        deleteIcon = new ImageIcon(getClass().getResource("/org/images/borrar.png"));
        deleteHoverIcon = new ImageIcon(getClass().getResource("/org/images/delete.png"));
        deleteButton = new JLabel(new ImageIcon(getClass().getResource("/org/images/borrar.png")));
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setOpaque(true);
        deleteButton.setBorder(null);
        deleteButton.setPreferredSize(new Dimension(16, 16));
        deleteButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Nombre del producto
        nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        nameLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        leftPanel.add(deleteButton, BorderLayout.WEST);
        leftPanel.add(nameLabel, BorderLayout.CENTER);

        // Panel derecho para los controles de cantidad
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        quantityPanel.setBackground(Color.WHITE);

        // Botón menos
        minusButton = new JLabel(new ImageIcon(getClass().getResource("/org/images/signo-menos.png")));
        minusButton.setBackground(Color.WHITE);
        minusButton.setOpaque(true);
        minusButton.setBorder(null);
        minusButton.setPreferredSize(new Dimension(16, 16));
        minusButton.setHorizontalAlignment(SwingConstants.CENTER);
        minusButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Label cantidad
        quantityLabel = new JLabel("1");
        quantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        quantityLabel.setBorder(new EmptyBorder(0, 12, 0, 12));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityLabel.setPreferredSize(new Dimension(40, 38));

        // Botón más
        plusButton = new JLabel(new ImageIcon(getClass().getResource("/org/images/anadir.png")));
        plusButton.setBackground(Color.WHITE);
        plusButton.setOpaque(true);
        plusButton.setBorder(null);
        plusButton.setPreferredSize(new Dimension(16, 16));
        plusButton.setHorizontalAlignment(SwingConstants.CENTER);
        plusButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        quantityPanel.add(minusButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusButton);

        add(leftPanel, BorderLayout.CENTER);
        add(quantityPanel, BorderLayout.EAST);
    }

    private void setupStyle() {
        setPreferredSize(new Dimension(180, 38));
        setMaximumSize(new Dimension(180, 38));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));
    }

    private void setupListeners() {
        // Efectos hover para el botón eliminar
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteButton.setIcon(deleteHoverIcon);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteButton.setIcon(deleteIcon);
            }
        });

        minusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (quantity > 1) {
                    quantity--;
                    quantityLabel.setText(String.valueOf(quantity));
                }
            }
        });

        plusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quantity++;
                quantityLabel.setText(String.valueOf(quantity));
            }
        });
    }

    // Getters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        quantityLabel.setText(String.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }
    
    public JLabel getMinusButton() {
        return minusButton;
    }

    public JLabel getPlusButton() {
        return plusButton;
    }

    public JLabel getDeleteButton() {
        return deleteButton;
    }
}