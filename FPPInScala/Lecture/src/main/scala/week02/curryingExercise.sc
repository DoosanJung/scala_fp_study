object curryingExercise {

  //exercise 1
  def product(f: Int => Int)(a: Int, b:Int): Int =
    if (a > b) 1 //unit value for multiplication: the value to return if the interval is empty
    else f(a) * product(f)(a+1, b)

  //exercise 2
  def factorial(n: Int) = product(x => x)(1,n)

  //exercise 3
  def mapReduce(f :Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:Int): Int =
    // unit value zero: the value to return if the interval is empty
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a+1, b))

  def productMR(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x,y) => x * y, 1)(a, b)

  def factorialMR(n : Int) = productMR(x => x)(1,n)
}

curryingExercise.product(x => x*x)(3,4)// (3*3)*(4*4) == 144
curryingExercise.factorial(5) //120
curryingExercise.productMR(x => x*x)(3,4) //same value 144
curryingExercise.factorialMR(5) //same value 120