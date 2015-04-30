package slickexample

import org.scalatra.{ScalatraBase, FutureSupport}
import slick.driver.JdbcDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Await

class CustomerService(repo: Repository) {
  def findAllCustomers: List[Customer] = {
    repo.findAllCustomers
  }
}
