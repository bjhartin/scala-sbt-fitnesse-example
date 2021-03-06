package slickexample

import org.scalatra.{ScalatraBase, FutureSupport, ScalatraServlet}

import slick.driver.JdbcDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

trait SlickRoutes extends ScalatraBase with FutureSupport {

  def db: Database
  def repo = new Repository(db)

  get("/db/create-db") {
    db.run(Tables.createDatabase)
    "Database created"
  }

  get("/db/drop-db") {
    db.run(Tables.dropSchemaAction)
    "Database dropped"
  }

  get("/customers") {
    repo.findAllCustomers map {_.email} mkString "\n"
  }
}

class SlickApp(val db: Database) extends ScalatraServlet with FutureSupport with SlickRoutes {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

}
