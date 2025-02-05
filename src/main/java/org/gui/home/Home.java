/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.gui.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.gui.alerts.AlertsView;
import org.gui.billing.BillingView;
import org.gui.dashboad.Dashboard;
import org.gui.inventory.InventoryView;
import org.gui.product.ProductView;
import org.gui.reports.ReportsViews;
import org.gui.sales.SalesView;
import org.gui.settings.Settings;

/**
 *
 * @author Encin
 */
public class Home extends javax.swing.JFrame {

    /**
     * Variables Globales
     */
    private final Dashboard gDashboardPanel;
    private final ProductView gProductPanel;
    private final InventoryView gInventoryPanel;
    private final SalesView gSalesPanel;
    private final BillingView gBillingPanel;
    private final AlertsView gAlertsPanel;
    private final ReportsViews gReportsPanel;
    private final Settings gSettingsPanel;
    private JPanel gSelectedPanel;

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();

        // Crear primero el panel de inventario
        gInventoryPanel = new InventoryView();

        // Crear ProductView pasándole la referencia del inventario
        gProductPanel = new ProductView(gInventoryPanel);

        gDashboardPanel = new Dashboard();
        gSalesPanel = new SalesView();
        gBillingPanel = new BillingView();
        gAlertsPanel = new AlertsView();
        gReportsPanel = new ReportsViews();
        gSettingsPanel = new Settings();

        jpView.add(gDashboardPanel, BorderLayout.CENTER);
        initDefaultSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpMenu = new javax.swing.JPanel();
        jlTitle = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jpDashboard = new javax.swing.JPanel();
        jlDasboard = new javax.swing.JLabel();
        jlIconDash = new javax.swing.JLabel();
        jpInventory = new javax.swing.JPanel();
        jlIconInv = new javax.swing.JLabel();
        jlInv = new javax.swing.JLabel();
        jpProduct = new javax.swing.JPanel();
        jlIconP = new javax.swing.JLabel();
        jlProduct = new javax.swing.JLabel();
        jpSales = new javax.swing.JPanel();
        jlIconSales = new javax.swing.JLabel();
        jlSales = new javax.swing.JLabel();
        jpBilling = new javax.swing.JPanel();
        jlIconB = new javax.swing.JLabel();
        jlBilling = new javax.swing.JLabel();
        jpAlerts = new javax.swing.JPanel();
        jlIconA = new javax.swing.JLabel();
        jlAlerts = new javax.swing.JLabel();
        jpReports = new javax.swing.JPanel();
        jlIconR = new javax.swing.JLabel();
        jlReports = new javax.swing.JLabel();
        jpSettings = new javax.swing.JPanel();
        jlIconSet = new javax.swing.JLabel();
        jlSettings = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jpView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpMenu.setBackground(new java.awt.Color(255, 255, 255));
        jpMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlTitle.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jlTitle.setText("TechPoint Manager");
        jpMenu.add(jlTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 138, 24));
        jlTitle.getAccessibleContext().setAccessibleName("jlTitle");

        jpMenu.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 150, 20));

        jpDashboard.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.borderColor"));
        jpDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpDashboardMouseExited(evt);
            }
        });

        jlDasboard.setText("Dashboard");

        jlIconDash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconDash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/dash_select.png"))); // NOI18N
        jlIconDash.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpDashboardLayout = new javax.swing.GroupLayout(jpDashboard);
        jpDashboard.setLayout(jpDashboardLayout);
        jpDashboardLayout.setHorizontalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addComponent(jlIconDash, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlDasboard, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
        );
        jpDashboardLayout.setVerticalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addGroup(jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlDasboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlIconDash, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpMenu.add(jpDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 150, 40));

        jpInventory.setBackground(new java.awt.Color(255, 255, 255));
        jpInventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpInventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpInventoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpInventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpInventoryMouseExited(evt);
            }
        });

        jlIconInv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconInv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/inv.png"))); // NOI18N

        jlInv.setText("Inventario");

        javax.swing.GroupLayout jpInventoryLayout = new javax.swing.GroupLayout(jpInventory);
        jpInventory.setLayout(jpInventoryLayout);
        jpInventoryLayout.setHorizontalGroup(
            jpInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInventoryLayout.createSequentialGroup()
                .addComponent(jlIconInv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlInv, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpInventoryLayout.setVerticalGroup(
            jpInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlIconInv, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 150, 40));

        jpProduct.setBackground(new java.awt.Color(255, 255, 255));
        jpProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpProductMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpProductMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpProductMouseExited(evt);
            }
        });

        jlIconP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/product.png"))); // NOI18N
        jlIconP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlProduct.setText("Producto");

        javax.swing.GroupLayout jpProductLayout = new javax.swing.GroupLayout(jpProduct);
        jpProduct.setLayout(jpProductLayout);
        jpProductLayout.setHorizontalGroup(
            jpProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProductLayout.createSequentialGroup()
                .addComponent(jlIconP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
        );
        jpProductLayout.setVerticalGroup(
            jpProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlIconP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 150, 40));

        jpSales.setBackground(new java.awt.Color(255, 255, 255));
        jpSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpSalesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpSalesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpSalesMouseExited(evt);
            }
        });

        jlIconSales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/sales.png"))); // NOI18N

        jlSales.setText("Ventas");

        javax.swing.GroupLayout jpSalesLayout = new javax.swing.GroupLayout(jpSales);
        jpSales.setLayout(jpSalesLayout);
        jpSalesLayout.setHorizontalGroup(
            jpSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSalesLayout.createSequentialGroup()
                .addComponent(jlIconSales, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlSales, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
        );
        jpSalesLayout.setVerticalGroup(
            jpSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlIconSales, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 150, 40));

        jpBilling.setBackground(new java.awt.Color(255, 255, 255));
        jpBilling.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpBilling.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpBillingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpBillingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpBillingMouseExited(evt);
            }
        });

        jlIconB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconB.setText("x");

        jlBilling.setText("Facturas");

        javax.swing.GroupLayout jpBillingLayout = new javax.swing.GroupLayout(jpBilling);
        jpBilling.setLayout(jpBillingLayout);
        jpBillingLayout.setHorizontalGroup(
            jpBillingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBillingLayout.createSequentialGroup()
                .addComponent(jlIconB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlBilling, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
        );
        jpBillingLayout.setVerticalGroup(
            jpBillingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlIconB, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlBilling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpBilling, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 150, 40));

        jpAlerts.setBackground(new java.awt.Color(255, 255, 255));
        jpAlerts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpAlerts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpAlertsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpAlertsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpAlertsMouseExited(evt);
            }
        });

        jlIconA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconA.setText("x");

        jlAlerts.setText("Alertas");

        javax.swing.GroupLayout jpAlertsLayout = new javax.swing.GroupLayout(jpAlerts);
        jpAlerts.setLayout(jpAlertsLayout);
        jpAlertsLayout.setHorizontalGroup(
            jpAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAlertsLayout.createSequentialGroup()
                .addComponent(jlIconA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlAlerts, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
        );
        jpAlertsLayout.setVerticalGroup(
            jpAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlIconA, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlAlerts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpAlerts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 150, 40));

        jpReports.setBackground(new java.awt.Color(255, 255, 255));
        jpReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpReportsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpReportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpReportsMouseExited(evt);
            }
        });

        jlIconR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconR.setText("x");

        jlReports.setText("Reportes");

        javax.swing.GroupLayout jpReportsLayout = new javax.swing.GroupLayout(jpReports);
        jpReports.setLayout(jpReportsLayout);
        jpReportsLayout.setHorizontalGroup(
            jpReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReportsLayout.createSequentialGroup()
                .addComponent(jlIconR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlReports, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
        );
        jpReportsLayout.setVerticalGroup(
            jpReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlIconR, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 150, 40));

        jpSettings.setBackground(new java.awt.Color(255, 255, 255));
        jpSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpSettingsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpSettingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpSettingsMouseExited(evt);
            }
        });

        jlIconSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconSet.setText("x");

        jlSettings.setText("Configuracion");

        javax.swing.GroupLayout jpSettingsLayout = new javax.swing.GroupLayout(jpSettings);
        jpSettings.setLayout(jpSettingsLayout);
        jpSettingsLayout.setHorizontalGroup(
            jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSettingsLayout.createSequentialGroup()
                .addComponent(jlIconSet, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
        );
        jpSettingsLayout.setVerticalGroup(
            jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlIconSet, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 150, 40));

        getContentPane().add(jpMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 540));

        jSeparator1.setBackground(new java.awt.Color(229, 231, 235));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 231, 235)));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 2, 540));

        jpView.setBackground(new java.awt.Color(249, 250, 251));
        jpView.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jpView, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 0, 740, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpDashboardMouseClicked

        handleMenuClick(jpDashboard, jlIconDash, "/org/images/dash_select.png", jlDasboard, gDashboardPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpDashboardMouseClicked

    private void jpInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpInventoryMouseClicked
        // TODO add your handling code here:
        handleMenuClick(jpInventory, jlIconInv, "/org/images/inv_select.png", jlInv, gInventoryPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpInventoryMouseClicked

    private void jpProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpProductMouseClicked
        handleMenuClick(jpProduct, jlIconP, "/org/images/product_select.png", jlProduct, gProductPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpProductMouseClicked

    private void jpBillingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBillingMouseClicked
        handleMenuClick(jpBilling, jlIconB, "/org/images/inv_select.png", jlBilling, gBillingPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpBillingMouseClicked

    private void jpAlertsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpAlertsMouseClicked
        // TODO add your handling code here:
        handleMenuClick(jpAlerts, jlIconA, "/org/images/inv_select.png", jlAlerts, gAlertsPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpAlertsMouseClicked

    private void jpReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpReportsMouseClicked
        // TODO add your handling code here:
        handleMenuClick(jpReports, jlIconR, "/org/images/inv_select.png", jlReports, gReportsPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpReportsMouseClicked

    private void jpSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpSettingsMouseClicked
        // TODO add your handling code here:
        handleMenuClick(jpSettings, jlIconSet, "/org/images/inv_select.png", jlSettings, gSettingsPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpSettingsMouseClicked

    private void jpDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpDashboardMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpSettings) {
            handleMouseEntered(jpSettings, jlIconDash, "/org/images/dashH.png", jlDasboard, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpDashboardMouseEntered

    private void jpInventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpInventoryMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpInventory) {
            handleMouseEntered(jpInventory, jlIconInv, "/org/images/inv_select.png", jlInv, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpInventoryMouseEntered

    private void jpInventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpInventoryMouseExited
        // TODO add your handling code here:
        if (gSelectedPanel != jpInventory) {
            handleMouseExited(jpInventory, jlIconInv, "/org/images/inv.png", jlInv, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpInventoryMouseExited

    private void jpProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpProductMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpProduct) {
            handleMouseEntered(jpProduct, jlIconP, "/org/images/product_select.png", jlProduct, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpProductMouseEntered

    private void jpProductMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpProductMouseExited
        // TODO add your handling code here:
        if (gSelectedPanel != jpProduct) {
            handleMouseExited(jpProduct, jlIconP, "/org/images/product.png", jlProduct, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpProductMouseExited

    private void jpSalesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpSalesMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpSales) {
            handleMouseEntered(jpSales, jlIconSales, "/org/images/sales_select.png", jlSales, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpSalesMouseEntered

    private void jpSalesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpSalesMouseExited
        // TODO add your handling code here:
        if (gSelectedPanel != jpSales) {
            handleMouseExited(jpSales, jlIconSales, "/org/images/sales.png", jlSales, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpSalesMouseExited

    private void jpSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpSalesMouseClicked
        // TODO add your handling code here:
        handleMenuClick(jpSales, jlIconSales, "/org/images/inv_select.png", jlSales, gSalesPanel);
        refreshAllViews();
    }//GEN-LAST:event_jpSalesMouseClicked

    private void jpBillingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBillingMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpBilling) {
            handleMouseEntered(jpBilling, jlIconB, "/org/images/product_select.png", jlBilling, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpBillingMouseEntered

    private void jpBillingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBillingMouseExited
        // TODO add your handling code here:
        if (gSelectedPanel != jpBilling) {
            handleMouseExited(jpBilling, jlIconB, "/org/images/inv.png", jlBilling, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpBillingMouseExited

    private void jpAlertsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpAlertsMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpAlerts) {
            handleMouseEntered(jpAlerts, jlIconA, "/org/images/product_select.png", jlAlerts, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpAlertsMouseEntered

    private void jpAlertsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpAlertsMouseExited
        // TODO add your handling code here:

        if (gSelectedPanel != jpAlerts) {
            handleMouseExited(jpAlerts, jlIconA, "/org/images/inv.png", jlAlerts, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpAlertsMouseExited

    private void jpReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpReportsMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpReports) {
            handleMouseEntered(jpReports, jlIconR, "/org/images/product_select.png", jlReports, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpReportsMouseEntered

    private void jpReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpReportsMouseExited
        // TODO add your handling code here:
        if (gSelectedPanel != jpReports) {
            handleMouseExited(jpReports, jlIconR, "/org/images/inv.png", jlReports, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpReportsMouseExited

    private void jpSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpSettingsMouseEntered
        // TODO add your handling code here:
        if (gSelectedPanel != jpSettings) {
            handleMouseEntered(jpSettings, jlIconSet, "/org/images/product_select.png", jlSettings, "#F1F6FD", "#4061DB");
        }
    }//GEN-LAST:event_jpSettingsMouseEntered

    private void jpSettingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpSettingsMouseExited
        // TODO add your handling code here:
        if (gSelectedPanel != jpSettings) {
            handleMouseExited(jpSettings, jlIconSet, "/org/images/inv.png", jlSettings, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpSettingsMouseExited

    private void jpDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpDashboardMouseExited
        // TODO add your handling code here:
         if (gSelectedPanel != jpDashboard) {
            handleMouseExited(jpDashboard, jlIconDash, "/org/images/dash_select.png", jlDasboard, "#FFFFFF", "#4E5561");
        }
    }//GEN-LAST:event_jpDashboardMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jlAlerts;
    private javax.swing.JLabel jlBilling;
    private javax.swing.JLabel jlDasboard;
    private javax.swing.JLabel jlIconA;
    private javax.swing.JLabel jlIconB;
    private javax.swing.JLabel jlIconDash;
    private javax.swing.JLabel jlIconInv;
    private javax.swing.JLabel jlIconP;
    private javax.swing.JLabel jlIconR;
    private javax.swing.JLabel jlIconSales;
    private javax.swing.JLabel jlIconSet;
    private javax.swing.JLabel jlInv;
    private javax.swing.JLabel jlProduct;
    private javax.swing.JLabel jlReports;
    private javax.swing.JLabel jlSales;
    private javax.swing.JLabel jlSettings;
    private javax.swing.JLabel jlTitle;
    private javax.swing.JPanel jpAlerts;
    private javax.swing.JPanel jpBilling;
    private javax.swing.JPanel jpDashboard;
    private javax.swing.JPanel jpInventory;
    private javax.swing.JPanel jpMenu;
    private javax.swing.JPanel jpProduct;
    private javax.swing.JPanel jpReports;
    private javax.swing.JPanel jpSales;
    private javax.swing.JPanel jpSettings;
    private javax.swing.JPanel jpView;
    // End of variables declaration//GEN-END:variables

    private void showPanel(JPanel panel) {
        jpView.removeAll();
        jpView.add(panel, BorderLayout.CENTER);
        jpView.revalidate();
        jpView.repaint();
    }

    private void initDefaultSelection() {
        gSelectedPanel = jpDashboard;
        handleMouseEntered(jpDashboard, jlIconDash, "/org/images/dashH.png", jlDasboard, "#F1F6FD", "#4061DB");
        showPanel(gDashboardPanel);
    }

    private void handleMouseEntered(JPanel panel, JLabel icon, String iconPath, JLabel textLabel, String backgroundColor, String textColor) {
        panel.setBackground(Color.decode(backgroundColor));
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconPath)));
        textLabel.setForeground(Color.decode(textColor));
    }

    private void handleMouseExited(JPanel panel, JLabel icon, String iconPath, JLabel textLabel, String backgroundColor, String textColor) {
        panel.setBackground(Color.decode(backgroundColor));
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconPath)));
        textLabel.setForeground(Color.decode(textColor));
    }

    public void handleMenuClick(JPanel panel, JLabel icon, String iconPathSelected, JLabel textLabel, JPanel targetPanel) {
        if (gSelectedPanel != null) {
            resetPanelState(gSelectedPanel);
        }
        handleMouseEntered(panel, icon, iconPathSelected, textLabel, "#F1F6FD", "#4061DB");
        gSelectedPanel = panel;

        // Mostrar el panel solicitado
        showPanel(targetPanel);

        // Refrescar los datos del panel actual
        if (targetPanel == gInventoryPanel) {
            ((InventoryView) gInventoryPanel).refreshData();
        } else if (targetPanel == gDashboardPanel) {
            ((Dashboard) gDashboardPanel).loadDashboardData();
        } else if (targetPanel == gSalesPanel) {
            ((SalesView) gSalesPanel).refreshData();
        }
    }


    private void resetPanelState(JPanel panel) {
        if (panel == jpDashboard) {
            handleMouseExited(jpDashboard, jlIconDash, "/org/images/dash_select.png", jlDasboard, "#FFFFFF", "#4E5561");
        } else if (panel == jpInventory) {
            handleMouseExited(jpInventory, jlIconInv, "/org/images/inv.png", jlInv, "#FFFFFF", "#4E5561");
        } else if (panel == jpProduct) {
            handleMouseExited(jpProduct, jlIconP, "/org/images/product.png", jlProduct, "#FFFFFF", "#4E5561");
        } else if (panel == jpSales) {
            handleMouseExited(jpSales, jlIconSales, "/org/images/sales.png", jlSales, "#FFFFFF", "#4E5561");
        }
        // Agregar más paneles aquí según sea necesario...
    }

    public BillingView getBillingPanel() {
        return gBillingPanel;
    }

    public JPanel getBillingButton() {
        return jpBilling;
    }

    public JLabel getIconBilling() {
        return jlIconB;
    }

    public JLabel getLabelBilling() {
        return jlBilling;
    }
    
    private void refreshAllViews() {
        // Actualizar Dashboard
        if (gDashboardPanel != null) {
            gDashboardPanel.refreshDashboard();
        }

        // Actualizar Inventario
        if (gInventoryPanel != null) {
            gInventoryPanel.refreshData();
        }

        // Actualizar Vista de Ventas
        if (gSalesPanel != null) {
            gSalesPanel.refreshData();
        }
    }
    
    public void requestViewsUpdate() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                refreshAllViews();
                return null;
            }

            @Override
            protected void done() {
                setCursor(Cursor.getDefaultCursor());
            }
        };

        worker.execute();
    }

}
