/*
To run a Scala program, you must supply the name of a standalone singleton object 
with a main method that takes one parameter, an Array[String], and has a result type of Unit.

Any standalone object with a main method of the proper signature can be used as 
the entry point into an application
*/

import ChecksumAccumulator.calculate

object Summer {
  def main(args: Array[String]) {
    for (arg <- args)
      println(arg + ": " + calculate(arg))
  }
}

/*
Neither ChecksumAccumulator.scala nor Summer.scala are scripts, because they end in a definition.
A script, by contrast, must end in a re- sult expression.
Thus if you try to run Summer.scala as a script, the Scala interpreter will complain that Summer.scala does not end in a result expression.
Instead, you’ll need to actually compile these files with the Scala compiler, then run the resulting class files.
$ scalac ChecksumAccumulator.scala Summer.scala

for faster compile, use fsc (not spending time scanning the contents of jar files and doing other initial work)
Using fsc, you only need to wait for the Java runtime to startup the first time
$ fsc ChecksumAccumulator.scala Summer.scala
$ fsc -shutdown
*/

/*
instead of giving it a filename with a .scala extension containing Scala code to interpret as you did in every previous example,
in this case you’ll give it the name of a standalone object containing a main method of the proper signature
$ scala Summer of love
of: -213
love : -182
*/
