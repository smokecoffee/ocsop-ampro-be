FROM openjdk:8-jdk-alpine

ENV JAR_FILE ampro015-boot/target/ampro015-boot-1.0-SNAPSHOT.jar
ENV PME00_HOME /opt/pme00

COPY ${JAR_FILE} ${PME00_HOME}/

WORKDIR ${PME00_HOME}

CMD ["java","-jar", "ampro015-boot-1.0-SNAPSHOT.jar"]