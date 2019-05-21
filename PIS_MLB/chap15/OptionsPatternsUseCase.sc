/*
Scala has a standard type named Option for optional values. Such a value
can be of two forms. It can be of the form Some(x) where x is the actual
value. Or it can be the None object, which represents a missing value.
*/

object Options {
   val capitals = Map("France" -> "Paris", "South Korea" -> "Seoul")

   def show(x: Option[String]) = x match {
      case Some(s) => s
      case None => "?"
   }

   val res = show(capitals get "South Korea")
   val res2 = show(capitals get "North Korea")
}

Options.capitals
Options.capitals get "France"
Options.capitals get "North Pole"
Options.res
Options.res2

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object PatternsUseCase {
   //Any time you define a val or a var,
   // you can use a pattern instead of a simple identifier.
   val myTuple = (123, "abc")
   val (number, string) = myTuple

   //quite useful when working with case classes
   val exp = new BinOp("*", Number(5), Number(1))
//   val BinOp(op, left, right) = exp
}
PatternsUseCase.number
PatternsUseCase.string
val BinOp(op, left, right) = PatternsUseCase.exp

