package slickexample

import scala.concurrent.duration._
import scala.concurrent.Await
import slick.driver.JdbcDriver.api._
import java.sql.Timestamp

case class Customer(email: String)

case class Order(orderNo: Int, customerEmail: String, datePlaced: Timestamp)

class Repository(db: Database) {
  import scala.concurrent.ExecutionContext.Implicits.global

  def findAllCustomers: List[Customer] = {
    Await.result(db.run(Tables.allCustomersQuery.result) map { xs =>
      xs map { case (email) => Customer(email) }
    }, 5 seconds).toList
  }

  def findAllOrders: List[Order] = Nil
}
