import week02.Pouring

object testPouring {
  val problem = new Pouring(Vector(2, 5))

  val moves = problem.moves

  val pathSets = problem.pathSets.take(3).toList

  val solutions = problem.solutions(3)
}

testPouring.moves
testPouring.pathSets
testPouring.solutions
