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

import com.oneandone.gameoflife.ui.LifeFrame;
import java.io.IOException;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

/**
 * The main class that gets executed from command line.
 * @author Stephan Fuhrmann
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) throws IOException {
        Params params = Params.parse(args);
        if (params == null) {
            return;
        }
        Locale.setDefault(new Locale("en"));
        LifeFrame lifeFrame = new LifeFrame();
        lifeFrame.setSize(640, 480);
        lifeFrame.pack();
        lifeFrame.setVisible(true);
    }
}
