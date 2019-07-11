object ListLiterals {
  /*
  * First, lists are immutable.
  * That is, elements of a list cannot be changed by assignment.
  * Second, lists have a recursive structure (i.e., a linked list),
  * whereas arrays are flat.
  * */
  val fruit = List("apples", "oragnes", "pears")
  val nums = List(1, 2, 3, 4)
  val diag3 =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )
  val empty = List()
}

ListLiterals.fruit

//Error: reassignment to val
//ListLiterals.fruit = List("something", "random")

object ListType {
  /*
  * Covariant:
  * For each pair of types S and T, if S is a subtype of T,
  * then List[S] is a subtype of List[T].
  */
  val fruit: List[String] = List("apples", "oragnes", "pears")
  val nums: List[Int] = List(1, 2, 3, 4)
  val diag3: List[List[Int]] =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )

  //Nothing is a subtype of every other Scala type
  val empty: List[Nothing] = List()
  // List() is also of type List[String]
  val xs: List[String] = List()
}

object ConstructingLists {
  /* The previous list values could also have been
  defined as follows:
  * */
  val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
  val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
  val nums2 = 1 :: 2 :: 3 :: 4 :: Nil //same as nums
  val diag3 = (1 :: (0 :: (0 :: Nil))) ::
              (0 :: (1 :: (0 :: Nil))) ::
              (0 :: (0 :: (1 :: Nil))) :: Nil
  val empty = Nil
}

object BasicOperationsList {
  /*
  * head: returns the first element of a list
  * tail: returns a list consisting of all elements except the first
  * isEmpty: returns true if the list is empty
  * */

  val test = List(4, 5, 7, 2, 1)

  //insertion sort
  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))

  def insert(x:Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)
}

BasicOperationsList.isort(BasicOperationsList.test)


object ListPatterns {
  val List(a, b,c ) = ConstructingLists.fruit
  val d :: e :: rest = ConstructingLists.fruit

  //insertion sort with pattern matching
  //often times, pattern matching is cleaner
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs1 => insert(x, isort(xs1))
  }

  def insert(x:Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs
                    else y :: insert(x, ys)
  }
}

ListPatterns.a //"apples"
ListPatterns.d //"apples"
ListPatterns.isort(xs = BasicOperationsList.test)

object FirstOrderMethodsList {
  //concatenating two lists
  val list1 = List(1, 2)
  val list2 = List(3, 4, 5)
  val concat = list1 ::: list2

  //let's implement ::: operator
  def append[T](xs: List[T], ys: List[T]): List[T] =
    xs match {
      case List() => ys
      case x :: xs1 => x :: append(xs1, ys)
    }

  val testappend = append(list1, list2)

  //length
  val len = list2.length

  //Accessing the end of a list: init and last
  //Unlike head and tail , which both run in constant time, init and last need
  //to traverse the whole list to compute their result.
  val abcde = List("a", "b", "c", "d", "e")

  //reversing lists: creates a new list
  val edcbe = abcde.reverse

  //let's implement reverse operator using :::
  //n^2 complexity
  def rev[T](xs: List[T]): List[T] = xs match {
    case List() => xs
    case x :: xs1 => rev(xs1) ::: List(x)
  }

  //prefixes and suffixes: drop, take and splitAt
  val take2 = abcde take 2
  val drop2 = abcde drop 2
  val splitat2 = abcde splitAt 2

  //Element selection: apply and indices
  val apply2 = abcde apply 2
  val ind = abcde.indices

  //Flattening a list of lists: flatten
  val flat = List(List(1, 2), List(3), List(), List(4, 5)).flatten
  val chararrayflat = List("apples", "oranges", "pears").map(_.toCharArray).flatten

  //Zipping lists: zip and unzip
  val zipped1 = abcde.indices zip abcde
  val zipped2 = abcde zip List(1, 2, 3) //unmatched dropped
  val zipped3 = abcde.zipWithIndex
  val unzipped = zipped2.unzip

  //Displaying lists: toString and mkString
  //mkString(pre, sep, post)

  //Converting lists: iterator , toArray , copyToArray
  val arr = abcde.toArray
  val lst = arr.toList
  val arr2 = new Array[Int](10)
  //Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
  List(1, 2, 3) copyToArray (arr2, start = 3)
  //Array[Int] = Array(0, 0, 0, 1, 2, 3, 0, 0, 0, 0)
  val iter = abcde.iterator

  //Example: mergesort
  //complexity (n * log(n))
  //less: a function to be used for the comparison of elements
  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

    val n = xs.length / 2

    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  //currying!
  val intSort = msort((x: Int, y:Int) => x < y) _

  //reverse Int Sort
  val reverseIntSort = msort((x: Int, y:Int) => x > y) _

  val mixedInts = List(4, 1, 9, 0, 5, 8, 3, 6, 2, 7)
}

FirstOrderMethodsList.concat //List{Int] = List(1, 2, 3, 4, 5)
FirstOrderMethodsList.testappend //List{Int] = List(1, 2, 3, 4, 5)
FirstOrderMethodsList.len
FirstOrderMethodsList.abcde.last //"e"
FirstOrderMethodsList.abcde.init //List(a, b, c, d)
FirstOrderMethodsList.edcbe
FirstOrderMethodsList.abcde //List(a, b, c, d, e)
FirstOrderMethodsList.rev(FirstOrderMethodsList.abcde)
FirstOrderMethodsList.abcde //List(a, b, c, d, e)
FirstOrderMethodsList.take2 //List(a, b)
FirstOrderMethodsList.drop2 //List(c, d, e)
FirstOrderMethodsList.splitat2 //(List(a, b), List(c, d, e))
FirstOrderMethodsList.apply2 //c
FirstOrderMethodsList.ind //Range 0 until 5
FirstOrderMethodsList.flat //List(1, 2, 3, 4, 5)
FirstOrderMethodsList.chararrayflat
//List(a, p, p, l, e, s, o, r, a, n, g, e, s, p, e, a, r, s)
FirstOrderMethodsList.zipped1
FirstOrderMethodsList.zipped2
FirstOrderMethodsList.zipped3
FirstOrderMethodsList.unzipped
FirstOrderMethodsList.abcde.toString
FirstOrderMethodsList.abcde mkString ("[", ",", "]")
FirstOrderMethodsList.abcde mkString ""
FirstOrderMethodsList.arr
FirstOrderMethodsList.arr2
FirstOrderMethodsList.iter.next //a
FirstOrderMethodsList.iter.next //b
FirstOrderMethodsList.iter.next //c
FirstOrderMethodsList.iter.next //d
FirstOrderMethodsList.iter.next //e
//FirstOrderMethodsList.iter.next //java.util.NoSuchElementException
FirstOrderMethodsList.msort((x: Int, y:Int) => x < y)(List(5, 7, 1, 3))
FirstOrderMethodsList.intSort(FirstOrderMethodsList.mixedInts)
FirstOrderMethodsList.reverseIntSort(FirstOrderMethodsList.mixedInts)
