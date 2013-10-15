object slide_week_2_4 {
  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4
  
  def isCloseEnough(x: Double, y: Double) =
    math.abs((x - y) / x) / x < tolerance         //> isCloseEnough: (x: Double, y: Double)Boolean
  
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      println(next)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double
  
  def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1.0)
                                                  //> sqrt: (x: Double)Double
  
  sqrt(10)                                        //> 5.5
                                                  //| 3.659090909090909
                                                  //| 3.196005081874647
                                                  //| 3.16245562280389
                                                  //| 3.162277665175675
                                                  //| res0: Double = 3.162277665175675
}