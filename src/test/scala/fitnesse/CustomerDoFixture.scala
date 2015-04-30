package slickexample.fitnesse

import fitlibrary.DoFixture

class CustomerDoFixture extends DoFixture {
  def given = this

  def aListOfCustomers = true

  def aUserViews = true
}
