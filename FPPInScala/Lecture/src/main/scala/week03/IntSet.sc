abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
  def union(other: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true //it must be equal

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this //elem is already in the tree

  override def toString = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}

class Empty extends IntSet {
  //empty tree does not contain anything
  def contains(x: Int): Boolean = false

  //return NonEmpty contains given element x and two sub-trees
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)

  override def toString = "."

  def union(other: IntSet): IntSet = other
}

object Empty extends IntSet {
  //No other Empty instances can be created.
  //Singleton objects are values, there is no evaluation step
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty) //no new
  def union(other: IntSet): IntSet = other
}

object IntSet {
  val t1 = new NonEmpty(3, new Empty, new Empty)
  val t2 = t1 incl 4
}

IntSet.t1
IntSet.t2