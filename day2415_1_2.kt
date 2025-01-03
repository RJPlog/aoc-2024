import java.io.File

fun warehouse2(puzzleInput1: String, puzzleInput2:String, w: Int, h: Int): Long {
    // modifiy warehouse
    var warehouse = ""
    puzzleInput1.chunked(w).forEach{
        it.forEach{
            when(it) {
                '#' -> warehouse += "##"
                '.' -> warehouse += ".."
                'O' -> warehouse += "[]"
                '@' -> warehouse += "@."
            }
        }
    }
    var w = 2*w

    puzzleInput2.forEach {
        var x = warehouse.indexOf('@') % w
        var y = warehouse.indexOf('@') / w
        
        when (it) {     			
            '^' -> {
                var moveArea = mutableListOf<Int>()
                moveArea.add(x+w*y)
                var loopStart = 0
                var loopEnd = 0
                var moveEval = false
                var movePos = true
                while (!moveEval) {
                    moveEval = true
                    for (j in loopStart..loopEnd) {
                        var tile = moveArea[j]
                        var xT = tile % w
                        var yT = tile / w
                        if (warehouse[xT+w*(yT-1)] == '[') {
                            if (!moveArea.contains(xT+w*(yT-1))) {
                                moveArea.add(xT+w*(yT-1))
                                loopEnd += 1
                            }
                            if (!moveArea.contains((xT+1)+w*(yT-1))) {
                                moveArea.add((xT+1)+w*(yT-1))
                                loopEnd += 1
                            }
                            loopStart +=1
                            moveEval = false
                        }  else if (warehouse[xT+w*(yT-1)] == ']') {
                            if (!moveArea.contains(xT+w*(yT-1))) {
                                moveArea.add(xT+w*(yT-1))
                                loopEnd += 1
                            }
                            if (!moveArea.contains((xT-1)+w*(yT-1))) {
                                moveArea.add((xT-1)+w*(yT-1))
                                loopEnd += 1}
                            loopStart += 1
                            moveEval = false
                        } else if (warehouse[xT+w*(yT-1)] == '#') {
                            movePos = false
                        }
                    }
                }
                // eval finished, now shift
                moveArea.sort()
                if (movePos) {
                    moveArea.forEach{
                        warehouse = warehouse.replaceRange(it-w, it-w + 1, warehouse[it].toString())
                        warehouse = warehouse.replaceRange(it, it+1, ".")
                    }
                }
            }
            'v' -> {
                var moveArea = mutableListOf<Int>()
                moveArea.add(x+w*y)
                var loopStart = 0
                var loopEnd = 0
                var moveEval = false
                var movePos = true
                while (!moveEval) {
                    moveEval = true
                    for (j in loopStart..loopEnd) {
                        var tile = moveArea[j]
                        var xT = tile % w
                        var yT = tile / w
                        if (warehouse[xT+w*(yT+1)] == '[') {
                            if (!moveArea.contains(xT+w*(yT+1))) {
                                moveArea.add(xT+w*(yT+1))
                                loopEnd += 1
                            }
                            if (!moveArea.contains((xT+1)+w*(yT+1))) {
                                moveArea.add((xT+1)+w*(yT+1))
                                loopEnd += 1
                            }
                            loopStart +=1
                            moveEval = false
                        }  else if (warehouse[xT+w*(yT+1)] == ']') {
                            if (!moveArea.contains(xT+w*(yT+1))) {
                                moveArea.add(xT+w*(yT+1))
                                loopEnd += 1
                            }
                            if (!moveArea.contains((xT-1)+w*(yT+1))) {
                                moveArea.add((xT-1)+w*(yT+1))
                                loopEnd += 1}
                            loopStart += 1
                            moveEval = false
                        } else if (warehouse[xT+w*(yT+1)] == '#') {
                            movePos = false
                        }
                    }
                }
                // eval finished, now shift
                moveArea.sortDescending()
                if (movePos) {
                    moveArea.forEach{
                        warehouse = warehouse.replaceRange(it+w, it+w + 1, warehouse[it].toString())
                        warehouse = warehouse.replaceRange(it, it+1, ".")
                    }
                }
            }
            '>' -> {
                var moveArea = "@"
                var moveIndex = 1
                var moveEval = false
                var movePos = false
                while (!moveEval){
                    if (warehouse[(x+moveIndex)+w*y] == '[') {
                        moveArea = moveArea + "[]"
                        moveIndex += 2
                    } else if (warehouse[(x+moveIndex)+w*y] == '.') {
                        moveEval = true
                        movePos = true
                    } else if (warehouse[(x+moveIndex)+w*y] == '#') {
                        moveEval = true
                    }
                }
                if (movePos) {
                    warehouse = warehouse.replaceRange((x+1)+w*y, (x+1)+w*y + moveArea.length, moveArea)
                    warehouse = warehouse.replaceRange(x + w*y, x + w*y + 1, ".")
                }
            }
            '<' -> {
                var moveArea = "@"
                var moveIndex = 1
                var moveEval = false
                var movePos = false
                while (!moveEval){
                    if (warehouse[(x-moveIndex)+w*y] == ']') {
                        moveArea = "[]" + moveArea
                        moveIndex += 2
                    } else if (warehouse[(x-moveIndex)+w*y] == '.') {
                        moveEval = true
                        movePos = true
                    } else if (warehouse[(x-moveIndex)+w*y] == '#') {
                        moveEval = true
                    }
                }
                if (movePos) {
                    warehouse = warehouse.replaceRange((x-moveIndex)+w*y, (x-moveIndex)+w*y + moveArea.length, moveArea)
                    warehouse = warehouse.replaceRange(x + w*y, x + w*y + 1, ".")
                }
            }
        } 
    }

    // calculating result    
    var result = 0L    
    for (y in 0..h-1) {
        for (x in 0..w-1) {
             if (warehouse[x +  w*y] == '[') {
                result += ( x + 100 * y).toLong()

            }           
        }
    }

    // finally return result
    return result
}

fun warehouse(puzzleInput1: String, puzzleInput2:String, w: Int, h: Int): Long {
	var warehouse = puzzleInput1
    puzzleInput2.forEach {
        var x = warehouse.indexOf('@') % w
        var y = warehouse.indexOf('@') / w
        
        when (it) {     			
            '^' -> {
                if (warehouse[(x)+ w*(y-1)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x)+ w*(y-1), (x)+ w*(y-1)+1, "@")
                } else if (warehouse[(x)+ w*(y-1)] == 'O') {
                    val pR = "\\.(.{" + (w-1).toString() + "}O)+.{" + (w-1).toString() + "}@"
    				val pD = pR.toRegex()  //"""\.(.{7}O)+.{7}@""".toRegex()
                    val matchD = pD.find(warehouse)
                    if (matchD != null) {
                    	warehouse = warehouse.replaceRange(x + w*y, x + w*y + 1, ".")
                        warehouse = warehouse.replaceRange(x + w*(y-1), x + w*(y-1) + 1, "@")
                        warehouse = warehouse.replaceRange(x + w*y - (matchD.value.length-1), x + w*y - (matchD.value.length-1) + 1, "O")
                    }
                }
            }
            '>' -> {
                if (warehouse[(x+1)+ w*(y)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x+1)+ w*(y), (x+1)+ w*(y)+1, "@")
                } else if (warehouse[(x+1)+ w*(y)] == 'O') {
                    val pR = """@O+\.""".toRegex()
                    val match = pR.find(warehouse)
                    if (match != null) {
                    	warehouse = warehouse.replace(match.value, "." + match.value.dropLast(1))
                    }
                }
            }
            'v' -> {
                if (warehouse[(x)+ w*(y+1)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x)+ w*(y+1), (x)+ w*(y+1)+1, "@")
                } else if (warehouse[(x)+ w*(y+1)] == 'O') {
                    val pR = "@(.{" + (w-1).toString() + "}O)+.{" + (w-1).toString() + "}\\."
    				val pD = pR.toRegex()  //"""@(.{7}O)+.{7}\.""".toRegex()
                    val matchD = pD.find(warehouse)
                    if (matchD != null) {
                    	warehouse = warehouse.replaceRange(x + w*y, x + w*y + 1, ".")
                        warehouse = warehouse.replaceRange(x + w*(y+1), x + w*(y+1) + 1, "@")
                        warehouse = warehouse.replaceRange(x + w*y + matchD.value.length-1, x + w*y + matchD.value.length-1 + 1, "O")
                    }
                }
            }
            '<' -> {
                if (warehouse[(x-1)+ w*(y)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x-1)+ w*(y), (x-1)+ w*(y)+1, "@")
                } else if (warehouse[(x-1)+ w*(y)] == 'O') {
                    val pR = """\.O+@""".toRegex()
                    val match = pR.find(warehouse)
                    if (match != null) {
                    	warehouse = warehouse.replace(match.value, match.value.drop(1) + ".")
                    }
                }
            }
        } 
    }
        
    var result = 0L    
    for (y in 0..h-1) {
        for (x in 0..w-1) {
             if (warehouse[x +  w*y] == 'O') {
                result += ( x + 100 * y).toLong()

            }           
        }
    }
    return result
}

fun main() {

    var t1 = System.currentTimeMillis()
   
    println("--- Day 15: Warehouse Woes ---")
    
    var puzzleInput1 = ""
    var puzzleInput2 = ""
    var width = 0
    var height = 0

    File("day2415_puzzle_input.txt").forEachLine {
        if (it.contains('#') || it.contains('.') || it.contains('O')) {
            puzzleInput1 += it
            width = it.length
        } else if (it.contains('^') || it.contains('>') ||it.contains('v') ||it.contains('<')) {
            puzzleInput2 += it
        }
    }
    height = puzzleInput1.length/width
     
    var solution1 = warehouse(puzzleInput1, puzzleInput2, width, height)

    println("  part1: the sum of all boxes' GPS coordinates is $solution1")

    var solution2 = warehouse2(puzzleInput1, puzzleInput2, width, height)
    println("  part2: the sum of all boxes' GPS coordinates is $solution2")

    t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
