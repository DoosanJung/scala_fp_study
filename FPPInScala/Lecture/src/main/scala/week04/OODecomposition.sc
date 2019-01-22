//Solution 1 - Object Oriented Decomposition
//suppose that all you want to do is evaluate expressions
trait Expr {
  def eval: Int
}
class Number(n: Int) extends Expr {
  def eval: Int = n
}
class Sum(e1: Expr, e2: Expr) extends Expr {
  def eval: Int = e1.eval + e2.eval
}


//testing..
val a = new Sum(new Number(1), new Number(2))
a.eval
/*
This is not good.
(1) what happens if you’d like to display expressions now?
e.g. add def show: String in trait Expr, you need to touch Number and Sum
You have to define new methods in all the subclasses.

(2) what if you want to simplify the expressions, say using the rule:
a * b + a * c -> a * (b + c)
Problem: This is a non-local simplification. It cannot be
encapsulated in the method of a single object.
You are back to square one; you need test and access methods for
all the different subclasses.
* */