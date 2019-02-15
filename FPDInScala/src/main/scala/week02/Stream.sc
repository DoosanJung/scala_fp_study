object test {
  def isPrime(n: Int) =
    (2 until n) forall (n % _ != 0)

  //finding the second prime between 1000 and 10000
  //performance problem: constructs all prime numbers and filter it
  //=> delayed evaluation!
  //=> Stream
  val prime = ((1000 to 10000) filter isPrime)(1)

  //is much shorter than the recursive alternative.
  def secondPrime(from: Int, to: Int) = nthPrime(from, to, 2)
  def nthPrime(from: Int, to: Int, n: Int): Int =
    if (from >= to) throw new Exception("no prime")
    else if (isPrime(from))
      if (n == 1) from else nthPrime(from + 1, to, n-1)
    else nthPrime(from + 1, to, n)

  //stream version
  val primeStream = ((1000 to 10000).toStream filter isPrime)(1)

}
test.prime
test.secondPrime(1000, 10000)
test.primeStream

object test2 {
  val xs = Stream.cons(1, Stream.cons(2, Stream.empty))
  val xs2 = Stream(1, 2, 3)
  val xs3 = (1 to 1000).toStream //Stream[Int] = Stream(1, ?)

  //func that returns (low until high).toStream
  def streamRange(low: Int, high: Int): Stream[Int] = {
    print(low + " ")
    if (low >= high) Stream.empty
    else Stream.cons(low, streamRange(low+1, high))
  }

  def listRange(low: Int, high: Int): List[Int] =
    if (low >= high) Nil
    else low :: listRange(low+1, high)

}

// #:: operator => produces a Stream
test2.xs #:: test2.xs2 == Stream.cons(test2.xs, test2.xs2)

test2.streamRange(1, 10).take(3).toList