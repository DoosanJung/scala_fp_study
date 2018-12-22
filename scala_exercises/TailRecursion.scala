#!/usr/bin/env scala
// Tail recursion
def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)

println(gcd(14,21))

// Not tail recursion
def factorial(n: Int): Int =
  if (n == 0) 1 else n * factorial(n - 1)

println(factorial(4))

// tail-recursion version of factorial
import scala.annotation.tailrec
def factorialTailRec(n: Int): Int = {

  @tailrec
  def iter(x: Int, result: Int): Int =
    if (x == 1) result
    else iter(x - 1, result * x)

iter(n, 1)

}

println(factorialTailRec(4))

//fibonacciNaive - not tail recursive
//last operation is always an 'add'
//https://medium.com/@frank.tan/fibonacci-tail-recursive-explained-876edf5e86fc
def fibonacciNaive(n: Int): Int = {
  if (n <= 0) 0
  else if (n == 1) 1
  else fibonacciNaive(n-1) + fibonacciNaive(n-2)
}

println(fibonacciNaive(10))

//fibonacci - tail recursive
def fibonacciReverse(n:Int):Int={
  
  @tailrec
  def iter(n:Int, prev:Int, current:Int) : Int={
    //print to see what's happening
    println("current n: %d".format(n))
    println("current value: %d".format(current))
    println("previous value: %d".format(prev))
    if (n<=0) current
    else iter(n-1, prev=prev+current, current=prev)
  }

  iter(n, prev=1, current=0)
}

println(fibonacciReverse(10))

