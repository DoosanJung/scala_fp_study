object HOF {

  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }

  def squareList(xs: List[Double]): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * y :: squareList(ys)
  }

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  }

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }

  // use pack
  def encode[T](xs: List[T]): List[(T, Int)] =
    pack(xs) map (ys => (ys.head, ys.length)) //ys is sublist
}

val lst = List(1.0, 2.0, 3.0, 4.0)
val intlst = List(-2, -1, 0, 1, 2)
val data = List("a","a","a","b","c","c","a")

HOF.scaleList(lst, 2.0)
HOF.squareList(lst)
HOF.posElems(intlst)
HOF.pack(data)
HOF.encode(data)

def scaleListMap(xs: List[Double], factor: Double) =
  xs map (x => x * factor)

def squareListMap(xs: List[Double]): List[Double] =
  xs map (x => x * x)

def posElemsFilter(xs: List[Int]): List[Int] =
  xs filter (x => x > 0)

scaleListMap(lst, 2.0)
squareListMap(lst)
posElemsFilter(intlst)
