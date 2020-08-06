FROM openjdk:8 AS BUILD_IMAGE
LABEL maintainer="BuJoAtCloud-Journal"

ENV APP_HOME /app
RUN mkdir $APP_HOME
WORKDIR $APP_HOME

# Download Dependencies
ADD gradlew $APP_HOME
COPY gradle $APP_HOME/gradle

RUN chmod +x $APP_HOME/gradlew
RUN $APP_HOME/gradlew --version

# Build
COPY src $APP_HOME/src
ADD settings.gradle.kts $APP_HOME
ADD build.gradle.kts $APP_HOME
RUN $APP_HOME/gradlew clean
RUN $APP_HOME/gradlew bootRun