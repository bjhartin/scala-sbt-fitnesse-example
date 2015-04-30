package slickexample

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.mockito.Mockito._
import org.mockito.Matchers._
import java.sql.Timestamp

class OrderServiceSpec extends Specification with Mockito {
  "An order service should" >> {
    "Retrieve all orders" >> {
      val mockRepo = mock[Repository]
      val expected = List(Order(1, "me@me.com", new Timestamp(1)))

      mockRepo.findAllOrders returns expected
      new OrderService(mockRepo).findAllOrders must_== expected
    }
  }
}
