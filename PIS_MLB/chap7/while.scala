#!/usr/bin/env scala
//while (imperative style)
def gcdLoop(x:Long, y:Long):Long = {
  var a = x
  var b = y
  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }
  b
}

println(gcdLoop(14,21))

//do-while
var line = ""
do {
  line = readLine()
  println("Read: "+line)
} while (line != "")

//an alternative way (FP style)
//no vars
def gcd(x:Long, y:Long):Long = {
  if (y == 0) x else gcd(y, x % y)
}
