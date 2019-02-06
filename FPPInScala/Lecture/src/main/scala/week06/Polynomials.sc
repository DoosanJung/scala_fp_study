object polynomials {

  class Poly(val terms: Map[Int, Double]) {
    //incorrect approach: it did not add coeff of same exp
    //def + (other: Poly) = new Poly(terms ++ other.terms)

    //in order to add coeff correctly...
    def + (other: Poly) =
      new Poly(terms ++ (other.terms map adjust))

    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      terms get exp match {
        case Some(coeff1) => exp -> (coeff + coeff1)
        case None => exp -> coeff
      }
    }

    override def toString =
      (for ((exp, coeff) <- terms.toList.sorted.reverse)
        yield coeff + "x^" + exp) mkString " + "
  }

  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))
  val res = p1 + p2
}
polynomials.p1
polynomials.p2
polynomials.res

object polynomialsConcise {

  class PolyConcise(terms0: Map[Int, Double]) {
    //* means it is a repeated parameter
    //call the primary constructor this(...) and convert the Seq to a Map
    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    val terms = terms0 withDefaultValue 0.0

    //in order to add coeff...
    //Create another list of terms that contain the adjusted terms
    //and then we would concatenate this list to the original one.
    def + (other: PolyConcise) =
      new PolyConcise(terms ++ (other.terms map adjust))

    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }

    override def toString =
      (for ((exp, coeff) <- terms.toList.sorted.reverse)
        yield coeff + "x^" + exp) mkString " + "
  }

  val p1 = new PolyConcise(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
  val p2 = new PolyConcise(0 -> 3.0, 3 -> 7.0)
  val res = p1 + p2
}
polynomialsConcise.p1.terms(7) //withDefaultValue 0.0
polynomialsConcise.res

object polynomialsFoldLeft {
  //more efficient because
  // (other.terms map adjust) in + method PolyConcise class creates an intermediate list

  class PolyFoldLeft(terms0: Map[Int, Double]) {
    //* means it is a repeated parameter
    //call the primary constructor this(...) and convert the Seq to a Map
    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    val terms = terms0 withDefaultValue 0.0

    //in order to add coeff
    def + (other: PolyFoldLeft) =
      new PolyFoldLeft((other.terms foldLeft terms)(addTerm))

    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }

    override def toString =
      (for ((exp, coeff) <- terms.toList.sorted.reverse)
        yield coeff + "x^" + exp) mkString " + "
  }

  val p1 = new PolyFoldLeft(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
  val p2 = new PolyFoldLeft(0 -> 3.0, 3 -> 7.0)
  val res = p1 + p2
}
polynomialsFoldLeft.res