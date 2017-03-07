#!/usr/bin/env bash

echo "running data discovery frontend tests"
mvn clean test -P FrontEnd-Tests
#mvn clean test -Dbackend=real -Dtest=**/CPITests.java
#mvn clean test -Dbackend=real -Dtest=**/AnnualBusinessSurvey.java
