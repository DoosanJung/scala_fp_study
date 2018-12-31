#!/usr/bin/env scala
/*
iteration through collections
*/

//prints out all files in the current directory
//filesHere is an Array[File]
//file's type is `File`
val filesHere = (new java.io.File(".")).listFiles
for (file <- filesHere) //a generator
  println(file)

//ranges
for (i <- 1 to 4)
  println("Iteration " + i)

for (i <- 1 until 4)
  println("Iteration (until) " + i)

//Not common in Scala
for (i <- 0 to filesHere.length -1)
  println(filesHere(i))

/*
filtering.
Sometimes you do not want to iterate through a collection in its 
entirety. You want to filter it down to some subset
*/

for (file <- filesHere if file.getName.endsWith(".scala"))
  println(file)

//alteratively - imperative style
for (file <- filesHere)
  if (file.getName.endsWith(".scala"))
    println(file)

//multiple filters
for (file <- filesHere
  if file.isFile
  if file.getName.endsWith(".scala")
) println(file)

/*
nested iteration.
*/
//result type of the fileLines method is Iterator[String]
def fileLines(file: java.io.File) = 
  scala.io.Source.fromFile(file).getLines().toList

def grep(pattern: String) = 
  for (file <- filesHere
    if file.getName.endsWith(".scala");
    line <- fileLines(file)
    if line.trim.matches(pattern)
  ) println(file + ": " + line.trim)

grep(".*gcd.*")

/*
mid-stream variable bindings
*/
def grepBinding(pattern: String) = 
  for {file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)

    //mid-stream variabel bindings
    trimmed = line.trim
    if trimmed.matches(pattern)
  } println(file + ": " + line.trim)

grep(".*gcd.*")

/*
producing a new collection
=> for clauses yield body
*/
def scalaFiles = 
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
  } yield file

//transforming an Array[File] to Array[Int] with a for.
val forLineLengths = 
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(".*for.*")
  } yield trimmed.length


