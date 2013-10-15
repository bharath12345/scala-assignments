object slide_week_4_1 {

	trait List[T] {
		def isEmpty: Boolean
		def head: T
		def tail: List[T]
	}

	class Cons[T](val head: T, val tail: List[T]) {
		def isEmpty = false
		
		def nth(n: Int, list: List[T]): T = {
			
			def nthIter(n: Int, list: List[T], counter: Int): T = {
				if(counter == n) list.head
				else nthIter(n, list, counter + 1)
			}
			
			nthIter(n, list, 0)
		}
	}
}