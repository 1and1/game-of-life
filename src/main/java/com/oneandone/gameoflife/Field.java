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
import java.util.function.BooleanSupplier;

/**
 * One generation of a rectangular two-dimensional life world.
 * @author Stephan Fuhrmann
 */
public interface Field {
    /** The default dimension of a world. */
    public final int FIELD_SIZE_DEFAULT = 10;
    
    /** Get the number of alive neighbors. There are 8 neighors maximum starting at upper left and ending at
     * lower right. The neighors at the border are counted as being dead. 
     * @param x the x coordinate of the reference cell.
     * @param y the y coordinate of the reference cell.
     * @return the number of alive neighbors ranging from 0 to 8.
     */
    public int getNeighborCount(int x, int y);
    
    /** Get the status of a cell.
     * @param x the x coordinate of the reference cell.
     * @param y the y coordinate of the reference cell.
     * @return {@code true} if the cell is alive, 
     * {@code false} if the cell is dead.
     */
    public boolean get(int x, int y);
    
    /** Set the status of a cell.
     * @param x the x coordinate of the reference cell.
     * @param y the y coordinate of the reference cell.
     * @param set the new status of the cell. {@code true} if the cell is alive, 
     * {@code false} if the cell is dead.
     */
    public void set(int x, int y, boolean set);
    
    
    /** Set the status of all cells.
     * @param supplier the supplier for destination values.
     * Iteration order is left to right and row by row.
     */
    public default void set(BooleanSupplier supplier) {
        Dimension dim = getDimensions();
        for (int y = 0; y < dim.height; y++) {
            for (int x = 0; x < dim.width; x++) {
                set(x, y, supplier.getAsBoolean());
            }
        }
    }
    
    /** Get the dimensions of the field.
     * @return a dimension object with the width and height of the Field
     * in cells.
     */
    public Dimension getDimensions();

    /** Checks whether the given coordinates are inside the field.
     * @param dimension the {@link #getDimensions() dimension} of the field.
     * @param x the x coordinate of the reference cell.
     * @param y the y coordinate of the reference cell.
     * @return {@code true} if the coordinates are inside the field, 
     * {@code false} otherwise.
     */
    default boolean isLegalCoordinates(Dimension dimension, int x, int y) {
        return x >= 0 && y >= 0 && x < dimension.width && y < dimension.height;
    }
    
    /** Checks whether the given coordinates are inside the field.
     * @param x the x coordinate of the reference cell.
     * @param y the y coordinate of the reference cell.
     * @return {@code true} if the coordinates are inside the field, 
     * {@code false} otherwise.
     */
    default boolean isLegalCoordinates(int x, int y) {
        Dimension dimension = getDimensions();
        return x >= 0 && y >= 0 && x < dimension.width && y < dimension.height;
    }
    
    /** Copy content to a field with possibly other dimensions.
     * @param target the field to copy to.
     */
    default void copyTo(Field target) {
        Dimension dimension = getDimensions();
        Dimension otherDimension = target.getDimensions();
        for (int y = 0; y < dimension.height; y++) {
            for (int x = 0; x < dimension.width; x++) {
                if (target.isLegalCoordinates(otherDimension, x, y)) {
                    target.set(x, y, get(x, y));
                }
            }
        }
    }
    
    /** Convert the field to a String. Will put each row in a linefeed terminated
     * row. 
     * @return linefeed terminated rows with alive cells written as a '1' and dead cells with '0'.
     */
    default String toStringDefault() {
        Dimension dimension = getDimensions();
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < dimension.height; y++) {
            for (int x = 0; x < dimension.width; x++) {
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
