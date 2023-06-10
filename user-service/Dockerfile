FROM openjdk:17 as final
ADD ./ /home/app
COPY ./build/libs/app-0.0.1-SNAPSHOT.jar /home/app/backend.jar
ENTRYPOINT ["java", "-jar", "/home/app/backend.jar"]