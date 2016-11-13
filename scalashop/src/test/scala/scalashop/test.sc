package scalashop

object test {
	

  var x = 0                                       //> x  : Int = 0
  var y = 0                                       //> y  : Int = 0
  var maxx = 10                                   //> maxx  : Int = 10
  var maxy = 10                                   //> maxy  : Int = 10
  
  while(x<maxx) {
  		println(x)
  	while(y<maxy){
  		println(x,y)
  		y+=1
  	}
  	x += 1
  }                                               //> 0
                                                  //| (0,0)
                                                  //| (0,1)
                                                  //| (0,2)
                                                  //| (0,3)
                                                  //| (0,4)
                                                  //| (0,5)
                                                  //| (0,6)
                                                  //| (0,7)
                                                  //| (0,8)
                                                  //| (0,9)
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
}