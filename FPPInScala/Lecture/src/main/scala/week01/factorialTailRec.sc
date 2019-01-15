import scala.annotation.tailrec
object factorialTailRec {
  def factorialTailRec(n: Int): Int = {

    @tailrec
    def loop(x: Int, result: Int): Int =
      if (x == 1) result
      else loop(x - 1, result * x)

    loop(n, 1)
  }
}

factorialTailRec.factorialTailRec(4)