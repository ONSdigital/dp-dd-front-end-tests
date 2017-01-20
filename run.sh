#!/usr/bin/env bash

echo "running data discovery frontend tests"
mvn clean test -Dbrowser=chrome -Dbase_url=https://discovery.onsdigital.co.uk/dd/ -Dbackend=real -Dtest=**/UITests.*
