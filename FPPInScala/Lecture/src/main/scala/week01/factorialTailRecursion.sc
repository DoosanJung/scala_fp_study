object factorialTailRecursion {
  def factorial(n: Int): Int = {

    def loop(acc: Int, n: Int): Int = //acc is an accumulator

      if (n == 0) acc
      else loop(acc * n, n-1)

    loop(1, n) //initial value is 1
  }
}

factorialTailRecursion.factorial(3)

