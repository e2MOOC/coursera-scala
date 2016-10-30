package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import Math._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] =  for {
    a <- arbitrary[A]
    h <- oneOf(Gen.const(empty), genHeap)
  } yield insert(a, h)
  
  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }
  
  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }  
  
  // If you insert any two elements into an empty heap, finding the minimum 
  // of the resulting heap should get the smallest of the two elements back.
  property("hint1") = forAll { (a: Int, b: Int, h: H) =>
    val h = insert(a, insert(b, empty))
    findMin(h) == min(a, b)
  } 
  
  // If you insert an element into an empty heap, then delete the minimum, the 
  // resulting heap should be empty.
  property("hint2") = forAll { a: Int =>
    val h = deleteMin(insert(a, empty))
    h == empty
  }   
  
  // Given any heap, you should get a sorted sequence of elements when 
  // continually finding and deleting minima. (Hint: recursion and helper functions are your friends.)
  property("hint3") = forAll { h: H =>
    def isSorted( h1: H, prev: Int): Boolean = {
      if (isEmpty(h1)) true
      else {
        val curr = findMin(h1)
        (curr >= prev) && isSorted(deleteMin(h1), curr)
      }
    }
    isEmpty(h) || isSorted(deleteMin(h), findMin(h))
  } 
  
  // Finding a minimum of the melding of any two heaps should return a minimum of one or the other.
  property("hint4") = forAll { (h1: H, h2: H) =>
    val answer = min (findMin(h1), findMin(h2))
    val h = meld(h1, h2)
    findMin(h) == answer
  }
  

  // check if meld is associative
  property("meld") = forAll { (h1: H, h2: H) =>
    def heapEqual(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else {
        val m1 = findMin(h1)
        val m2 = findMin(h2)
        m1 == m2 && heapEqual(deleteMin(h1), deleteMin(h2))
      }
    heapEqual(meld(h1, h2), meld(deleteMin(h1), insert(findMin(h1), h2)))
  } 
  
}
