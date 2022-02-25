package assignment

import java.io.{File, FileNotFoundException}
import scala.io.Source

object CSVFileReader {
  val filePath = "C:\\Mohit Data\\Scala-Training\\Assignment\\src\\main\\resources\\movies_dataset.csv"

  def readMovies: List[Movie] = {
    val file = new File(filePath)
    var movieList: List[Movie] = List()
    val fileContent = try{
      Source.fromFile(file)
    } catch{
      case ex: FileNotFoundException => {
        throw FileNotFoundException("File not found")
      }
      case ex: _ => {
        throw Exception("Something happens wrong")
      }
    }
    var count = 0
    for(
      line <- fileContent.getLines
    ){
      if( count <= 10000){
        val col = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1).map(_.trim)
        val movie: Movie = new Movie()
        movie.imdbTitleId = col(0)
        movie.title = col(1)
        movie.originalTitle = col(2)
        movie.year = col(3)
        movie.datePublished = col(4)
        movie.genre = col(5)
        movie.duration = col(6)
        movie.country = col(7)
        movie.language = col(8)
        movie.director = col(9)
        movie.writer = col(10)
        movie.productionCompany = col(11)
        movie.actors = col(12)
        movie.description = col(13)
        movie.averageVote = col(14)
        movie.votes = col(15)
        movie.budget = col(16)
        movie.useGrossIncome = col(17)
        movie.worldwideGrossIncome = col(18)
        movie.metascore = col(19)
        movie.reviewsFromUser = col(20)
        movie.reviewFromCritics = col(21)
        movieList = movieList :+ movie
      }
      count+=1
    }
    fileContent.close
    movieList
  }
}
