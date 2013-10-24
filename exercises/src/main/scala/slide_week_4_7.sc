object slide_week_4_7 {

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr, e3: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr, e3: Expr) extends Expr
  case class Var(c: Char) extends Expr

  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2, e3) => eval(e1) + eval(e2) + eval(e3)
    case Prod(e1, e2, e3) => eval(e1) * eval(e2) * eval(e3)
    case Var(c) => throw new Error("cannot eval a variable")
  }                                               //> eval: (e: slide_week_4_7.Expr)Int

  eval(Sum(Number(1), Number(2), Number(0)))      //> res0: Int = 3
  
  def show(e: Any): String = e match {
    case Number(n) => n.toString
    case Sum(e1, e2, e3) => show(e1) + " + " + show(e2) + " + " + show(e3)
    case Prod(e1, e2, e3) => show(e1) + " * " + show(e2) + " * " + show(e3)
    case Var(c) => c.toString
    case n: Int => n.toString
  }                                               //> show: (e: Any)String

  show(Sum(Number(1), Number(2), Number(1)))      //> res1: String = 1 + 2 + 1
  
  show(Prod(Number(2), Var('x'), Number(1)))      //> res2: String = 2 * x * 1
  
  show(Sum(Prod(Number(2), Var('x'), Number(1)), Var('y'), Number(0)))
                                                  //> res3: String = 2 * x * 1 + y + 0
  //show(Prod(Sum(2, Var("x")), Var("y")))
  
}