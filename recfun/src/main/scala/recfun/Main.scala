package recfun
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      @tailrec 
      def subrot(cc: Int, rr: Int, delta: Int): Int = {
        if (cc==0) 1
        else if (cc>=rr+1) 0
        else subrot(cc-delta,rr-1,0)
      }
      
      subrot(c,r,0) + subrot(c,r,1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = { 
      def check(state: Int, c: Char): Int =
        if (c=='(') state + 1
        else if (c==')') state - 1
        else state
      
      @tailrec 
      def loop(state: Int, chars: List[Char]): Boolean =
        if (state < 0) false
        else if (chars.isEmpty) state == 0
        else loop(check(state,chars.head),chars.tail)
        
      loop(0,chars)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if(money == 0) 1
      else if (money < 0 || coins.isEmpty) 0
      else countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
