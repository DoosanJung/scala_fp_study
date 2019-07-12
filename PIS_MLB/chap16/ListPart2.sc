object HigherOrderMethodsList {

  //Mapping over lists: map, flatMap and foreach
  val plus1 = List(1, 2, 3) map (_ + 1)
  val words = List("the", "quick", "brown", "fox")
  val lengths = words map (_.length)
  val reversed = words map (_.toList.reverse.mkString)

  val res = List.range(1, 5) flatMap (
    i => List.range(1, i) map (j => (i, j))
  )

  val resfor = for (i <- List.range(1, 5); j <- List.range(1, i)) yield (i, j)

  var sum = 0

  //Filtering lists: filter, partition, find, takeWhile, dropWhile, and span
  val even = List(1, 2, 3, 4, 5, 6) filter (_ % 2 == 0)
  val wordsLength3 = words filter (_.length == 3)

  val evenPartition = List(1, 2, 3, 4, 5) partition (_ % 2 == 0)

  val evenFind = List(1, 2, 3, 4, 5, 6) find (_ % 2 == 0)
  val negativeFind = List(1, 2, 3, 4, 5) find (_ <= 0)

  val positiveTakeWile = List(1, 2, 3, -4, 5) takeWhile (_ > 0)
  val wordsNotStartT = words dropWhile (_ startsWith "t")

  val spanList = List(1, 2, 3, -4, 5) span (_ > 0)

  //Predicates over lists: forall and exists
  def hasZeroRow(m: List[List[Int]]) =
    m exists (row => row forall (_ == 0))

  val diag3 =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )

  val test = hasZeroRow(diag3)

  //Folding lists: /: and :\
  //A fold left operation “ (z /: xs) (op) ” involves three objects:
  //a start value z , a list xs , and a binary operation op
  //(z /: List(a, b, c)) (op)
  //equals
  //op(op(op(z, a), b), c)
  def sum(xs: List[Int]): Int = (0 /: xs) (_ + _)
  def product(xs: List[Int]): Int = (1 /: xs) (_ * _)

  val joinedWords = ("" /: words) (_ + " " + _)
  val joinedWords2 = (words.head /: words.tail) (_ + " " + _)
}

HigherOrderMethodsList.plus1 //res0: List[Int] = List(2, 3, 4)
HigherOrderMethodsList.lengths  //res1: List[Int] = List(3, 5, 5, 3)

//You see that where map returns a list of lists,
//flatMap returns a single list in which all element lists are concatenated
HigherOrderMethodsList.words map (_.toList)
//res2: List[List[Char]] = List(List(t, h, e), List(q, u, i, c, k), List(b, r, o, w, n), List(f, o, x))
HigherOrderMethodsList.words flatMap (_.toList)
//res3: List[Char] = List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x)

HigherOrderMethodsList.res //res4: List[(Int, Int)] = List((2,1), (3,1), (3,2), (4,1), (4,2), (4,3))
HigherOrderMethodsList.resfor //res5: List[(Int, Int)] = List((2,1), (3,1), (3,2), (4,1), (4,2), (4,3))

List(1, 2, 3, 4, 5) foreach (HigherOrderMethodsList.sum += _)
HigherOrderMethodsList.sum //res7: Int = 15

//filtering
HigherOrderMethodsList.even //res8: List[Int] = List(2, 4, 6)
HigherOrderMethodsList.wordsLength3 //res9: List[String] = List(the, fox)
HigherOrderMethodsList.evenPartition //res10: (List[Int], List[Int]) = (List(2, 4),List(1, 3, 5))
HigherOrderMethodsList.evenFind //res11: Option[Int] = Some(2)
HigherOrderMethodsList.negativeFind //res12: Option[Int] = None
HigherOrderMethodsList.positiveTakeWile //res13: List[Int] = List(1, 2, 3)
HigherOrderMethodsList.wordsNotStartT //res14: List[String] = List(quick, brown, fox)
HigherOrderMethodsList.spanList //res15: (List[Int], List[Int]) = (List(1, 2, 3),List(-4, 5))

//Perdicates
HigherOrderMethodsList.test //false

//Folding lists: /: and :\
HigherOrderMethodsList.joinedWords //res17: String = " the quick brown fox"
HigherOrderMethodsList.joinedWords2 //res18: String = the quick brown fox