import java.io.File

fun guard(yM: String, w: Int, h: Int, xP: Int, yP: Int, part: Int, xMod: Int = 0, yMod: Int = 0 ): Int {

    var yardMap = yM
    var xPos = xP
    var yPos = yP
    var width = w
    var height = h
    var i = 0
    var direction = "^"


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
    if (part == 1) {
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

    var yardMap = ""
    var xPos = 0
    var yPos = 0
    var width = 0
    var height = 0
    var i = 0

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


    println("--- Day 6: Guard Gallivant ---")
    
    println("   the guard will visit ${guard(yardMap, width, height, xPos, yPos, 1)} distinct points")
	   
    var result2 = 0
    for (y in 0..height -1) {  
		for (x in 0..width-1) {     
            if (yardMap[x + width*y] == '.') {
				var steps = guard(yardMap, width, height, xPos, yPos, 2, x, y)
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
