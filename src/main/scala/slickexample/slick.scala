package slickexample

import org.scalatra.{ScalatraBase, FutureSupport, ScalatraServlet}

import slick.driver.JdbcDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

trait SlickRoutes extends ScalatraBase with FutureSupport {

  def db: Database

  get("/db/create-db") {
    db.run(Tables.createDatabase)
    "Database created"
  }

  get("/db/drop-db") {
    db.run(Tables.dropSchemaAction)
    "Database dropped"
  }

  get("/customers") {
    db.run(Tables.findAllCustomers.result) map { xs =>
      println(xs)
      contentType = "text/plain"
      xs map { case (email) => f"$email" } mkString "\n"
    }
  }

  /*
  get("/formtest") {
    contentType="text/html"
    <html>
    <head><title>Test</title>
    </head>
    <body>
    <form method="GET" action="/coffees2">
      <input type="submit" value="Show me the coffees"/>
    </form>
    </body>
    </html>
  }*/

}

class SlickApp(val db: Database) extends ScalatraServlet with FutureSupport with SlickRoutes {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

}
