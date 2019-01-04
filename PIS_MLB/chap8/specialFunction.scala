#!/usr/bin/env scala
//repeated parameters
def echo(args: String*) = //String* is actually Array[String]
  for (arg <- args) println(arg)

echo()
echo("a")
echo("a","b")

val arr = Array("what's","up","doc?")
//echo(arr) will throw an error: type mismatch
// found    :Array[java.lang.String]
// required :String

echo(arr: _*)

//named arguments
//normal function call
def speed(distance:Float, time:Float):Float =
  distance / time

println(speed(100,10))
println(speed(distance=100, time=10))
println(speed(time=10, distance=100))

//default parameter values
def printTime(out: java.io.PrintStream = Console.out) =
  out.println("time: " + System.currentTimeMillis())

printTime()

def printTime2(out: java.io.PrintStream = Console.out, divisor:Int = 1) =
  out.println("time: " + System.currentTimeMillis()/divisor)

printTime2()

def defaultSum(x:Int = 1 , y:Int = 2):Int =
  x + y

println(defaultSum(3, 5))
println(defaultSum())
