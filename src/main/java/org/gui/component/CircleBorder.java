package org.gui.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.border.AbstractBorder;

public class CircleBorder extends AbstractBorder {
    private Color color;
    private int thickness;
    private int radius;

    public CircleBorder(Color color, int thickness, int radius) {
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        
        // Rellenar el círculo
        g2d.setColor(c.getBackground());
        g2d.fill(new Ellipse2D.Double(x, y, width - 1, height - 1));
        
        // Dibujar el borde
        g2d.setColor(color);
        g2d.draw(new Ellipse2D.Double(x, y, width - 1, height - 1));
        
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 1, 1);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}