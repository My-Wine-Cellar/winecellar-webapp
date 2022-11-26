# Testing

## Test suite

```
mvn test
```

## Integration Tests

```mvn verify``` will build and run the winecellar-webapp container to connect with PostgreSQL for 
executing integration tests against our own API.

If you want to run manual tests in Postman or using curl you can
use ```mvn docker:build docker:start``` and that will launch both containers to test individual endpoints.  Use ```mvn docker:stop```
to shutdown both containers.

