object slide_week_5_6 {

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())((y, ys) => f(y) :: ys)

  val l = List(1, 2, 3)
  //mapFun(Int)(l, (x => (1)))

  def addOne(x: Int): Int = x + 1

  mapFun(l, addOne)

  def lengthFun[T](xs: List[T]): Int = {
    var z = 0
    (xs foldRight 0)((y, ys) => (z += 1))
    z
  }

}