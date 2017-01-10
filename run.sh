#!/usr/bin/env bash

echo "running data discovery frontend tests"
mvn clean test -Dbrowser=chrome -Dbase_url=http://localhost:20040 -Dbackend=stub -Dtest=**/UITests.*
