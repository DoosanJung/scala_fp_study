import frp.{BankAccount, Signal}

object accounts {
  def consolidated(accts: List[BankAccount]): Signal[Int] =
    Signal(accts.map(_.balance()).sum)

  val a = new BankAccount()
  val b = new BankAccount()
  val c = consolidated(List(a, b))

  println(c())
  a deposit 20
  println(c())
  b deposit 30
  println(c())

  val xchange = Signal(246.00)
  val inDollar = Signal(c() * xchange())
  println(inDollar())
  b withdraw 10
  println(inDollar())
}
accounts