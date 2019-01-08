#!/usr/bin/env scala
//a method that determines whether a passed value is contained in a collection
/*
Using var: Having a var initialized to false, looping through the collection checking each item, 
and setting the var to true if you find what you are looking for
*/
def containsNeg(nums: List[Int]): Boolean = {
  var exists = false
  for (num <- nums)
    if (num < 0)
      exists = true
  exists
}

println(containsNeg(List(1,2,3,4)))
println(containsNeg(List(1,2,-3,4)))

def containsOdd(nums: List[Int]): Boolean = {
  var exists = false
  for (num <- nums)
    if (num % 2 == 1)
      exists = true
  exists
}

println(containsOdd(List(1,2,3)))
println(containsOdd(List(2,4,6,8,10)))

//more concisely, use the HOF 'exists'
/*
The exists method represents a control abstraction.
It is a special-purpose looping construct provided by the Scala library.
It is public in Scala's collections API, the code duplication it reduces is client code of that 
API.
*/
def containsNegConcise(nums: List[Int]) = nums.exists(_ < 0)

println(containsNegConcise(Nil))
println(containsNegConcise(List(0,-1,-2)))

def containsOddConcise(nums: List[Int]) = nums.exists(_ % 2 == 1)

println(containsOddConcise(Nil))
println(containsOddConcise(List(0,-2,-4)))

