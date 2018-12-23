//Rational.scala Part 1
/*
A rational number is a number that can be expressed as a ratio n/d, where n and d are integers, except that d cannot be zero. 
n is called the numerator and d the denominator.

Rational numbers do not have mutable state. 
You can add one rational number to another, but the result will be a new rational number.

Each rational number will be represented by one Rational object. 
When you add two Rational objects, you’ll create a new Rational object to hold the sum
*/

//Constructing a Rational
/*
Given we’ve decided to make Rational objects immutable, we’ll require that clients provide all data needed by an instance 
(in this case, a numerator and a denominator) when they construct the instance.
The identifiers n and d in the parentheses after the class name, Rational, are called class parameters.
The Scala compiler will gather up these two class parameters and create a primary constructor that takes the same two parameters.
*/

class RationalFirst(n: Int, d:Int){
  println("Created " + n + "/" + d)
}

println(new RationalFirst(1,2))

//Reimplementing the toString method
/*
By default, class Rational inherits the implementation of toString defined in class java.lang.Object, 
which just prints the class name, an @ sign, and a hexadecimal number.

You can override the default implementation by adding a method toString to class Rational
*/

class RationalOverride(n:Int, d:Int){
  override def toString = n + "/" + d 
  //The override modifier in front of a method definition signals that 
  //a previous method definition is overridden
}

val x = new RationalOverride(1,3)
println(x)

//Checking preconditions
/*
One of the benefits of object-oriented programming is that it allows you to encapsulate data inside objects
so that you can ensure the data is valid throughout its lifetime.
In the case of an immutable object such as Rational, this means that you should ensure the data is valid when the object is constructed.
*/

class RationalCheckCondition(n:Int, d:Int){
  require(d != 0) //define as a precondition of the primary constructor that d must be non-zero
  override def toString = n + "/" + d
}

val y = new RationalCheckCondition(1,3)
println(y)

/*
val wrong = new RationalCheckCondition(1,0)
println(wrong)
*/

//Adding fields
class RationalAdd(n:Int, d:Int){
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString = numer + "/" + denom
  
  def add(that: RationalAdd): RationalAdd =
    new RationalAdd(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
}

val oneHalf = new RationalAdd(1,2)
val twoThirds = new RationalAdd(2,3)

println(oneHalf add twoThirds)

val r = new RationalAdd(1,2)
println(r.numer)
println(r.denom)

//Self references
class RationalSelfRef(n:Int, d:Int){
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString = numer + "/" + denom 
  
  def add(that: RationalSelfRef): RationalSelfRef = 
    new RationalSelfRef(
    numer * that.denom + that.numer * denom,
    denom * that.denom
    )

  def lessThan(that: RationalSelfRef) = 
    this.numer * that.denom < that.numer * this.denom

  def max(that: RationalSelfRef) = 
    if (this.lessThan(that)) that else this
}

val oneHalf2 = new RationalSelfRef(1,2)
val twoThirds2 = new RationalSelfRef(2,3)
println("which is bigger? 1/2 vs 2/3")
println(oneHalf2 max twoThirds2)

//Auxiliary constructors
//Sometimes you need multiple constructors in a class.
class RationalAux(n:Int, d:Int) {
  require(d != 0)
  
  val numer: Int = n
  val denom: Int = d

  def this(n:Int) = this(n, 1) // auxiliary constructor starts with this(...)

  override def toString = numer + "/" + denom

  def add(that: RationalAux): RationalAux = 
    new RationalAux (
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
}

val three = new RationalAux(3,1)

//Private fields and methods
//To normalize in this way, you need to divide the numerator and denominator by their greatest common divisor
class RationalPrivate(n:Int, d:Int) {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def add(that: RationalPrivate): RationalPrivate = 
    new RationalPrivate(
      numer * that.denom + that.numer * denom,
      denom * that.denom
      )

  override def toString = numer + "/" + denom
  
  private def gcd(a: Int, b:Int): Int = 
    if (b == 0) a else gcd(b, a % b)

}

val sixtySixFourtyTwo = new RationalPrivate(66,42)
println(sixtySixFourtyTwo)

//Defining operators
//The first step is to replace add by the usual mathematical symbol
//This is straightforward, as + is a legal identifier in Scala
//Simply define a method with + as its name
class RationalOp(n: Int, d: Int) {
  require(d != 0)
  
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  
  def this(n: Int) = this(n, 1)
  
  def + (that: RationalOp): RationalOp =
    new RationalOp(
      numer * that.denom + that.numer * denom,
      denom * that.denom
      )
  
  def * (that: RationalOp): RationalOp =
    new RationalOp(numer * that.numer, denom * that.denom)
  
  override def toString = numer +"/"+ denom
  
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}

val xOp = new RationalOp(1,2)
val yOp = new RationalOp(2,3)
println(xOp + yOp)
println(xOp + xOp * yOp)

