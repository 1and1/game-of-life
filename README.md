## Game of life

[![Java Build](https://github.com/1and1/game-of-life/actions/workflows/maven.yml/badge.svg)](https://github.com/1and1/game-of-life/actions/workflows/maven.yml)
[![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/1and1/game-of-life)](https://github.com/1and1/game-of-life/releases)
[![ReleaseDate](https://img.shields.io/github/release-date/1and1/game-of-life)](https://github.com/1and1/game-of-life/releases)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

*This project has been set to read-only since there is no longer an active maintainer in the organization.*

Simple version of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
with a Java Swing user interface.

## Demo

![Demo GIF animation](/videos/GameOfLife2.gif)

## Purpose

This software is for demonstration and training purposes. 

## Features

The code has the following features:
* Java Swing user interface.
* Current generation can be modified using mouse clicks.
* Visual animation of life area.

## Building & running

Building is done with

    mvn clean package
   
after that you have a JAR archive you can execute with

    java -jar target/gameoflife-*-jar-with-dependencies.jar

## License

Copyright 2018 1&1 Internet SE

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
