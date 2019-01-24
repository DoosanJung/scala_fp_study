object ListExamples {
  val fruit = List("apples","oranges","pears")
  val nums = List(1,2,3,4)
  val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  val empty = List()

  //Lists are homogeneous: the elements of a list must all have the same type
  val fruit2: List[String] = List("apples","oranges","pears")
  val nums2: List[Int] = List(1,2,3,4)
  val diag32: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  val empty2: List[Nothing] = List()

  //the construction operation :: and the empty list Nil
  val fruit3 = "apples" :: ("oranges" :: ("pears" :: Nil))
  val nums3 = 1 :: 2 :: 3 :: 4 :: Nil
  // operators ending in ':' => can omit the parentheses
  // same as Nil.::(4).::(3).::(2).::(1)
  val empty3 = Nil

  //fundamental operations
  fruit.head == "apples"
  fruit.tail.head == "oranges"
  diag3.head == List(1, 0, 0)
  //empty.head == throw new NoSuchElementException("head of empty list")

  //List patterns
  /*
  * Nil Pattern == the Nil constant
  * p :: ps Pattern == A pattern that matches a list with a head matching p and a tail matching ps
  * List(p1, ..., pn) == same as p1 :: ... :: pn :: Nil
  * */

  //sorting lists
  //complexity of insertionsort is proportional to N*N
  def insertionSort(xs: List[Int]): List[Int] = xs match {
    //is it empty?
    case List() => List()
    //if it's not, then...
    case y :: ys => insert(y, insertionSort(ys))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    //if empty list, then the list that contains just the element
    case List() => List(x)
    //if x<= y, then x wil be the head element of the new list
    //else (y < x) then y will be the head element of the new list because y is the
    //smallest element we've seen. And call insert recursively on x and the tail('ys')
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
  }


}

val lst = List(7,3,9,2)
val res = ListExamples.insertionSort(lst)