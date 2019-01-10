/*
You might also choose to hide the hierarchy behind a factory object.
Clients would then use these factory methods for object construction rather than 
constructing the objects directly with new.
The details of how objects are represented with classes can be hidden.
*/

/*
with factory method...no need to create new ArrayElement instances explicitly.
abstract class Element {
  def contents: Array[String]
  def width: Int =
    if (height == 0) 0 else contents(0).length
  def height: Int = contents.length
  def above(that: Element): Element =
    new ArrayElement(this.contents ++ that.contents)
  def beside(that: Element): Element =
    new ArrayElement(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
  override def toString = contents mkString "\n"
}
*/

/*
we still need to implement widen ahd heighten...
import Element.elem
abstract class Element {
  def contents: Array[String]
  def width: Int =
    if (height == 0) 0 else contents(0).length
  def height: Int = contents.length
  
  def above(that: Element): Element =
    elem(this.contents ++ that.contents)
  
  def beside(that: Element): Element =
    elem(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
  override def toString = contents mkString "\n"
}
*/

import Element.elem
abstract class Element {
  def contents:  Array[String]
  def width: Int = contents(0).length
  def height: Int = contents.length
  
  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(
      this1.contents ++ that1.contents
    )
  }

  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for (
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }
       
  def widen(w: Int): Element =
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      var right = elem(' ', w - width - left.width, height)
      left beside this beside right
  }
       
  def heighten(h: Int): Element =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      var bot = elem(' ', width, h - height - top.height)
      top above this above bot
  }
        
  override def toString = contents mkString "\n"
}


/*
==> private classes
class ArrayElement(
    val contents: Array[String]
) extends Element

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
*/

/*
object Element {
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)
  
  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)
  
  def elem(line: String): Element =
    new LineElement(line)
}
*/
object Element {
  private class ArrayElement(
    val contents: Array[String]
  ) extends Element
  
  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }
  
  private class UniformElement(
    ch: Char,
    override val width: Int,
    override val height: Int
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }
  
  def elem(contents:  Array[String]): Element =
    new ArrayElement(contents)
  
  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)
  
  def elem(line: String): Element =
    new LineElement(line)
}
/*
given the factory methods, the subclasses ArrayElement, LineElement and UniformElement could 
now be private, because they need no longer be accessed directly by clients.
*/
