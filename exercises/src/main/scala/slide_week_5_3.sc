object slide_week_5_3 {

import math.Ordering

  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T])(lt: (T, T) => Boolean): List[T] = (xs, ys) match {
        case (xs1, Nil) => xs1
        case (Nil, xs1) => xs1
        case (a :: as, b :: bs) => {
          if(lt(a,b)) a :: merge(as, ys)(lt)
          else b :: merge(xs, bs)(lt)
        }
      }
      val (fst, snd) = xs splitAt n
      merge(msort(fst)(lt), msort(snd)(lt))(lt)
    }
  }                                               //> msort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]
  
  var x = List(3, 1, 6, 5, 9)                     //> x  : List[Int] = List(3, 1, 6, 5, 9)
  msort(x)((x: Int, y: Int) => x < y)             //> res0: List[Int] = List(1, 3, 5, 6, 9)
}