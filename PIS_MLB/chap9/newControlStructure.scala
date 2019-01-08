#!/usr/bin/env scala
/*
In languages with first-class functions, you can effectively make new control structures even 
though the syntax of the language is fixed. All you need to do is create methods that take 
functions as arguments.
*/
def twice(op:Double => Double, x:Double) = op(op(x))

println(twice(_ + 1, 5)) //Double = 7.0

//the type of op is Double => Double, which means it's a function 
//(takes Dobule as an arg and returns another Double)

//open a resource, operate on it, and then close the resource
/*
loan pattern.
*/
import java.io.{File, PrintWriter}

def withPrintWriter(file: File, op:PrintWriter => Unit) { //one parameter list with two parameters
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally { 
    /*
    The resource is then closed in a finally block, to ensure it is indeed closed, re- gardless of 
    whether the function completes by returning normally or throw- ing an exception
    */
    writer.close()
  }
}

withPrintWriter(
  new File("date.txt"),
  writer => writer.println(new java.util.Date)
)

//In any method invocation in Scala in which you’re passing in exactly one argument, you can opt 
//to use curly braces to surround the argument instead of parentheses
println("Use parentheses!")
println{"Use curly braces!!"}

//with currying..
//two parameters with one parameter each
def withPrintWriterCurried(file: File)(op: PrintWriter => Unit) { 
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

//more natural syntax
val file = new File("dateCurried.txt")
//the first argument list, which contains one File argument, is written surrounded by parentheses
//The second argument list, which contains one function argument, is surrounded by curly braces

withPrintWriterCurried(file) { //the first arg list
  writer => writer.println(new java.util.Date) //the second arg list
}
