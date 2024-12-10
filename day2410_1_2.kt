fun findNextAllVariants(in1: String, in2: Int, in3: Int, in4: Int, in5: Int, in6: Int): Int {
    val trailMap = in1
    val width = in2
    val height = in3
    
    val xTrail = in4
    val yTrail = in5
    val trailH = in6
    
    var result = 0
    
    val up = (xTrail) + width * (yTrail-1)
    val right = (xTrail+1) + width * (yTrail)
    val down = (xTrail) + width * (yTrail+1)
    val left = (xTrail-1) + width * (yTrail)
    
    if (trailH !=8) {
        if ((yTrail-1) >= 0 && trailMap[up].toString().toInt() == trailH+1) result += findNextAllVariants(trailMap, width, height, xTrail, yTrail-1, trailH+1)
        if ((xTrail+1) < width && trailMap[right].toString().toInt() == trailH+1) result += findNextAllVariants(trailMap, width, height, xTrail+1, yTrail, trailH+1)
        if ((yTrail+1) < height && trailMap[down].toString().toInt() == trailH+1) result += findNextAllVariants(trailMap, width, height, xTrail, yTrail+1, trailH+1)
        if ((xTrail-1) >= 0 && trailMap[left].toString().toInt() == trailH+1) result += findNextAllVariants(trailMap, width, height, xTrail-1, yTrail, trailH+1)
    } else {
        if ((yTrail-1) >= 0 && trailMap[up] == '9') result += 1 
        if ((xTrail+1) < width && trailMap[right] == '9') result += 1
        if ((yTrail+1) < height && trailMap[down] == '9') result += 1
        if ((xTrail-1) >= 0 && trailMap[left] == '9') result += 1
    }
    return result
}

fun findNext(in1: String, in2: Int, in3: Int, in4: Int, in5: Int, in6: Int): List<Pair<Int,Int>> {
    val trailMap = in1
    val width = in2
    val height = in3
    
    val xTrail = in4
    val yTrail = in5
    val trailH = in6
    
    val result = mutableListOf<Pair<Int,Int>>()
    
    val up = (xTrail) + width * (yTrail-1)
    val right = (xTrail+1) + width * (yTrail)
    val down = (xTrail) + width * (yTrail+1)
    val left = (xTrail-1) + width * (yTrail)
    
    if (trailH !=8) {
        if ((yTrail-1) >= 0 && trailMap[up].toString().toInt() == trailH+1) result.addAll(findNext(trailMap, width, height, xTrail, yTrail-1, trailH+1))
        if ((xTrail+1) < width && trailMap[right].toString().toInt() == trailH+1) result.addAll(findNext(trailMap, width, height, xTrail+1, yTrail, trailH+1))
        if ((yTrail+1) < height && trailMap[down].toString().toInt() == trailH+1) result.addAll(findNext(trailMap, width, height, xTrail, yTrail+1, trailH+1))
        if ((xTrail-1) >= 0 && trailMap[left].toString().toInt() == trailH+1) result.addAll(findNext(trailMap, width, height, xTrail-1, yTrail, trailH+1))
    } else {
        if ((yTrail-1) >= 0 && trailMap[up] == '9') result.add(Pair(xTrail,yTrail-1))
        if ((xTrail+1) < width && trailMap[right] == '9') result.add(Pair(xTrail+1,yTrail))
        if ((yTrail+1) < height && trailMap[down] == '9') result.add(Pair(xTrail,yTrail+1))
        if ((xTrail-1) >= 0 && trailMap[left] == '9') result.add(Pair(xTrail-1,yTrail))
    }
    return result
}

fun hoofIt(in1: String, in2: Int, in3: Int, in4: Int): Int {
    val trailMap = in1
    val width = in2
    val height = in3
    
    var xTrail: Int
    var yTrail: Int
    
    val trailScore = mutableListOf<Pair<Int,Int>>()
    var result = 0
    
    for (y in 0..height-1) {
        for (x in 0..width-1) {
            if (trailMap[x + y*width] == '0') {
                xTrail = x
                yTrail = y
                if (in4 == 1) {
                    trailScore.addAll(findNext(trailMap, width, height, xTrail, yTrail, 0))
                    result += trailScore.distinct().size
                    trailScore.clear()
                } else {
                    result += findNextAllVariants(trailMap, width, height, xTrail, yTrail, 0)
                }
            }
       }
    }
    
    return result
}

fun main() {
	println("--- Day 10: Hoof It ---")
    
    val puzzleInput = listOf("89010123",
							 "78121874",
							 "87430965",
							 "96549874",
							 "45678903",
							 "32019012",
							 "01329801",
							 "10456732")
    
    val trailMap = puzzleInput.joinToString("")
    val width = puzzleInput[0].length
    val height = puzzleInput.size
    
    val solution1 = hoofIt(trailMap, width, height, 1)
    val solution2 = hoofIt(trailMap, width, height, 2)
    
	println("   the sum all trailheads is ${solution1} ")
    println("   the sum all paths is ${solution2} ")
}
