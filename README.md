# FIBEX-Import-Export
A simple import and export tool for FIBEX files to Java objects. Currently FIBEX version 2.0.1 is supported.

## Requirements
XMLBeans is required.
This project has been build with XMLBeans version 2.5.0.

## Usage
This repository contains two Eclipse projects. Project FIBEX contains the representation objects while project FIBEX2.0.1 implements the FIBEX importer.

1. Create the FIBEX.jar with the Eclipse JAR exporter
2. Create FIBEX2.0.1.jar with Ant
3. Use Fibex importer/exporter (see Test.java for examples)

## Issues
- Currently only FlexRay is implemented.
- Move from Eclipse JAR export to Ant export for FIBEX representation