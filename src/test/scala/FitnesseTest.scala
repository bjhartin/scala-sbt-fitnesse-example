package slickexample

import scala.collection.JavaConversions._
import org.junit.Test
import org.junit.runner.RunWith
import fitnesse.junit._
import fitnesse.junit.FitNesseRunner._
import fitnesse.junit.FitNesseSuite.Name

@RunWith(classOf[FitNesseSuite])
@Name("FrontPage.TestsHome")
@FitnesseDir("fitnesse")
@OutputDir("target/fitnesseOutput")
class FitnesseTest {
  @Test
  def placeholder = {} // Necessary for tests to run
}
