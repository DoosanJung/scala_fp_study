#!/usr/bin/env scala
/*
The most common example of scoping is that curly braces generally introduce a new scope, 
so anything defined inside curly braces leaves scope after the final closing brace.
*/
//All variables defined in this example— i , j , prod , and k —are local variables.
def printMultiTable() {
  var i = 1 // only i in scope here
  while (i <= 10) {
    var j = 1 // both i and j in scope here
    while (j <= 10) {
      val prod = (i * j).toString // i, j, and prod in scope here

      //to make a correct spacing between prods
      var k = prod.length // i, j, prod, and k in scope here
      while (k < 4) {
        print(" ") //print space in the same line
        k += 1
      }
      print(prod) //print prod in the same line
      j += 1
    } // i and j still in scope; prod and k out of scope
  println() //new line
  i += 1
  } // i still in scope; j, prod, and k out of scope
}

printMultiTable()

//you can’t define a new variable with the same name in the same scope
//what you can do is ..
val a = 1;
{
  val a = 2; //compiles just fine
  println(a)
}
println(a)
/*
NOTE:Java will not let you create a variable in an inner scope that has the same name
as a variable in an outer space.
In a Scala program, an inner variable is said to shadow a like-named outer variable,
because the outer variable becomes invisible in the inner scope
*/
