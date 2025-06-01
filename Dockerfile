FROM tomcat:10.1-jdk21

# Remove default apps (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR into Tomcat's webapps directory
COPY target/pingpal-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Expose port (default is 8080)
EXPOSE 8080

CMD ["catalina.sh", "run"]