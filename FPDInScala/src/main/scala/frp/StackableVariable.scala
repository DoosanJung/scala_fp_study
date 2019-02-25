package frp

class StackableVariable[T](init: T) {
  //a stack
  private var values: List[T] = List(init)
  //current value
  def value: T = values.head
  //put newValue on top of the stack
  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue :: values
    try op finally values = values.tail
  }
}

/*Access it like this..
*
* val caller = new StackableVariable(initialSig)
* caller.withValue(otherSig) { ... }
* ... caller.value ...
* */