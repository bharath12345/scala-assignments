package patmat

import common._
import scala.collection.mutable.ListBuffer

/**
 * Assignment 4: Huffman coding
 *
 */
object Huffman {

  /**
   * A huffman code is represented by a binary tree.
   *
   * Every `Leaf` node of the tree represents one character of the alphabet that the tree can encode.
   * The weight of a `Leaf` is the frequency of appearance of the character.
   *
   * The branches of the huffman tree, the `Fork` nodes, represent a set containing all the characters
   * present in the leaves below it. The weight of a `Fork` node is the sum of the weights of these
   * leaves.
   */
  abstract class CodeTree
  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
  case class Leaf(char: Char, weight: Int) extends CodeTree

  // Part 1: Basics

  def evalWeight(t: CodeTree): Int = t match {
    case Fork(left, right, chars, weight) => evalWeight(left) + evalWeight(right)
    case Leaf(char, weight) => weight
  }

  def evalChar(t: CodeTree): List[Char] = t match {
    case Fork(left, right, chars, weight) => chars
    case Leaf(char, weight) => List(char)
  }

  def evalForkLeaf(t: CodeTree): Int = t match {
    case Fork(left, right, chars, weight) => 1
    case Leaf(char, weight) => 0
  }

  def traverseLeft(c: CodeTree): CodeTree = c match {
    case Fork(left, right, chars, weight) => left
    case Leaf(char, weight) => { new Error("leaf traverse left"); return null }
  }

  def traverseRight(c: CodeTree): CodeTree = c match {
    case Fork(left, right, chars, weight) => right
    case Leaf(char, weight) => { new Error("leaf traverse right"); return null }
  }

  def treeContains(c: CodeTree, cc: Char): Int = c match {
    case Fork(left, right, chars, weight) => if (chars.contains(cc)) chars.size else 0
    case Leaf(char, weight) => if (char.equals(cc)) 1 else 0
  }

  def evalLevel2Bit(t: CodeTree, direction: Boolean): Char = t match {
    case Fork(left, right, chars, weight) => if (direction == true) evalLevel2Bit(right, true) else evalLevel2Bit(left, false)
    case Leaf(char, weight) => char
  }

  def whichLevel2TreeContains(c: CodeTree, char: Char): Int = c match {
    case Fork(left, right, chars, weight) => {
      if (chars.contains(char) == false) {
        new Error("tree does not contain");
        -1
      } else if (treeContains(left, char) == 1) {
        0
      } else if (treeContains(right, char) == 1) {
        1
      } else {
        2
      }
    }
    case Leaf(char, weight) => { new Error("tree does not contain"); -1 }
  }

  def whichTreeContains(c: CodeTree, char: Char): Int = c match {
    case Fork(left, right, chars, weight) => {
      if (chars.contains(char) == false) {
        new Error("tree does not contain");
        -1
      } else if (treeContains(left, char) > 0) {
        0
      } else /*if (treeContains(right, char) > 1) */ {
        1
      }
    }
    case Leaf(char, weight) => { new Error("tree does not contain"); -1 }
  }

  def getContainingTree(c: CodeTree, char: Char): CodeTree = c match {
    case Fork(left, right, chars, weight) => if (treeContains(left, char) >= 1) left else if (treeContains(right, char) >= 1) right else null
    case Leaf(char, weight) => { new Error("trap 2"); null }
  }

  def weight(tree: CodeTree): Int = evalWeight(tree)

  def chars(tree: CodeTree): List[Char] = evalChar(tree)

  def makeCodeTree(left: CodeTree, right: CodeTree) =
    Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))

  // Part 2: Generating Huffman trees

  /**
   * In this assignment, we are working with lists of characters. This function allows
   * you to easily create a character list from a given string.
   */
  def string2Chars(str: String): List[Char] = str.toList

  /**
   * This function computes for each unique character in the list `chars` the number of
   * times it occurs. For example, the invocation
   *
   *   times(List('a', 'b', 'a'))
   *
   * should return the following (the order of the resulting list is not important):
   *
   *   List(('a', 2), ('b', 1))
   *
   * The type `List[(Char, Int)]` denotes a list of pairs, where each pair consists of a
   * character and an integer. Pairs can be constructed easily using parentheses:
   *
   *   val pair: (Char, Int) = ('c', 1)
   *
   * In order to access the two elements of a pair, you can use the accessors `_1` and `_2`:
   *
   *   val theChar = pair._1
   *   val theInt  = pair._2
   *
   * Another way to deconstruct a pair is using pattern matching:
   *
   *   pair match {
   *     case (theChar, theInt) =>
   *       println("character is: "+ theChar)
   *       println("integer is  : "+ theInt)
   *   }
   */
  def times(chars: List[Char]): List[(Char, Int)] = {

    def traverse(char: Char, charList: List[Char], buf: ListBuffer[(Char, Int)]): ListBuffer[(Char, Int)] = {

      def addOrIncChar(char: Char, tuples: ListBuffer[(Char, Int)]): ListBuffer[(Char, Int)] = {
        if (tuples.size == 0) {
          val pair: (Char, Int) = (char, 1)
          val buffer = new ListBuffer[(Char, Int)]
          buffer += pair
        } else {
          val tuple = tuples.head
          if (tuple._1 == char) {
            val x = tuple._2
            val newtuple = tuple.copy(_2 = x + 1)
            val newtuples = newtuple +: tuples.tail
            newtuples
          } else {
            val newtuples = addOrIncChar(char, tuples.tail)
            tuples.head +: newtuples
          }
        }
      }

      val buffer = addOrIncChar(char, buf)
      if (charList.size != 0) {
        return traverse(charList.head, charList.tail, buffer)
      } else {
        return buffer
      }
    }

    if (chars.length > 0) {
      val finallist = traverse(chars.head, chars.tail, new ListBuffer[(Char, Int)]).toList
      //println(finallist)
      finallist
    } else {
      (new ListBuffer[(Char, Int)]).toList
    }
  }

  /**
   * Returns a list of `Leaf` nodes for a given frequency table `freqs`.
   *
   * The returned list should be ordered by ascending weights (i.e. the
   * head of the list should have the smallest weight), where the weight
   * of a leaf is the frequency of the character.
   */
  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = {
    def constructLeaf(tuple: (Char, Int), tupleList: List[(Char, Int)], leafList: ListBuffer[Leaf]): ListBuffer[Leaf] = {
      val leaf = Leaf(tuple._1, tuple._2)
      leafList += leaf
      if (tupleList.size > 0)
        constructLeaf(tupleList.head, tupleList.tail, leafList)
      else
        leafList
    }

    if (freqs.length > 0) {
      val sortedFreq = freqs.sortBy(_._2)
      //println(sortedFreq)
      constructLeaf(sortedFreq.head, sortedFreq.tail, new ListBuffer[Leaf]).toList
    } else {
      (new ListBuffer[Leaf]).toList
    }
  }

  /**
   * Checks whether the list `trees` contains only one single code tree.
   */
  def singleton(trees: List[CodeTree]): Boolean = if (trees.size == 1) true else false

  /**
   * The parameter `trees` of this function is a list of code trees ordered
   * by ascending weights.
   *
   * This function takes the first two elements of the list `trees` and combines
   * them into a single `Fork` node. This node is then added back into the
   * remaining elements of `trees` at a position such that the ordering by weights
   * is preserved.
   *
   * If `trees` is a list of less than two elements, that list should be returned
   * unchanged.
   */
  def combine(trees: List[CodeTree]): List[CodeTree] = {
    def addOrdered(node: CodeTree, tree: List[CodeTree], fork: CodeTree, finalTree: ListBuffer[CodeTree]): ListBuffer[CodeTree] = {
      if (evalWeight(node) < evalWeight(fork)) {
        finalTree += node
        addOrdered(tree.head, tree.tail, fork, finalTree)
      } else {
        finalTree += fork
        finalTree += node
        finalTree ++= tree
        finalTree
      }
    }

    if (trees.length > 0) {
      val node1 = trees.head
      val node2 = trees.tail.head
      val fork = Fork(node1, node2, evalChar(node1) ::: evalChar(node2), evalWeight(node1) + evalWeight(node2))

      val remainingTree = trees.tail.tail
      val x = addOrdered(remainingTree.head, remainingTree.tail, fork, new ListBuffer[CodeTree]).toList
      //println(x)
      x
    } else {
      (new ListBuffer[CodeTree]).toList
    }
  }

  /**
   * This function will be called in the following way:
   *
   *   until(singleton, combine)(trees)
   *
   * where `trees` is of type `List[CodeTree]`, `singleton` and `combine` refer to
   * the two functions defined above.
   *
   * In such an invocation, `until` should call the two functions until the list of
   * code trees contains only one single tree, and then return that singleton list.
   *
   * Hint: before writing the implementation,
   *  - start by defining the parameter types such that the above example invocation
   *    is valid. The parameter types of `until` should match the argument types of
   *    the example invocation. Also define the return type of the `until` function.
   *  - try to find sensible parameter names for `xxx`, `yyy` and `zzz`.
   */
  def until(checkLength: List[CodeTree] => Boolean,
    combineTree: List[CodeTree] => List[CodeTree])(trees: List[CodeTree]): CodeTree = {

    if (checkLength(trees) == false) until(singleton, combine)(combineTree(trees))
    else trees.head

  }

  /**
   * This function creates a code tree which is optimal to encode the text `chars`.
   *
   * The parameter `chars` is an arbitrary text. This function extracts the character
   * frequencies from that text and creates a code tree based on them.
   */
  def createCodeTree(chars: List[Char]): CodeTree = {
    until(singleton, combine)(makeOrderedLeafList(times(chars)))
  }

  // Part 3: Decoding

  type Bit = Int

  /**
   * This function decodes the bit sequence `bits` using the code tree `tree` and returns
   * the resulting list of characters.
   */
  def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {
    def decodeIter(subtree: CodeTree, bit: Int, bits: List[Bit], charList: ListBuffer[Char]): ListBuffer[Char] = {
      println("bit = " + bit)

      val c = evalChar(subtree)
      println("c = " + c + " len = " + c.size)

      val treeToUse =
        if (c.size == 1) {
          val decodedChar = evalLevel2Bit(subtree, true)
          charList += decodedChar
          println("list = " + charList)
          tree
        } else if (c.size == 2) {
          val decodedChar =
            if (bit == 1) {
              println("going right")
              evalLevel2Bit(subtree, true)
            } else {
              println("going left")
              evalLevel2Bit(subtree, false)
            }

          charList += decodedChar
          println("list = " + charList)
          tree
        } else {
          if (bit == 0) {
            traverseLeft(subtree)
          } else {
            traverseRight(subtree)
          }
        }

      println("using tree = " + treeToUse)
      if (bits.size > 0) decodeIter(treeToUse, bits.head, bits.tail, charList)
      else if (evalChar(treeToUse).size == 1) decodeIter(treeToUse, bit, bits, charList)
      else charList

    }

    if (tree != null && bits.length > 0) {
      val x = decodeIter(tree, bits.head, bits.tail, new ListBuffer[Char]).toList
      println("decoded = " + x)
      x
    } else {
      (new ListBuffer[Char]).toList
    }
  }

  /**
   * A Huffman coding tree for the French language.
   * Generated from the data given at
   *   http://fr.wikipedia.org/wiki/Fr%C3%A9quence_d%27apparition_des_lettres_en_fran%C3%A7ais
   */
  val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s', 121895), Fork(Leaf('d', 56269), Fork(Fork(Fork(Leaf('x', 5928), Leaf('j', 8351), List('x', 'j'), 14279), Leaf('f', 16351), List('x', 'j', 'f'), 30630), Fork(Fork(Fork(Fork(Leaf('z', 2093), Fork(Leaf('k', 745), Leaf('w', 1747), List('k', 'w'), 2492), List('z', 'k', 'w'), 4585), Leaf('y', 4725), List('z', 'k', 'w', 'y'), 9310), Leaf('h', 11298), List('z', 'k', 'w', 'y', 'h'), 20608), Leaf('q', 20889), List('z', 'k', 'w', 'y', 'h', 'q'), 41497), List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127), List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291), Fork(Fork(Leaf('o', 82762), Leaf('l', 83668), List('o', 'l'), 166430), Fork(Fork(Leaf('m', 45521), Leaf('p', 46335), List('m', 'p'), 91856), Leaf('u', 96785), List('m', 'p', 'u'), 188641), List('o', 'l', 'm', 'p', 'u'), 355071), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362), Fork(Fork(Fork(Leaf('r', 100500), Fork(Leaf('c', 50003), Fork(Leaf('v', 24975), Fork(Leaf('g', 13288), Leaf('b', 13822), List('g', 'b'), 27110), List('v', 'g', 'b'), 52085), List('c', 'v', 'g', 'b'), 102088), List('r', 'c', 'v', 'g', 'b'), 202588), Fork(Leaf('n', 108812), Leaf('t', 111103), List('n', 't'), 219915), List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503), Fork(Leaf('e', 225947), Fork(Leaf('i', 115465), Leaf('a', 117110), List('i', 'a'), 232575), List('e', 'i', 'a'), 458522), List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)

  /**
   * What does the secret message say? Can you decode it?
   * For the decoding use the `frenchCode' Huffman tree defined above.
   */
  val secret: List[Bit] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  /**
   * Write a function that returns the decoded secret
   */
  def decodedSecret: List[Char] = decode(frenchCode, secret)

  // Part 4a: Encoding using Huffman tree

  /**
   * This function encodes `text` using the code tree `tree`
   * into a sequence of bits.
   */
  def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {

    def encodeIter(subtree: CodeTree, char: Char, text: List[Char], charList: ListBuffer[Bit]): ListBuffer[Bit] = {
      println("char = " + char)
      println("subtree = " + subtree)

      val ss = treeContains(subtree, char)
      println("ss = " + ss)

      if (ss == 2) {
        val x = whichLevel2TreeContains(subtree, char)
        println("x = " + x)
        charList += x

        println("using full tree = " + tree)
        if (text.size > 0) encodeIter(tree, text.head, text.tail, charList)
        else charList

      } else if (ss > 2) {
        val x = whichTreeContains(subtree, char)
        println("x = " + x)
        charList += x

        val treeToUse = getContainingTree(subtree, char)
        println("using subtree = " + treeToUse)
        encodeIter(treeToUse, char, text, charList)

      } else {
        return charList
      }
    }

    if (tree != null && text.length > 0) {
      val y = encodeIter(tree, text.head, text.tail, new ListBuffer[Bit]).toList
      println("encoded = " + y)
      y
    } else {
      (new ListBuffer[Bit]).toList
    }
  }

  // Part 4b: Encoding using code table

  type CodeTable = List[(Char, List[Bit])]

  /**
   * This function returns the bit sequence that represents the character `char` in
   * the code table `table`.
   */
  def codeBits(table: CodeTable)(char: Char): List[Bit] = {
    def codeTableIter(char: Char, tuple: (Char, List[Bit]), table: List[(Char, List[Bit])]): List[Bit] = {
      if (tuple._1 == char) tuple._2
      else if (table.size > 0) codeTableIter(char, table.head, table.tail)
      else { new Error("not found in table = " + char); null }
    }
    if (table.length > 0) {
      codeTableIter(char, table.head, table.tail)
    } else {
      (new ListBuffer[Bit]).toList
    }
  }

  /**
   * Given a code tree, create a code table which contains, for every character in the
   * code tree, the sequence of bits representing that character.
   *
   * Hint: think of a recursive solution: every sub-tree of the code tree `tree` is itself
   * a valid code tree that can be represented as a code table. Using the code tables of the
   * sub-trees, think of how to build the code table for the entire tree.
   */
  def convert(tree: CodeTree): CodeTable = {
    def convertIter(tree: CodeTree, codeTable: ListBuffer[(Char, List[Bit])], bitList: ListBuffer[Bit]): ListBuffer[(Char, List[Bit])] = {
      println("working on tree = " + tree)
      if (evalForkLeaf(tree) > 0) {
        val leftBitList = bitList :+ 0
        convertIter(traverseLeft(tree), codeTable, leftBitList)

        val rightBitList = bitList :+ 1
        convertIter(traverseRight(tree), codeTable, rightBitList)
      } else {
        val char = evalChar(tree)
        val tuple = (char(0), bitList.toList)
        codeTable += tuple
      }
    }

    if (tree != null) {
      val codeTable = convertIter(tree, new ListBuffer[(Char, List[Bit])], new ListBuffer[Bit])
      val a = codeTable.toList
      println(a)
      a
    } else {
      (new ListBuffer[(Char, List[Bit])]).toList
    }
  }

  /**
   * This function takes two code tables and merges them into one. Depending on how you
   * use it in the `convert` method above, this merge method might also do some transformations
   * on the two parameter code tables.
   */
  def mergeCodeTables(a: CodeTable, b: CodeTable): CodeTable = ???

  /**
   * This function encodes `text` according to the code tree `tree`.
   *
   * To speed up the encoding process, it first converts the code tree to a code table
   * and then uses it to perform the actual encoding.
   */
  def quickEncode(tree: CodeTree)(text: List[Char]): List[Bit] = {
    val codeTable = convert(tree)
    encode(tree)(text) // => the usual way of encoding
  }
}
