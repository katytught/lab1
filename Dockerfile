FROM openjdk:11
COPY . /myapp/
WORKDIR /myapp/
ENV CLASSPATH=antlr-4.9.2-complete.jar
RUN javac -cp src/ src/Main.java -d dst/
