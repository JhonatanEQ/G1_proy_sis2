/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gui.component;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollPane extends JScrollPane {
    
    public CustomScrollPane(Component view) {
        super(view);
        setupScrollPane();
    }
    
    private void setupScrollPane() {
        this.setBorder(null);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.getVerticalScrollBar().setUnitIncrement(16);
        this.setBackground(Color.WHITE);
        
        JScrollBar verticalBar = this.getVerticalScrollBar();
        verticalBar.setUI(new ModernScrollBarUI());
        verticalBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
    }
    
    private static class ModernScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(210, 210, 210);
            this.trackColor = Color.WHITE;
        }
        
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }
        
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setPaint(thumbColor);
            g2.fillRoundRect(thumbBounds.x + 1, thumbBounds.y + 1, 
                           thumbBounds.width - 2, thumbBounds.height - 2, 
                           8, 8);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setPaint(trackColor);
            g2.fillRect(trackBounds.x, trackBounds.y, 
                       trackBounds.width, trackBounds.height);
            g2.dispose();
        }
    }
}