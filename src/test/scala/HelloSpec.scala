import org.scalatest._

class HelloSpec extends WordSpec with MustMatchers {

  "true..." must {
    "be true" in {
      true must equal(true)
    }
  }
}
