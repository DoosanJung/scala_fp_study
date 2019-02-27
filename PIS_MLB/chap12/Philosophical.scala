trait Philosophical {
  def philosophize() {
    println("I consume memory, therefore I am!")
  }
}

//class Frog extends Philosophical {
//  override def toString = "green"
//}

class Animal
trait HasLegs
class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "animal_green"

  override def philosophize(): Unit = {
    println("It ain't easy being " + toString + "!")
  }
}


object Frog {
  val frog = new Frog
  println(frog)
  frog.philosophize()

  val phil: Philosophical = frog
  println(phil)
  phil.philosophize()
}

Frog

