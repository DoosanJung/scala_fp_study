#!/usr/bin/env scala
//for an immutable sequence of objects that share the same type, you can use Scala's List class
//always immutable

//create a list
val onTwoThree = List(1,2,3)

//list concatenation
val oneTwo = List(1,2)
val threeFour = List(3,4)
val oneTwoThreeFour = oneTwo ::: threeFour
println(oneTwo +" and "+ threeFour +" were not mutated.")
println("Thus, "+ oneTwoThreeFour +" is a new list.")

//cons
val twoThree = List(2,3)
val oneTwoThree = 1 :: twoThree
println(oneTwoThree)

println("1 :: List (2,3) == 1 :: 2 :: 3 :: Nil  ? " + (oneTwoThree == 1 :: 2 :: 3 :: Nil))

/* If a method is used in operator notation, such as a * b, the method is invoked on the left operand, as in a.*(b)
—unless the method name ends in a colon. If the method name ends in a colon, the method is invoked on the right operand.
Therefore, in 1 :: twoThree, the :: method is invoked on twoThree, passing in 1, like this: twoThree.::(1)
*/

/* NOTE: 'append' operation rarely used*/
//because the time it takes to append to a list grows linearly with the size of the list, whereas prepending with :: takes constant time

//List methods
List() // empty list
List("Cool", "tools","rule") //create a new List[String]

val thrill = "Will" :: "fill" :: "until" :: Nil

println(thrill(2)) //"until"

println(thrill.count(s => s.length == 4)) //Counts the number of string elements in thrill that have length 4 (returns 2)

println(thrill.drop(2)) //Returns the thrill list without its first 2 elements (returns List("until"))

println(thrill.dropRight(2)) //Returns the thrill list without its rightmost 2 elements (returns List("Will"))

println(thrill.exists(s => s == "until")) //Determines whether a string element exists in thrill that has the value "until" (returns true)

println(thrill.filter(s => s.length == 4)) //Returns a list of all elements, in order, of the thrill list that have length 4 (returns List("Will", "fill"))

println(thrill.forall(s => s.endsWith("l"))) //Indicates whether all elements in the thrill list end with the letter "l" (returns true)

thrill.foreach(print)

println(thrill.head) //Returns the first element in the thrill list (returns "Will")

println(thrill.init) //Returns a list of all but the last element in the thrill list (returns List("Will", "fill"))

println(thrill.isEmpty) //Indicates whether the thrill list is empty (returns false)

println(thrill.last) //Returns the last element in the thrill list (returns "until")

println(thrill.length) //Returns the number of elements in the thrill list (returns 3)

println(thrill.map(s => s + "y")) //Returns a list resulting from adding a "y" to each string element in the thrill list (returns List("Willy", "filly", "untily"))

println(thrill.mkString(",")) //Makes a string with the elements of the list (returns "Will, fill, until")
/*
println(thrill.remove(s => s.length == 4)) //Returns a list of all elements, in order, of the thrill list except those that have length 4 (returns List("until"))
*/
println(thrill.reverse) //Returns a list containing all elements of the thrill list in reverse order (returns List("until", "fill", "Will"))
/*
println(thrill.sort((s,t) => s.charAt(0).toLower < t.charAt(0).toLower)) //Returns a list containing all elements of the thrill list in alphabetical order of the first character lowercased (returns List("fill", "until", "Will"))
*/
println(thrill.tail) //Returns the thrill list minus its first element

