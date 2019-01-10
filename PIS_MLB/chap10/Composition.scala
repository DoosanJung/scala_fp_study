/*
Composition and inheritance are two ways to define a new class in terms of another existing 
class.
*/
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int =
    if (height == 0) 0 else contents(0).length

  //implementing above, beside, and toString
  def above(that: Element): Element = 
    new ArrayElement(this.contents ++ that.contents) //++ concatenates two arrays

  //imperative style
  def besideImperative(that: Element): Element = {
    val contents = new Array[String](this.contents.length)
    for (i <- 0 until this.contents.length)
      contents(i) = this.contents(i) + that.contents(i)
    new ArrayElement(contents)
  }

  //besideImperative can be abbreviated to one expression:
  def beside(that: Element): Element =
    new ArrayElement(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
    //If one of the two operand arrays is longer than the other, zip drops the remaining elements.

  override def toString = contents mkString "\n"
}

class ArrayElement( //ArrayElement is-an Element.
  val contents: Array[String]
) extends Element //we do expect clients will want to use an ArrayElement as an Element.

/*
To define LineElement as a direct subclass of Element..
class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}
LineElement had an inheritance relationship with ArrayElement, from which it inherited 
contents.

It now has a composition relationship with Array: it holds a reference to an array of strings 
from its own contents field.
*/
class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}


class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

