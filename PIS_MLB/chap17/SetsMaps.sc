import scala.collection.immutable.{TreeMap, TreeSet}
import scala.collection.mutable

object SetsMaps {
  /*By default when you write “ Set ” or “ Map ” you get an immutable object.*/
  //mutable
  val mutableSet = mutable.Set(1, 2, 3)

  //using sets
  val text = "See Spot run. Run, Spot. Run!"
  val wordsArray = text.split("[ !,.]+")
  val words = mutable.Set.empty[String]
  for (word <- wordsArray)
    words += word.toLowerCase

  //immutable
  val numsSet = Set(1, 2, 3)

  //mutable
  val words2 = mutable.Set.empty[String]

  //using maps
  val numsMap = Map("i" -> 1, "ii" -> 2)
  val map = mutable.Map.empty[String, Int]
  map("hello") = 1
  map("there") = 2

  //putting it all together
  def countWords(text: String): mutable.Map[String, Int] = {
    val counts = mutable.Map.empty[String, Int]

    for (raw <- text.split("[ !,.]+")) {
      val word = raw.toLowerCase
      val oldCount =
        if (counts.contains(word)) counts(word)
        else 0

      counts += (word -> (oldCount + 1))
    }
    counts
  }

  val countResult = countWords(text)

  //sorted sets and maps
  val ts = TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
  val cs = TreeSet("f", "u", "n")
  var tm = TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')
  tm += (2 -> 'x') //var => can be “updated” with a += operation, even though it is immutable

  //initializing collections
  //val stuff = mutable.Set(42) and stuff += "somestring" will throw an error because element type was Int
  val stuff = mutable.Set[Any](42)

  val colors = List("blue", "yellow", "red", "green")
  //val treeSet = TreeSet(colors) will throw an error because it could not find the implicit value
  val treeSet = TreeSet[String]() ++ colors //instead, create an empty TreeSet[String] and add to it

  //tuples
  //Unlike an array or list, a tuple can hold objects with different types
  def longestWord(words: Array[String]) = {
    var word = words(0)
    var idx = 0
    for (i <- 1 until words.length)
      if (words(i).length > word.length) {
        word = words(i)
        idx = i
      }
    (word, idx)
  }

  val longest = longestWord("The quick brown fox".split(" "))
  val (elem1, elem2) = longest
}

SetsMaps.mutableSet //res0: scala.collection.mutable.Set[Int] = Set(1, 2, 3)
SetsMaps.wordsArray //res1: Array[String] = Array(See, Spot, run, Run, Spot, Run)
SetsMaps.words //res2: scala.collection.mutable.Set[String] = Set(see, run, spot)
SetsMaps.numsSet + 5 //res3: scala.collection.immutable.Set[Int] = Set(1, 2, 3, 5)
SetsMaps.numsSet - 3 //res4: scala.collection.immutable.Set[Int] = Set(1, 2)
SetsMaps.numsSet ++ List(5, 6) //res5: scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 3)
SetsMaps.numsSet -- List(1, 2) //res6: scala.collection.immutable.Set[Int] = Set(3)
SetsMaps.numsSet & Set(1, 3, 5, 7) //res7: scala.collection.immutable.Set[Int] = Set(1, 3)
SetsMaps.numsSet.size //res8: Int = 3
SetsMaps.numsSet.contains(3) //res9: Boolean = true
SetsMaps.words2 += "true" //res10: SetsMaps.words2.type = Set(true)
SetsMaps.words2 -= "the" //res11: SetsMaps.words2.type = Set(true)
SetsMaps.words2 ++= List("do", "re", "mi") //res12: SetsMaps.words2.type = Set(true, re, do, mi)
SetsMaps.words2 --= List("do", "re") //res13: SetsMaps.words2.type = Set(true, mi)
SetsMaps.words2.clear
SetsMaps.words2 //res15: scala.collection.mutable.Set[String] = Set()
SetsMaps.countResult //res16: scala.collection.mutable.Map[String,Int] = Map(spot -> 2, see -> 1, run -> 3)

SetsMaps.numsMap + ("vi"-> 6) //res17: scala.collection.immutable.Map[String,Int] = Map(i -> 1, ii -> 2, vi -> 6)
SetsMaps.numsMap  - "ii" //res18: scala.collection.immutable.Map[String,Int] = Map(i -> 1)
SetsMaps.numsMap ++ List("iii" -> 3, "v" -> 5)
//res19: scala.collection.immutable.Map[String,Int] = Map(i -> 1, ii -> 2, iii -> 3, v -> 5)
SetsMaps.numsMap -- List("i", "ii") //res20: scala.collection.immutable.Map[String,Int] = Map()
SetsMaps.numsMap.size //res21: Int = 2
SetsMaps.numsMap.contains("ii") //res22: Boolean = true
SetsMaps.numsMap("ii") //res23: Int = 2
SetsMaps.numsMap.keys //res24: Iterable[String] = Set(i, ii)
SetsMaps.numsMap.keySet //res25: scala.collection.immutable.Set[String] = Set(i, ii)
SetsMaps.numsMap.values //res26: Iterable[Int] = MapLike.DefaultValuesIterable(1, 2)
SetsMaps.numsMap.isEmpty //res27: Boolean = false

SetsMaps.tm //res28: scala.collection.mutable.TreeMap[Int,Char] = TreeMap(1 -> x, 2 -> x, 3 -> x, 4 -> x)

SetsMaps.treeSet //res29: scala.collection.immutable.TreeSet[String] = TreeSet(blue, green, red, yellow)
SetsMaps.treeSet.toList //res30: List[String] = List(blue, green, red, yellow)
SetsMaps.treeSet.toArray //res31: Array[String] = Array(blue, green, red, yellow)
//The elements in the list produced by invoking toList on the TreeSet are in alphabetical order.

SetsMaps.longest //res32: (String, Int) = (quick,1)
SetsMaps.longest._1 //res33: String = quick
SetsMaps.longest._2 //res34: Int = 1
SetsMaps.elem1 //res35: String = quick
SetsMaps.elem2 //res36: Int = 1











