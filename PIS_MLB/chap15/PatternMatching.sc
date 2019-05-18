/*
* Use pattern matching!
* UnOp("-", UnOp("-", e )) => e // Double negation
BinOp("+", e , Number(0)) => e // Adding zero
BinOp("*", e , Number(1)) => e // Multiplying by one
*/
import math.{E, Pi}

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object PatternMatching {
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e))  => e // Double negation
    case BinOp("+", e, Number(0)) => e // Adding zero
    case BinOp("*", e, Number(1)) => e
    case _ => expr // Multiplying by one
  }

  val v = Var("x")
  val res = simplifyTop(UnOp("-", UnOp("-", v)))

  val op = BinOp("+", Number(1), v)
  val resop = simplifyTop(BinOp("+", op, Number(0)))

}

PatternMatching.v
PatternMatching.res
PatternMatching.op
PatternMatching.resop


object Patterns {
  //Wildcard patterns
  def wildcardPatterns(expr: Expr): Unit = expr match {
    case BinOp(_, _, _) => println(expr +" is a binary operation")
    case _ => println("It's something else")
  }

  //constant patterns
  def describe(x: Any): String = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

  //variable patterns
  def variablePatterns(x: Any): String = x match {
    case 0 => "zero"
    case somethingElse => "not zero: "+ somethingElse
  }

  //constant vs variable patterns
  //How does the Scala compiler know that Pi is a constant imported from
  //scala.math, and not a variable that stands for the selector value itself?
  //=> a simple name starting with a lowercase letter is taken to be a pattern variable;
  //all other references are taken to be constants.
  def uppercasePatterns(x: Any): String = x match {
    case Pi => "strange math? Pi = "+ Pi
    case _ => "OK"
  }

  def lowercasePatterns(x: Any): String = x match {
    case pi => "strange math? Pi = "+ pi
    //compiler will not even let you add a default case at all
    // case _ => "OK"
  }

  //constructor patterns
  //not only check the top-level object supplied, but also check
  //the contents of the object against further patterns
  def constructorPattenrs(expr: Expr): Unit = expr match {
    case BinOp("+", e, Number(0)) => println("a deep match")
    case _ =>
  }

  //sequence patterns
  //you can specify _* as the last element of the pattern
  //case List(0, _*) => println("found it")
  def sequencePatterns(x: Any): Unit = x match {
    case List(0, _, _) => println("found it")
    case _ =>
  }

  //tuple patterns
  def tupleDemo(x: Any): Unit = x match {
    case (a, b, c) => println("matched "+a+", "+b+", "+c)
    case _ =>
  }

  //typed patterns
  //as a convenient replacement for type tests and
  //type casts
  def generalSize(x: Any) = x match {
    case s: String => s.length
    //An equivalent but more long-winded way
    /*
    * if (x.isInstanceOf[String]) {
        val s = x.asInstanceOf[String]
        s.length
      }
    * => writing type tests and casts is rather verbose in Scala
    * You are usually better off using a pattern match with a typed pattern.
    * */
    case m: Map[_, _] => m.size
    case _ => -1
  }

  //weird run-time behavior
  /*
  * type pattern is unchecked since it is eliminated by erasure
  * case m: Map[Int, Int] => true
  * */
  def isIntIntMap(x: Any): Boolean = x match {
    case m: Map[Int, Int] => true
    case _ => false
  }

  //The only exception to the erasure rule is arrays
  def isStringArray(x: Any): String = x match {
    case a: Array[String] => "yes"
    case _ => "no"
  }

  //variable binding
  //to perform the pattern match as normal, and if the pattern succeeds,
  //set the variable to the matched object just as with a simple variable pattern.
  def varBinding(expr: Expr): Expr = expr match {
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case _ => Var("no match")
  }

  val op = BinOp("+", Number(1), Var("x"))
  val pi = math.Pi
  val op2 = BinOp("+", Var("x"), Number(0))
  val as = Array("abc") //array of strings
  val ai = Array(1, 2, 3) // array of integers
  val op3 = UnOp("abs", UnOp("abs", Var("x")))

}

Patterns.wildcardPatterns(Patterns.op)
Patterns.describe(5)
Patterns.describe(List(1, 2, 3))
Patterns.variablePatterns(0)
Patterns.uppercasePatterns(E)
Patterns.lowercasePatterns(E)
Patterns.lowercasePatterns(Patterns.pi)
Patterns.constructorPattenrs(expr=Patterns.op)
Patterns.constructorPattenrs(expr=Patterns.op2)
Patterns.sequencePatterns(List(0, 1, 2))
Patterns.tupleDemo(("apple", "banana", "orange"))
Patterns.generalSize("abc")
Patterns.generalSize(Map(1 -> 'a', 2 -> 'b'))
Patterns.generalSize(math.Pi)
Patterns.isIntIntMap(Map(1 -> 1))
Patterns.isIntIntMap(Map("abc" -> "abc"))
Patterns.isStringArray(Patterns.as)
Patterns.isStringArray(Patterns.ai)
Patterns.varBinding(Patterns.op3)

//www.tutorialspoint.com/scala/scala_pattern_matching.htm
object Demo {
  def main(): Unit = {
    println(matchTest(3))
    println(matchTest("two"))
    println(matchTest("test"))
    println(matchTest(1))
  }

  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case 2 => "two"
    case "one" => 1
    case "two" => 2
    case y: Int => "scala.Int"
    case _ => "many"
  }
}

Demo.main()

object DemoTwo {
  //matching using case class
  case class Person(name: String, age: Int)

  def main() {
    val alice = Person("Alice", 25)
    val bob = Person("Bob", 32)
    val charlie = Person("Charlie", 32)

    for (person <- List(alice, bob, charlie)) {
      person match {
        case Person("Alice", 25) => println("Hi Alice!")
        case Person("Bob", 32) => println("Hi Bob!")
        case Person(name, age) => println(
          "Age: " + age + " year, name: " + name + "?")
      }
    }
  }
}

DemoTwo.main()
