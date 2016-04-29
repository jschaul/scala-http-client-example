# scala-http-example

A sample repo to show people new to Scala, play-json and ScalaWS client how to make use of these. 

Meant to be used as basis for individual tutoring/pair programming, this is not a stand-alone resource nor a full tutorial (yet?)

Code is intentionally not fully refactored by just featuring one "main" method. (Can be done by the person learning)

## setup

Either type `sbt` if you have it already, or execute `./activator` to get an sbt shell.

Once in the sbt shell, you can execute

* `compile`
* `test`
* `run`

(or type `tasks` to see more possibilities) 

## Documentation

* for syntax of wsClient, see https://www.playframework.com/documentation/2.5.x/ScalaWS
* for json docs, see https://www.playframework.com/documentation/2.5.x/ScalaJson
* for scalatest syntax, see http://www.scalatest.org/user_guide/using_matchers 
* to configure logging, look at [src/main/resources/logback.xml](src/main/resources/logback.xml)
    * project uses [scala-logging](https://github.com/typesafehub/scala-logging) (configured in [build.sbt](build.sbt))

