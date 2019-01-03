#!/usr/bin/env scala

//looping without break or continue
/*To get rid of the break , you would normally add a boolean variable 
indicating whether to keep going, but in this case you can reuse foundIt.
*/
var iLoop = 0
var foundIt = false

while (iLoop < args.length && !foundIt){
  if (!args(iLoop).startsWith("-")) {
    if (args(iLoop).endsWith(".scala"))
      foundIt = true
  }
  iLoop = iLoop + 1
}

println(foundIt)

//a recursive alternative to looping with vars
def searchFrom(i:Int): Int = 
  if (i >= args.length) -1
  else if (args(i).startsWith("-")) searchFrom(i+1)
  else if (args(i).endsWith(".scala")) i
  else searchFrom(i+1)

val i = searchFrom(0)
println(i)

//$scala NoBreakContinue.scala "-test.scala" "test2.scala"

//if you still feel the need to use break..
/*
This will repeatedly read non-empty lines from the standard input. 
Once the user enters an empty line, control flow exits from the enclosing breakable, 
and with it the while loop
*/
import scala.util.control.Breaks._
import java.io._

val in = new BufferedReader(new InputStreamReader(System.in))

breakable {
  while (true) {
    println("next line? ")
    if (in.readLine() == "") break
  }
}
