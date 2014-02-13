# uniba-hive-udf

UDFs for Uniba-Hive project


## Building the project

Building JAR package with Maven

```
mvn package
```

## Use the extension

Load JAR from Hive and use it by creating temporary functions

```
hive > ADD JAR /path/to/uniba-hive-udf/target/uniba-hive-udf-$version.jar;
hive > CREATE TEMPORARY FUNCTION harvesinedistance AS 'com.uniba.udfs.UDFHaversineDistance';
hive > SELECT haversinedistance(lat1,lng1,lat2,lng2) FROM source;
```