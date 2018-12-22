#!/usr/bin/env scala

def abs(x: Double) = if (x>=0) x else -x
def square(x: Double) = x * x

/* Nested functions */
def sqrt(x: Double) = {
    def sqrtIter(guess: Double, x:Double): Double =
        if (isGoodEnough(guess, x)) guess
        else sqrtIter(improve(guess, x), x)
    
    def improve(guess:Double, x:Double): Double =
        (guess + x/guess)/2
        
    def isGoodEnough(guess:Double, x:Double) =
        abs(square(guess) -x) < 0.001

    sqrtIter(1.0, x)
}

/* The definitions inside a block are only visible from within the block.
The definitions inside a block shadow definitions of the same names outside the block.
*/
def sqrtIter(z: Double) = z

println(sqrt(2.0))
println(sqrtIter(2.0))

/*Blocks in Scala*/
val x = 0
def f(y: Int) = y + 1
val result = {
  val x = f(3)
  x * x
} + x
println(x)
println(result)

