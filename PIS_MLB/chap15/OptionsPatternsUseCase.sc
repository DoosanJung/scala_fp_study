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

   //case sequences as partial func
   val withDefault: Option[Int] => Int = {
      case Some(x) => x
      case None => 0
   }

   val default = withDefault(Some(10))
   val defaultNone = withDefault(None)

   //a sequence of cases gives you a partial function
   //the compiler will correctly complain that the match is not exhaustive
   val secondWrong: List[Int] => Int = {
      case x :: y :: _ => y
   }

   //isDefinedAt method exists for partial func
   val second: PartialFunction[List[Int],Int] = {
      case x :: y :: _ => y
   }
   /*
   *in this case,
   *def isDefinedAt(xs: List[Int]) = xs match {
      case x :: y :: _ => true
      case _ => false
     }
   * */

   //Patterns in for expression
   def forLoop(x: Unit) = {
      for ((country, city) <- Options.capitals)
         println("The capital of "+ country +" is "+ city)
   }

   //possible that a pattern might not match a generated value
   //generated values that do not match the pattern are discarded
   //the second element None in the results list does not match the pattern Some(fruit);
   //therefore it does not show up in the output.
   def forLoop2(x: Unit): Unit = {
      val results = List(Some("apple"), None, Some("orange"))
      for (Some(fruit) <- results) println(fruit)
   }


}

PatternsUseCase.number
PatternsUseCase.string
val BinOp(op, left, right) = PatternsUseCase.exp

PatternsUseCase.default
PatternsUseCase.defaultNone

PatternsUseCase.secondWrong(List(5, 6, 7)) //works
PatternsUseCase.secondWrong(List(5, 6)) //works
//PatternsUseCase.secondWrong(List()) //throws a scala.MatchError
//PatternsUseCase.secondWrong.isDefinedAt() //no method

PatternsUseCase.second(List(1, 2, 3)) //works
PatternsUseCase.second(List(1, 2)) //works
//PatternsUseCase.second(List())//throws a scala.MatchError
PatternsUseCase.second.isDefinedAt(List(1, 2, 3)) //Boolean = true
PatternsUseCase.second.isDefinedAt(List(1, 2)) //Boolean = true
PatternsUseCase.second.isDefinedAt(List()) //Boolean = false

PatternsUseCase.forLoop()
PatternsUseCase.forLoop2()