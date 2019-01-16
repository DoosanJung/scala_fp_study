package week03

class Rational(x: Int, y: Int){
  //predefined function, require
  //to enforce a precondition on the caller of a function
  require(y != 0, "denominator must be nonzero")

  //second constructor of the class in addition to the primary constructor
  def this(x: Int) = this(x, 1) //from implicit primary constructor Rational(x, y)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  def numer = x / g
  def denom = y / g

  def + (that: Rational) =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  override def toString =
    numer + "/" + denom

  //must have a space between unary_- and :
  def unary_- : Rational = new Rational(-numer, denom)

  def - (that: Rational) = this + -that

  def < (that: Rational) = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) = if (this < that) that else this

}