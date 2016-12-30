dp-dd-front-end-tests
=====================
Front end tests for data discovery.

### Getting started
Install maven
Get the dataset stub and have it running locally
Get the front end data discovery app and have it running locally

````
brew install maven
````
Get the dataset stub and have it running locally
Get the front end data discovery app and have it running locally


Get the front end test project

````
git clone https://github.com/ONSdigital/dp-dd-front-end-tests
````

Run the test passing all the parameters. If any -Dproperty is not specified; default specified in the local_config.properties will be used.

````
mvn clean test -Dbrowser=chrome -Dbase_url=http://localhost:20040 -Dbackend=stub -Dtest=**/UITests.*

````

### Contributing

See [CONTRIBUTING](CONTRIBUTING.md) for details.

### License

Copyright ©‎ 2016, Office for National Statistics (https://www.ons.gov.uk)

Released under MIT license, see [LICENSE](LICENSE.md) for details.
