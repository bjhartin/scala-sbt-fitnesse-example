package slickexample

import org.scalatra.{ScalatraBase, FutureSupport, ScalatraServlet}

import slick.driver.JdbcDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

trait SlickRoutes extends ScalatraBase with FutureSupport {

  def db: Database
  def repo = new Repository(db)
  def customerService = new CustomerService(repo)

  get("/db/create-db") {
    db.run(Tables.createDatabase)
    "Database created"
  }

  get("/db/drop-db") {
    db.run(Tables.dropSchemaAction)
    "Database dropped"
  }

  get("/customers") {
    customerService.findAllCustomers map {_.email} mkString "\n"
  }

  get("/ordertotals") {
    "someorders"
  }
}

class SlickApp(val db: Database) extends ScalatraServlet with FutureSupport with SlickRoutes {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

}
