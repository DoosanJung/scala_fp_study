import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  //adds new integers to the queue
  def put(x: Int)
  //removes and returns them
  def get(): Int
}

class BasicIntQueue extends IntQueue {
  private val bug = new ArrayBuffer[Int]
  def get() = bug.remove(0)
  def put(x: Int) {bug += x}
}

object BasicIntQueue {
  val queue = new BasicIntQueue
  queue.put(10)
  queue.put(20)
  println(queue.get())
  println(queue.get())
  //java.lang.IndexOutOfBoundsException: 0
  //println(queue.get())
}
BasicIntQueue

trait Doubling extends IntQueue {
  abstract override def put(x: Int) {super.put(2 * x)}
}

class MyQueue extends BasicIntQueue with Doubling

object MyQueue {
  val queue = new MyQueue
  queue.put(10)
  println(queue.get())

  //also this is possible
  val queue2 = new BasicIntQueue with Doubling
  queue2.put(10)
  println(queue2.get())
}
MyQueue

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) {super.put(x + 1)}
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int): Unit = {
    if (x >= 0) super.put(x)
  }
}

object IncrementingFiltering {
  //traits further to the right takes effects first
  val queue = new BasicIntQueue with Incrementing with Filtering
  queue.put(-1)
  queue.put(0)
  queue.put(1)
  println(queue.get())
  println(queue.get())

  val queue2 = new BasicIntQueue with Filtering with Incrementing
  queue2.put(-1)
  queue2.put(0)
  queue2.put(1)
  println(queue2.get())
  println(queue2.get())
  println(queue2.get())
}
IncrementingFiltering