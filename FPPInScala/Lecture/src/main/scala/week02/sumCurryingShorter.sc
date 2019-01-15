/*
* Func that returns func is so useful in FP.
* there is a special syntax for it in Scala.
* => get rid of sumInts, sumCubes,...
* */
object sumCurryingShorter {

  def sum(f: Int => Int)(a: Int, b: Int) : Int =
    if (a > b) 0 else f(a) + sum(f)(a+1, b)
  //remove the middlemen; sumInts, sumCubes, ...
  //expansion of multiple parameter lists

  //the type of sum ?
  //(Int => Int) => ((Int, Int) => Int) or
  //(Int => Int) => (Int, Int) => Int with no parenthesis
}

//sum(cube)(1,3) == (sum(cube))(1,3)
sumCurryingShorter.sum(x => x)(1,3)
sumCurryingShorter.sum(x => x*x*x)(1,3)

//expansion of multiple parameter lists
/*
* def f(args 1)...(args n) = E
*
* where n > 1, is equivalent to
* def f(args 1)...(args n-1) = {def g(args n) = E; g} //function g is returned
*
* e.g.
* def sum(f)(a,b) = {def sumF(a,b)= E; sumF} in sumCurrying.sc
*
* or for short, using anonymous function..
* def f(args 1)..(args n-1) = (args n => E)
*
* if you repeat this n times,
* def f = (args 1 => (args 2 => ... (args n => E)))
* */