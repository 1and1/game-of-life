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

import java.util.Arrays;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * The command line parameters as a POJO.
 * Must be created using {@link #parse(java.lang.String[]) }.
 * @see #parse(java.lang.String[]) 
 */
@Slf4j
public class Params {
    
    @Getter
    @Option(name = "-help", aliases = {"-h"}, usage = "Show this command line help.", help = true)
    private boolean help;
    
    /** Parse the command line options. 
     * @param args the command line args as passed to the main method of the
     * program.
     * @return the parsed command line options or {@code null} if
     * the program needs to exit. {@code null} will be returned
     * if the command lines are wrong or the command line help
     * was displayed.
     */
    public static Params parse(String[] args) {
        Params result = new Params();
        CmdLineParser cmdLineParser = new CmdLineParser(result);
        try {
            if (log.isDebugEnabled()) {
                log.debug("Args: {}", Arrays.toString(args));
            }
            
            cmdLineParser.parseArgument(args);
            
            if (result.help) {
                cmdLineParser.printUsage(System.err);
                return null;
            }
                        
            return result;
        } catch (CmdLineException ex) {
            log.warn("Error in parsing", ex);
            System.err.println(ex.getMessage());
            cmdLineParser.printUsage(System.err);
        }
        return null;
    }
}
