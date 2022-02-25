package assignment

import scala.io.StdIn

object MainAssignment extends App {

  def display(): Unit ={
    println("Choose the option from the menu")
    println("1 -> Get report by director name and range of years")
    println("2 -> Get report of movies which have reviews more than user review by user review and language")
    println("3 -> Get report of highest budget by country and year")
    println("4 -> Get report of longest duration by country and votes")
    println("5 -> Get report language wise for given budget range and country ")
    println("0 -> For exit")
  }

  display()
  var inp = try{
    StdIn.readInt()
  } catch{
    case e: NumberFormatException => println("Please choose the correct value from menu")
  }
  var flag = true
  while(true && flag){
    inp match {
      case 1 => {
        println("\nEnter Director's name: ")
        val director = StdIn.readLine()
        print("Enter start year: ")
        val startYear: Int = try{
          StdIn.readInt()
        }catch {
          case e: Exception => e match {
            case e: NumberFormatException => println("Please enter numeric value")
            case _ => println("Something wrong happen")
          }
          0
        }
        print("Enter End year: ")
        val endYear: Int = try{
          StdIn.readInt()
        }catch{
          case e: Exception => e match {
            case e: NumberFormatException => println("Please enter numeric value")
            case _ => println("Something wrong happen")
          }
          0
        }
        val movies = try{
          MovieUtils.getReportByDirectorAndYear(director,startYear,endYear)
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          List()
        }
        if(movies.length != 0) printMovie(movies) else println("No record found!")
      }
      case 2 => {
        println("\nEnter language: ")
        val language = StdIn.readLine()
        println("Enter minimum user review: ")
        val userReview: Int = try{
          StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          0
        }
        val movies = try{
          MovieUtils.getReportByLanguageAndReview(userReview,language)
        } catch{
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          List()
        }
        if(movies.length != 0) printMovie(movies) else println("No record found!")
      }
      case 3 => {
        println("\nEntry country: ")
        val country = StdIn.readLine()
        println("Enter the year: ")
        val year: Int = try{
          StdIn.readInt()
        } catch {
          case e: Exception => e match {
            case e: NumberFormatException => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          0
        }
        val movies = try{
          MovieUtils.getReportByCountryAndYear(country,year)
        } catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          List()
        }
        if(movies.length != 0) printMovie(movies) else println("No records found!")
      }
      case 4 => {
        println("\nEnter country: ")
        val country = StdIn.readLine()
        println("Enter minimum user votes: ")
        val votes: Int = try{
          StdIn.readInt()
        }catch{
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          0
        }
        val movies = try{
          MovieUtils.getReportByCountryAndVoters(votes, country)
        }catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          List()
        }
        if(movies.length != 0) printMovie(movies) else println("No records found!")
      }
      case 5 => {
        println("\nEnter country name: ")
        val country = StdIn.readLine()
        println("Enter minimum budget(numeric value): ")
        val minBudget: Int = try{
          StdIn.readInt()
        }catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          0
        }
        println("Enter maximum budget(numeric value): ")
        val maxBudget: Int = try{
          StdIn.readInt()
        }catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          0
        }
        val movies = try{
          MovieUtils.getReportByBudgetRangeAndCountry(minBudget, maxBudget,country)
        }catch {
          case e: Exception => e match {
            case e: Exception => println(e.getMessage)
            case _ => println("Something wrong happen")
          }
          List()
        }
        if(movies.length != 0) printMovie(movies) else println("No records found!")
      }
      case _ => println("Wrong input, Please enter again")
    }
    println("\n")
    display()
    inp = try{
      StdIn.readInt()
    }catch {
      case e: NumberFormatException => println("Please choose the correct value from menu")
    }
    if(inp == 0){
      flag = false
    }
  }

  def printMovie(movieList: List[Movie]) = {
    movieList.map(movie => println(s"| Title: ${movie.title} | Published Year: ${movie.year} |" +
      s" Publised Date: ${movie.datePublished} | Genre: ${movie.genre} | " +
      s"Duration: ${movie.duration} | Country: ${movie.country} | Language: ${movie.language} |  Budget: ${movie.budget} | " +
      s"User Review: ${movie.reviewsFromUser} | Director: ${movie.director} | Votes: ${movie.votes}"))
  }
}
