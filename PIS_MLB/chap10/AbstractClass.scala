abstract class Element {
  //where each string represents a line
  //class Element declares the abstract method contents, but no definition.
  def contents: Array[String] //no equal sign or body => abstract method
  /*
  contents is declared as a method without implementation.
  the method is an abstract member of class Element.
  A class with abstract members must be declared abstract.
  */
  def height: Int = contents.length
  def width: Int 
    = if (height == 0) 0 else contents(0).length
  //parameterless methods height, width
  //
}

/*
completely equivalent from a client's point of view.
abstract class Element {
  def contents: Array[String]
  val height = contents.length
  val width =
    if (height == 0) 0 else contents(0).length
}
//slightly faster (pre-computed when initialization), but requires extra memory
*/

//a subclass that extends Element and implements the abstract contents method.
class ArrayElement(conts: Array[String]) extends Element {
  //class ArrayElement is “composed” out of class Array[String]
  def contents: Array[String] = conts
}
/* ...extends Element ...
(1) class ArrayElement inherit all non-private members from class Element
(2) makes the type ArrayElement a subtype of the type Element

If you leave out an extends clause, the Scala compiler implicitly as- sumes your class extends 
from scala.AnyRef.
Thus, class Element implicitly extends class AnyRef.
*/

//the contents method in ArrayElement overrides abstract method contents in class Element.
//By contrast, class ArrayElement inherits the width and height methods from class Element.
//If superclass is abstract, we say "implement" instead of "override".
val ae = new ArrayElement(Array("hello","world"))
println(ae.width)

//subtyping: a value of the sub can be used wherever a value of the super is required.
val e: Element = new ArrayElement(Array("hello"))
println(e)
//Variable e is defined to be of type Element, so its initializing value should also be an Element. 
//In fact, the initializing value’s type is ArrayElement.

/*
same as above
class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts
  //change the implementation of contents in class ArrayElement from a method to a field 
  //without having to modify the abstract method definition of contents in class Element
}
*/

/*
Scala’s two namespaces are:
• values (fields, methods, packages, and singleton objects)
• types (class and trait names)

THIS WILL NOT WORK because a field and method have the same name 
(fields, methods same namespace)

class WontCompile {
    private var f = 0 // Won’t compile, because a field
    def f = 1         // and method have the same name
}
*/

//ArrayElement has a parameter conts whose sole purpose is to be copied into the contents.
//redundant. unnecessary.

class ArrayElementConcise(
  //combine the parameter and the field in a single 'parametric field'
  val contents: Array[String] //defines a parameter and field (same name) at the same time
) extends Element

/*
Same as below..
class ArrayElementConcis(anyArbitraryName: Array[String]) extends Element {
  val contents: Array[String] = anyArbitraryName
}
*/

//possible to add modifiers such as private, protected, or override to these parametric fields
class Cat {
  val dangerous = false
}

class Tiger(
  override val dangerous: Boolean,
  private var age: Int
) extends Cat

/*
above is the shorthand for this..
class Tiger(param1: Boolean, param2:Int) extends Cat{
  override val dangerous = param1
  private var age = param2
}
*/

//add more subclasses..
//a layout element consisting of a single line given by a string
class LineElement(s: String) extends ArrayElement(Array(s)) {
  //LineElement passes Array(s) to ArrayElement’s primary constructor by placing it 
  //in parentheses after the superclass ArrayElement’s name. 
  override def width = s.length
  override def height = 1
  //The modifier is optional if a member implements an abstract member with the same name.
  //Since height and width in LineElement override concrete definitions in class Element, 
  //"override" is required. 
}

val le = new LineElement("hello")
println(le.width)
println(le.height)
