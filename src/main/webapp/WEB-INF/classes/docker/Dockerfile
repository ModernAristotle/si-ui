FROM ping2ravi/tomcat:8.5.6_oracle_jdk8.102.14_ubuntu16.04
MAINTAINER Ravi Sharma 

ADD ./ROOT.war ROOT.war
ADD ./server.xml server.xml
RUN rm -fR $TOMCAT_HOME/webapps/*; cp ROOT.war $TOMCAT_HOME/webapps/.; cp -f server.xml $TOMCAT_HOME/conf/.
