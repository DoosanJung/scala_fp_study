/*
* Use pattern matching!
* UnOp("-", UnOp("-", e )) => e // Double negation
BinOp("+", e , Number(0)) => e // Adding zero
BinOp("*", e , Number(1)) => e // Multiplying by one
*/
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