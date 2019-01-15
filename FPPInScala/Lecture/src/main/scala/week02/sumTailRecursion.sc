object sumTailRecursion {

  def sum(f: Int => Int)(a: Int, b: Int): Int = {

    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a+1, acc + f(a))
    }

    loop(a, 0)
  }

  def sumInts(a: Int, b: Int) = sum(x => x)(a, b)
  def sumCubes(a: Int, b: Int) = sum(x => x * x * x)(a, b)
  def sumFactorials(a: Int, b:Int) = sum(factorial)(a,b)
  def factorial(x: Int): Int = if (x == 0) 1 else factorial(x - 1)
}

sumTailRecursion.sumInts(1, 3)
sumTailRecursion.sumCubes(1, 3)
sumTailRecursion.sumFactorials(1,3) //1! + 2!