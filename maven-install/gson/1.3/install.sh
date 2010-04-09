#!/bin/sh


#gson.1.3.jar
mvn install:install-file -DgroupId=com.google.code.gson -DartifactId=gson -Dversion=1.3 -Dpackaging=jar -Dfile=gson-1.3.jar -DgeneratePom=true

#directjngine.1.2.src.jar
mvn install:install-file -DgroupId=com.google.code.gson -DartifactId=gson -Dversion=1.3 -Dpackaging=jar  -Dclassifier=sources -Dfile=gson-1.3-sources.jar


