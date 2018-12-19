#!/usr/bin/env scala

//in the imperative style
def printArgsImperative(args: Array[String]): Unit = {
  var i = 0
  while (i < args.length) {
    println(args(i))
    i += 1 
  }
}

printArgsImperative(args)

//more functional style
def printArgsFP1(args: Array[String]): Unit = {
  args.foreach(println)
}

printArgsFP1(args)

//even more functional stype - no side effect of printing stdout
def formatArgs(args: Array[String]) = args.mkString("\n")

println(formatArgs(args))

//testing is easy
val res = formatArgs(Array("zero", "one", "two"))
assert(res == "zero\none\ntwo")

