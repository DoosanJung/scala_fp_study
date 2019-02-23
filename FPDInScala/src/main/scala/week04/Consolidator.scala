package week04

class Consolidator(observed: List[BankAccount]) extends Subscriber {
  observed.foreach(_.subscribe(this))

  //_ means that the variable is initially uninitialized.
  // I initialize it by calling the compute method.
  private var total: Int = _
  compute()

  private def compute(): Unit =
    total = observed.map(_.currentBalance).sum

  def handler(pub: Publisher): Unit = compute()

  def totalBalance: Int = total
}