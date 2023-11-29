FROM maven:3.8.6-amazoncorretto-17
ADD target/online-bookstore-1.0.0-SNAPSHOT.jar online-bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "online-bookstore.jar"]