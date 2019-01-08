/*
Reducing code duplication using Higer-Order Function!
*/

//Final version
object FileMatcher {
  //obtains the list of all files in the current directory
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) = 
    for (file <- filesHere; if matcher(file.getName))
      yield file

  def filesEnding(query: String) = 
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) = 
    filesMatching(_.contains(query))

  def filesRegex(query: String) =
    filesMatching(_.matches(query))

}

/*
Moreover, the function literal _.endsWith(query), used in the final version, contains 
one bound variable, the argument represented by the underscore, and one free variable named query.
=> closure, more concise

By contrast, _.endsWith(_), used in the second attempt, uses two bound variables, and no free variables.
=> Not a closure
*/

//First attempt
object FileMatcher1 {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEnding1(query: String) =
    for (file <- filesHere; if file.getName.endsWith(query))
      yield file

  //later on, you add.. 
  def filesContaining1(query: String) = 
    for (file <- filesHere; if file.getName.contains(query))
      yield file

  //later on, you also add..
  def filesRegex1(query: String) =
    for (file <- filesHere; if file.getName.matches(query))
      yield file
}
//it becomes redundant code duplication

//second attempt
object FileMatcher2 {
  private def filesHere = (new java.io.File(".")).listFiles

  //matcher function takes two string arguments—the file name and the query—and returns a boolean
  def filesMatching2(query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
}
  
  //given this new filesMatching helper method ..
  def filesEnding2(query: String) =
    filesMatching2(query, _.endsWith(_))

  def filesContaining2(query: String) =
    filesMatching2(query, _.contains(_))

  def filesRegex2(query: String) =
    filesMatching2(query, _.matches(_))
  
  /* 
  The function literal _.endsWith(_), used in the filesEnding method, means the same thing as:
    (fileName: String, query: String) => fileName.endsWith(query)

  Because filesMatching takes a function that requires two String arguments, however, 
  you need not specify the types of the arguments. Thus you could also write:
    (fileName, query) => fileName.endsWith(query)

  Since the parameters are each used only once in the body of the function, 
  and since the first parameter, fileName, is used first in the body, 
  and the second parameter, query, is used second, you can use the placeholder syntax: 
    _.endsWith(_)
  
  The first underscore is a placeholder for the first parameter, the file name, 
  and the second underscore a placeholder for the second parameter, the query string.
  */
}

//you can even more simplify by removing 'query' from filesMatching => using closure => final version

