#!/usr/bin/env scala
//for arg in args..
/*
arg is a val.
arg cannot be reassigned inside the body of the for expression.
for each element of the args array, a 'new' arg val will be created
and initialized to the element value
*/

for (arg <- args)
  println(arg)
