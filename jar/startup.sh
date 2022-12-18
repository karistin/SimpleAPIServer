#! /bin/bash

echo "---Example Dummy File---"
java -jar .\dummyInsert-1.0.0.jar

echo "---Running API SERVER---"
java -jar .\api-server-1.0.0.jar -Dspring.main.banner-mode=off -Dlogging.pattern.console= --spring.config.name=application.yml  --spring.config.location=file:./application.yml
