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

import lombok.Getter;

/**
 * Parameter for the {@link LifeRunnable}.
 * @author Stephan Fuhrmann
 */
public class LifeControl {
    /** The interval between two generation calculations.
     * The time is in milliseconds.
     */
    @Getter
    private final int intervalMillis;    

    /** Constructor.
     * @param intervalMillis the time interval in milliseonds between
     * two generations.
     */
    public LifeControl(int intervalMillis) {
        this.intervalMillis = intervalMillis;
    }
}
