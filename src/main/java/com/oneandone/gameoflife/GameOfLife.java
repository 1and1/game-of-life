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
import lombok.Getter;
import lombok.Setter;

/**
 * The game of life logic.
 * @author Stephan Fuhrmann
 */
public class GameOfLife {
    /** The active and inactive field as an array.
     * The array has the size 2.
     * The active field is referenced by {@link #activeFieldIndex}.
     */
    private final Field[] fields;
    
    /** The index of the currently active field in {@link #fields}.
     */
    private int activeFieldIndex;
    
    /** The current iteration number. */
    @Getter @Setter
    private int iteration;

    public GameOfLife() {
        fields = new Field[2];
        fields[0] = new FieldImpl(Field.FIELD_SIZE_DEFAULT, Field.FIELD_SIZE_DEFAULT);
        fields[1] = new FieldImpl(Field.FIELD_SIZE_DEFAULT, Field.FIELD_SIZE_DEFAULT);
    }
    
    /** Sets the size of the field. 
     * The data in the field will be kept as much as possible.
     * New areas are initialized with dead cells.
     * Nothing will happen if the dimensions match the
     * current dimensions.
     * @param width the new width of the field.
     * @param height the new height of the field.
     */
    public void setSize(int width, int height) {
        boolean needChange = 
                getActiveField().getDimensions().width != width ||
                getActiveField().getDimensions().height != height;
        
        if (needChange) {
            for (int i = 0; i < fields.length; i++) {
                Field newField = new FieldImpl(width, height);
                fields[i].copyTo(newField);
                fields[i] = newField;
            }
        }
    }
    
    /** Gets the active field.
     * @return the active field containing the alive cell generation.
     */
    public Field getActiveField() {
        return fields[activeFieldIndex];
    }
    
    /** Calculates the alive status for the next iteration.
     * @param currentAlive whether the reference cell is at the moment alive.
     * @param currentNeighborsAlive the number of neighours currently alive.
     * @return the new status of the cell. {@code true} if the cell is alive, 
     * {@code false} if the cell is dead.
     */
    private static boolean calculateAliveStatusFor(boolean currentAlive, int currentNeighborsAlive) {
        boolean result;
        if (currentAlive) {
            result = currentNeighborsAlive >= 2 && currentNeighborsAlive <= 3;
        } else {
            result = currentNeighborsAlive == 3;
        }
        return result;
    }
    
    /** Calculates one iteration. After the call the active field
     * can be received using {@link #getActiveField()}.
     */
    public void doIteration() {
        Field active  = fields[activeFieldIndex];
        Field passive = fields[1 - activeFieldIndex];
        
        Dimension dimension = active.getDimensions();
        for (int y = 0; y < dimension.height; y++) {
            for (int x = 0; x < dimension.width; x++) {
                boolean current = active.get(x, y);
                boolean next = calculateAliveStatusFor(current, active.getNeighborCount(x, y));
                passive.set(x, y, next);
            }
        }
        
        activeFieldIndex = 1 - activeFieldIndex;
        iteration++;
    }
}
