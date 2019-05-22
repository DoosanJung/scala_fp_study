/*
*write an expression formatter class that displays an arithmetic expression in a two-dimensional layout.
*Divisions such as “ x / (x + 1) ” should be printed vertically, by placing the numerator on top of the denominator,
*like this:
    x
  -----
  x + 1


  Result
  ================
   ~/DSJ/Projects/scala_fp_study/PIS_MLB/chap15   master ● ?  scalac Element.scala
   ~/DSJ/Projects/scala_fp_study/PIS_MLB/chap15   master ● ?  scalac Expr.scala
   ~/DSJ/Projects/scala_fp_study/PIS_MLB/chap15   master ● ?  scalac Express.scala
   ~/DSJ/Projects/scala_fp_study/PIS_MLB/chap15   master ● ?  scala Express

    1
    - * (x + 1)
    2


    x   1.5
    - + ---
    2    x


    1
    - * (x + 1)
    2
    -----------
      x   1.5
      - + ---
      2    x
*/

//epm: example pattern matching
import epm.expr._

object Express extends App {

  val f = new ExprFormatter

  val e1 = BinOp("*",
      BinOp("/", Number(1), Number(2)),
      BinOp("+", Var("x"),  Number(1))
  )

  val e2 = BinOp("+",
    BinOp("/", Var("x"),    Number(2)),
    BinOp("/", Number(1.5), Var("x"))
  )

  val e3 = BinOp("/", e1, e2)

  def show(e: Expr) = println(f.format(e)+ "\n\n")

  for (e <- Array(e1, e2, e3)) show(e)
}