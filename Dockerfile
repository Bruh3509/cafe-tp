FROM openjdk:21

WORKDIR /cafe-tp

COPY target/cafe-tp.jar ./cafe-tp.jar

EXPOSE 5050

ENTRYPOINT ["java", "-jar", "cafe.jar"]

