/*
 * Copyright 2018 1&1 Internet SE.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oneandone.gameoflife.ui;

import com.oneandone.gameoflife.Field;
import com.oneandone.gameoflife.BooleanFieldImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;

/**
 * A canvas showing the current game field as
 * filled rectangle cells. The cells are painted
 * in either {@linkplain #aliveColor} or
 * {@linkplain #deadColor}.
 * The canvas is clickable and can be painted with
 * mouse clicks.
 * @author Stephan Fuhrmann
 */
public class LifeContentCanvas extends JPanel {
    
    /** The field we're rendering. */
    @Getter
    private Field field;

    /** The color of alive cells. */
    @Getter @Setter
    private Color aliveColor;
    
    /** The color of dead cells. */
    @Getter @Setter
    private Color deadColor;

    /** The mouse listener for changing the pixels in the {@link #field}.
     */
    private final MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                float pixelWidth = pixelWidth();
                float pixelHeight = pixelHeight();
                
                int xIndex = (int)(x / pixelWidth);
                int yIndex = (int)(y / pixelHeight);
                
                boolean oldValue = field.get(xIndex, yIndex);
                boolean newValue = !oldValue;
                field.set(xIndex, yIndex, newValue);
                repaint(new Rectangle(
                        Math.round(pixelWidth * xIndex), 
                        Math.round(pixelHeight * yIndex),
                        Math.round(pixelWidth),
                        Math.round(pixelHeight)));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    
    public LifeContentCanvas() {
        this(new BooleanFieldImpl(10, 10));
    }
    
    public LifeContentCanvas(Field field) {
        this.field = field;
        aliveColor = Color.WHITE;
        deadColor = Color.BLACK;
        
        super.addMouseListener(mouseListener);
    }
    
    /** Sets the rendered field to the given instance and repaints
     * the canvas.
     * @param f the field to render.
     */
    public void setField(Field f) {
        this.field = f;
        repaint();
    }
    
    /** Width of a cell in pixels. */
    private float pixelWidth() {
        float pixelWidth = (float)(getWidth() / field.getDimensions().getWidth());
        return pixelWidth;
    }
    
    /** Height of a cell in pixels. */
    private float pixelHeight() {
        float pixelHeight = (float)(getHeight() / field.getDimensions().getHeight());
        return pixelHeight;
    }

    @Override
    public void paintComponent(Graphics g) {       
        super.paintComponent(g);
        Dimension dimension = field.getDimensions();
        float pixelWidth = pixelWidth();
        float pixelHeight = pixelHeight();
        
        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                float pixelX = x * pixelWidth;
                float pixelY = y * pixelHeight;
                
                boolean alive = field.get(x, y);
                Color color;
                if (alive) {
                    color = aliveColor;
                } else {
                    color = deadColor;
                }
                
                g.setColor(color);
                g.fillRect(
                        Math.round(pixelX), 
                        Math.round(pixelY),
                        1 + Math.round(pixelWidth), 
                        1 + Math.round(pixelHeight));
            }
        }
    }
}
