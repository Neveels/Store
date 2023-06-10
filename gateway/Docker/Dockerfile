FROM openjdk:17 as final
ADD ./ /home/app
COPY /build/libs/gateway-0.0.1-SNAPSHOT.jar /home/app/gateway.jar
ENTRYPOINT ["java", "-jar", "/home/app/gateway.jar"]