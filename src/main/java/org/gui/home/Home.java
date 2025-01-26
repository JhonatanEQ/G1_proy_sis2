/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.gui.home;

import java.awt.BorderLayout;
import javax.swing.JPanel;
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
    
    

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        gDashboardPanel = new Dashboard();
        gProductPanel = new ProductView();
        gInventoryPanel = new InventoryView();
        gSalesPanel = new SalesView();
        gBillingPanel = new BillingView();
        gAlertsPanel = new AlertsView();
        gReportsPanel = new ReportsViews();
        gSettingsPanel = new Settings();
        jpView.add(gDashboardPanel, BorderLayout.CENTER);
        
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
        jlIconD = new javax.swing.JLabel();
        jlbuttonDasboard = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        jlBilling = new javax.swing.JLabel();
        jpAlerts = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpReports = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        });

        jlIconD.setBackground(new java.awt.Color(255, 255, 255));
        jlIconD.setForeground(new java.awt.Color(51, 153, 255));
        jlIconD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/dash_select.png"))); // NOI18N

        jlbuttonDasboard.setText("Dashboard");

        javax.swing.GroupLayout jpDashboardLayout = new javax.swing.GroupLayout(jpDashboard);
        jpDashboard.setLayout(jpDashboardLayout);
        jpDashboardLayout.setHorizontalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addComponent(jlIconD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbuttonDasboard, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jpDashboardLayout.setVerticalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addGroup(jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlbuttonDasboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlIconD, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpMenu.add(jpDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 150, 40));

        jpInventory.setBackground(new java.awt.Color(255, 255, 255));
        jpInventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpInventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpInventoryMouseClicked(evt);
            }
        });

        jlIconInv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconInv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/4544845-business-comerce-delivery-list-shop_121445 (1).png"))); // NOI18N

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
        });

        jlIconP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/‌inventory.png"))); // NOI18N
        jlIconP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlProduct.setText("Producto");
        jlProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlProductMouseClicked(evt);
            }
        });

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

        jlIconSales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlIconSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/sales.png"))); // NOI18N

        jlSales.setText("Ventas");
        jlSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlSalesMouseClicked(evt);
            }
        });

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
            .addComponent(jlSales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 150, 40));

        jpBilling.setBackground(new java.awt.Color(255, 255, 255));
        jpBilling.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpBilling.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpBillingMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("x");

        jlBilling.setText("Facturas");
        jlBilling.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlBillingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpBillingLayout = new javax.swing.GroupLayout(jpBilling);
        jpBilling.setLayout(jpBillingLayout);
        jpBillingLayout.setHorizontalGroup(
            jpBillingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBillingLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlBilling, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
        );
        jpBillingLayout.setVerticalGroup(
            jpBillingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jlBilling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpBilling, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 150, 40));

        jpAlerts.setBackground(new java.awt.Color(255, 255, 255));
        jpAlerts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpAlerts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpAlertsMouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("x");

        jLabel3.setText("Alertas");

        javax.swing.GroupLayout jpAlertsLayout = new javax.swing.GroupLayout(jpAlerts);
        jpAlerts.setLayout(jpAlertsLayout);
        jpAlertsLayout.setHorizontalGroup(
            jpAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAlertsLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
        );
        jpAlertsLayout.setVerticalGroup(
            jpAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpAlerts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 150, 40));

        jpReports.setBackground(new java.awt.Color(255, 255, 255));
        jpReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpReportsMouseClicked(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("x");

        jLabel5.setText("Reportes");

        javax.swing.GroupLayout jpReportsLayout = new javax.swing.GroupLayout(jpReports);
        jpReports.setLayout(jpReportsLayout);
        jpReportsLayout.setHorizontalGroup(
            jpReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReportsLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
        );
        jpReportsLayout.setVerticalGroup(
            jpReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpMenu.add(jpReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 150, 40));

        jpSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpSettingsMouseClicked(evt);
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
        // TODO add your handling code here:
        showPanel(gDashboardPanel);
    }//GEN-LAST:event_jpDashboardMouseClicked

    private void jpInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpInventoryMouseClicked
        // TODO add your handling code here:
        showPanel(gInventoryPanel);
    }//GEN-LAST:event_jpInventoryMouseClicked

    private void jlProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlProductMouseClicked
        // TODO add your handling code here:
        showPanel(gProductPanel);
    }//GEN-LAST:event_jlProductMouseClicked

    private void jpProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpProductMouseClicked

    }//GEN-LAST:event_jpProductMouseClicked

    private void jlBillingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlBillingMouseClicked
        // TODO add your handling code here:
        showPanel(gBillingPanel);
    }//GEN-LAST:event_jlBillingMouseClicked

    private void jpBillingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBillingMouseClicked

    }//GEN-LAST:event_jpBillingMouseClicked

    private void jpAlertsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpAlertsMouseClicked
        // TODO add your handling code here:
        showPanel(gAlertsPanel);
    }//GEN-LAST:event_jpAlertsMouseClicked

    private void jpReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpReportsMouseClicked
        // TODO add your handling code here:
        showPanel(gReportsPanel);
    }//GEN-LAST:event_jpReportsMouseClicked

    private void jpSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpSettingsMouseClicked
        // TODO add your handling code here:
        showPanel(gSettingsPanel);
    }//GEN-LAST:event_jpSettingsMouseClicked

    private void jlSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlSalesMouseClicked
        // TODO add your handling code here:
        showPanel(gSalesPanel);
    }//GEN-LAST:event_jlSalesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jlBilling;
    private javax.swing.JLabel jlIconD;
    private javax.swing.JLabel jlIconInv;
    private javax.swing.JLabel jlIconP;
    private javax.swing.JLabel jlIconSales;
    private javax.swing.JLabel jlIconSet;
    private javax.swing.JLabel jlInv;
    private javax.swing.JLabel jlProduct;
    private javax.swing.JLabel jlSales;
    private javax.swing.JLabel jlSettings;
    private javax.swing.JLabel jlTitle;
    private javax.swing.JLabel jlbuttonDasboard;
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
}
