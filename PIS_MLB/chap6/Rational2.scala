//Rational.scala Part 2
//Identifiers
/*
Camel-case names of fields, method parameters, local variables, and 
functions should start with lower case letter, for example: length, 
flatMap, and s.

Camel-case names of classes and traits should start with an upper 
case letter, for example: BigInt, List, and UnbalancedTreeMap.

Thus, constants named in the Java style, such as X_OFFSET, will 
work as Scala constants, but the Scala convention is to use camel 
case for constants, such as XOffset.
*/

/*
An operator identifier

Here are some examples of operator identifiers:
+ ++ ::: <?> :->
*/

/*
A literal identifier

an arbitrary string enclosed in back ticks (` . . . `). Some 
examples of literal identifiers are:
`x` `<clinit>` `yield`
*/

//Method overlodaing
class RationalOverload(n: Int, d: Int) {

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def + (that: RationalOverload): RationalOverload =
    new RationalOverload(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def + (i: Int): RationalOverload =
    new RationalOverload(numer + i * denom, denom)

  def - (that: RationalOverload): RationalOverload =
    new RationalOverload(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def - (i: Int): RationalOverload =
    new RationalOverload(numer - i * denom, denom)

  def * (that: RationalOverload): RationalOverload =
    new RationalOverload(numer * that.numer, denom * that.denom)

  def * (i: Int): RationalOverload =
    new RationalOverload(numer * i, denom)

  def / (that: RationalOverload): RationalOverload =
    new RationalOverload(numer * that.denom, denom * that.numer)

  def / (i: Int): RationalOverload =
    new RationalOverload(numer, denom * i)

  override def toString = numer +"/"+ denom

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}

val oneHalf = new RationalOverload(1,2)
val one:Int = 1

println(oneHalf + one)

//Implicit conversions
val r = new RationalOverload(2,3)
println(r * 2)
// println(2 * r) yields an error.
/*
The problem here is that 2 * r is equivalent to 2.*(r), so it is a method 
call on the number 2, which is an integer. But the Int class contains no 
multiplication method that takes a Rational argument—it couldn’t because 
class Rational is not a standard class in the Scala library.
*/

implicit def intToRationalOverload(x:Int) = new RationalOverload(x)
/*
This defines a conversion method from Int to Rational. The implicit 
modifier in front of the method tells the compiler to apply it 
automatically in a number of situations
*/

println(2*r)
