FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine@sha256:aec0deabd6a70fe8bdc0c98a472aa1826325dec1d4976efd2d1a37ff0a10a57d
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY ./build/libs/reviewer-0.0.1-SNAPSHOT.jar /app/reviewer-0.0.1-SNAPSHOT.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-jar" "reviewer-0.0.1-SNAPSHOT.jar"