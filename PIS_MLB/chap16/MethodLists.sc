object MethodLists {
  val res0 = List.apply(1, 2, 3)

  //Creating a range of numbers: List.range
  val res1 = List.range(1, 5)
  val res2 = List.range(1, 9, 2)
  val res3 = List.range(9, 1, -3)

  //Creating uniform lists: List.fill
  val res4 = List.fill(5)('a')
  val res5 = List.fill(3)("hello")
  val res6 = List.fill(2, 3)('b')

  //Tabulating a function: List.tabulate
  val squares = List.tabulate(5)(n => n * n)
  val multiplication = List.tabulate(5,5)(_ * _)

  //Concatenating multiple lists: List.concat
  val res7 = List.concat(List('a', 'b'), List('c'))
  val res8 = List.concat(List(), List('b'), List('c'))
  val res9 = List.concat()
}
MethodLists.res0
MethodLists.res1
MethodLists.res2
MethodLists.res3
MethodLists.res4
MethodLists.res5
MethodLists.res6

MethodLists.squares
MethodLists.multiplication

MethodLists.res7
MethodLists.res8
MethodLists.res9

object MultipleLists {
  val res10 = (List(10, 20), List(3, 4, 5)).zipped.map(_*_)

  val res11 = (List("abc", "de"), List(3, 2)).zipped.forall(_.length == _)

  val res12 = (List("abc", "de"), List(3, 2)).zipped.exists(_.length != _)

}

MultipleLists.res10
MultipleLists.res11
MultipleLists.res12



