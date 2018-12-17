#!/usr/bin/env scala
var i: Int  = 0
println("args.length: %d".format(args.length))
while (i < args.length) {
  println(args(i))
  i += 1
}
