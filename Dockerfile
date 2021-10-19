FROM openjdk:11
WORKDIR /app/
COPY * ./
RUN chmod 777 *
RUN docker network ls |wc -l
RUN javac -cp .:antlr-4.9.2-complete.jar Main.java
