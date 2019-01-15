/*
* Still, there are redundancies...
* def sumInts(a: Int, b: Int) = sum(x => x, a, b)
  def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)
* a and b get passed unchaged from sumInts and sumCubes into sum
* => get rid of a and b
* */
object sumCurrying {

  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a+1, b)
    sumF //sum is now a function that returns another function, sumF
  }

  def sumInts = sum(x => x)
  def sumCubes = sum(x => x*x*x)
  def sumFactorials = sum(factorial)
  def factorial(x: Int): Int = if (x == 0) 1 else factorial(x - 1)
}

sumCurrying.sumInts(1,3)
sumCurrying.sumCubes(1,3)
sumCurrying.sumFactorials(1,3) //1! + 2!

