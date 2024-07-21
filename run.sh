#!/bin/bash

# Ensure the script exits if any command fails
set -e

# Build the application
./gradlew clean build

# Define the JAR file location
JAR_FILE=$(find build/libs -name "*.jar" | head -n 1)

# Run the application
java -jar $JAR_FILE
