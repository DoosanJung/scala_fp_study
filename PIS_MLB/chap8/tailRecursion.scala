#!/usr/bin/env scala

/*
Functions like approximate, which call themselves as their last action, 
are called tail recursive.

def approximate(guess: Double): Double =
  if (isGoodEnough(guess)) guess
  else approximate(improve(guess))

//more efficient imperative style? => almost the same
def approximateLoop(initialGuess: Double): Double = {
  var guess = initialGuess
  while (!isGoodEnough(guess))
    guess = improve(guess)
  guess
}

*/

//tracing tail-recursive functions
//not tail-recursive because because it performs an increment operation 
//after the recursive call.
def boom(x:Int): Int =
  if (x == 0) throw new Exception("boom!")
  else boom(x - 1) + 1

//tail-recursive
def bang(x: Int): Int =
  if (x == 0) throw new Exception("bang!")
  else bang(x - 1)

//limits of tail recursion
/*
If the recursion is indirect, as in the following example 
of two mutually recursive functions, no optimization is possible.
*/
def isEven(x: Int): Boolean =
  if (x == 0) true else isOdd(x - 1)

def isOdd(x: Int): Boolean =
  if (x == 0) false else isEven(x - 1)

println(isEven(4))
println(isOdd(4))

//You also won’t get a tail-call optimization if the final call goes to a function value
val funValue = nestedFun _
def nestedFun(x: Int) {
  if (x != 0) { println(x); funValue(x - 1) }
}

nestedFun(3)
