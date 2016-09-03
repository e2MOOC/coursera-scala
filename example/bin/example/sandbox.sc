package example

object sandbox {
  
  def sum(xs: List[Int]): Int = xs.reduce(_ + _)  //> sum: (xs: List[Int])Int
  def max(xs: List[Int]): Int = {
	  def maxInt(a: Int, b: Int) = if (a>b) a else b
	  xs.reduce(maxInt)
	}                                         //> max: (xs: List[Int])Int
  max(List(1,2,3,10,1,1))                         //> res0: Int = 10
  
}