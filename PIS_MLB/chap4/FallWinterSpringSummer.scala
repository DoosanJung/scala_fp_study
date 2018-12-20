/*
Scala provides a trait, scala.Application, that can save you some finger typing
To use the trait, you first write “extends App” after the name of your singleton object.
(Application has been deprecated from scala 2.9, probably it has been deleted in scala 2.11)

Then instead of writing a main method, you place the code you would have put in the main method 
directly between the curly braces of the singleton object. "a primary constructor"
*/

import ChecksumAccumulator.calculate

object FallWinterSpringSummer extends App {
  for (season <- List("fall", "winter", "spring"))
    println(season +": "+ calculate(season))
}

/*
CAUTION! SHORTCOMINGS!
1. First, you can’t use this trait if you need to access command-line arguments, because the args array isn’t available.
2. Second, because of some restrictions in the JVM threading model, you need an explicit main method if your program is multi-threaded.
3. Finally, some implementations of the JVM do not optimize the initialization code of an object which is executed by the Application trait.

Use trait APP only if 
1. no command-line args
2. single-threaded
*/
