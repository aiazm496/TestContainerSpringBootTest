Testing SpringBoot Controller using TestContainer and TestRestTemplate
By default, springbootTest spings up an embedded H2 inmemory database, which doesn't replicate the DBs used in realtime production environments.

TestContainers gives us the features to spin a docker container running our preferrable traditional DBs and Autoconfigures the db running in the configure with the test environment.
All the test data is saved in the docker container's db and is destroyed once the test execution is finished.

Here, we are using TestRestTemplate to execute the HTTP calls.
