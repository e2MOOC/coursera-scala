package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
    c: Signal[Double]): Signal[Double] = {
      Var(b() * b() - 4 * a() * c())
    }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Var(
      if (delta() >= 0 && a() != 0) {
        val denom = 2 * a()
        var sqrtDelta = math.sqrt(delta())
        Set((-b() + sqrtDelta)/denom, (-b() - sqrtDelta)/denom)
      } else {
        Set()
      }
    )
  }
}
