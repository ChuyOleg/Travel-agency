FROM postgres:alpine
ENV POSTGRES_USER=Oleh
ENV POSTGRES_PASSWORD=Oleh_Password
ENV POSTGRES_DB=travel_agency
COPY ../../src/main/resources/db.sql /docker-entrypoint-initdb.d/
