//Solution 2 - Functional decomposition with pattern matching
trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
/*
* It also implicitly defines companion objects with apply methods.

object Number {
  def apply(n: Int) = new Number(n)
}

object Sum {
  def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
}

so you can write Number(1) instead of new Number(1).
*/

/*
* Pattern matching is a generalization of switch from C/Java to class hierarchies.
It’s expressed in Scala using the keyword match.
*/
def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
}
eval(Sum(Number(1), Number(2)))
/*
* it’s also possible to define the evaluation function as a method of the base trait.
trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }
}
*/
object Exprs {
  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
  }
}
Exprs.show(Sum(Number(1), Number(44)))