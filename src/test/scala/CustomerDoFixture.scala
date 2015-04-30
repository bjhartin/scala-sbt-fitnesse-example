package slickexample

import fitlibrary.DoFixture

import net.sourceforge.jwebunit.junit.JWebUnit._


class CustomerDoFixture extends DoFixture {
  setBaseUrl("http://localhost:8080")

  def given = this

  def then = this

  def when = this

  def aCleanDatabase = {
    beginAt("/db/create-db")
  }

  def aUserVisits(path: String) = {
    beginAt(path)
  }

  def theResponseCodeIs: Int = {
    web.getServerResponseCode
  }

  def thePageTextIs: String = {
    web.getPageText
  }


  // This is only needed to get around a FitNesse bug that
  // will produce a harmless, but noisy, error message.
  def slickexampleDotCustomerDoFixture() = {}



  private def web = getTestingEngine
}
