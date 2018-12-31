#!/usr/bin/env scala
/*
Scala’s if is an expression that results in a value
*/

//imperative style
var filename = "default.txt"
if (!args.isEmpty)
  filename = args(0)
println(filename)

//FP stype
val filename = if (!args.isEmpty) args(0) else "default.txt"
println(filename)

//equatinoal reasoning
//any time you are about to write the variable name, you could instead write the expression
println(if (!args.isEmpty) args(0) else "default.txt")

