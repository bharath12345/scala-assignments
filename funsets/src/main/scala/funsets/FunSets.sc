package funsets

import common._

/**
 * 2. Purely Functional Sets.
 */
object FunSets2 {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
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
		
	
	/**********************************************************************************************************/
	def twoAndFive: Set = x => x == 2 || x == 5
                                                  //> twoAndFive: => Int => Boolean
	twoAndFive(5)                             //> res3: Boolean = true
	twoAndFive(6)                             //> res4: Boolean = false
	
	
  /***********************************************************************************************************
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: Set, t: Set): Set = x => s(x) || t(x)
                                                  //> union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  val y = union(singletonSet(1), singletonSet(2)) //> y  : Int => Boolean = <function1>
  contains(y, 0)                                  //> res5: Boolean = false
  contains(y, 1)                                  //> res6: Boolean = true
  contains(y, 2)                                  //> res7: Boolean = true
  contains(y, 3)                                  //> res8: Boolean = false


  /**************************************************************************************************************
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
  def intersect(s: Set, t: Set): Set = x => s(x) && t(x)
                                                  //> intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  val z = intersect(singletonSet(1), singletonSet(2))
                                                  //> z  : Int => Boolean = <function1>
  contains(y, 0)                                  //> res9: Boolean = false
  contains(y, 1)                                  //> res10: Boolean = true
  contains(y, 2)                                  //> res11: Boolean = true
  contains(y, 3)                                  //> res12: Boolean = false

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = ???             //> diff: (s: Int => Boolean, t: Int => Boolean)Int => Boolean

  /**
   * Returns the subset of `s` for which `p` holds.
   */
  def filter(s: Set, p: Int => Boolean): Set = ???//> filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000                                //> bound  : Int = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (???) ???
      else if (???) ???
      else iter(???)
    }
    iter(???)
  }                                               //> forall: (s: Int => Boolean, p: Int => Boolean)Boolean

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: Set, p: Int => Boolean): Boolean = ???
                                                  //> exists: (s: Int => Boolean, p: Int => Boolean)Boolean

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
  def map(s: Set, f: Int => Int): Set = ???       //> map: (s: Int => Boolean, f: Int => Int)Int => Boolean

  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }                                               //> toString: (s: Int => Boolean)String

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }                                               //> printSet: (s: Int => Boolean)Unit
}