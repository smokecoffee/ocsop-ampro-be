FROM anapsix/alpine-java

LABEL maintainer="thisisminh172@gmail.com"

COPY ampro015-boot/target/ampro015-boot-1.0-SNAPSHOT.jar home/ampro015-boot-1.0-SNAPSHOT.jar
EXPOSE 8080

CMD ["java","-jar", "home/ampro015-boot-1.0-SNAPSHOT.jar"]