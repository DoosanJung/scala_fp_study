object Loops {
  //not a best way ...
  def power(x: Double, exp: Int): Double = {
    var r = 1.0
    var i = exp
    while (i > 0) {r = r * x; i = i - 1}
    r
  }
  val twoFour = power(2,4)

  //emulate the WHILE function
  //condition and command should be passed by name
  def WHILE(condition: => Boolean)(command: => Unit): Unit =
    if (condition) {
      command
      WHILE(condition)(command) //tail-recursive
    }
    else ()

  //emulate the REPEAT function
  def REPEAT(command : => Unit)(condition : => Boolean): Unit = {
    command
    if (condition) ()
    else REPEAT(command)(condition)
  }

  //using emulated WHILE function
  def powerWhile(x: Double, exp: Int): Double = {
    var r = 1.0
    var i = exp
    WHILE(i > 0){r = r * x; i = i - 1}
    r
  }
  val twoFourWhile = powerWhile(2,4)

  //For-Loops
  val forLoops: Unit = for (i <- 1 until 3; j <- "abc") println(i + " " + j)
  val forLoopsTranslated: Unit = (1 until 3) foreach (i => "abc" foreach (j => println(i + " " + j)))
}
Loops.twoFour
Loops.twoFourWhile
