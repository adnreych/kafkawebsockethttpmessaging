FROM openjdk:11
RUN apt-get update && apt-get install -y maven
COPY common/pom.xml ./app
COPY common ./app/common
RUN mvn -f /common/pom.xml -Dmaven.test.skip=true clean install
COPY mc1 ./app/mc1
COPY mc2 ./app/mc2
RUN mvn -f /mc1/pom.xml -Dmaven.test.skip=true clean install && mvn -f /mc2/pom.xml -Dmaven.test.skip=true clean install
EXPOSE 8081
EXPOSE 8082
#CMD ["java", "-jar", "/pimodbus/target/pimodbusmaster-0.0.1-SNAPSHOT.war"]