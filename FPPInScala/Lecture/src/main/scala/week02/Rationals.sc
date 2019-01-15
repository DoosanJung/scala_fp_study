object Rational {

  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
  println(x - y - z)
  println(y + y)
  println(x < y)
  println(x max y)

}

class Rational(x: Int, y: Int){
  //predefined function, require
  //to enforce a precondition on the caller of a function
  require(y != 0, "denominator must be nonzero")

  //second constructor of the class in addtion to the primary constructor
  def this(x: Int) = this(x, 1) //from implicit primary constructor Rational(x, y)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  //to avoid arithmetic overflow, perform the division by gcd early as possible
  def numer = x / g
  def denom = y / g

  /*
  * We could also do
  * def numer = x /gcd(x,y)
  * def denom = x /gcd(x,y)
  * or even
  * val numer = x/gcd(x,y)
  * val denom = x/gcd(x,y)
  *
  * ability to choose different implementations for the data without affecting clients
  * is called 'data abstraction'
  * */

  def + (that: Rational) =
    //takes only single parameter, that
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  override def toString =
    numer + "/" + denom

  //must have a space between unary_- and :
  def unary_- : Rational = new Rational(-numer, denom)

  //DRY principle
  def - (that: Rational) = this + -that

  def < (that: Rational) = this.numer * that.denom < that.numer * this.denom
  //you could also do numer * that.denom < that.numer * denom

  //this refers to the current rational
  def max(that: Rational) = if (this < that) that else this

}

Rational