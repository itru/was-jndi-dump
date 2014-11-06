was-jndi-dump
=============

Show an IBM WebSphere Application Server namespace dump in a browser.

Add an com.ibm.ws.runtime.jar to your local maven repository

    mvn install:install-file -Dfile=com.ibm.ws.runtime.jar -DgroupId=com.ibm.ws -DartifactId=runtime -Dversion=8.5.0.2 -Dpackaging=jar
