/*
A pattern guard comes after a pattern and starts with an if.
The guard can be an arbitrary boolean expression, which typically refers to variables
in the pattern.
If a pattern guard is present, the match succeeds only if the guard evaluates to true.
*/

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object PatternGuard {
  //pattern guard
  /*
  *a pattern variable may only appear once in a pattern
  *
  *For example,
  *e + e => e * 2
  *BinOp("+", Var("x"), Var("x")) => BinOp("*", Var("x"), Number(2))
  *def simplifyAdd(e: Expr) = e match {
    case BinOp("+", x, x) => BinOp("*", x, Number(2))
    case _ => e
  }
  <console>:11: error: x is already defined as value x
  case BinOp("+", x, x) => BinOp("*", x, Number(2))
  *                  ^
  *
  * */
  def simplifyAdd(e: Expr) = e match {
    //a pattern variable x should only be used once
    case BinOp("+", x, y) if x == y =>
      BinOp("*", x, Number(2))
    case _ => e
  }

  val op = BinOp("+", Var("x"), Var("x"))
  val res = simplifyAdd(op)

  //other examples
  // match only positive integers
  // case n: Int if 0 < n => ...
  // match only strings starting with the letter ‘a’
  // case s: String if s(0) == 'a' => ...
}

PatternGuard.op
PatternGuard.res


