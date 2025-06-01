FROM tomcat:10.1-jdk21

# Remove default apps (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR into Tomcat's webapps directory
COPY target/pingpal-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Expose port
EXPOSE 8080

CMD ["catalina.sh", "run"]

# Build the container: docker build -t pingpal-frontend .
# Run the app: docker run -d -p 8080:8080 --name pingpal-frontend pingpal-frontend