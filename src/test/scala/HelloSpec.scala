import org.scalatest._

class HelloSpec extends WordSpec with MustMatchers {

  // see www.scalatest.org/user_guide/using_matchers for syntax

  "true..." must {
    "be true" in {
      true must equal(true)
    }
  }
}
