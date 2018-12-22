#!/usr/bin/env scala
/*conditional expressions*/
def abs(x: Double) = if (x>=0) x else -x
println(abs(2))
println(abs(-2))

/**Boolean expressions**/
println("ab"=="ab")
println("ab"!="ab")

/* Calculates the square root of parameter x */
/* Newton's method:
Start with an initial estimate y (let's pick y = 1).
Repeatedly improve the estimate by taking the mean of y and x/y.
*/

def sqrtIter(guess:Double, x:Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)
    //sqrtIter is recursive
    //Recursive methods need an explicit return type in Scala.

def improve(guess:Double, x:Double): Double =
    (guess + x/guess) /2
    // improve the estimate by taking the mean of y and x/y

def isGoodEnough(guess:Double, x:Double) =
    abs(guess * guess - x) <0.001

def sqrt(x:Double) = sqrtIter(1.0, x)
    //randome initial estimate = 1.0

println(sqrt(2))

/** Calculates the factorial of parameter x */
def factorial(n: Int): Int =
  if (n == 1) n
  else factorial(n - 1) * n

println(factorial(3))
println(factorial(4))

