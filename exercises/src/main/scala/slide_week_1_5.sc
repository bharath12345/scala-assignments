object slide_week_1_5 {

	def sqrt(x: Double): Double = {
		def sqrtIter(guess: Double): Double = {
			if(isGoodEnough(guess)) guess
			else sqrtIter(improve(guess))
		}
		
		def isGoodEnough(guess: Double): Boolean = {
			math.abs(x - guess) < 0.001
		}
		
		def improve(guess: Double): Double = {
			val a = (guess + (x/guess))/2
			//println(a)
			a
		}
		
		//sqrtIter(1.5)
	}
	
	sqrt(2)

}