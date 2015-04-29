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

  val insertCustomerData = DBIO.seq(
    Tables.customers ++= Seq("brian@banno.com", "adam@banno.com")
  )

  val insertInventoryItemData = DBIO.seq(
    Tables.inventoryItems ++= Seq(("0000001", "Hammer", 100, 5.99),
                                  ("0000002", "Screwdriver", 200, 2.99))
  )

  // DBIO Action which creates the schema
  val createSchemaAction = (customers.schema ++ inventoryItems.schema).create

  // DBIO Action which drops the schema
  val dropSchemaAction = (customers.schema ++ inventoryItems.schema).drop

  // Create database, composing create schema and insert sample data actions
  val createDatabase = DBIO.seq(createSchemaAction,
                                insertCustomerData,
                                insertInventoryItemData)
}
