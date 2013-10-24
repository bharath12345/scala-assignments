object slide_week_5_2 {

  def last[T](xs: List[T]): T = xs match {
    case List() => throw new Error("zero len list")
    case List(x) => x
    case y :: ys => last(ys)
  }                                               //> last: [T](xs: List[T])T
  
  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("zero len list")
    case List(x) => List[T]()
    case y :: ys => y :: init(ys)
  }                                               //> init: [T](xs: List[T])List[T]
  
  val x = List(1, 2, 3)                           //> x  : List[Int] = List(1, 2, 3)
  last(x)                                         //> res0: Int = 3
  init(x)                                         //> res1: List[Int] = List(1, 2)
  

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case List(x) => x :: ys
    case z :: zs => z :: concat(zs, ys)
  }                                               //> concat: [T](xs: List[T], ys: List[T])List[T]

	val y = List(8,9)                         //> y  : List[Int] = List(8, 9)
	concat(x, y)                              //> res2: List[Int] = List(1, 2, 3, 8, 9)
	
	def reverse[T](xs: List[T]): List[T] = xs match {
	  case List() => xs
	  case List(x) => xs
	  case y :: ys => reverse(ys) :+ y
	}                                         //> reverse: [T](xs: List[T])List[T]
	
	reverse(y)                                //> res3: List[Int] = List(9, 8)
	
	def insert(x: Int, xs: List[Int]): List[Int] = xs match {
	  case List() => List(x)
	  case y :: ys => if(x <= y) x :: xs else y :: insert(x, ys)
	}                                         //> insert: (x: Int, xs: List[Int])List[Int]
	
	def isort(xs: List[Int]): List[Int] = xs match {
	  case List() => List()
	  case y :: ys => insert(y, isort(ys))
	}                                         //> isort: (xs: List[Int])List[Int]
	

}