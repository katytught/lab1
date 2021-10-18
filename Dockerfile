FROM openjdk:11
WORKDIR /app/
COPY * ./
RUN chmod 777 *
RUN javac -cp "./bin: ./src/antlr-4.9.2-complete.jar" ./src/Main.java
