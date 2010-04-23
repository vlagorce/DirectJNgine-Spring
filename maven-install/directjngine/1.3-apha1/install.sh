#!/bin/sh


#directjngine.1.3-alpha1.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.3-alpha1 -Dpackaging=jar -Dfile=directjngine.1.3-alpha1.jar -DpomFile=pom.xml

#directjngine.1.3-alpha1.javadoc.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.3-alpha1 -Dpackaging=jar -Dclassifier=javadoc -Dfile=directjngine.1.3-alpha1.javadoc.jar
  
#directjngine.1.3-alpha1.src.jar
mvn install:install-file -DgroupId=com.softwarementors.extjs -DartifactId=djn -Dversion=1.3-alpha1 -Dpackaging=jar -Dclassifier=sources -Dfile=directjngine.1.3-alpha1.src.jar


