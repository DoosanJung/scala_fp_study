object RandomGenerators {

  trait Generator[+T] {
    def generate: T
  }

  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }

  val booleans = new Generator[Boolean] {
    def generate = integers.generate > 0
  }

  val pairs = new Generator[(Int, Int)] {
    def generate = (integers.generate, integers.generate)
  }
}

RandomGenerators.integers.generate
RandomGenerators.booleans.generate
RandomGenerators.pairs.generate

//can we avoid the new Generator ... boilerplate?
//more convenient version of Generator
object RandomGenerators2 {

  trait Generator[+T] {
    self => // an alias for ”this”.

    def generate: T

    def map[S](f: T => S): Generator[S] = new Generator[S] {
      //this in f(this.generate) will refer to the current method def "generate" = f(...)
      //would be a recursive call and not terminate.
      //What we need instead is we want to call the generate method of the object one further out.
      //"def generate: T"
      //thus, we need "self"
      def generate = f(self.generate)

      //below will also work
      def generate2 = f(Generator.this.generate)
    }

    def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
      //self.generate to generate random value type of T
      //apply f(...) => gives us a complete generator on the new domain S
      //.generate will pick a random value in the domain S
      def generate = f(self.generate).generate
    }
  }

  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }

  //we wished for this syntax
  val booleans = for (x <- integers) yield x > 0
  /* the above will be translated as follows...
  *
  * => val booleans = integers map {x => x > 0}
  * => val booleans = new Generator[Boolean]{
  *      def generate = (x: Int => x > 0)(integers.generate)
  *    }
  * => val booleans = new Generator[Boolean]{
  *      def generate = integers.generate > 0
  *    }
  * */

  def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
    x => u map {y => (x, y)}
  }
  /* the above will be translated as follows...
  *
  * => def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
  *      x => new Generator[(T, U)] {def generate = (x, u.generate)}
  *    }
  *
  * => def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)]{
  *      def generate = (new Generator[(T, U)] {
  *        def generate = (t.generate, u.generate)
  *      }).generate
  *    }
  *
  * => def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)]{
  *      def generate = (t.generate, u.generate)
  *    }
  * */
  val pair = pairs(integers, integers)

  def single[T](x: T): Generator[T] = new Generator[T] {
    //always give back the same random value
    def generate = x
  }

  def choose(low: Int, high: Int): Generator[Int] =
    for (x <- integers) yield low + Math.abs(x % (high - low))

  def oneOf[T](xs: T*): Generator[T] =
    //oneOf(red, blue, yellow) => randomly choose one
    for (idx <- choose(0, xs.length)) yield xs(idx)

  //Randome list
  def lists: Generator[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyLists else nonEmptyLists
  } yield list

  def emptyLists = single(Nil)

  def nonEmptyLists = for {
    head <- integers
    tail <- lists
  } yield head :: tail


  //Random test
  def test[T](g: Generator[T], numTimes: Int = 100)(test: T => Boolean): Unit = {
    for (i <- 0 until numTimes) {
      val value = g.generate
      assert(test(value), "test failed for "+value)
    }
    println("passed "+numTimes+" tests")
  }

  val testResult = test(pairs(lists, lists)) {
    case (xs, ys) => (xs ++ ys).length >= xs.length
  }
}
RandomGenerators2.integers.generate
RandomGenerators2.booleans.generate
RandomGenerators2.pair.generate
RandomGenerators2.single(Nil).generate
for (i <- 1 to 5) println(RandomGenerators2.choose(3, 6).generate)
for (i <- 1 to 5) println(RandomGenerators2.oneOf("Red", "Blue", "Yellow").generate)
for (i <- 1 to 5) println(RandomGenerators2.lists.generate)

//Random tests
RandomGenerators2.testResult