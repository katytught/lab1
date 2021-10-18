FROM openjdk:11
WORKDIR /app/
COPY * ./
RUN chmod 777 *
RUN ls
RUN javac -cp "*.class: antlr-4.9.2-complete.jar" Main.java
