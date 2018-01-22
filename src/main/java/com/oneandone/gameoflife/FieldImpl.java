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
package com.oneandone.gameoflife;

import java.awt.Dimension;

/**
 * Implementation of a field using a boolean array.
 * @author Stephan Fuhrmann
 */
public class FieldImpl implements Field {

    /** The cell status. First dimension is width, second dimension is height.
     */
    private final boolean alive[][];
    
    /** The width in cells. */
    private final int width;
    
    /** The height in cells. */
    private final int height;
    
    /** The dimension object returned by {@link #getDimensions() }. */
    private final Dimension dimension;
    
    /**
     * Creates a new instance.
     * @param width the width in cells.
     * @param height the height in cells.
     */
    public FieldImpl(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width <= 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height <= 0");
        }
        alive = new boolean[width][height];
        this.width = width;
        this.height = height;
        this.dimension = new Dimension(width, height);
    }
    
    @Override
    public boolean get(int x, int y) {
        return alive[x][y];
    }

    @Override
    public void set(int x, int y, boolean set) {
        alive[x][y] = set;
    }

    @Override
    public Dimension getDimensions() {
        return dimension;
    }

    @Override
    public int getNeighborCount(int x, int y) {
        int sum = 0;
        for (int yi = -1; yi <= 1; yi++) {
            for (int xi = -1; xi <= 1; xi++) {
                if (xi == 0 && yi == 0) {
                    // don't count cell itself
                    continue;
                }
                
                int xr = x + xi;
                int yr = y + yi;
                
                if (xr >= 0 && xr < width && yr >= 0 && yr < height) {
                    if (alive[xr][yr]) {
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    @Override
    public void copyTo(Field other) {
        Dimension otherDimension = other.getDimensions();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (other.isLegalCoordinates(otherDimension, x, y)) {
                    other.set(x, y, get(x, y));
                }
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char c = '0';
                if (get(x, y)) {
                    c = '1';
                }
                sb.append(c);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
