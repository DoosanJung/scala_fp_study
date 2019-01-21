package week04

//peano numbers

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  //def successor: Nat
  def successor = new Succ(this) //duplicates in Zero and Succ
  def +(that: Nat): Nat
  def -(that: Nat): Nat
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new Error("0.predecessor")
  //def successor = new Succ(this)
  def +(that: Nat) = that
  def -(that: Nat) = if (that.isZero) this else throw new Error("negative number")
}

class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor = n //class Succ represents n+1
  //def successor = new Succ(this)
  def +(that: Nat) = new Succ(n + that)
  def -(that: Nat) = if (that.isZero) this else n - that.predecessor
}