# Description
We are developing an quarkus applications which will run as Azure Function. The project was created according to the description: https://quarkus.io/guides/azure-functions-http .We are using quarkus-resteasy to be able to use JAX-RS specification for coding.

The application produces response body which can be greater the 8KB, but it is not a problem if the application is started in dev mode with e.g. mvnw quarkus:dev. If the application is started with with Azure Functions Core Tools or deployed zu Azure, it do not respond anymore if the request response exceeds the size of 8KB.

If quarkus-undertow is in additional is added to the pom.xml file, the application hangs only if the body size exceeds 16KB.

The issue does not appear if:

1. quarkus-funqy-http is used in the application
2. only Azure APIs are used (without quarkus) to create the function

## Expected behavior
Bodies greater then 8KB can be returned from quarkus + Azure Function application

## Actual behavior
Bodies greater then 8KB can NOT be returned from quarkus + Azure Function application. The application hangs.

## How to Reproduce?
I created an example which can be use to reproduce the issue: https://github.com/franden/quarkus-azure-fn-issue-8k-body
This example creates an endpoint which produces a response with a string length depending on the input parameter.

1. install Azure Functions Core Tools
2. git clone https://github.com/franden/quarkus-azure-fn-issue-8k-body
3. mvnw clean package azure-functions:run -DskipTests=true -DenableDebug
4. curl http://localhost:7071/api/jaxrs?times=79 --get -v

The last line will let the application produce and return a string with length of 8295 Bytes.
curl http://localhost:7071/api/jaxrs?times=78 --get -v will work, because it produces a string with length 8190 Bytes