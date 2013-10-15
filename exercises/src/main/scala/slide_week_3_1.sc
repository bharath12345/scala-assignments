object slide_week_3_1 {
  
  class Rational(x: Int, y: Int) {

    def this(x: Int) = this(x, 1)

    require(y != 0, "denominator must not be zero")

    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    private val g = gcd(x, y)

    def numer = x
    def denom = y

    def add(r: Rational) =
      new Rational(numer * r.denom + r.numer * denom,
        denom * r.denom)

    def mul(r: Rational) =
      new Rational(numer * r.numer, denom * r.denom)

    override def toString = (numer/g) + "/" + (denom/g)

    def neg = new Rational(-numer, denom)

    def sub(r: Rational) =
      new Rational(numer * r.denom - r.numer * denom, denom * r.denom)

    def less(that: Rational) =
      numer * that.denom < that.numer * denom

    def max(that: Rational) =
      if (this.less(that)) that else this
      
    def + (r: Rational) =
      new Rational(numer * r.denom + r.numer * denom, denom * r.denom)

  }

  new Rational(1, 2).neg                          //> res0: slide_week_3_1.Rational = 1/-2

  val x = new Rational(1, 3)                      //> x  : slide_week_3_1.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : slide_week_3_1.Rational = 5/7
  val z = new Rational(15, 10)                    //> z  : slide_week_3_1.Rational = 3/2
  x.add(y).mul(z)                                 //> res1: slide_week_3_1.Rational = 11/7
  x.sub(y).sub(z)                                 //> res2: slide_week_3_1.Rational = -79/42
  x.add(y.neg).add(z.neg)                         //> res3: slide_week_3_1.Rational = -79/42
  x.sub(y)                                        //> res4: slide_week_3_1.Rational = 8/-21
  x.add(y.neg)                                    //> res5: slide_week_3_1.Rational = 8/-21
  
  x add z                                         //> res6: slide_week_3_1.Rational = 11/6
  x + y                                           //> res7: slide_week_3_1.Rational = 22/21
}