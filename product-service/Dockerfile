FROM openjdk:17 as final
ADD ./ /home/app
COPY ./build/libs/product-service-0.0.1-SNAPSHOT.jar /home/app/product-service.jar
ENTRYPOINT ["java", "-jar", "/home/app/product-service.jar"]