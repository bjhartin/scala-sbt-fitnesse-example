package slickexample

import org.scalatra.{ScalatraBase}
import slick.driver.JdbcDriver.api._
import java.sql.Timestamp

object Tables {

  class Customers(tag: Tag) extends Table[(String)](tag, "CUSTOMERS") {
    def email = column[String]("EMAIL", O.PrimaryKey)

    def * = (email)
  }

  class InventoryItems(tag: Tag) extends Table[(String, String, Int, Double)](tag, "INVENTORY_ITEMS") {
    def sku = column[String]("SKU", O.PrimaryKey)
    def desc = column[String]("DESC")
    def quantity = column[Int]("QUANTITY")
    def price = column[Double]("PRICE")

    def * = (sku, desc, quantity, price)
  }

  class Orders(tag: Tag) extends Table[(Int, String, Timestamp)](tag, "ORDERS") {
    def orderNo = column[Int]("ORDERNO", O.PrimaryKey)
    def customerEmail = column[String]("CUSTOMEREMAIL")
    def datePlaced = column[Timestamp]("DATEPLACED")

    def * = (orderNo, customerEmail, datePlaced)
  }

  class OrderLines(tag: Tag) extends Table[(Int, Int, String, Int)](tag, "ORDERLINES") {
    def orderLineNo = column[Int]("ORDERLINENO", O.PrimaryKey)
    def orderNo = column[Int]("ORDERNO")
    def sku = column[String]("SKU")
    def quantity = column[Int]("QUANTITY")

    def * = (orderLineNo, orderNo, sku, quantity)
  }

  // Retained from original scalatra example for reference

  // A reified foreign key relation that can be navigated to create a join
  // def supplier = foreignKey("SUP_FK", supID, suppliers)(_.id)

  // Query, implicit inner join coffes and suppliers, return their names
  // val findCoffeesWithSuppliers = {
  //   for {
  //     c <- coffees
  //     s <- c.supplier
  //   } yield (c.name, s.name)
  // }

  val customers = TableQuery[Customers]

  val inventoryItems = TableQuery[InventoryItems]

  val orders = TableQuery[Orders]

  val orderLines = TableQuery[OrderLines]

  val allCustomersQuery = {
    for {
      c <- customers
    } yield c.*
  }

  val allOrdersQuery = {
    for {
      o <- orders
    } yield o.*
  }

  val insertCustomerData = DBIO.seq(
    Tables.customers ++= Seq("brian@banno.com", "adam@banno.com")
  )

  val insertInventoryItemData = DBIO.seq(
    Tables.inventoryItems ++= Seq(("0000001", "Hammer", 100, 5.99),
                                  ("0000002", "Screwdriver", 200, 2.99))
  )

  val insertOrderData = DBIO.seq(
    Tables.orders ++= Seq((1, "brian@banno.com", time("2015-04-23 10:10:10.0")),
                          (2, "brian@banno.com", time("2015-04-29 04:01:13.0")))
  )

  val insertOrderLineData = DBIO.seq(
    Tables.orderLines ++= Seq(
      (1, 1, "0000001", 3),
      (2, 1, "0000002", 1),
      (3, 2, "0000001", 1)
    )
  )

  // DBIO Action which creates the schema
  val createSchemaAction = (customers.schema ++ inventoryItems.schema ++ orders.schema ++ orderLines.schema).create

  // DBIO Action which drops the schema
  val dropSchemaAction = (orderLines.schema ++ orders.schema ++ customers.schema ++ inventoryItems.schema).drop

  // Create database, composing create schema and insert sample data actions
  val createDatabase = DBIO.seq(createSchemaAction,
                                insertCustomerData,
                                insertInventoryItemData,
                                insertOrderData,
                                insertOrderLineData)


  private def time(s: String) = Timestamp.valueOf(s)

}
