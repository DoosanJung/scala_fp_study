abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(
  val contents: Array[String]
) extends Element

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}

class UniformElement(
  //given width and height and is filled everywhere with a given character Char
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

val e1: Element = new ArrayElement(Array("hello", "world"))
val ae: ArrayElement = new LineElement("hello")
val e2: Element = ae
val e3: Element = new UniformElement('x', 2, 3)

/*
Method invocations on variables and expressions are dynamically bound.
The actual method implementation invoked is determined at run time based on the class of the 
object, not the type of the variable or expression
*/

abstract class AbsClass {
  def demo() {
    println("AbsClass's implementation invoked")
  }
}

class ArrayAbsClass extends AbsClass {//insert 'final' before 'class' => not be subclassed
  override def demo() {
    println("ArrayAbsClass's implementation invoked")
  }
  /*
  If you want to ensure that a member cannot be overridden by subclasses
  final override def demo() { //insert 'final' modifier before 'override'
    println("ArrayAbsClass's implemenetation invoked")
  } 
  */
}

class LineAbsClass extends ArrayAbsClass {
  override def demo() {
    println("LineAbsClass's implementation invoked")
  }
}

class UniformAbsClass extends AbsClass

//to invoke demo methods
def invokeDemo(e: AbsClass) {
  e.demo()
}

invokeDemo(new ArrayAbsClass)
invokeDemo(new LineAbsClass)
invokeDemo(new UniformAbsClass) //"AbsClass's implementation invoked"
/*
UniformAbsClass does not override demo, it inherits the implementation of demo from its 
superclass, AbsClass.
*/
