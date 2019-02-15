object LazyEvaluation {

  def expr = {
    val x = { print("x"); 1}
    lazy val y = { print("y"); 2}
    def z = { print("z"); 3}
    z + y + x + z + y + x
  }

}
LazyEvaluation.expr //prints "xzyz"


object InfiniteStreams {

  //stream of all integers starting from a given number
  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  //streams of all natural numbers
  val nats = from(0)

  //stream of all multiples of 4
  val m4s = nats map (_ * 4)
  val m4slst =  (m4s take 100).toList

  //The Sieve of Eratosthenes is an ancient technique to calculate prime numbers.
  /*
  => Start with all integers from 2, the first prime number.
  => Eliminate all multiples of 2.
  => The first element of the resulting list is 3, a prime number.
  => Eliminate all multiples of 3.
  => Iterate forever.

  At each step, the first number in the list is a prime number and we eliminate all its multiples.
  */
  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))
  val primes = sieve(from(2))

  //back to squaure roots
  //Our previous algorithm for square roots always used a isGoodEnough
  //test to tell when to terminate the iteration.
  //With streams we can now express the concept of a converging
  //sequence without having to worry about when to terminate it:
  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2

    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses

  }
  //evaluation criteria
  def isGoodEnough(guess: Double, x: Double) =
    math.abs((guess * guess - x) / x) < 0.0001

  val sqrt4 = sqrtStream(4).take(10).toList
  val sqrt4GoodEnough = sqrtStream(4).filter(isGoodEnough(_, 4)).take(10).toList

}

InfiniteStreams.m4slst
InfiniteStreams.primes.take(100).toList
InfiniteStreams.sqrt4
InfiniteStreams.sqrt4GoodEnough