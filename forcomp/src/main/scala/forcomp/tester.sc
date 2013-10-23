package forcomp

object tester {
  
  println("hello world")                          //> hello world
  
  Anagrams.wordOccurrences("hello")               //> res0: forcomp.Anagrams.Occurrences = List((e,1), (h,1), (l,2), (o,1))
                                        
                                        
  val wordList = List("jai", "shri", "ram")       //> wordList  : List[String] = List(jai, shri, ram)
  Anagrams.sentenceOccurrences(wordList)          //> res1: forcomp.Anagrams.Occurrences = List((s,1), (j,1), (a,2), (m,1), (i,2),
                                                  //|  (h,1), (r,2))
                                         
  val amap = Anagrams.dictionaryByOccurrences     //> amap  : Map[forcomp.Anagrams.Occurrences,List[forcomp.Anagrams.Word]] = Map(
                                                  //| List((u,1), (b,1), (c,1), (h,1), (r,1)) -> List(burch), List((e,1), (s,1), (
                                                  //| t,1), (u,1), (a,1), (l,1), (p,2), (o,1)) -> List(populates), List((e,2), (s,
                                                  //| 2), (n,1), (a,1), (r,1), (o,1)) -> List(seasoner), List((s,1), (u,1), (a,1),
                                                  //|  (b,2), (c,1), (r,1), (k,1), (o,1), (d,1)) -> List(buckboards), List((n,1), 
                                                  //| (h,1), (s,1), (u,1)) -> List(huns), List((e,2), (s,2), (t,1), (u,1), (a,1), 
                                                  //| (i,1), (g,1), (l,2), (r,1)) -> List(legislatures), List((s,1), (n,1), (t,1),
                                                  //|  (u,1), (a,1), (l,1), (w,1)) -> List(walnuts), List((e,1), (a,1), (m,1), (g,
                                                  //| 1), (l,1)) -> List(gleam), List((t,1), (a,1), (l,1), (h,1), (o,1)) -> List(l
                                                  //| oath), List((s,1), (n,1), (p,1), (h,1), (r,1), (w,1), (o,2)) -> List(shopwor
                                                  //| n), List((e,2), (y,1), (v,1), (l,2), (c,1), (r,1)) -> List(cleverly), List((
                                                  //| e,2), (n,1), (f,1), (a,1), (r,2), (w,1), (o,1), (d,1)) -> List(forewarned), 
                                                  //| List((e,3), (s,1), (t,1)
                                                  //| Output exceeds cutoff limit.
 
  //val keys = amap.keys
  val key0 = List(('e', 1), ('a', 1), ('t', 1))   //> key0  : List[(Char, Int)] = List((e,1), (a,1), (t,1))
  amap.get(key0)                                  //> res2: Option[List[forcomp.Anagrams.Word]] = None
  
  val values = amap.values                        //> values  : Iterable[List[forcomp.Anagrams.Word]] = MapLike(List(burch), List(
                                                  //| populates), List(seasoner), List(buckboards), List(huns), List(legislatures)
                                                  //| , List(walnuts), List(gleam), List(loath), List(shopworn), List(cleverly), L
                                                  //| ist(forewarned), List(everest), List(milquetoasts), List(coroutines), List(b
                                                  //| aked), List(tompkins), List(broadcasters), List(ching), List(carols), List(h
                                                  //| omers), List(sherry), List(circuses), List(disassembly), List(bazaars), List
                                                  //| (busboy), List(initiators), List(hindu), List(defections), List(fedders), Li
                                                  //| st(continuing), List(vegetables), List(carbonation), List(whale), List(caval
                                                  //| ierly), List(tanaka), List(dustbin), List(helsinki), List(trapping), List(pr
                                                  //| iming), List(flowerpot), List(injunctions), List(aluminum), List(compiled), 
                                                  //| List(intangibles), List(penthouse), List(lumped), List(feature), List(nests)
                                                  //| , List(demonstrations), List(infers), List(sponging), List(highways), List(m
                                                  //| ortification), List(engl
                                                  //| Output exceeds cutoff limit.
  
  values.size                                     //> res3: Int = 42343
  
  val biglist = List()                            //> biglist  : List[Nothing] = List()
  values.foreach(innerList => if(innerList.size > 1) biglist +: innerList)
  biglist                                         //> res4: List[Nothing] = List()
  
  //for(innerList <- values) yield (innerList.size > 1)
  
  Anagrams.wordAnagrams("tea")                    //> res5: List[forcomp.Anagrams.Word] = List(ate)
  
}