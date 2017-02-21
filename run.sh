#!/usr/bin/env bash

echo "running data discovery frontend tests"
mvn clean test -Dbackend=real -Dtest=**/ArmedForcesTests.java
mvn clean test -Dbackend=real -Dtest=**/CPITests.java
mvn clean test -Dbackend=real -Dtest=**/AnnualBusinessSurvey.java
