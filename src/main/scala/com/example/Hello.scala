package com.example

import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.scalalogging.LazyLogging
import play.api.libs.json.Json
import play.api.libs.ws.ahc.AhcWSClient
import play.api.libs.ws.{WSRequest, WSResponse}

import scala.concurrent.Future
import scala.util.Try

object Hello extends LazyLogging {

  // There should only be one actor system created for a given application.
  // Terminate it if you want to stop the process - it is long-lived.
  implicit val actorSystem = akka.actor.ActorSystem()

  // this sets up the actual wsclient we will be using. Make sure to close it when you're done.
  implicit val wsClient = AhcWSClient()(ActorMaterializer()(actorSystem))

  import scala.concurrent.ExecutionContext.Implicits.global

  def cleanUp() = {
    logger.debug("cleaning up, closing wsClient and actorsystem")
    wsClient.close()
    actorSystem.terminate()
  }

  def main(args: Array[String]): Unit = {
    logger.debug("Hello, world!")

    val organizationsUrl: String = "https://api.github.com/users/jschaul/orgs"

    val request: WSRequest = wsClient.url(organizationsUrl)

    val responseFuture: Future[WSResponse] = request.get()

    responseFuture.map { response =>
      // the inner part of "map" happens when
      // 1) the future completes
      // 2) it is successful (i.e. doesn't throw an exception)
      logger.info(s"response: ${Json.prettyPrint(response.json)}")
      cleanUp()
    }

    responseFuture.onComplete { f: Try[WSResponse] =>
      // this adds a second callback to the future created above
      // and cleans up resources irrespective of whether the request completed successfully or with an exception
      cleanUp()
    }

    logger.debug("finished Hello.scala")
  }
}
