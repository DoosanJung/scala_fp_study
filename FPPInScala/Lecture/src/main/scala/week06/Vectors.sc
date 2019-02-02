object Vectors {

  val nums = Vector(1, 2, 3, -88)
  val people = Vector("Bob", "James", "Peter")

  val nums2 = nums :+ 3
  val people2 = "DJ" +: people
}
println(Vectors.nums2)
println(Vectors.people2)


object ArrayAndString{

  val xs = Array(1, 2, 3, 44)

  val s = "Hello World"

  val pairs = List(1, 2, 3) zip s
}
ArrayAndString.xs map (x => x * 2)
ArrayAndString.s filter (c => c.isUpper)
ArrayAndString.s exists (c => c.isUpper)
ArrayAndString.s forall (c => c.isUpper)
ArrayAndString.pairs
ArrayAndString.pairs.unzip
ArrayAndString.s flatMap (c => List('.', c))

object Ranges {

  val r: Range = 1 until 5
  val s: Range = 1 to 5
}
1 to 10 by 3
6 to 1 by -2

//exercises
object Exercise {
  def combination(M: Int, N:Int) =
    (1 to M) flatMap (x => (1 to N) map (y => (x,y)))

  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map(xy => xy._1 * xy._2).sum

  def scalarProductPatternMatching(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map{case (x, y) => x * y}.sum

  def isPrime(n: Int): Boolean =
    (2 until n) forall (d => n % d != 0)
}

Exercise.combination(2, 3)
Exercise.scalarProduct(Vector(1.0, 2.0, 3.0), Vector(3.0, 2.0, 1.0))
Exercise.scalarProductPatternMatching(Vector(1.0, 2.0, 3.0), Vector(3.0, 2.0, 1.0))
Exercise.isPrime(7)