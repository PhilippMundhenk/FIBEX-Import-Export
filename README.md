# FIBEX Import/Export
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

## Reference
Please refer to this project either via this repository or via the paper it was built for:

Philipp Mundhenk, Florian Sagstetter, Sebastian Steinhorst, Martin Lukasiewycz, Samarjit Chakraborty. "Policy-based Message Scheduling Using FlexRay". In: Proceedings of the 12th International Conference on Hardware/Software Codesign and System Synthesis (CODES+ISSS 2014). India, pp. 19:1–19:10. DOI: 10.1145/2656075.2656094

### BibTeX: <br />
@inproceedings{msslc:2014, <br />
	doi = { 10.1145/2656075.2656094 }, <br />
	pages = { 19:1--19:10 }, <br />
	year = { 2014 }, <br />
	location = { India }, <br />
	booktitle = { Proceedings of the 12th International Conference on Hardware/Software Codesign and System Synthesis (CODES+ISSS 2014) }, <br />
	author = { Philipp Mundhenk and Florian Sagstetter and Sebastian Steinhorst and Martin Lukasiewycz and Samarjit Chakraborty }, <br />
	title = { Policy-based Message Scheduling Using FlexRay }, <br />
}

##Support
This work has been created in TUM CREATE and was financially supported by the Singapore National Research Foundation under its Campus for Research Excellence and Technological Enterprise (CREATE) program.