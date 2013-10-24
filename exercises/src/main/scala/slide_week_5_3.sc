object slide_week_5_3 {

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (xs1, Nil) => xs1
        case (Nil, xs1) => xs1
        case (a :: as, b :: bs) => {
          if(a < b) a :: merge(as, ys)
          else b :: merge(xs, bs)
        }
      }
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }                                               //> msort: (xs: List[Int])List[Int]
  
  var x = List(3, 1, 6, 5, 9)                     //> x  : List[Int] = List(3, 1, 6, 5, 9)
  msort(x)                                        //> res0: List[Int] = List(1, 3, 5, 6, 9)
}