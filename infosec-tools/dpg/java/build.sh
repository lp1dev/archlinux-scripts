#!/bin/sh

echo "Compiling..."
javac dpg.java

echo "Creating jar..."
jar cfm dpg.jar Manifest.txt *.class

## Comment this out if you want to run
## the class files directly with java
## via `java dpg`
echo "Removing class files..."
rm -f *.class

echo "Build Complete."

