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

import java.util.Objects;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

/**
 * A runnable calculating a game of life until interrupted.
 * @author Stephan Fuhrmann
 */
public class LifeRunnable implements Runnable {

    /** Whether to iterate in the loop. Can be used to shut down the
     * runnable by flagging to {@code false}.
     */
    @Getter
    @Setter
    private boolean run;
    
    /** The configuration for the runnable. */
    private final LifeControl control;

    /** The game of life to process. */
    private final GameOfLife gameOfLife;
    
    /** Consumer for repainting. Will receive the currently active (and calculated) field.
     * The consumer can paint the field then. Is pre-initialized with a no-operation
     * instance.
     */
    @Getter
    @Setter
    private Consumer<Field> repaintConsumer = f -> {};
    
    /** Consumer for iteration count. Will receive the current iteration.
     * Is pre-initialized with a no-operation
     * instance.
     */
    @Getter
    @Setter
    private Consumer<Integer> iterationConsumer = f -> {};

    /** Constructor.
     * @param control the configuration for the runnable.
     * @param gameOfLife the game to calculate.
     */
    public LifeRunnable(LifeControl control, GameOfLife gameOfLife) {
        this.control = Objects.requireNonNull(control);
        this.gameOfLife = Objects.requireNonNull(gameOfLife);
        this.run = true;
    }
    
    @Override
    public void run() {
        while (run) {
            try {
                gameOfLife.doIteration();
                repaintConsumer.accept(gameOfLife.getActiveField());
                iterationConsumer.accept(gameOfLife.getIteration());
                Thread.sleep(control.getIntervalMillis());
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
    
}
