package slickexample

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.specs2.mutable.Specification

class CustomerServiceSpec extends Specification with Mockito {
  "A customer service should" >> {
      "Retrieve all customers (mocked repository)" >> {
      val mockRepo = mock[Repository]
      val expected = List(Customer("brian@banno.com"), Customer("adam@banno.com"))

      // when(mockRepo.findAllCustomers).thenReturn(expected) (Mockito style)
      mockRepo.findAllCustomers returns expected
      new CustomerService(mockRepo).findAllCustomers must_== expected

      // there was one(mockRepo).findAllCustomers (needed if not stubbing)
    }

    /* Not necessary with mocks, unless this is our only integration test.
       FitNesse has got this covered

     def before {
     db.run(Tables.dropSchemaAction)
     db.run(Tables.createDatabase)
     }

    "Retrieve all customers (from live DB)" >> {
      val repo = new Repository(db)
      new CustomerService(repo).findAllCustomers must_==
        List(Customer("brian@banno.com"), Customer("adam@banno.com"))
    }
     */
  }
}
