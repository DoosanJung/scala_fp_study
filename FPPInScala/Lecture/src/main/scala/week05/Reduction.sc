object Reduction {

//  def sum(xs: List[Int]) = (0 :: xs) reduceLeft((x, y) => x + y)
  def sum(xs: List[Int]) = (0 :: xs) reduceLeft(_ + _)

//  def product(xs: List[Int]) = (1 :: xs) reduceLeft((x, y) => x * y)
  def product(xs: List[Int]) = (1 :: xs) reduceLeft(_ * _)

// reduceLeft only works with non empty List. throws Error if the list is empty.
// foldLeft (more general) will work with empty List too.
// if the list is empty, the accumulator will be returned.
  def sumFold(xs: List[Int]) = (xs foldLeft 0)(_ + _) //accumulator z == 0
  def productFold(xs: List[Int]) = (xs foldLeft 1)(_ * _) //accumulator z == 1

  def concat[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys) (_ :: _)
  //cannot replace foldRight by foldLeft
}

val lst = List(1, 2, 3, 4)
Reduction.sum(lst)
Reduction.product(lst)
Reduction.sumFold(lst)
Reduction.productFold(lst)
Reduction.concat(lst, lst)