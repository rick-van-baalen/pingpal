FROM tomcat:10.1-jdk21
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY target/pingpal-1.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080