class Rational(n: Int, d: Int) {

  require(d != 0)

  val numer = n
  val denom = d

  def > (that: Rational) =
    this.numer * that.denom > that.numer * this.denom

  def < (that: Rational) = that > this

  def <= (that: Rational) = (this < that) || (this == that)

  def >= (that: Rational) = (this > that) || (this == that)

  override def toString = numer +"/"+ denom
}

object Rational {
  val oneHalf = new Rational(1, 2)
  val twoThird = new Rational(2, 3)
  println(oneHalf < twoThird)
  println(twoThird > oneHalf)
  println(oneHalf > twoThird)
  println(twoThird < oneHalf)
  println(oneHalf >= oneHalf)
  println(oneHalf <= oneHalf)
}
Rational

class RationalTrait(n: Int, d: Int) extends Ordered[RationalTrait] {
  // ...
  val numer = n
  val denom = d

  //The Ordered trait then defines < , > ,
  //<= , and >= for you in terms of this compare method.
  def compare(that: RationalTrait) =
    (this.numer * that.denom) - (that.numer * this.denom)
}

object RationalTrait {
  val oneHalf = new RationalTrait(1, 2)
  val twoThird = new RationalTrait(2, 3)
  println(oneHalf < twoThird)
  println(twoThird > oneHalf)
  println(oneHalf > twoThird)
  println(twoThird < oneHalf)
  println(oneHalf >= oneHalf)
  println(oneHalf <= oneHalf)

}
RationalTrait
