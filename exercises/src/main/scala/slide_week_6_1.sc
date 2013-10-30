object slide_week_6_1 {

  def scalaProduct(xs: Vector[Int], ys: Vector[Int]): Int = {
    (xs zip ys).map(xy => xy._1 * xy._2).sum
  }                                               //> scalaProduct: (xs: Vector[Int], ys: Vector[Int])Int
  
  val a = Vector(1, 2)                            //> a  : scala.collection.immutable.Vector[Int] = Vector(1, 2)
  val b = Vector(2, 1)                            //> b  : scala.collection.immutable.Vector[Int] = Vector(2, 1)
  
  scalaProduct(a, b)                              //> res0: Int = 4
  
  def isPrime(n: Int): Boolean = (2 to n/2) forall (d => n % d != 0)
                                                  //> isPrime: (n: Int)Boolean
  
  isPrime(7)                                      //> res1: Boolean = true
  isPrime(10)                                     //> res2: Boolean = false
}