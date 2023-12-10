FROM maven:3.8.6-amazoncorretto-17
ADD target/online-bookstore-1.0.0-SNAPSHOT.jar online-bookstore.jar
ADD src/main/resources/dbinit /src/main/resources/dbinit
ADD src/main/resources/public /src/main/resources/public
ADD src/main/resources/public/static/templates /src/main/resources/templates
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "online-bookstore.jar"]