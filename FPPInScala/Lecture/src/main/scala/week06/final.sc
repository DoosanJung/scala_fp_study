import scala.io.Source

object MnemonicPhoneNumber {
  /* read a file of words */
  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

  /* create a list and filter all words where their characters are not letters (like -) */
  val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))

  /* define the map of numbers to letters */
  val nmem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ"
  )

  /* invert the map the get a map of letters to digits */
  val charCode: Map[Char, Char] =
    for ((digit, str) <- nmem; ltr <- str) yield ltr -> digit

  /* define a function that returns the numbers of a given word */
  def wordCode(word: String): String =
    word.toUpperCase map charCode

  /* group all words of our long list with the same number */
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq() //empty sequence as a default value

  /* function that receives a number and finds the words that match it */
  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length // iterate over the number
        word <- wordsForNum(number take split) // get the word before the spilt
        rest <- encode(number drop split) //get the words after the split
      } yield word :: rest
    }.toSet // pass a set to the for

  /* better print of the results */
  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")


}
MnemonicPhoneNumber.in //res0: scala.io.BufferedSource = <iterator>
MnemonicPhoneNumber.words.isEmpty //res1: Boolean = true
MnemonicPhoneNumber.nmem //res2: scala.collection.immutable.Map[Char,String] = Map(8 -> TUV, 4 -> GHI, 9 -> WXYZ, 5 -> JKL, 6 -> MNO, 2 -> ABC, 7 -> PQRS, 3 -> DEF)
MnemonicPhoneNumber.wordCode("Java") //res3: String = 5282
MnemonicPhoneNumber.encode("5282") //res4: Set[List[String]] = Set()
MnemonicPhoneNumber.encode(number="7225247386") //res5: Set[List[String]] = Set()
