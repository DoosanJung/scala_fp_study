import week03.Rational

object scratch {
  println(new Rational(1, 2))

  def error(msg: String) = throw new Error(msg) //will return Nothing

  val x = null
  val y: String = x

  //not do
  //val z: Int = null //Type mismatch

  if (true) 1 else false //AnyVal
}