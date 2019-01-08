#!/usr/bin/env scala
/*
To understand how to make control abstractions that feel more like language extensions, you first 
need to under- stand the functional programming technique called currying.
*/
//A curried function is applied to multiple argument lists, instead of just one

//a regular, non-curried function, which adds two Int x and y
def plainOldSum(x:Int, y:Int) = x + y

println(plainOldSum(1,2))

//curried!
def curriedSum(x:Int)(y:Int) = x + y

println(curriedSum(1)(2))

//explained - just an illustration
//invoke curriedSum == get two traditional function invocations back to back
/*
The first function invocation takes a single Int parameter named x, and returns a function value 
for the second function. This second function takes the Int parameter y
*/
//Applying 1 to the first function—in other words, invoking the first function and passing in 1 
//yields the second function
def first(x:Int) = (y:Int) => x + y 
val second = first(1) //Applying 2 to the second function yields the result
println(second(2)) //3

//actual curriedSum .. using partial applied function expression
val onePlus = curriedSum(1)_ //the underscore is a placeholder for second parameter list
println(onePlus(2))

val twoPlus = curriedSum(2)_
println(twoPlus(2))
