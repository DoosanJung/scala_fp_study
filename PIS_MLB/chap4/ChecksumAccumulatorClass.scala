#!/usr/bin/env scala
class ChecksumAccumulatorClass {
  var sum = 0
}

val acc = new ChecksumAccumulatorClass
val csa = new ChecksumAccumulatorClass

//Since sum, a field declared inside class ChecksumAccumulator, is a var, not a val, 
//you can later reassign to sum a different Int value, like this:
acc.sum = 3

/*
What you can’t do with acc (or csa), given that they are vals, not vars, is reassign a different object to them. 
For example, the following attempt would fail:

Won’t compile, because acc is a val
acc = new ChecksumAccumulatorClass

acc will always refer to the same ChecksumAccumulator object with which you initialize it, 
but the fields contained inside that object might change over time
*/

/* NOTE: Public is Scala’s default access level */
//prevent outsiders from accessing the fields directly by making the fields private
class ChecksumAccumulatorPrivate {
  private var sum = 0
  
  def add(b: Byte): Unit = {
    //One important characteristic of method parameters in Scala is that they are vals, not vars
    //b = 1 <- this won’t compile, because b is a val
    sum += b
  }

  def checksum(): Int = { 
    return ~(sum & 0xFF) + 1
  }  
}

//concise way
//In the absence of any explicit return statement, a Scala method returns the last value computed by the method.
//Another shorthand for methods is that you can leave off the curly braces if a method computes only a single result expression
class ChecksumAccumulatorConcise {
  private var sum = 0
  
  def add(b: Byte): Unit = sum += b
  
  def checksum(): Int = ~(sum & 0xFF) + 1
}

//final version
//Methods with a result type of Unit, such as ChecksumAccumulator’s add method, are executed for their side effects
//A side effect is generally defined as mutating state somewhere external to the method
//In add’s case, for example, the side effect is that sum is reas- signed
class ChecksumAccumulator {
  private var sum = 0
  
  def add(b: Byte) { sum += b } //the method looks like a procedure, a method that is executed only for its side effects
  
  def checksum(): Int = ~(sum & 0xFF) + 1
}

/* NOTE:
whenever you leave off the equals sign before the body of a function, its result type will definitely be Unit.
If the last result of a method is a String, but the method’s result type is declared to be Unit, 
the String will be converted to Unit and its value lost

scala> def f() {"string"}
<console>:11: warning: a pure expression does nothing in statement position
       def f() {"string"}
                ^
f: ()Unit

scala> def f():Unit = "String"
<console>:11: warning: a pure expression does nothing in statement position
       def f():Unit = "String"
                      ^
f: ()Unit

==> so DON'T FORGET THE EQUAL SIGN!

scala> def h() = { "this String gets returned!" }
        h: ()java.lang.String
scala> h
        res0: java.lang.String = this String gets returned!
*/
