#!/bin/sh


#jargs-1.0.jar
 mvn install:install-file -DgroupId=jargs -DartifactId=jargs -Dversion=1.0 -Dpackaging=jar -Dfile=jargs-1.0.jar -DgeneratePom=true
