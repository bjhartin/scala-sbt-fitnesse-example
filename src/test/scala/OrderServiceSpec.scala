package slickexample

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeExample
import slick.driver.JdbcDriver.api._
import com.mchange.v2.c3p0.ComboPooledDataSource

class OrderServiceSpec extends Specification with Mockito with BeforeExample {
  def db: Database = {
    val cpds = new ComboPooledDataSource
    Database.forDataSource(cpds)
  }

  def before {
   db.run(Tables.dropSchemaAction)
   db.run(Tables.createDatabase)
  }

  "An order service should" >> {
    sequential

    "Retrieve all orders" >> {
      val mockRepo = mock[Repository]
      val expected = Nil

      mockRepo.findAllOrders returns expected
      new OrderService(mockRepo).findAllOrders must_== expected
    }
  }
}
