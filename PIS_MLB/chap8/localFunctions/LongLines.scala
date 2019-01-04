//read a file with a given name and print out all lines whose length exceeds a given width
/*
refactored the original LongLines version, by transforming private method, processLine,
into a local function of processFile.
As a local function, processLine is in scope inside processFile , but inaccessible outside
=> No need for private modifier..
*/
import scala.io.Source

object LongLines {

  def processFile(filename:String, width:Int) {

    //def processLine(filename:String, width:Int, line:String){ <= verbose

    def processLine(line:String) { //a local function. no need to pass filename, width
      if (line.length > width)
        println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      //processLine(filename, width, line) <= verbose
      processLine(line)
  }

}
