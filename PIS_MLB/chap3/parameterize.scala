#!/usr/bin/env scala
val big = new java.math.BigInteger("12345")
println(big)
println(" ")

//parameterize an instance with types
val greetStrings = new Array[String](3)

greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"
//greetStrings(i) = "Something" gets transformed into greetStrings.update(i) = "Something"

for (i <- 0 to 2)
  print(greetStrings(i))
  //greetStrings(i) gets transformed into greetStrings.apply(i). 
  //Thus accessing an element of an array in Scala is simply a method call like any other
println(" ")

// Or more explicitly..
// The type of greetStringsExplicit is Array[String], not Array[String](3)
val greetStringsExplicit : Array[String] = new Array[String] (3)

greetStringsExplicit(0) = "Hello"
greetStringsExplicit(1) = ", "
greetStringsExplicit(2) = "world!\n"
greetStringsExplicit.foreach(elem => print(elem))
println(" ")

/* NOTE: it does not show the recommended way to create and initialize an array in Scala */

//When you define a variable with val, the variable can’t be reassigned, but the object to which it refers could potentially still be changed.
//you couldn’t reassign greetStrings to a different array; greetStrings will always point to the same Array[String] instance with which it was initialized
//you can change the elements of that Array[String] over time, so the array itself is mutable
greetStrings(0) = "Hi"
greetStrings(1) = ", "
greetStrings(2) = "Scala!\n"

greetStrings.foreach(elem => print(elem))

//no operator overloading
println(1 + 2)
println((1) .+ (2))

//Scala provides a more concise way to create and initialize ar- rays that you would normally use
val numNames = Array("zero","one","two")
//same as ..
val numNames2 = Array.apply("zero","one","two")
