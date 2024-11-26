FROM openjdk:17-jdk-alpine
WORKDIR /opt/app
RUN addgroup --system javauser && adduser -S -s /usr/sbin/nologin -G javauser javauser
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN chown -R javauser:javauser .
USER javauser
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar ${@}"]