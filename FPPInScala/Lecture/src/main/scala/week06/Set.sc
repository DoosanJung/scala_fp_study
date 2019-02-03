object Sets {

  val fruit = Set("apple", "banana", "pear")
  val s = (1 to 6).toSet



}
Sets.s contains 5

object nqueens {
  /*
  * An example of nqueens(4):
  *    0  1  2  3 <= column number
  * 0  *  Q  *  *
  * 1  *  *  *  Q
  * 2  Q  *  *  *
  * 3  ?  ?  ?  ? => Should be *  *  Q  *
  * Therefore..
  * => List(0, 3, 1) => List(2, 0, 3, 1)
  * (later queen comes first in the result!)
  */
  def queens(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int): Set[List[Int]] =
      //if we don't need to place any queen
      if (k == 0) Set(List())
      // if we need to place any queen
      else
        for {
          queens <- placeQueens(k -1)
          col <- 0 until n
          //if no threat to any other queens
          if isSafe(col, queens)
        } yield col :: queens

    placeQueens(n)
  }

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    //First explicitly display the row numbers..
    //List(0, 3, 1) => List((2, 0), (1, 3), (0, 1))
    val row = queens.length
    val queensWithRow = (row -1 to 0 by -1) zip queens

    //check if the queen at r, c is not it check
    queensWithRow forall {
      //current column is not the same as any of the columns of the previous queens
      //i.e. col != c
      //the queen ins also not in check over any of diagonals.
      //i.e. math.abs(col - c) != math.abs(row - r)
      case (r, c) => col != c && math.abs(col - c) != math.abs(row - r)
    }
  }

  def show(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse)
        yield Vector.fill(queens.length)("* ").updated(col, "Q ").mkString
    "\n" + (lines mkString "\n")

  }
}
nqueens.queens(4)
//res1: Set[List[Int]] = Set(List(2, 0, 3, 1), List(1, 3, 0, 2))

(nqueens.queens(4) take 1 map nqueens.show) mkString "\n"
//res1: String =
//"
//* Q * *
//* * * Q
//Q * * *
//* * Q * "