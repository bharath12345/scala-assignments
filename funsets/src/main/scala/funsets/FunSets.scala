package funsets

import common._

/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   * Constructor takes a Integer
   */
  type Set = Int => Boolean

  /*********************************************************************************************************
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: Int => Boolean, elem: Int)Boolean
  contains(Set(11), 10)                           //> res0: Boolean = false
  contains(Set(10), 10)                           //> res1: Boolean = true


  /*********************************************************************************************************
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): Set = Set(elem)    //> singletonSet: (elem: Int)Int => Boolean
	singletonSet(10)                          //> res2: Int => Boolean = Set(10)
	
	def reverseSet(elem: Int): Set = x => x != elem
                                                  //> reverseSet: (elem: Int)Int => Boolean
	
	/**********************************************************************************************************/
	def twoAndFive: Set = x => x == 2 || x == 5
                                                  //> twoAndFive: => Int => Boolean
	twoAndFive(5)                             //> res3: Boolean = true
	twoAndFive(6)                             //> res4: Boolean = false
	
	def notTwo: Set = x => x != 2             //> notTwo: => Int => Boolean
	notTwo(1)                                 //> res5: Boolean = true
	notTwo(2)                                 //> res6: Boolean = false
	notTwo(3)                                 //> res7: Boolean = true
	
	def notThree: Set = reverseSet(3)         //> notThree: => Int => Boolean
	val n = notThree(1)                       //> n  : Boolean = true
	notThree(2)                               //> res8: Boolean = true
	notThree(3)                               //> res9: Boolean = false
	n                                         //> res10: Boolean = true
	
	def reverseFullSet(s: Set): Set = x => !s(x)
                                                  //> reverseFullSet: (s: Int => Boolean)Int => Boolean
  var b = reverseFullSet(twoAndFive)              //> b  : Int => Boolean = <function1>
  b(1)                                            //> res11: Boolean = true
  b(2)                                            //> res12: Boolean = false
  b(3)                                            //> res13: Boolean = true
  b(4)                                            //> res14: Boolean = true
  b(5)                                            //> res15: Boolean = false
  
  def addToSet(s: Set, i: Int): Set = x => s(x) || (x == i)
                                                  //> addToSet: (s: Int => Boolean, i: Int)Int => Boolean
  
  var g = addToSet(twoAndFive, 6)                 //> g  : Int => Boolean = <function1>
  g(1)                                            //> res16: Boolean = false
  g(2)                                            //> res17: Boolean = true
  g(3)                                            //> res18: Boolean = false
  g(4)                                            //> res19: Boolean = false
  g(5)                                            //> res20: Boolean = true
  g(6)                                            //> res21: Boolean = true
  g(7)                                            //> res22: Boolean = false
	
	
  /***********************************************************************************************************
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: Set, t: Set): Set = x => s(x) || t(x)
                                                  //> union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  val y = union(singletonSet(1), singletonSet(2)) //> y  : Int => Boolean = <function1>
  contains(y, 0)                                  //> res23: Boolean = false
  contains(y, 1)                                  //> res24: Boolean = true
  contains(y, 2)                                  //> res25: Boolean = true
  contains(y, 3)                                  //> res26: Boolean = false


  /**************************************************************************************************************
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
  def intersect(s: Set, t: Set): Set = x => s(x) && t(x)
                                                  //> intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  val z = intersect(twoAndFive, singletonSet(2))  //> z  : Int => Boolean = <function1>
  contains(z, 0)                                  //> res27: Boolean = false
  contains(z, 1)                                  //> res28: Boolean = false
  contains(z, 2)                                  //> res29: Boolean = true
  contains(z, 3)                                  //> res30: Boolean = false
  

  /**************************************************************************************************************
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = x => {
  	val rev = reverseFullSet(t)
  	s(x) && rev(x)
  }                                               //> diff: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  
  val a = diff(twoAndFive, singletonSet(2))       //> a  : Int => Boolean = <function1>
  contains(a, 2)                                  //> res31: Boolean = false
  contains(a, 3)                                  //> res32: Boolean = false
  contains(a, 4)                                  //> res33: Boolean = false
  contains(a, 5)                                  //> res34: Boolean = true
  contains(a, 6)                                  //> res35: Boolean = false

  /**************************************************************************************************************
   * Returns the subset of `s` for which `p` holds.
   */
  def filter(s: Set, p: Int => Boolean): Set = x => {
		s(x) && p(x)
  }                                               //> filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean
   
  val d = filter(twoAndFive, notTwo)              //> d  : Int => Boolean = <function1>
  contains(d, 2)                                  //> res36: Boolean = false
  contains(d, 3)                                  //> res37: Boolean = false
  contains(d, 4)                                  //> res38: Boolean = false
  contains(d, 5)                                  //> res39: Boolean = true
  contains(d, 6)                                  //> res40: Boolean = false

  /**************************************************************************************************************
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 20                                  //> bound  : Int = 20

  /**************************************************************************************************************
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (!p(a)) false
      else iter(a+1)
    }
		iter(-bound)
  }                                               //> forall: (s: Int => Boolean, p: Int => Boolean)Boolean
  
  def wholeNumbers: Set = x => (x % 1) == 0       //> wholeNumbers: => Int => Boolean
  def evenNumbers: Set = x => (x / 2) == 0        //> evenNumbers: => Int => Boolean
  def oddNumbers: Set = x => (x % 2) == 1         //> oddNumbers: => Int => Boolean
  
  def inBounds: Set = x => x < bound || x > -bound//> inBounds: => Int => Boolean
  
  forall(inBounds, wholeNumbers)                  //> res41: Boolean = true
  forall(inBounds, evenNumbers)                   //> res42: Boolean = false
  forall(inBounds, oddNumbers)                    //> res43: Boolean = false
  

  /**************************************************************************************************************
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: Set, p: Int => Boolean): Boolean = {
  	!forall(s, x => !p(x))
  }                                               //> exists: (s: Int => Boolean, p: Int => Boolean)Boolean
  
  exists(inBounds, wholeNumbers)                  //> res44: Boolean = true
  exists(inBounds, evenNumbers)                   //> res45: Boolean = true
  exists(inBounds, oddNumbers)                    //> res46: Boolean = true

  /**************************************************************************************************************
   * Returns a set transformed by applying `f` to each element of `s`.
   */
  def map(s: Set, f: Int => Int): Set = (e:Int) => exists(s, (x => f(x) == e))
                                                  //> map: (s: Int => Boolean, f: Int => Int)Int => Boolean
  
  def double(x: Int): Int = 2 * x                 //> double: (x: Int)Int
  val k = map(twoAndFive, double)                 //> k  : Int => Boolean = <function1>
	contains(k, 1)                            //> res47: Boolean = false
	contains(k, 2)                            //> res48: Boolean = true
	contains(k, 3)                            //> res49: Boolean = false
	contains(k, 4)                            //> res50: Boolean = true
	contains(k, 5)                            //> res51: Boolean = false
	contains(k, 6)                            //> res52: Boolean = true
	contains(k, 7)                            //> res53: Boolean = false
	contains(k, 8)                            //> res54: Boolean = true
	contains(k, 9)                            //> res55: Boolean = false
	contains(k, 10)                           //> res56: Boolean = true
	contains(k, 11)                           //> res57: Boolean = false
	
	printSet(k)                               //> {-20,-18,-16,-14,-12,-10,-8,-6,-4,-2,0,2,4,6,8,10,12,14,16,18,20}
	

  /**************************************************************************************************************
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }                                               //> toString: (s: Int => Boolean)String

  /**************************************************************************************************************
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }                                               //> printSet: (s: Int => Boolean)Unit
}