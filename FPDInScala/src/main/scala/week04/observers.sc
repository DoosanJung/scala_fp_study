import week04.{BankAccount, Consolidator}

object observers {

  val a = new BankAccount
  val b = new BankAccount
  val c = new Consolidator(List(a, b))
  println(c.totalBalance)
  a deposit 20
  println(c.totalBalance)
  b deposit 30
  println(c.totalBalance)
}

observers