package frp

class Var[T](expr: => T) extends Signal[T](expr) {
  //Var is a Signal which can be updated by the client program
  //all necessary functionality is already present in class Signal; we
  //just need to expose it.
  override def update(expr: => T): Unit = super.update(expr)
}

//Var(expr)
object Var {
  def apply[T](expr: => T) = new Var(expr)
}