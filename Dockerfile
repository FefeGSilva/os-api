FROM openjdk:21
RUN mkdir /app
WORKDIR /app
RUN ls -la /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080:8080