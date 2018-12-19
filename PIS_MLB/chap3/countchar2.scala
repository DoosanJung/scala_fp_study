#!/usr/bin/env scala

//reads lines from a file and prints them out prepended with the number of characters in each line
/*
Updated to have line up the numbers, right adjusted, and add a pipe character so that the output looks instead like:
22 | import scala.io.Source 
0  |
22 | if (args.length > 0) { 
0  |
51 |   for (line <- Source.fromFile(args(0)).getLines())
35 |     println(line.length +" "+ line) 
1  | }
4  | else
46 |   Console.err.println("Please enter filename")

To accomplish this, you can iterate through the lines twice. 
The first time through you’ll determine the maximum width required by any line’s character count. 
The second time through you’ll print the output, using the maximum width calculated previously
*/

import scala.io.Source
  
def widthOfLength(s: String) = s.length.toString.length
//because you’ll be calculating the width of each line’s character count twice, once per iteration, you might factor that expression out into a small function
//which calculates the character width of the passed string’s length

if (args.length > 0) {
  //Because you’ll be iterating through the lines twice, you may as well assign them to a variable  
  val lines = Source.fromFile(args(0)).getLines().toList
  
  /*
  The reduceLeft method applies the passed function to the first two elements in lines, 
  then applies it to the result of the first application and the next element in lines, and so on, 
  all the way through the list.
  */
  val longestLine = lines.reduceLeft(

      (a, b) => if (a.length > b.length) a else b
    )
    
  val maxWidth = widthOfLength(longestLine) //calculates the character width of the passed string’s length
  
  for (line <- lines) {

    val numSpaces = maxWidth - widthOfLength(line)
    
    val padding = " " * numSpaces
    
    println(padding + line.length +" | "+ line)
  } 
}

else
  Console.err.println("Please enter filename")
