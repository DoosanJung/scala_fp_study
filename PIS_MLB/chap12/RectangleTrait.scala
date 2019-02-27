class Point(val x: Int, val y: Int)

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // and many more geometric methods...
}

abstract class Component extends Rectangular {
  // other methods...
}

class Rectangle(val topLeft: Point, val bottomRight: Point)
  extends Rectangular {
  // other methods...
}

object Test {
  val point1 = new Point(1, 1)
  val point2 = new Point(5, 5)
  val rec = new Rectangle(point1, point2)
  println(rec.width)
}

Test