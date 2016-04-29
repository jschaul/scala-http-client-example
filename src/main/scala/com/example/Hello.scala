package com.example

import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.scalalogging.LazyLogging
import play.api.libs.json.Json
import play.api.libs.ws.ahc.AhcWSClient
import play.api.libs.ws.{WSRequest, WSResponse}

import scala.concurrent.Future
import scala.util.Try

object Hello extends LazyLogging {

  implicit val actorSystem = akka.actor.ActorSystem()

  implicit val materializer: Materializer = ActorMaterializer()(actorSystem)

  implicit val wsClient = AhcWSClient()(materializer)

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
