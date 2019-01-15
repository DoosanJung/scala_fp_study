object session {
  def abs(x: Double) = if (x > 0) x else -x

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double) =
  /*
  * abs(guess * guess - x) < 0.001 does not work for very small and large numbers.
  * using absolute threshold 0.001 caused the issue.
  */
    abs(guess * guess -x) /x < 0.001

  def improve(guess: Double, x: Double) =
    (guess + x/guess)/2

  def sqrt(x: Double) = sqrtIter(1.0, x)
}

session.sqrt(2)
session.sqrt(4)
session.sqrt(1e-6) //expected value Double = 0.00100
session.sqrt(1e60) //expected value Double = 1.0000