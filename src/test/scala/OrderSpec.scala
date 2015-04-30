package slickexample

import org.specs2.mutable.Specification
import java.sql.Timestamp

class OrderSpec extends Specification {
  "An order should" >> {
    "Format itself" >> {
      val order = Order(1, "me@me.com", Timestamp.valueOf("2015-04-03 10:10:10.0"))
      order.toString must_== "1 me@me.com 2015-04-03 10:10:10.0 $5.00"
    }
  }
}
