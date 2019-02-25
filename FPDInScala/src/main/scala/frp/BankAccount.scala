package frp

class BankAccount {
  var balance = Var(0) //var => balance is now a signal

  def deposit(amount: Int): Unit = {
    if (amount > 0) {
      //throws an error
      //balance() = balance() + amount
      val b = balance()
      balance() = b + amount
    }
  }

  def withdraw(amount: Int): Unit =
    if (0 < amount && amount <= balance()) {
      //throws an error
      //balance() = balance() - amount
      val b = balance()
      balance() = b - amount
    } else throw new Error("insufficient funds")
}