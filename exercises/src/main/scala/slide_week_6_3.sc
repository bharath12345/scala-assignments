object slide_week_6_3 {

	case class Book(title: String, authors: List[String])
	
	val books: List[Book] = List(
			Book(title = "SICP", authors = List("Harald Abelsson", "Gerald Sussman")),
			Book(title = "Mahabharata", authors = List("Valmiki")),
			Book(title = "Ramayana", authors = List("Veda Vyasa")),
			Book(title = "Programming in Scala", authors = List("Martin Odersky", "Lex Spoon", "Bill Venners"))
			)                         //> books  : List[slide_week_6_3.Book] = List(Book(SICP,List(Harald Abelsson, Ge
                                                  //| rald Sussman)), Book(Mahabharata,List(Valmiki)), Book(Ramayana,List(Veda Vya
                                                  //| sa)), Book(Programming in Scala,List(Martin Odersky, Lex Spoon, Bill Venners
                                                  //| )))
			
  for(b <- books; a <- b.authors if a startsWith "V")
    yield b                                       //> res0: List[slide_week_6_3.Book] = List(Book(Mahabharata,List(Valmiki)), Book
                                                  //| (Ramayana,List(Veda Vyasa)))
			

}