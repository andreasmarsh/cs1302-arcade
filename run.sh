#!/bin/bash -ex

mvn clean
mvn compile
export MAVEN_OPTS=-Dprism.order=sw;
mvn exec:java -Dexec.mainClass="cs1302.arcade.ArcadeDriver"
#checkstyle -c cs1302_checks.xml src/cs1302/Test.java
