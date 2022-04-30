#!/usr/bin/env bash
## Build Project
./gradlew clean build --warning-mode=none

# Start the service
java -Dspring.config.location=config/development/application.yml -jar build/libs/microservice.jar