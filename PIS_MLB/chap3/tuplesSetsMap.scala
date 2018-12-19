#!/usr/bin/env scala

/*
TUPLE.
Like lists, tuples are immutable, but unlike lists, tuples can contain different types of elements
*/

val pair = (99, "Luftballons")
println(pair._1)
println(pair._2)

//you can’t access the elements of a tuple like the elements of a list, for example, with “pair(0)”
//The reason is that a list’s apply method always returns the same type, 
//but each element of a tuple may be a different type: _1 can have one result type, _2 another, and so on

/*
SETS
There’s a base Set trait in package scala.collection, and two subtrait Sets: 
a mutable Set in scala.collection.mutable and an immutable one in scala.collection.immutable
*/

//The default way to create a set
//The Scala compiler infers jetSet’s type to be the immutable Set[String]
var jetSet = Set("Boeing", "Airbus") //an immutable set containing the two strings
jetSet += "Lear"
println(jetSet.contains("Cessna"))

//a mutable set
import scala.collection.mutable.Set
val movieSet = Set("Hitch", "Poltergeist")
movieSet += "Shrek"
println(movieSet)

//an explicit set class
import scala.collection.immutable.HashSet
val hashSet = HashSet("Tomatoes", "Chilies")
println(hashSet + "Coriander")


/*
MAPS
There’s a base Map trait in package scala.collection, and two subtrait Maps: 
a mutable Map in scala.collection.mutable and an immutable one in scala.collection.immutable
*/

//an immutable map
//no import is necessary, as immutable is the default map
val romanNumeral = Map(
    1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V"
  )
println(romanNumeral(4))

//a mutable map
import scala.collection.mutable.Map
val treasureMap = Map[Int, String]() // key:Int, value:String
treasureMap += (1 -> "Go to island.") //add key,value to the map, actually calling a method named -> on an integer passing in a string
treasureMap += (2 -> "Find big X on ground.")
treasureMap += (3 -> "Dig.")
println(treasureMap)
println(treasureMap(2))
//This -> method, which you can invoke on any object in a Scala program, returns a two-element tuple containing the key and value.
//The explicit type parameterization, “[Int, String]”, is required in Listing 3.7 
//because without any values passed to the factory method, the compiler is unable to infer the map’s type parameters.

val anotherMutableTreasureMap = Map(
    1 -> "a", 2 -> "b", 3 -> "c"
  )
println(anotherMutableTreasureMap)
