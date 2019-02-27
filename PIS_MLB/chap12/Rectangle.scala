class Point(val x: Int, val y: Int)

class Rectangle(val topLeft: Point, val bottomRight: Point) {
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // and many more geometric methods...
}

abstract class Component {
  def topLeft: Point
  def bottomRight: Point
  //Notice that the definitions of left , right , and width are exactly the same
  //in the two classes.
  //This repetition can be eliminated with an enrichment trait.
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // and many more geometric methods...
}

object Test {
  val point1 = new Point(1, 1)
  val point2 = new Point(5, 5)
  val rec = new Rectangle(point1, point2)
  println(rec.width)
}

Test