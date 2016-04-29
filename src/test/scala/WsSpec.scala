import org.scalatest._
import akka.actor.Cancellable
import akka.stream.{ ActorMaterializer, ClosedShape, Graph, Materializer }
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.{ AsyncAssertions, ScalaFutures }
import org.scalatest.mock.MockitoSugar
import org.scalatest.time.{ Millis, Seconds, Span }
import org.slf4j.LoggerFactory
import play.api.Play
import play.api.libs.ws.ahc.AhcWSClient
import play.api.libs.ws.ning.NingWSClient

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.FiniteDuration

class WsSpec extends WordSpec with MustMatchers with ScalaFutures with BeforeAndAfterAll {

  implicit override val patienceConfig =
    PatienceConfig(timeout = Span(2, Seconds), interval = Span(50, Millis))

  implicit val actorSystem = akka.actor.ActorSystem()

  implicit val wsClient = AhcWSClient()(ActorMaterializer()(actorSystem))

  override def afterAll() = {
    wsClient.close()
    actorSystem.terminate()
  }

  def myfunction() = {

    wsClient.url("https://...").get().map { response =>


    }

  }

  "response" must {
    "contain avatar url" in {
      whenReady(myfunction()) { result =>
        result mustEqual "https://avatar...."
      }

    }
  }
}
