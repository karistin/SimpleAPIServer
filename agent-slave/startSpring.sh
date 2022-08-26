#!/usr/bin/zsh

PROJECT_PATH=$(pwd)
echo ${PROJECT_PATH}/TestCase.jar

#gradle build
cd ${PROJECT_PATH}
java  -Dspring.main.banner-mode=off -Dlogging.pattern.console= -jar TestCase.jar
