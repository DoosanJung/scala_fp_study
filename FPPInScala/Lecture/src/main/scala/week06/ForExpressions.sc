object Pairs {

  def isPrime(n: Int) =
    (2 until n) forall (n % _ != 0)

  // given a positive integer n, find all pairs of positive integers i and j,
  // with 1 <= j < i < n s.t. i + j is prime.
  // e.g. n = 7,
  //   i |  2   3   4   4   5   6   6
  //   j |  1   2   1   3   2   1   5
  // ---------------------------------
  // i+j |  3   5   5   7   7   7  11

  def solution(n: Int) =
    (1 until n) flatMap (
      i => (1 until i) map (
        j => (i, j)
        )
    ) filter (pair => isPrime(pair._1 + pair._2))

  def solutionUsingFor(n: Int) =
    for {
      i <- 1 until n
      j <- 1 until i
      if isPrime(i +j)
    } yield (i, j)

  //direct translation of For expression
  def solutionTranslationFor(n: Int) =
    (1 until n) flatMap (i =>
      (1 until i).withFilter(j => isPrime(i + j))
      .map (j => (i, j))
    )
}

Pairs.solution(7)
Pairs.solutionUsingFor(7)
Pairs.solutionTranslationFor(7)

object Exercise {

  def scalarProductUsingFor(xs: List[Double], ys: List[Double]): Double =
    //if one line, use () instead of {}
    (for ((x, y) <- xs zip ys) yield x * y).sum
}

Exercise.scalarProductUsingFor(List(1.0, 2.0, 3.0), List(3.0, 2.0, 1.0))