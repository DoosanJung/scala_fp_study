object MoreFuncList {
  def last[T](xs: List[T]):T = xs match {
    case List() => throw new Exception("last of empty list")
    case List(x) => x
    case y :: ys => last(ys)
  }

  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Exception("init of empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case z :: zs => z :: concat(zs, ys)
  }

  def reverse[T](xs: List[T]): List[T] = xs match {
    case List() => xs
    //case List(x) => included already..
    case y :: ys => reverse(ys) ++ List(y)
  }

  def removeAt(n: Int, xs: List[Int]): List[Int] =
    (xs take n) ::: (xs drop n + 1)

  def flatten(xs: List[Any]): List[Any] = xs match {
    case Nil => Nil
    case (y :: ys) :: yss => flatten(y :: ys) ::: flatten(yss)
    //alternatively..
    //case (head: List[_]) :: tail => flatten(head) ++ flatten(tail)
    case head :: tail => head :: flatten(tail)
  }


}
val fruit = List("apples", "oranges", "pears")
val nums = List(1, 2, 3, 4)
val nums2 = List(8, 5, 7, 6)
val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
val empty = List()


MoreFuncList.last(nums)
MoreFuncList.init(nums)
MoreFuncList.concat(nums, nums2)
MoreFuncList.reverse(nums)
MoreFuncList.reverse(List(3))
MoreFuncList.removeAt(1, nums)
MoreFuncList.flatten(List(List(1, 1), 2, List(3, List(5, 8))))
MoreFuncList.flatten(List(List(List(4))))