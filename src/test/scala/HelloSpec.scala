import org.scalatest._

class HelloSpec extends WordSpec with MustMatchers {

  "Hello" must {
    "have tests" in {
      true must equal(true)
    }
  }
}
