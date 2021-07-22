FROM openjdk:12-alpine

WORKDIR /app
COPY ./target/pdfmerger-*.jar ./pdfmerger.jar
COPY Libro* ./resources/

CMD ["java", "-jar", "/app/pdfmerger.jar"]