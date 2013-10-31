package forcomp

import common._
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /**
   * `Occurrences` is a `List` of pair of character and positive integer saying
   *  how often the character appears.
   *  This list is sorted alphabetically w.r.t. to the character in each pair.
   *  All characters in the occurrence list are lowercase.
   *
   *  Any list of pairs of lowercase characters and their frequency which is not sorted
   *  is **not** an occurrence list.
   *
   *  Note: If the frequency of some character is zero, then that character should not be
   *  in the list.
   */
  type Occurrences = List[(Char, Int)]

  /**
   * The dictionary is simply a sequence of words.
   *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  val dictionary: List[Word] = loadDictionary

  /**
   * Converts the word into its character occurence list.
   *
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   */
  def wordOccurrences(w: Word): Occurrences = {
    w.groupBy((c: Char) => c).mapValues(x => x.length).toList
  }

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = {
    wordOccurrences(s.flatten mkString)
  }

  /**
   * The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   *  the words that have that occurrence count.
   *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *
   *  For example, the word "eat" has the following character occurrence list:
   *
   *     `List(('a', 1), ('e', 1), ('t', 1))`
   *
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   *    List(('a', 1), ('e', 1), ('t', 1)) -> List("ate", "eat", "tea")
   *
   */
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
    
    def dicOccurIter(word: Word, words: List[Word], dicMap: Map[Occurrences, List[Word]]): Map[Occurrences, List[Word]] = {
      val occurrences = wordOccurrences(word)
      var mapCopy = dicMap
      
      dicMap.get(occurrences) match {
        case Some(wordList: List[Word]) => {
          var newWordList = List[String]()
          newWordList = word :: wordList
          mapCopy += (occurrences -> newWordList)
        }
        case None => {
          val wordList = List(word)
          mapCopy += (occurrences -> wordList)
        }
      }

      if (!words.isEmpty)
        dicOccurIter(words.head.toLowerCase, words.tail, mapCopy)
      else
        mapCopy
    }

    val dicMap = new HashMap[Occurrences, List[Word]]
    if (!dictionary.isEmpty)
      dicOccurIter(dictionary.head.toLowerCase, dictionary.tail, dicMap);
    else
      dicMap
  }

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = {
    dictionaryByOccurrences(wordOccurrences(word))
  }

  /**
   * Returns the list of all subsets of the occurrence list.
   *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   *  is a subset of `List(('k', 1), ('o', 1))`.
   *  It also include the empty subset `List()`.
   *
   *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   *    List(
   *      List(),
   *      List(('a', 1)),
   *      List(('a', 2)),
   *      List(('b', 1)),
   *      List(('a', 1), ('b', 1)),
   *      List(('a', 2), ('b', 1)),
   *      List(('b', 2)),
   *      List(('a', 1), ('b', 2)),
   *      List(('a', 2), ('b', 2))
   *    )
   *
   *  Note that the order of the occurrence list subsets does not matter -- the subsets
   *  in the example above could have been displayed in some other order.
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    val singles = for {
      (char, count) <- occurrences;
      i <- 1 to count
    } yield (char, i)
    println("singles = " + singles)
   
    // make Lists of occurrences for each character
    // finally u have a map like Map(('a', List[('a',1),('a',2)]), ('b', List[('b',1),('b',2)])) 
    def pack(tuple: (Char, Int), tuples: List[(Char, Int)], acc: Map[Char, List[(Char, Int)]] = Map.empty): Map[Char, List[(Char, Int)]] = {
      if(acc contains tuple._1) {
        val newacc = acc + (tuple._1 -> (tuple +: acc(tuple._1)))
        if(tuples.size != 0)
          pack(tuples.head, tuples.tail, newacc)
        else
          newacc  
      } else {
        val newacc = acc + (tuple._1 -> List(tuple))
        if(tuples.size != 0)
          pack(tuples.head, tuples.tail, newacc)
        else
          newacc
      }
    }    
    val combos = pack(singles.head, singles.tail)
    println("combos = " + combos)
    
    def getListOfTuples(tuple: (Char, Int), tupleList: List[(Char, Int)], acc: List[List[(Char, Int)]] = List.empty): List[List[(Char, Int)]] = {
      val newacc = List(tuple) +: acc
      if(tupleList.size != 0)
        getListOfTuples(tupleList.head, tupleList.tail, newacc)
      else 
        newacc
    }
    
    def getListOfNTuples(
        fullMap: Map[Char, List[(Char, Int)]],
        n: Int,
        acc: List[List[(Char, Int)]] = List.empty): List[List[(Char, Int)]] = {
      
      def buildTupleList(remainingMap: Map[Char, List[(Char, Int)]], innerList: List[(Char, Int)] = List.empty): List[(Char, Int)] = {
        val filteredMap = for((c, i) <- innerList) yield remainingMap - c // do not consider characters already in the list
        
        /*val filteredMap = remainingMap - tuple._1 // remove list of tuples of this character from the map
        if(filteredMap.size != 0) {
          if()
        } else 
          innerList  
        }*/
        
        innerList
      }
    
      for(tuple <- singles) {
        val allCombinations = buildTupleList(fullMap, List(tuple))
      }
      
      acc
      
    }
    
    val finalList : List[List[(Char, Int)]] = List.empty;
    for(i <- 1 to occurrences.size) {
      for((k,v) <- combos) { 
        val listOfTuples = getListOfTuples(v.head, v.tail)
        println("list of tuples = " + listOfTuples)
      }
    }
    
    println("final list = " + finalList)
    
    finalList
  }

  /**
   * Subtracts occurrence list `y` from occurrence list `x`.
   *
   *  The precondition is that the occurrence list `y` is a subset of
   *  the occurrence list `x` -- any character appearing in `y` must
   *  appear in `x`, and its frequency in `y` must be smaller or equal
   *  than its frequency in `x`.
   *
   *  Note: the resulting value is an occurrence - meaning it is sorted
   *  and has no zero-entries.
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = ???

  /**
   * Returns a list of all anagram sentences of the given sentence.
   *
   *  An anagram of a sentence is formed by taking the occurrences of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *
   *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   *    List(
   *      List(en, as, my),
   *      List(en, my, as),
   *      List(man, yes),
   *      List(men, say),
   *      List(as, en, my),
   *      List(as, my, en),
   *      List(sane, my),
   *      List(Sean, my),
   *      List(my, en, as),
   *      List(my, as, en),
   *      List(my, sane),
   *      List(my, Sean),
   *      List(say, men),
   *      List(yes, man)
   *    )
   *
   *  The different sentences do not have to be output in the order shown above - any order is fine as long as
   *  all the anagrams are there. Every returned word has to exist in the dictionary.
   *
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = ???

}
