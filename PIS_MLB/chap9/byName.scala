#!/usr/bin/env scala
//without using by-name parameters
var assertionsEnabled = true
def myAssert(predicate: () => Boolean) = 
  if (assertionsEnabled && !predicate())
    throw new AssertionError

myAssert(() => 5 > 3) //using it is a bit awkward...
//myAssert(() => 3 > 5) //throws AssertionError
//I'd like to use something like 'myAssert(5>3)' without ()

def byNameAssert(predicate: => Boolean) = 
  if (assertionsEnabled && !predicate)
    throw new AssertionError

//the result looks exactly like using a built-in control structure:
byNameAssert(5 > 3)

//byNameAssert is different from boolAssert
def boolAssert(predicate: Boolean) = 
  if (assertionsEnabled && !predicate)
    throw new AssertionError

/*
the expression inside the parentheses in boolAssert(5 > 3) is evaluated be- fore the call to 
boolAssert. The expression 5 > 3 yields true, which is passed to boolAssert.

By contrast, because the type of byNameAssert’s predicate parameter is => Boolean, the expression 
inside the parentheses in byNameAssert(5 > 3) is not evaluated before the call to byNameAssert.

e.g.
scala> boolAssert(x / 0 == 0)
java.lang.ArithmeticException: / by zero

scala> byNameAssert(x / 0 == 0)
no exception!!
*/
