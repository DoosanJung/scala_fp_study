#!/usr/bin/env scala
//you can refer to variables defined elsewhere:
// (x:Int) => x + more <== how much more??
// x: bound variable and more: free variable

var more = 1
val addMore = (x:Int) => x + more
//The function value that’s created at runtime from this function literal is called a closure

println(addMore(10)) //11

/*
Any function value created at runtime from (x: Int) => x + more will (by definition) 
require that a binding for its free variable, more, be captured.
The resulting function value, which will contain a reference to the captured more
variable, is called a closure
*/

//what happens if more changes after the clousure is created?
more = 9999 //changes outside the closure
println(addMore(10)) //10009
//Scala’s closures capture variables themselves, not the value to which variables refer.

//vice versa
val someNumbers = List(-11, -10, -5, 0, 5, 10)
var sum = 0
someNumbers.foreach(sum += _) //modify sum at runtime
println(sum) //the result, -11, is still visible outside the closure

def makeIncreaser(more:Int) = (x:Int) => x + more
//each time this function is called it will create a new closure
//each closure will access the more variable that was active when the closure was created.
val inc1 = makeIncreaser(1) //more==1
val inc9999 = makeIncreaser(9999) //more==9999

println(inc1(10)) //11
println(inc9999(10)) //10009
