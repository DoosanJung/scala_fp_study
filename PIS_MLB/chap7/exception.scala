#!/usr/bin/env scala
//Throwing exceptions
//One branch of an if computes a value, while the other throws an exception and computes Nothing.
val n = 2
println("the original value: "+n)

val half =
  if (n % 2 == 0)
    n /2
  else
    throw new RuntimeException("n must be even")

println("half: " + half)

//catching exceptions
import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException
try {
  val f = new FileReader("input.txt")
  //use and close file
} catch {
  case ex: FileNotFoundException => println("Exception!! input.txt not found !!")//handle missing file
  case ex: IOException => println("Exception!! handle other IO error !!") //handle other IO error
}

//the finally clause
/*
the fianlly clause should not normally change the value computed in the main body or a catch clause of the try.
*/
val file = new FileReader("input.txt")
try {
  //use this file
} finally {
  file.close() //be sure to close the file
}
/*
NOTE: In Scala you can alternatively employ a technique called the loan pattern to achieve the same goal more concisely.
*/

//yielding a value
//try - catch - finally results in a value
//this example shows how you can try to parse a URL but use a default value if the URL is badly formed.
import java.net.URL
import java.net.MalformedURLException

def urlFor(path:String) =
  try {
    new URL(path)
  } catch {
    case e: MalformedURLException => new URL("http://www.scala-lang.org")
  }

println(urlFor("not-a-valid-url"))
//please avoid returning value from finally clauses.
//The best way to think of finally clauses is as a way to ensure some side effect happens, such as closing an open file.
//calling f() results in 2
//calling g() results in 1
def f():Int = try {return 1} finally {return 2}
def g():Int = try {1} finally {2}

println(f())
println(g())

