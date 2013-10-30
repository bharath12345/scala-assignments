object slide_week_6_2 {

  val s = (1 to 6).toSet                          //> s  : scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 3, 4)
  s.map(_ + 2)                                    //> res0: scala.collection.immutable.Set[Int] = Set(5, 6, 7, 3, 8, 4)
}