#!/bin/sh


#directjngine.1.2.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.2 -Dpackaging=jar -Dfile=directjngine.1.2.jar -DpomFile=pom.xml

#directjngine.1.2.javadoc.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.2 -Dpackaging=jar -Dclassifier=javadoc -Dfile=directjngine.1.2.javadoc.jar

#directjngine.1.2.src.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.2 -Dpackaging=jar -Dclassifier=sources -Dfile=directjngine.1.2.src.jar


