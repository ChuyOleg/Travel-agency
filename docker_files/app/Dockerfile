FROM tomcat:8-alpine
COPY ../../target/ROOT.war /usr/local/tomcat/webapps/
COPY ../../src/main/resources/db.properties /usr/local/tomcat/webapps/travel_agency/
RUN cd webapps/; rm -r ROOT
CMD ["catalina.sh", "run"]