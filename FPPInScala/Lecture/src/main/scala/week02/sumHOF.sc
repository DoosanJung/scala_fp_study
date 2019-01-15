object sumHOF {
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a+1, b)

  def sumInts(a: Int, b: Int) = sum(id, a, b)
  def sumCubes(a: Int, b: Int) = sum(cube, a, b)
  def sumFactorials(a: Int, b:Int) = sum(factorial, a,b )

  // name all the auxiliary functions
  def id(x: Int): Int = x
  def cube(x: Int): Int =  x * x * x
  def factorial(x: Int): Int = if (x == 0) 1 else factorial(x - 1)
}

sumHOF.sumInts(1,3)
sumHOF.sumCubes(1,3)
sumHOF.sumFactorials(1,3) //1! + 2!