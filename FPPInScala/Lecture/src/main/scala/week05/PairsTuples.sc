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

  def mergeSort(xs: List[Int]): List[Int] = {
    val n = xs.length/2
    if (n ==0) xs //xs has 0 or 1 elment
    else {

      def merge(xs: List[Int], ys: List[Int]): List[Int] = xs match {
          case Nil => ys //if the left list is Nil
          case x :: xs1 => ys match {
            case Nil => xs //if the right list is Nil
            case y :: ys1 =>  // two head elements x and y, two tail elements xs and ys
              if (x < y) x :: merge(xs1, ys) // x is the smallest one, merge remaining xs1 and ys
              else y :: merge(xs, ys1) // y is the smallest one, merge remaining xs and ys1
        }
      }

      val (fst, snd) = xs splitAt n //the lists are returned in a pair
      merge(mergeSort(fst), mergeSort(snd))
    }
  }

  def mergeSort2(xs: List[Int]): List[Int] = {
    val n = xs.length/2
    if (n ==0) xs //xs has 0 or 1 elment
    else {

      //shorter and cleaner
      def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n //the lists are returned in a pair
      merge(mergeSort2(fst), mergeSort2(snd))
    }
  }
}


val lst = List(7,3,9,2)
val res = ListExamples.insertionSort(lst)
val res2 = ListExamples.mergeSort(lst)
val res3 = ListExamples.mergeSort2(lst)

object Pair {
  val pair = ("answer", 42)
  val (label, value) = pair //short and clear
  //same as above but not preferred
  val label2 = pair._1
  val value2 = pair._2
 }

