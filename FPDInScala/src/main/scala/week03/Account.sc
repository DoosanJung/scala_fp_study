import week03.BankAccount

object Account {
  val acct = new BankAccount
  acct deposit 50
  acct withdraw 20
  acct withdraw 20
}

println(Account.acct)