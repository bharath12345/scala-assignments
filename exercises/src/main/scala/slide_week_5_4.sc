object slide_week_5_4 {

  def squareListOne(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => (y * y) :: squareListOne(ys)
  }                                               //> squareListOne: (xs: List[Int])List[Int]
  
  def squareListTwo(xs: List[Int]): List[Int] =
    xs map (x => x * x)                           //> squareListTwo: (xs: List[Int])List[Int]
    
  val a = List(1, 2, 3)                           //> a  : List[Int] = List(1, 2, 3)
  squareListOne(a)                                //> res0: List[Int] = List(1, 4, 9)
  squareListTwo(a)                                //> res1: List[Int] = List(1, 4, 9)
}