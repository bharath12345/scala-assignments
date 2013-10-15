import scala.annotation.tailrec

object slide_week_2_3 {
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    @tailrec def loop(a: Int, acc: Int): Int = {
      if (a>b) acc
      else loop(a+1, acc + f(a))
    }
    loop(a, 0)
  }                                               //> sum: (f: Int => Int)(a: Int, b: Int)Int
  def sumInts = sum(x => x)(0, 4)                 //> sumInts: => Int
  sumInts                                         //> res0: Int = 10
  
  def sumSquares = sum(x => x * x)(0, 3)          //> sumSquares: => Int
  sumSquares                                      //> res1: Int = 14
}