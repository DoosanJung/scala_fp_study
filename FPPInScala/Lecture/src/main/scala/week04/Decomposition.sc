trait Expr {
  //classification method
  def isNumber: Boolean
  def isSum: Boolean
  //access method
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  //classification method
  def isNumber: Boolean = true
  def isSum: Boolean = false
  //access method
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  //new Sum(e1, e2) corresponds to e1 + e2
  //classification method
  def isNumber: Boolean = false
  def isSum: Boolean = true
  //access method
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

//in order to test above..
def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Exception("Unknown expression" + e)
}
//testing..
eval(new Sum(new Number(1), new Number(2)))

//Writing all these classification and accessor functions
//quickly becomes tedious!
//==> quadratic increase of methods

//type test and type cast are not recommended in Scala.
//because it is low-level and potentially unsafe.
//eval with type test and type cast
def evalTypeTestCast(e: Expr): Int =
  if (e.isInstanceOf[Number])
    e.asInstanceOf[Number].numValue
  else if (e.isInstanceOf[Sum])
    eval(e.asInstanceOf[Sum].leftOp) +
      eval(e.asInstanceOf[Sum].rightOp)
  else throw new Error("Unknown expression" + e)

//testing..
evalTypeTestCast(new Sum(new Number(1), new Number(2)))