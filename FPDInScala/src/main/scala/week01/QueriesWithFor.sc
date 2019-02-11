object Books {
  case class Book(title: String, authors: List[String])

  //Mini-Database
  val books: List[Book] = List(
    Book(title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),

    Book(title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),

    Book(title = "Effective Java",
      authors = List("Bloch, Joshua")),

    Book(title = "Effective Java2",
      authors = List("Bloch, Joshua")),

    Book(title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")),

    Book(title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
  )

  val res0 = for (b <- books; a <- b.authors if a startsWith "Bird,")
    yield b.title

  val res1 = for (b <- Books.books if (b.title indexOf "Program") >= 0)
    yield b.title

  val res2 = for {
    b1 <- books
    b2 <- books
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1

  val res3 = for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1

  val res4 = {
    for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1
  }.distinct

  //changed the database from List to Set
  val booksSet: Set[Book] = Set(
    Book(title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),

    Book(title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),

    Book(title = "Effective Java",
      authors = List("Bloch, Joshua")),

    Book(title = "Effective Java2",
      authors = List("Bloch, Joshua")),

    Book(title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")),

    Book(title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
  )
  val res5 = for {
    b1 <- booksSet
    b2 <- booksSet
    if b1 != b2 //just works!
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1
}

Books.res0
Books.res1
Books.res2
Books.res3
Books.res4
//changed the database from List to Set
Books.res5