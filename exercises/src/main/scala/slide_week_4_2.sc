object slide_week_4_2 {

  abstract class Nat {
    def isZero: Boolean
    def predecessor: Nat
    def successor: Nat
    def +(that: Nat): Nat
    def -(that: Nat): Nat
  }
  
  class Succ(n: Nat) extends Nat {
    override def isZero = false
    override def predecessor = new Succ(this - 1)
    override def successor = new Succ(this + 1)
    override def + (that: Nat): Nat = null
    override def - (that: Nat): Nat = null
  }
  
  object Zero extends Nat {
    override def isZero = true
    override def predecessor = this
    override def successor = new Succ(1)
    override def + (that: Nat) = that
    override def - (that: Nat) = this
  }
  
  
}