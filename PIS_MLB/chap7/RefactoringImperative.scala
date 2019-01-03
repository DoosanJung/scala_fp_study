#!/usr/bin/env scala
/*
First, invoking printMultiTable has a side effect: printing a multiplication table to the standard output.
The other telltale sign of the imperative style in printMultiTable is its while loop and vars. 
By contrast, the multiTable function uses vals, for expressions, helper functions, and calls to mkString.
*/

//returns a row as a sequence
def makeRowSeq(row:Int) = 
  for (col <- 1 to 10) yield {
    val prod = (row * col).toString
    val padding = " "*(4 - prod.length)
    padding + prod
  }

//returns a row as a string
def makeRow(row: Int) = makeRowSeq(row).mkString

//returns table as a stirng with one row per line
def multiTable() = {
  val tableSeq =  //a sequence of row string
    for (row <- 1 to 10)
      yield makeRow(row)

  tableSeq.mkString("\n")
}

println(multiTable())
