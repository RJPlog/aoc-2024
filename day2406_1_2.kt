import java.io.File

fun guard(in1: Int, xMod: Int, yMod: Int ): Int {

    var yardMap = ""
    var xPos = 0
    var yPos = 0
    var width = 0
    var height = 0
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

	if (xMod >= 0 && yMod >= 0) {
	yardMap = yardMap.replaceRange(xMod + yMod*width, xMod + yMod*width + 1, "#")
	}

    var guardInYard = true
    var guardInLoop = false
    var lastFourTurns = mutableListOf<Int>()

    i = 0

    while (guardInYard && !guardInLoop) {

		// move guard and turn if necessary
        if (direction == "^") {

            if (yPos - 1 >= 0) {

                if (yardMap[xPos + width*(yPos-1)] != '#') {
                    yPos -= 1
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                } else {
                    direction = ">"
					lastFourTurns.add(xPos + yPos*width)
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                }
            } else {
                guardInYard = false
            }

        } else if (direction == ">") {
            if (xPos + 1 < width) {
                if (yardMap[xPos + 1 + width*(yPos)] != '#') {
                    xPos += 1				
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + 1 + yPos*width,  direction)
                } else {
                    direction = "v"
					lastFourTurns.add(xPos + yPos*width)
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + 1 + yPos*width,  direction)
                }
            } else {
                guardInYard = false
            }
        } else if (direction == "v") {
            if (yPos + 1 < height) {
                if (yardMap[xPos + width*(yPos+1)] != '#') {
                    yPos += 1
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                } else {
                    direction = "<"
					lastFourTurns.add(xPos + yPos*width)
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                }
            } else {
                guardInYard = false
            }

        } else if (direction == "<") {

            if (xPos - 1 >= 0) {
				 if (yardMap[xPos - 1 + width*(yPos)] != '#') {
                    xPos -= 1
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                } else {
                    direction = "^"
					 lastFourTurns.add(xPos + yPos*width)
                    yardMap = yardMap.replaceRange(xPos + yPos*width, xPos + yPos*width + 1,  direction)
                }
            } else {
                guardInYard = false
            }
        }

         i += 1
		
		if (i > width * height) guardInLoop = true
		if (lastFourTurns.size > 8 && lastFourTurns.takeLast(4) == lastFourTurns.takeLast(8).dropLast(4)) guardInLoop= true
    }
    if (in1 == 1) {
        return yardMap.count { it != '.' && it != '#' }
    } else {
		if (guardInLoop) {
			return -1
		} else {
			return i
		}
    }
}

fun main() {

    var t1 = System.currentTimeMillis()

    println("--- Day 6: Guard Gallivant ---")
    
    println("   the guard will visit ${guard(1,-1,-1)} distinct points")
	   
    var result2 = 0
    for (y in 0..129) {  // 129 -  -9
		for (x in 0..129) {  // 129 - 9    
			if (!(x == 47 && y == 49)) {   //  47 / 49       4 -6
				var steps = guard(2,x,y)
				if (steps == -1) {
                    result2 += 1
                }
			}
		}
	}
   println("   ${result2} different positions could be choosen for an obstruction")

   t1 = System.currentTimeMillis() - t1
   println("puzzle solved in ${t1} ms")
}
