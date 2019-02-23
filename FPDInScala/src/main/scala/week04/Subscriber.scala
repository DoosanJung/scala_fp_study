package week04

trait Subscriber {
  def handler(pub: Publisher)
}