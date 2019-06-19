FROM java:8
VOLUME /resource
COPY target/adwebserver-1.0.0.jar server.jar
RUN bash -c "touch /server.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","server.jar"]
