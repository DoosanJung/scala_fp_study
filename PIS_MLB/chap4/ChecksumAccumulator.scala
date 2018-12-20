//companion class of the singleton object
class ChecksumAccumulator {
  private var sum = 0
  def add(b: Byte) { sum += b }
  def checksum(): Int = ~(sum & 0xFF) + 1
}


//class’s companion object
import scala.collection.mutable.Map
        
object ChecksumAccumulator {
  private val cache = Map[String, Int]()
  
  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs) //the passed string key is mapped to the integer checksum value, 
                         //and this key- value pair is added to the cache map
      cs //ensures the checksum is the result of the method
    } 
}
