package forcomp

object tester {
  
  println("hello world")                          //> hello world
  
  Anagrams.wordOccurrences("hellow")              //> res0: forcomp.Anagrams.Occurrences = List((e,1), (l,2), (h,1), (w,1), (o,1))
                                                  //| 
                                        
                                        
  val wordList = List("jai", "shri", "ram")       //> wordList  : List[String] = List(jai, shri, ram)
  Anagrams.sentenceOccurrences(wordList)          //> res1: forcomp.Anagrams.Occurrences = List((s,1), (j,1), (a,2), (m,1), (i,2),
                                                  //|  (h,1), (r,2))
                                         
  val amap = Anagrams.dictionaryByOccurrences     //> amap  : Map[forcomp.Anagrams.Occurrences,List[forcomp.Anagrams.Word]] = Map(
                                                  //| List((u,1), (b,1), (c,1), (h,1), (r,1)) -> List(burch), List((e,1), (s,1), (
                                                  //| t,1), (u,1), (a,1), (l,1), (p,2), (o,1)) -> List(populates), List((e,2), (s,
                                                  //| 2), (n,1), (a,1), (r,1), (o,1)) -> List(seasoner), List((s,1), (u,1), (a,1),
                                                  //|  (b,2), (c,1), (r,1), (k,1), (o,1), (d,1)) -> List(buckboards), List((n,1), 
                                                  //| (h,1), (s,1), (u,1)) -> List(shun, huns), List((e,2), (s,2), (t,1), (u,1), (
                                                  //| a,1), (i,1), (g,1), (l,2), (r,1)) -> List(legislatures), List((s,1), (n,1), 
                                                  //| (t,1), (u,1), (a,1), (l,1), (w,1)) -> List(walnuts), List((e,1), (a,1), (m,1
                                                  //| ), (g,1), (l,1)) -> List(gleam), List((t,1), (a,1), (l,1), (h,1), (o,1)) -> 
                                                  //| List(loath), List((s,1), (n,1), (p,1), (h,1), (r,1), (w,1), (o,2)) -> List(s
                                                  //| hopworn), List((e,2), (y,1), (v,1), (l,2), (c,1), (r,1)) -> List(cleverly), 
                                                  //| List((e,2), (n,1), (f,1), (a,1), (r,2), (w,1), (o,1), (d,1)) -> List(forewar
                                                  //| ned), List((e,3), (s,1),
                                                  //| Output exceeds cutoff limit.
 
  //val keys = amap.keys
  val key0 = List(('e', 1), ('a', 1), ('t', 1))   //> key0  : List[(Char, Int)] = List((e,1), (a,1), (t,1))
  amap.get(key0)                                  //> res2: Option[List[forcomp.Anagrams.Word]] = None
  
  val values = amap.values                        //> values  : Iterable[List[forcomp.Anagrams.Word]] = MapLike(List(burch), List(
                                                  //| populates), List(seasoner), List(buckboards), List(shun, huns), List(legisla
                                                  //| tures), List(walnuts), List(gleam), List(loath), List(shopworn), List(clever
                                                  //| ly), List(forewarned), List(everest), List(milquetoasts), List(coroutines), 
                                                  //| List(baked), List(tompkins), List(rebroadcasts, broadcasters), List(ching), 
                                                  //| List(carols), List(homers), List(sherry), List(circuses), List(disassembly),
                                                  //|  List(bazaars), List(busboy), List(initiators), List(hindu), List(defections
                                                  //| ), List(fedders), List(continuing), List(vegetables), List(carbonation), Lis
                                                  //| t(whale), List(cavalierly), List(tanaka), List(dustbin), List(helsinki), Lis
                                                  //| t(trapping), List(priming), List(flowerpot), List(injunctions), List(aluminu
                                                  //| m), List(complied, compiled), List(intangibles), List(penthouse), List(plume
                                                  //| d, lumped), List(feature), List(nests), List(demonstrations), List(infers), 
                                                  //| List(sponging), List(hig
                                                  //| Output exceeds cutoff limit.
  
  values.size                                     //> res3: Int = 42343
  
  var biglist = 0                                 //> biglist  : Int = 0
  values.foreach(innerList => biglist = biglist + innerList.size)
  biglist                                         //> res4: Int = 45374
  
  //for(innerList <- values) yield (innerList.size > 1)
  
  Anagrams.wordAnagrams("ate")                    //> res5: List[forcomp.Anagrams.Word] = List(tea, eat, ate)
  
  var comb = List(('a', 2), ('b', 2))             //> comb  : List[(Char, Int)] = List((a,2), (b,2))
  Anagrams.combinations(comb)                     //> res6: List[forcomp.Anagrams.Occurrences] = List(List(), List((b,1)), List((b
                                                  //| ,2)), List((a,1)), List((a,2)), List((a,1), (b,1)), List((a,2), (b,1)), List
                                                  //| ((a,1), (b,2)), List((a,2), (b,2)))
                                                  
  val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
                                                  //> x  : List[(Char, Int)] = List((a,1), (d,1), (l,1), (r,1))
  val y = List(('r', 1))                          //> y  : List[(Char, Int)] = List((r,1))
  
  Anagrams.subtract(x, y)                         //> res7: forcomp.Anagrams.Occurrences = List((a,1), (d,1), (l,1))
                                                  
  val s = List(('a', 2), ('b', 2))                //> s  : List[(Char, Int)] = List((a,2), (b,2))
  (for {
    (ch, tm) <- s
  } yield ch).mkString                            //> res8: String = ab
  
  val a = List(('a', 2), ('b', 2))                //> a  : List[(Char, Int)] = List((a,2), (b,2))
  val b = List(('a', 1), ('b', 2))                //> b  : List[(Char, Int)] = List((a,1), (b,2))
  
  val z = (for {
    (cha, tma) <- a
    (chb, tmb) <- b
    if(cha == chb)
    if(tma - tmb != 0)
  } yield (cha, tma - tmb))                       //> z  : List[(Char, Int)] = List((a,1))
  
  wordList.flatten.mkString                       //> res9: String = jaishriram
}