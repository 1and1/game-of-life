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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for FieldImpl.
 * @author stephan
 */
public abstract class AbstractFieldImplTest {

    protected abstract Field newField(int width, int height);    
    @Test
    public void testGet() {
        Field fieldImpl = newField(10, 10);
        boolean state;
        state = fieldImpl.get(0, 0);
        assertEquals(false, state);
        state = fieldImpl.get(9, 0);
        assertEquals(false, state);
        state = fieldImpl.get(9, 9);
        assertEquals(false, state);
    }
    
    @Test
    public void testSet() {
        Field fieldImpl = newField(10, 10);
        boolean state;
        state = fieldImpl.get(0, 0);
        assertEquals(false, state);
        fieldImpl.set(0, 0, true);
        state = fieldImpl.get(0, 0);
        assertEquals(true, state);

        state = fieldImpl.get(9, 0);
        assertEquals(false, state);
        fieldImpl.set(9, 0, true);
        state = fieldImpl.get(9, 0);
        assertEquals(true, state);
        
        state = fieldImpl.get(0, 9);
        assertEquals(false, state);
        fieldImpl.set(0, 9, true);
        state = fieldImpl.get(0, 9);
        assertEquals(true, state);
    }
        
    @Test
    public void testGetNeighborCountWithEmpty() {
        Field fieldImpl = newField(10, 10);
        int count;
        count = fieldImpl.getNeighborCount(0, 0);
        assertEquals(0, count);
    }
    
    @Test
    public void testGetNeighborCountWithSelf() {
        Field fieldImpl = newField(10, 10);
        int count;
        fieldImpl.set(0, 0, true); 
        count = fieldImpl.getNeighborCount(0, 0);
        // self is not counted
        assertEquals(0, count);
    }
    
    @Test
    public void testGetNeighborCountWithUpperLeftAndRightSet() {
        Field fieldImpl = newField(10, 10);
        int count;
        fieldImpl.set(1, 0, true); 
        count = fieldImpl.getNeighborCount(0, 0);
        // self is not counted
        assertEquals(1, count);
    }
    
    @Test
    public void testGetNeighborCountWithUpperLeftAndBottomSet() {
        Field fieldImpl = newField(10, 10);
        int count;
        fieldImpl.set(0, 1, true); 
        count = fieldImpl.getNeighborCount(0, 0);
        // self is not counted
        assertEquals(1, count);
    }
    
    @Test
    public void testGetNeighborCountUpperLeftAndAllSet() {
        Field fieldImpl = newField(10, 10);
        int count;
        fieldImpl.set(0, 1, true); 
        fieldImpl.set(1, 1, true); 
        fieldImpl.set(1, 0, true); 
        count = fieldImpl.getNeighborCount(0, 0);
        // self is not counted, but 3 neighbors
        assertEquals(3, count);
    }
    
    @Test
    public void testGetNeighborCountCenterAndAllSet() {
        Field fieldImpl = newField(10, 10);
        int count;
        
        fieldImpl.set(4, 4, true); 
        fieldImpl.set(4, 5, true); 
        fieldImpl.set(4, 6, true); 
        
        fieldImpl.set(5, 4, true); 
        fieldImpl.set(5, 5, true); 
        fieldImpl.set(5, 6, true); 
        
        fieldImpl.set(6, 4, true); 
        fieldImpl.set(6, 5, true); 
        fieldImpl.set(6, 6, true); 

        count = fieldImpl.getNeighborCount(5, 5);
        // self is not counted, but 8 neighbors
        assertEquals(8, count);
    }
    
    @Test
    public void testToString() {
        Field fieldImpl = newField(3, 3);

        fieldImpl.set(0, 1, true); 
        fieldImpl.set(1, 1, true); 
        fieldImpl.set(1, 0, true);
        
        String str = fieldImpl.toString();
        
        assertEquals(
                  "010\n"
                + "110\n"
                + "000\n", str);
    }
    
    @Test
    public void testCopyToWithSmallerTarget() {
        Field fieldImpl = newField(3, 3);

        fieldImpl.set(0, 1, true); 
        fieldImpl.set(1, 1, true); 
        fieldImpl.set(1, 0, true);
        
        Field copy = newField(2, 2);

        fieldImpl.copyTo(copy);
        
        String str = copy.toString();
        
        assertEquals(
                  "01\n"
                + "11\n", str);
    }
    
    @Test
    public void testCopyToWithBiggerTarget() {
        Field fieldImpl = newField(3, 3);

        fieldImpl.set(0, 1, true); 
        fieldImpl.set(1, 1, true); 
        fieldImpl.set(1, 0, true);
        
        Field copy = newField(3, 4);

        fieldImpl.copyTo(copy);
        
        String str = copy.toString();
        
        assertEquals(
                  "010\n"
                + "110\n"
                + "000\n"
                + "000\n", str);
    }
}
