# pmn-couchbase-wrapper Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_PREREQUISITE:_** Couchbase Server to be pointed, you can check in the application.properties for the username and password

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

native executeable cannot be built on office laptop, don't ever dream about it, however you can use jenkins as media to build as native, please consult to PRUForce team as they already can built as native.

**_NOTE:_** as per now, couchbase plugin still cannot build as native, however there's a possibility in the future that it can be build as native. please refer to this url: https://github.com/couchbaselabs/quarkus-couchbase
