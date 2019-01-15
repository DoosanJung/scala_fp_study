object sumHOFAnonymous {
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a+1, b)

  def sumInts(a: Int, b: Int) = sum(x => x, a, b)
  def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)
  def sumFactorials(a: Int, b:Int) = sum(factorial, a,b )
  def factorial(x: Int): Int = if (x == 0) 1 else factorial(x - 1)
}

sumHOFAnonymous.sumInts(1,3)
sumHOFAnonymous.sumCubes(1,3)
sumHOFAnonymous.sumFactorials(1,3) //1! + 2!