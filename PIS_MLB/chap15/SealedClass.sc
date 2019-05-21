/*
A sealed class cannot have any new subclasses added except the ones in the same file.
This is very useful for pattern matching, because it means you only need to worry about
the subclasses you already know about.
*/

sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object SealedClass {
  // non-exhaustive pattern matching will raise Warning
  //  def describeNonExhaustive(e: Expr): String = e match {
  //    case Number(_) => "a number"
  //    case Var(_) => "a variable"
  //  }
  //=> match may not be exhaustive!
  //=> it would fail on the following inputs: BinOp(_,_,_), UnOp(_,_)

  //o make the warning go away, you could add;
  //a third catch-all case to the method, like this:
  //=> not ideal: one more line...
  def describeSupressWarning(e: Expr): String = e match {
    case Number(_) => "a number"
    case Var(_) => "a variable"
    case _ => throw new RuntimeException // Should not happen
  }

  //little bit more concise
  def describe(e: Expr): String = (e: @unchecked) match {
    case Number(_) => "a number"
    case Var(_)    => "a variable"
  }

  val v = Var("x")
  val resv = describe(v)
  val op = BinOp("+", Var("x"), Var("x"))
  //val resop = describe(op)//=> scala.MatchError
}

SealedClass.v
SealedClass.resv
SealedClass.op
//SealedClass.resop //=> scala.MatchError


