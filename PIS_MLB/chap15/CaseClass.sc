/*
* one abstract base class and four subclasses with case modifier
1. add a factory method: val v = Var("x") //no need for new keyword
2. all aurgs in param get a val prefix
3. compiler adds 'natural' implementaion of methods toString, hashCode, and equals
4. compiler adds 'copy' method: useful for making a new instance of the class that is
the same as another one except that one or two attributes are different
*/

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr


object CaseClass {
  val v = Var("x")
  val op = BinOp("+", Number(1), v)
}

CaseClass.v
CaseClass.op

CaseClass.v.name
CaseClass.op.left

println(CaseClass.op)
CaseClass.op.right == Var("x")

CaseClass.op.copy(operator = "-")