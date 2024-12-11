fun colinearity(in1: String, w: Int, h: Int, part: Int): Int {
	
    // collect coordinates of all antennas in map
    var antennaMap = mutableMapOf<Char, MutableList<Int>>()
   
    for (y in 0..h-1) {
        for (x in 0..w-1) {
            if (in1[x + y*w] != '.') {
                if (antennaMap.containsKey(in1[x+y*w])) {
                    var newContent = antennaMap.getValue(in1[x+y*w])
                    newContent.add(x+y*w)
                    antennaMap.put(in1[x+y*w], newContent)
                } else {
                    antennaMap.put(in1[x+y*w], mutableListOf(x+y*w))
                }
            }
        }
    }
    
    // set up antinodes
    var antinodeMap = mutableListOf<Int>()
    
    for ((key, value) in antennaMap) {
        var currentAntenna = value
        currentAntenna.forEach {
            val firstAntenna = it
            currentAntenna.forEach {
                val secondAntenna = it
                if (secondAntenna != firstAntenna) {
                    val xFirstAntenna = firstAntenna % w
                    val yFirstAntenna = firstAntenna / w
                    val xSecondAntenna = secondAntenna % w
                    val ySecondAntenna = secondAntenna / w
                    val xDist = xSecondAntenna - xFirstAntenna
                    val yDist = ySecondAntenna - yFirstAntenna
                    if (part == 1) {
                        val xFirstAntinode = xFirstAntenna - xDist
                        val xSecondAntinode = xSecondAntenna + xDist
                        val yFirstAntinode = yFirstAntenna - yDist
                        val ySecondAntinode = ySecondAntenna + yDist
                        if (xFirstAntinode >= 0 && xFirstAntinode < w && yFirstAntinode >= 0 && yFirstAntinode < h) {
                            antinodeMap.add(xFirstAntinode + yFirstAntinode * w)
                        }
                        if (xSecondAntinode >= 0 && xSecondAntinode < w && ySecondAntinode >= 0 && ySecondAntinode < h) {
                            antinodeMap.add(xSecondAntinode + ySecondAntinode * w)
                        }
                    } else {
                    	antinodeMap.add(firstAntenna)
                        // add to the left
                        var xLeft = xFirstAntenna - xDist
                        var yLeft = yFirstAntenna - yDist
                        while (xLeft >= 0 && yLeft >= 0 && xLeft < w && yLeft < h) {
							antinodeMap.add(xLeft +yLeft *w)
                            xLeft -= xDist
                            yLeft -= yDist
                        }
                        // add to the right
                        var xRight = xFirstAntenna + xDist
                        var yRight = yFirstAntenna + yDist
                        while (xRight >= 0 && yRight >= 0 && xRight < w && yRight < h) {
							antinodeMap.add(xRight +yRight *w)
                            xRight -= xDist
                            yRight -= yDist
                        }
                    }
                }
            }
        }
    }
       
    return antinodeMap.distinct().size
}

fun main() {
    println("--- Day 8: Resonant Collinearity ---")
    
    var puzzleInput = listOf("............",
"........0...",
".....0......",
".......0....",
"....0.......",
"......A.....",
"............",
"............",
"........A...",
".........A..",
"............",
"............")
    
    val width = puzzleInput[0].length
    val height = puzzleInput.size
   
    val solution1 = colinearity(puzzleInput.joinToString(""), width, height, 1)
    println("   there are ${solution1} total unique locations" )

	val solution2 = colinearity(puzzleInput.joinToString(""), width, height, 2)
    println("   there are ${solution2} total unique locations")
}
