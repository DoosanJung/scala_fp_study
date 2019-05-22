/*
*write an expression formatter class that displays an arithmetic expression in a two-dimensional layout.
*Divisions such as “ x / (x + 1) ” should be printed vertically, by placing the numerator on top of the denominator,
*like this:
    x
  -----
  x + 1
*/

//epm: example pattern matching
package epm.expr
import epm.layout.Element
import epm.layout.Element.elem

sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr


class ExprFormatter {
  //To know where to put parentheses, the code needs to know about the relative precedence of each operator,
  //Define groups of operators of increasing precedence and then calculate the precedence of each operator from that.
  private val opGroups =
  Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("ˆ"),
    Set("==", "!="),
    Set("<", "<=", ">", ">="),
    Set("+", "-"),
    Set("*", " % ")
  )

  //A map from operators to their precedences, which are integers starting with 0
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length //every index i of the opGroups array
        op <- opGroups(i) //every operator op in opGroups(i)
      } yield op -> i //op -> i is same as the pair (op, i)
    assocs.toMap
  }

  //The precedence of a unary operator is higher than the precedence of every binary operator.
  private val unaryPrecedence = opGroups.length //just one larger integer than i for op: Set("*", " % ")
  private val fractionPrecedence = -1 //division got its own special value

  //enclPrec = precedence of the operator
  private def format(e: Expr, enclPrec: Int): Element =
    e match {
      //1st case
      case Var(name) => elem(name)

      //2nd case
      case Number(num) =>
        def stripDot(s: String) =
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))

      //3rd case
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)

      //4th case
      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence) frac
        else elem(" ") beside frac beside elem(" ")

      //5th case
      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" "+ op +" ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")

    }

  def format(e: Expr): Element = format(e, 0)
}
