//read a file with a given name and print out all lines whose length exceeds a given width
import scala.io.Source

object LongLines {

  def processFile(filename:String, width:Int) {
    val source = Source.fromFile(filename) //creates a source object
    for (line <- source.getLines()) //calls getLines => returns an iterator
      processLine(filename, width, line)
  }

  private def processLine(filename:String, width:Int, line:String){
    if (line.length > width)
      println(filename + ": " + line.trim)
  }
}
