package assignment

object MovieUtils {

  val moveList: List[Movie] = fetchMovies

  def fetchMovies: List[Movie] = {
    val list = try{
      CSVFileReader.readMovies
    }catch{
      case ex: _ => throw ex
    }
    if(list.isEmpty)
      List()
    else
      list
  }

  def getReportByDirectorAndYear(director: String, fromYear: Int, toYear: Int): List[Movie] = {
    if(fromYear <= 0 || toYear <= 0)
      throw Exception("Please enter valid range of year")
    moveList.filter(movie => movie.director.toString.contains(director) &&
      (fromYear <= movie.year.toString.toInt && toYear >= movie.year.toString.toInt))
  }

  def getReportByLanguageAndReview(review: Int, language: String = "English"): List[Movie] = {
    if(review <= 0) throw Exception("Please enter valid review and language")
    moveList.filter(movie => {
      movie.language == language && movie.reviewsFromUser.toString != "" && movie.reviewsFromUser.toString.toInt >= review})
      .sortWith(_.reviewsFromUser.toString.toInt > _.reviewsFromUser.toString.toInt)
  }

  def getReportByCountryAndYear(country: String, year: Int): List[Movie] = {
    if(year <= 0) throw Exception("Invalid year must be greater than 0")
    moveList.filter(movie => movie.year != "year" && movie.year.toString.toInt == year && movie.country == country && movie.budget != "")
      .sortWith((movie1: Movie , movie2: Movie) => {
      compare(movie1.budget.toString,movie2.budget.toString)
    })
  }

  def getReportByCountryAndVoters(votes: Int, country: String): List[Movie] = {
    if(votes<=0) throw Exception("Please enter valid number of votes")
    moveList.filter(movie => {
      movie.country == country && movie.votes.toString.toInt >= votes
    }).sortWith(_.votes.toString.toInt > _.votes.toString.toInt)
  }

  def getReportByBudgetRangeAndCountry(min: Int, max: Int, country: String): List[Movie] = {
    if(min<=0 || max<=0) throw Exception("Invalid budget input must be grater than 0")
    moveList.filter(movie => {println(movie.country==country)
      movie.country == country && movie.budget.toString != "" && budgetStringToInt(movie.budget.toString) >= min
      && budgetStringToInt(movie.budget.toString) <= max})
      .sortWith((movie1: Movie , movie2: Movie) => {compare(movie1.budget.toString,movie2.budget.toString)})
  }

  def compare(value1: String, value2: String) = {
    val numeric = "0123456789"
    val value1Int = value1.toCharArray.filter(c => numeric.contains(c)).mkString.toInt
    val value2Int = value2.toCharArray.filter(c => numeric.contains(c)).mkString.toInt
    value1Int > value2Int
  }

  def budgetStringToInt(budget: String): Int = {
    val numeric = "0123456789"
    budget.toCharArray.filter(c => numeric.contains(c)).mkString.toInt
  }
}
