package recfun
import common._
import scala.collection.immutable._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(c == 0) 1
    else if(c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r -1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    var stack = new Stack[Char]();
    
    def traverse(subset: List[Char]): Boolean = {
      if(subset.isEmpty) true;
      else {
        val first = subset.head
        
        if(first == '(') {
          //println("push = " + first)
          stack = stack.push(first)
          //println("stack size = " + stack.length)
        } else if(first == ')') {
          //println("pop = " + first)
          if(stack.size == 0) return false
          else stack = stack.pop
        } else {
          //println("moving on = " + first)
        }
        
        //println("traversing...")
        if(!traverse(subset.tail)) false
          else true
      }
    }
    
    if(chars.isEmpty) true;
    else traverse(chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
    def traverse(moneyLeft: Int, coinsLeft: List[Int]): Int = {
      if(moneyLeft == 0) 
        return 1
      
      if((coinsLeft.isEmpty && moneyLeft >= 1) || moneyLeft < 0)
        return 0
      
      traverse(moneyLeft, coinsLeft.tail) + traverse(moneyLeft - coinsLeft.head, coinsLeft)
    }
    
    traverse(money, coins.sortWith(_.compareTo(_) < 0))
  }
}
