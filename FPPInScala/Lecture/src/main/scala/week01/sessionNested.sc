object sessionNested {
  def abs(x: Double) = if (x > 0) x else -x

  def sqrt(x: Double) = {
    /*
    * A block is delimited by braces.
    * We can eliminate redundant parameter x: lexical scoping
    * x is visible inside the blocks.
    * */
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) =
    /*
    * abs(guess * guess - x) < 0.001 does not work for very small and large numbers.
    * using absolute threshold 0.001 caused the issue.
    */
      abs(guess * guess -x) /x < 0.001

    def improve(guess: Double) =
      (guess + x/guess)/2

    sqrtIter(1.0)
  }
}

sessionNested.sqrt(2)
sessionNested.sqrt(4)
sessionNested.sqrt(1e-6)
sessionNested.sqrt(1e60)
