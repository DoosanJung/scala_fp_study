package frp
import scala.util.DynamicVariable

class Signal[T](expr: => T) {
  import Signal._

  //current expr
  private var myExpr: () => T = _
  //current value of the Signal
  private var myValue: T = _
  //Set of observers
  private var observers: Set[Signal[_]] = Set()

  update(expr)

  protected def update(expr: => T): Unit = {
    //take the expr and assigns it to myExpr
    myExpr = () => expr
    computeValue()
  }

  protected def computeValue(): Unit = {
    //myValue = caller.withValue(this)(myExpr())

    //need propagating changes
    val newValue = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue
      //assign it to local val
      val obs = observers
      //clear up the observers
      observers = Set()
      //for each eob, computeValue()
      obs.foreach(_.computeValue())
    }
  }

  def apply() = {
    //apply returns the current value of the Signal
    //but, before it does...
    //add it to observers
    observers += caller.value
    //S() = S() + 1 should throw an error
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    //then, returns the currrent value of the Signal
    myValue
  }
}

//Signal(expr)
object Signal {
  //[_] : can take signals of any value type
  //private val caller = new StackableVariable[Signal[_]](NoSignal)

  /*What happens if we try to evaluate several signal expressions in parallel?
  * The caller signal will become “garbled” by concurrent updates
  * => Replace global state by thread-local state
  * It is supported in Scala through class scala.util.DynamicVariable.
  * */
  val caller = new DynamicVariable[Signal[_]](NoSignal)

  /*
  * However, its imperative nature often produces hidden dependencies which are
  hard to manage.
  * Its implementation on the JDK involves a global hash table lookup,
  which can be a performance problem.
  * It does not play well in situations where threads are multiplexed
  between several tasks.
  * */


  def apply[T](expr: => T) = new Signal(expr)
}

//the "sentinal" object
//no value: Nothing
//no implementation: (???)
object NoSignal extends Signal[Nothing](???) {
  //computeValue needs to be disabled for NoSignal because we cannot
  //evaluate an expression of type Nothing:
  override def computeValue(): Unit = ()
}

