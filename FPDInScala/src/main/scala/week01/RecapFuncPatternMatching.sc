abstract class JSON
case class JSeq (elems: List[JSON]) extends JSON
case class JObj (bindings: Map[String, JSON]) extends JSON
case class JNum (num: Double) extends JSON
case class JStr (str: String) extends JSON
case class JBool(b: Boolean) extends JSON
case object JNull extends JSON

val data = JObj(Map(
  "firstName" -> JStr("John"),
  "lastName" -> JStr("Smith"),
  "address" -> JObj(Map(
    "streetAddress" -> JStr("21 2nd Street"),
    "state" -> JStr("NY"),
    "postalCode" -> JNum(10021)
  )),
  "phoneNumbers" -> JSeq(List(
    JObj(Map(
    "type" -> JStr("home"), "number" -> JStr("212 555-1234")
    )),
    JObj(Map(
    "type" -> JStr("fax"), "number" -> JStr("646 555-4567")
    ))
  ))
))

def show(json: JSON): String = json match {
  case JSeq(elems) =>
    "[" + (elems map show mkString ", ") + "]"

  case JObj(bindings) =>
    val assocs = bindings map {
      //what's the type of this func? {case (k, v)} => k + ": " + v}
      //mape expects "JBinding => String" i.e. scala.Function1[JBinding, String]
      //type JBinding = (Stirng, Json)
      /*
      * new Function1[JBinding, String]{
      *   def apply(x: JBinding) = x match {
      *     case (k, v)} => k + ": " + v
      *   }
      * }
      * */
      case (key, value) => "\"" + key + "\": " + show(value)
      }
    "{" + (assocs mkString ", ") + "}"

  case JNum(num) => num.toString
  case JStr(str) => '\"' + str + '\"'
  case JBool(b) => b.toString
  case JNull => "null"
}

show(data)

object test {
  val f: String => String = { case "ping" => "pong"}

  //ParticalFunction has isDefinedAt method
  val f2: PartialFunction[String, String] = { case "ping" => "pong"}
  /*
  * { case "ping" => "pong"} expands in scala compiler like this...
  * new PartialFunction[String, String] {
  *   def apply(x: String) = x match {
  *     case "ping" => "pong"
  *   }
  *   def isDefinedAt(x: String) = x match {
  *     case "ping" => true
  *     case _ => false //o.w. return false
  *   }
  * }
  * */

  val f3: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: y :: rest => "two"
  }
}

test.f("ping")
//below throws a scala.MatchError
//test.f("abc")
test.f2.isDefinedAt("ping")
test.f2.isDefinedAt("abc")
test.f3.isDefinedAt(List(1, 2, 3))
