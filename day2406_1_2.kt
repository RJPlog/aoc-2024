import java.io.File

fun guardNew(in1: Int, insertObstacle: Int ): Int {

    var yardMap = ""
    var xPos = 0
    var yPos = 0
    var width = 0
    var height : Int
    var i = 0
    var direction = "^"

  File("day2406_puzzle_input.txt").forEachLine {
        width = it.length
        yardMap += it
        if (it.contains("^")) {
            xPos = it.indexOf("^")
            yPos = i
        }
        i += 1
    }
    height = i
   
    var guardInYard = true
    var guardInLoop = false
    var obstaclePos = 0
	var lastFourTurns = mutableListOf<Int>()
   
    i = 0

    while (guardInYard && !guardInLoop) {
			
			
		    // insert obstacle at step i.
		    // add starting of counting turns
			if (i == insertObstacle) {
				if (direction == "^") {
					if (yPos-1 >= 0) {
						yardMap = yardMap.replaceRange((xPos) + (yPos-1)*width, (xPos) + (yPos-1)*width + 1, "#")
						obstaclePos = (xPos) + (yPos-1)*width
					}
				} else if (direction == "<") {
					if (xPos-1 >= 0) {
						yardMap = yardMap.replaceRange((xPos-1) + (yPos)*width, (xPos-1) + (yPos)*width + 1, "#")
						obstaclePos = (xPos-1) + (yPos)*width
					}
				} else if (direction == "v") {
					if (yPos+1 <= height - 1) {
						yardMap = yardMap.replaceRange((xPos) + (yPos+1)*width, (xPos) + (yPos+1)*width + 1, "#")
						obstaclePos = (xPos) + (yPos+1)*width
					}
				} else if (direction == ">") {
					if (xPos+1 <= width - 1) {
						yardMap = yardMap.replaceRange((xPos+1) + (yPos)*width, (xPos+1) + (yPos)*width + 1, "#")
						obstaclePos = (xPos+1) + (yPos)*width
					}
				}
			}
		
		// move guard and turn if necessary
        if (direction == "^") {
            if (yPos - 1 >= 0) {
                if (yardMap[xPos + width*(yPos-1)] != '#') {
                    //yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1, "^")
                    yPos -= 1
					if (yardMap[xPos + yPos*width].toString() == direction) {
						//guardInLoop = true
					}
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                } else {
                    direction = ">"
					lastFourTurns.add(xPos + yPos*width)
				    if (yardMap[xPos + yPos*width].toString() == direction) {
						//guardInLoop = true
					}
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                }
            } else {
                guardInYard = false
            }
        } else if (direction == ">") {
            if (xPos + 1 < width) {
                if (yardMap[xPos + 1 + width*(yPos)] != '#') {
                    //yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + 1 + yPos*width, ">")
                    xPos += 1
					if (yardMap[xPos + yPos*width].toString() == direction) {
						//guardInLoop = true
					}					
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + 1 + yPos*width,  direction)
                } else {
                    direction = "v"
					lastFourTurns.add(xPos + yPos*width)
                    if (yardMap[xPos + yPos*width].toString() == direction) {
						//guardInLoop = true
					}
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + 1 + yPos*width,  direction)
                }
            } else {
                guardInYard = false
            }
        } else if (direction == "v") {
            if (yPos + 1 < height) {
                if (yardMap[xPos + width*(yPos+1)] != '#') {
                    //yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1, "v")
                    yPos += 1
					if (yardMap[xPos + yPos*width].toString() == direction) {
					//	guardInLoop = true
					}
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                } else {
                    direction = "<"
					lastFourTurns.add(xPos + yPos*width)
					if (yardMap[xPos + yPos*width].toString() == direction) {
						//guardInLoop = true
					}
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                }
            } else {
                guardInYard = false
            }

        } else if (direction == "<") {
            if (xPos - 1 >= 0) {
				 if (yardMap[xPos - 1 + width*(yPos)] != '#') {
                    //yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1, "<")
                    xPos -= 1
					if (yardMap[xPos + yPos*width].toString() == direction) {
						//guardInLoop = true
					}
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                } else {
                    direction = "^"
					 lastFourTurns.add(xPos + yPos*width)
					if (yardMap[xPos + yPos*width].toString() == direction) {
						//guardInLoop = true
					}
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                }
            } else {
                guardInYard = false
            }
        }

         i += 1
		if (i > width * height) guardInYard = false
		if (lastFourTurns.size > 8 && lastFourTurns.takeLast(4) == lastFourTurns.takeLast(8).dropLast(4)) guardInLoop= true
        if (guardInLoop) println("----------Loop-------------------")
		
         if (false) {
			 println("x $xPos, y $yPos")
            for (y in 0..height-1) {
                for (x in 0..width-1) {
                    print(yardMap[x + y * width])
                }
                println()
            }
        println()
        } 
    }

	//print("lastFourTurns :$lastFourTurns   :")
	

	
    if (in1 == 1) {
        return yardMap.count { it != '.' && it != '#' }
    } else {
		if (guardInLoop) {
			return obstaclePos
		} else {
			return -1
		}
    }
}

fun main() {

    println("--- Day 6: Guard Gallivant ---")
            
	var result1 = guardNew(1,-1)
	println("   the guard will visit ${result1} distinct points")
	
	println()
	/* 
	var result2 = mutableListOf<Int>()
	for (i in 0..5443) {
		var steps = guardNew(2,i)
		if (steps != -1) result2.add(steps)
		println("i: $i, steps: $steps, result = ${result2.size}")	
	}
	println((result2.distinct().size))
	println("   ${result2} different positions could be choosen for an obstruction") //5822/5821/5304 to high 1288/1287 / 1805 / 1721/ 1659 /1288 /211 /3xx /309
    */
}