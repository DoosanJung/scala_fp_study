#!/usr/bin/env scala
/*LEXICAL SCOPING
Definitions of outer blocks are visible inside a block unless they are shadowed.

Therefore, we can simplify sqrt by eliminating redundant occurrences of the x parameter, 
which means everywhere the same thing
*/
def abs(x:Double) = if(x>=0) x else -x
def square(x:Double) = x*x

def sqrt(x: Double) = {
  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  def improve(guess: Double) =
    (guess + x / guess) / 2

  def isGoodEnough(guess: Double) =
    abs(square(guess) - x) < 0.001

  sqrtIter(1.0)
}

println(sqrt(2))

//Top level definitions
/*In real Scala programs, def and val definitions must be written 
within a top-level object definition, in .scala file: */

object MyExecutableProgram {
  val myVal = 2
  def myMethodSquare(x:Double) = x*x 
}

println(MyExecutableProgram.myMethodSquare(MyExecutableProgram.myVal))

object Foo {
  val x = 1
}

object Bar {
  val x = 2
}

object Baz {
  import Bar.x
  val y = x + Foo.x
}

println(Baz.y)

//Packages and imports
/*Definitions located in a package are visible 
from other definitions located in the same package.
On the other hand, definitions located in other packages are not directly visible: 
you must use fully qualified names to refer to them */

