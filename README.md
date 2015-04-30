# Scala, SBT and Fitnesse example #

A super simple example to illustrate acceptance test driven development in a short talk I'm giving for my employer.  Happens to integrate scala, SBT, Fitnesse around a simple Scalatra CRUD app.

## Build & Run ##

```sh
$ git clone https://github.com/bjhartin/scala-sbt-fitnesse-example
$ sbt
$ container:start
$ open http://localhost:8080
```

## Fitnesse tests ##

```sh
$ cd fitnesse
$ ./run.sh
$ open http://localhost:9090
```
