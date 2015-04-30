package slickexample.fitnesse

import fitlibrary.DoFixture

import net.sourceforge.jwebunit.junit.JWebUnit._


class CustomerDoFixture extends DoFixture {
  setBaseUrl("http://localhost:8080")

  def given = this

  def aCleanDatabase: Boolean = {
    beginAt("/db/create-db")
    true
  }

  def aListOfCustomers = true

  def aUserViews = true
}
