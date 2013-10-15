import scala.annotation.tailrec

object slide_week_2_2 {

	def product(f: Long => Long, x: Int, y: Int): Long = {
		@tailrec def productIter(f: Long => Long, x: Int, y: Int, value: Long): Long = {
			if(x >= y) return value
			productIter(f, x + 1, y, value * f(x))
		}
		productIter(f, x, y, 1)
	}                                         //> product: (f: Long => Long, x: Int, y: Int)Long
	
	def productInts(x: Int, y: Int) = product(x => x, x, y)
                                                  //> productInts: (x: Int, y: Int)Long
	productInts(1, 5)                         //> res0: Long = 24
	
	
	def productSquares(x: Int, y: Int) = product(x => x * x, x, y)
                                                  //> productSquares: (x: Int, y: Int)Long
	productSquares(1, 5)                      //> res1: Long = 576
	
	
	def factorial(x: Int) = productInts(1, x+1)
                                                  //> factorial: (x: Int)Long
	factorial(5)                              //> res2: Long = 120
}