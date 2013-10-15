import scala.annotation.tailrec

object slide_week_2_1 {

		def factorial(x: Int): Long = {
			@tailrec def factIter(x: Int, value: Long): Long = {
				if(x == 0) return value
				factIter(x - 1, value * x)
			}
			factIter(x, 1)
		}                                 //> factorial: (x: Int)Long
		
		factorial(5)                      //> res0: Long = 120
}