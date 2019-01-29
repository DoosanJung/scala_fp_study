import math.Ordering

object ListExamples {
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

  def mergeSort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = { //lt == less than
    val n = xs.length/2
    if (n ==0) xs //xs has 0 or 1 elment
    else {

      //shorter and cleaner
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n //the lists are returned in a pair
      merge(mergeSort(fst)(lt), mergeSort(snd)(lt))
    }
  }

  //using predefined func
  def mergeSortOrdering[T](xs: List[T])(ord: Ordering[T]): List[T] = {
    val n = xs.length/2
    if (n ==0) xs //xs has 0 or 1 elment
    else {

      //shorter and cleaner
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n //the lists are returned in a pair
      merge(mergeSortOrdering(fst)(ord), mergeSortOrdering(snd)(ord))
    }
  }

  //using predefined func
  def mergeSortOrderingImplicit[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length/2
    if (n ==0) xs //xs has 0 or 1 elment
    else {

      //shorter and cleaner
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n //the lists are returned in a pair
      merge(mergeSortOrderingImplicit(fst), mergeSortOrderingImplicit(snd))
      //no need to put (ord) <= visible at the function call
    }
  }
}


val lst = List(7,3,9,2)
ListExamples.insertionSort(lst)
ListExamples.mergeSort(lst)((x: Int, y: Int) => x < y)

val fruits = List("apple", "pineaple", "oranges", "banana")
ListExamples.mergeSort(fruits)((x: String, y:String)=> x.compareTo(y) < 0)
// if the first string less than the second string, then return -1
//Since Scala compiler can figure out data type
ListExamples.mergeSort(lst)((x, y) => x < y)
ListExamples.mergeSort(fruits)((x, y)=> x.compareTo(y) < 0)

ListExamples.mergeSortOrdering(lst)(Ordering.Int)
ListExamples.mergeSortOrdering(fruits)(Ordering.String)

ListExamples.mergeSortOrderingImplicit(lst)
ListExamples.mergeSortOrderingImplicit(fruits)
