object slide_week_5_5 {

  def inner[T](): List[List[T]] = {
    return Nil
  }                                               //> inner: [T]()List[List[T]]

  def pack[T](charList: List[T], acc: List[List[T]] = List.empty): List[List[T]] = charList match {
    case Nil => acc
    case xs => {
      val (same, rest) = xs.span { _ == xs.head }
      pack(rest, acc :+ same)
    }
  }                                               //> pack: [T](charList: List[T], acc: List[List[T]])List[List[T]]
  
  def innerEncode[T](listOfLists: List[List[T]], acc: List[(Any, Int)] = List.empty ): List[(Any, Int)] = listOfLists match {
    case Nil => acc
    case x :: xs => {
      val pair = (x(0), x.size)
      innerEncode(xs, acc :+ pair)
    }
  }                                               //> innerEncode: [T](listOfLists: List[List[T]], acc: List[(Any, Int)])List[(Any
                                                  //| , Int)]
  
  def encode[T](charList: List[T], acc: List[(Any, Int)] = List.empty): List[(Any, Int)] = charList match {
    case Nil => acc
    case xs => {
    	val listOfLists = pack(charList)
    	innerEncode(listOfLists)
    }
  }                                               //> encode: [T](charList: List[T], acc: List[(Any, Int)])List[(Any, Int)]

  val testList = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> testList  : List[String] = List(a, a, a, b, c, c, a)
  pack(testList)                                  //> res0: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  encode(testList)                                //> res1: List[(Any, Int)] = List((a,3), (b,1), (c,2), (a,1))
  
}