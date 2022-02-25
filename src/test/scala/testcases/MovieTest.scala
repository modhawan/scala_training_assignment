package testcases

import assignment.MovieUtils
import org.scalatest.funsuite.AnyFunSuite

class MovieTest extends AnyFunSuite {

  test("test getReportByDirectorAndYear"){
    val movies = MovieUtils.getReportByDirectorAndYear(director = "Sidney Franklin", fromYear = 1900, toYear = 2000)

    //Positive Cases
    assert(movies.length > 0)

    //Negative Cases
    assertThrows[Exception]{
      MovieUtils.getReportByDirectorAndYear(
        director = "Cecil B. DeMille", fromYear = -5, toYear = 1925)
    }
    assertThrows[Exception]{
      MovieUtils.getReportByDirectorAndYear(
        director = "Cecil B. DeMille", fromYear = 1950, toYear = -3)
    }
  }

  test("test getReportByLanguageAndReview"){
    val movies = MovieUtils.getReportByLanguageAndReview(review = 80, language = "German")

    // Positive Cases
    assert(movies.length > 0)
    if(movies.length > 1){
      val movieA = movies(0)
      val movieB = movies(movies.length - 1)
      assert(movieA.reviewsFromUser.toString.toInt > movieB.reviewsFromUser.toString.toInt)
    }

    // Negative Cases
    assertThrows[Exception]{
      MovieUtils.getReportByLanguageAndReview(review = -10, language = "German")
    }
  }

  test("test getReportByCountryAndYear"){
    val movies = MovieUtils.getReportByCountryAndYear("Germany", 1920)

    // Positive Cases
    assert(movies.length > 0)
    if(movies.length > 1){
      val movieA = movies(0)
      val movieB = movies(movies.length - 1)
      assert(MovieUtils.compare(movieA.budget.toString, movieB.budget.toString))
    }

    // Negative Cases
    assertThrows[Exception]{
      MovieUtils.getReportByCountryAndYear("France", -2)
    }
  }

  test("test getReportByCountryAndVoters"){
    val movies = MovieUtils.getReportByCountryAndVoters(country = "Germany", votes = 1300)

    // Positive Cases
    assert(movies.length > 0)
    if(movies.length > 1){
      val movieA = movies(0)
      val movieB = movies(movies.length - 1)
      assert(movieA.votes.toString.toInt > movieB.votes.toString.toInt)
    }

    // Negative Cases
    assertThrows[Exception]{
      MovieUtils.getReportByCountryAndVoters(country = "Germany", votes = -1)
    }
  }

  test("test getReportByBudgetRangeAndCountry"){
    val movies = MovieUtils.getReportByBudgetRangeAndCountry(country = "USA", min = 5000, max = 20000)

    //Positive Cases
    assert(movies.length > 0)

    //Negative Cases
    assertThrows[Exception]{
      MovieUtils.getReportByBudgetRangeAndCountry(country = "USA", min = -3, max = 200)
    }
    assertThrows[Exception]{
      MovieUtils.getReportByBudgetRangeAndCountry(country = "USA", min = 5000, max = -5)
    }
  }

}
