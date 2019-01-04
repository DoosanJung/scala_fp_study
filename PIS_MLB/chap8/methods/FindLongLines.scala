/*
To use LongLines from the command line, we’ll create an application 
that expects the line width as the first command-line argument, and interprets subsequent arguments as filenames
*/
import LongLines.processFile

object FindLongLines {
  def main(args: Array[String]) {
    val width = args(0).toInt
    for (arg <- args.drop(1))
      LongLines.processFile(arg, width)
  }
}

//$scala FindLongLines 45 LongLines.scala
