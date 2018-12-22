#!/usr/bin/env scala
//
println("Hello, Scala!".size)
println(1.to(10))
println(3.+(2))
println(1 to 10)
println(16.toHexString)
println((0 to 10).contains(10))
println((0 until 10).contains(10))
println("foo".drop(1))
println("bar".take(2))

//method
def square(x: Double) = x * x
println(square(3.0))

def area(radius: Double): Double = 3.14159 * square(radius)
println(area(10))

//Multiple Parameters
def sumOfSquares(x: Double, y: Double) = square(x) + square(y)
println(sumOfSquares(3,4))

//Parameters and return types
import scala.math.pow
def power(x: Double, y: Int): Double = pow(x,y)
println(power(2,3))

//Call-by-value vs. Call-by-name
//Exercise
def triangleArea(base:Double, height:Double): Double = 
base * height/2

println(triangleArea(3, 4))
println(triangleArea(5, 6))


