package week04

trait List[+T] { //T is a Type parameter //Let's make T covariant "+T"
  //in order to distinguish Cons cell and the empty list
  def isEmpty: Boolean

  //if nonEmpty list
  def head: T

  //if noEmpty list, remaining list
  def tail: List[T]

  //this yields an error
  //def prepend(elem: T): List[T] = new Cons(elem, this)
  //make use of a lower bound: U be a supertype of the list element type T
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
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

/* Let's make a class Nil to an object Nil
* class Nil[T] extends List[T] {
  def isEmpty: Boolean = true //always Empty
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing= throw new NoSuchElementException("Nil.tail")
}
*/
object Nil extends List[Nothing] { //Nothing is a subtype of every other types
  def isEmpty: Boolean = true //always Empty
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing= throw new NoSuchElementException("Nil.tail")
}

object List {
  // List(1, 2) = List.apply(1, 2)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T](x: T): List[T] = new Cons(x, new Nil)
  def apply[T]() = new Nil
}

// this throws an error - Nil is not a subtype of String
/*
* object test {
  val x: List[String] = Nil
}
*/
//once we changed the type parameter T in List to +T, no error
//Nil is a list of Nothing, Nothing is a subtype of String, and List is covariants.
object test {
  val x: List[String] = Nil
}
