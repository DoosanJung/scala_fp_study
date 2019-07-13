import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Sequences {
  //Lists
  //Lists support fast addition and removal of items to the beginning of the list,
  //but they do not provide fast access to arbitrary indexes because the implementation
  //must iterate through the list linearly.
  val colors = List("red", "blue", "green")
  val h = colors.head
  val t = colors.tail

  //arrays
  //Arrays allow you to hold a sequence of elements and efficiently access an
  //element at an arbitrary position, both to get or update the element, with a
  //zero-based index
  val fiveInts = new Array[Int](5)
  //when you know the element values
  val fiveToOne = Array(5, 4, 3, 2, 1)
  fiveInts(0) = fiveToOne(4)

  //ListBuffer
  val listBuf = new ListBuffer[Int]
  listBuf += 1 //append
  listBuf += 2
  3 +=: listBuf //prepend

  //array buffers
  val arrBuf = new ArrayBuffer[Int]()
  arrBuf += 12
  arrBuf += 15

  //strings
  def hasUpperCase(s: String) = s.exists(_.isUpper)

}

Sequences.fiveInts // changed to have 1 in index 0
Sequences.listBuf //scala.collection.mutable.ListBuffer
Sequences.listBuf.toList //List
Sequences.arrBuf.length
Sequences.arrBuf(0)
Sequences.hasUpperCase("Robert Frost")
Sequences.hasUpperCase("e e cummings")
