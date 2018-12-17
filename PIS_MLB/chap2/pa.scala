#!/usr/bin/env scala
//call the foreach method on args, and pass in a function
args.foreach(arg => println(arg))
println(" ")

//wrap the arg in parentheses
//to be more explicit
args.foreach((arg:String) => println(arg))
println(" ")

//more conciseness instead of more explicitness
args.foreach(println)

//functional literal
//(x:Int, y:Int) => x + y
