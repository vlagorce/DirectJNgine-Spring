#!/bin/sh


#directjngine.1.1.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.1 -Dpackaging=jar -Dfile=directjngine.1.1.jar -DgeneratePom=true

#directjngine.1.1.javadoc.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.1 -Dpackaging=jar -Dclassifier=javadoc -Dfile=directjngine.1.1.javadoc.jar

#directjngine.1.1.src.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.1 -Dpackaging=jar -Dclassifier=sources -Dfile=directjngine.1.1.src.jar


