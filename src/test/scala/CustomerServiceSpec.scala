package slickexample

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeExample
import slick.driver.JdbcDriver.api._
import com.mchange.v2.c3p0.ComboPooledDataSource

class CustomerServiceSpec extends Specification with Mockito with BeforeExample {
  def db: Database = {
    val cpds = new ComboPooledDataSource
    Database.forDataSource(cpds)
  }

  def before {
   db.run(Tables.dropSchemaAction)
   db.run(Tables.createDatabase)
  }

  "A customer service should" >> {
    sequential

    "Retrieve all customers (mocked repository)" >> {
      val mockRepo = mock[Repository]
      val expected = List(Customer("brian@banno.com"), Customer("adam@banno.com"))

      // when(mockRepo.findAllCustomers).thenReturn(expected) (Mockito style)
      mockRepo.findAllCustomers returns expected
      new CustomerService(mockRepo).findAllCustomers must_== expected

      there were two(mockRepo).findAllCustomers
    }

    "Retrieve all customers (from live DB)" >> {
      val repo = new Repository(db)
      new CustomerService(repo).findAllCustomers must_==
        List(Customer("brian@banno.com"), Customer("adam@banno.com"))
    }
  }
}
