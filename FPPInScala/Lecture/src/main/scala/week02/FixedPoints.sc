import math.abs

object FixedPoints {

  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {

    def iterate(guess: Double): Double = {
      //println("guess = " + guess)
      val next = f(guess)

      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  //sqrt(x) is a fixed point of the function (y => x / y) since
  //sqrt(x) == the number y s.t. y * y = x
  //<=> sqrt(x) == the number y s.t. y = x / y

  //However, this does not converge
  //def sqrt(x: Double) = fixedPoint(y => x/y)(1.0)

  //One way to control such oscillations is to prevent the estimation from varying too much.
  //This is done by averaging successive values of the original sequence.
  //def sqrt(x: Double) = fixedPoint(y => (y + x/y)/2)(1.0)
  //make averaging a function!
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2

  def sqrt(x: Double) =
    fixedPoint(averageDamp(y => x/y))(1.0)
}

FixedPoints.fixedPoint(x => 1 + x/2)(1)
FixedPoints.sqrt(2)