#!/usr/bin/env scala

//reads lines from a file and prints them out prepended with the number of characters in each line
//the first version
import scala.io.Source
if (args.length > 0) {
  for (line <- Source.fromFile(args(0)).getLines())
    println(line.length +" "+ line)
}
else
  Console.err.println("Please enter filename")
//The expression Source.fromFile(args(0)) attempts to open the specified file and returns a Source object, on which you call getLines.
//The getLines method returns an Iterator[String], which provides one line on each iteration, excluding the end-of-line character.
//The for expression iterates through these lines and prints for each the length of the line, a space, and the line itself.

