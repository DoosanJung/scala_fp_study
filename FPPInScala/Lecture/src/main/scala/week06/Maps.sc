object Maps {
  //Map[String, Int]
  val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)

  //Map[String, String]
  val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")

  def showCapital(country: String) = capitalOfCountry.get(country) match {
    case Some(capital) => capital
    case None => "missing data"
  }

  //instead of using get()...
  //withDefaultValue turns a map into a total function, instead of partial functions.
  //Maps were partial functions: Applying a map to a key value in map(key)
  //could lead to an exception, if the key was not stored in the map.
  val cap1  =capitalOfCountry withDefaultValue "<unknown>"
}

Maps.capitalOfCountry("US")
//to prevent NoSuchElementException: key not found
// None: if map does not contain the given key
Maps.capitalOfCountry get "Republic of Korea"
// Some(x): if map associates the given key with the value x
Maps.capitalOfCountry get "US"

Maps.showCapital("Republic of Korea")
Maps.showCapital("US")
//withDefaultValue
Maps.cap1("Republic of Korea")

object OrderByGroupBy {
  val fruit = List("apple", "pear", "orange", "pineapple")
}
OrderByGroupBy.fruit sortWith (_.length < _.length)
OrderByGroupBy.fruit.sorted
OrderByGroupBy.fruit groupBy (_.head)
