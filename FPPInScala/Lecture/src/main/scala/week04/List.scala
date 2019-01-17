package week04

trait List[T] { //T is a Type parameter
  //in order to distinguish Cons cell and the empty list
  def isEmpty: Boolean

  //if nonEmpty list
  def head: T

  //if noEmpty list, remaining list
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false //never Empty
  /*
  * is same as below
  * class Cons[T](_head: T, _tail: List[T]) extends List[T] {
  *   val head = _head
  *   val tail = _ tail  *
  * }
  * where _head and _tail are otherwise unused names
  * */
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true //always Empty
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing= throw new NoSuchElementException("Nil.tail")
}

