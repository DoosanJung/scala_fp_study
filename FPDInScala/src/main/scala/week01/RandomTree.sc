object RandomTree {

  trait Generator[+T] {
    self => // an alias for ”this”.
    def generate: T

    def map[S](f: T => S): Generator[S] = new Generator[S] {
      def generate = f(self.generate)
    }

    def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
      def generate = f(self.generate).generate
    }
  }

  /*
  A tree is either a leaf or an inner node.
  * */
  trait Tree
  case class Inner(left: Tree, right: Tree) extends  Tree
  case class Leaf(x: Int) extends Tree

  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }

  val booleans = integers.map(_ > 0)

  def leafs: Generator[Leaf] = for {
    x <- integers
  } yield Leaf(x)

  def inners: Generator[Inner] = for {
    l <- trees
    r <- trees
  } yield Inner(l, r)

  def trees: Generator[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
  } yield tree

}

for (i <- 1 to 5) println(RandomTree.trees.generate)

