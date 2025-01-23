import java.io.File

fun maze(puzzleInput: String, w: Int, h: Int, start: Int, end: Int, dir: Int, part: Int): Pair<Int, Int> {
      
    // initialize all necessary variables for Dijkstra
    var Q = mutableMapOf<Int,List<Int>>()  // id -> dist, previous, direction (N = 0, E = 1, S = 2, W = 3)
    var allNodes = mutableMapOf<Int,List<Int>>()
    var startIndex = start

    for (i in 0..puzzleInput.length-1) {
        if (puzzleInput[i] != '#') {
            var node = listOf(w*h*1000, 0, 10 )
            Q.put(i, node)
        }
    }
    Q.put(startIndex, listOf(0,0,dir))
    
    var j = 0
    while (Q.size > 0) {
        // take node with shortest distance
        var idU = 0
        var distU = w*h*1000
        var dirU = 10
        for ((key, value) in Q) {
            if (value[0] < distU) {
                idU = key
                distU = value[0]
                dirU = value[2]
            }
        }
        allNodes.put(idU, listOf(distU,idU,dirU))
        Q.remove(idU)
        
        // for each neigbour of U
        var xU = idU % w
        var yU = idU / w
        if (Q.containsKey((xU) + w * (yU-1))) {
            var distance = distU
            if (dirU == 0) {
                distance += 1
            } else if (dirU == 1 || dirU == 3) {
                distance += 1001
            } else if (dirU == 2) {
                distance += 2001
            }
            if (distance < Q.getValue((xU) + w * (yU-1))[0]) Q.put((xU) + w * (yU-1), listOf(distance, idU, 0))
        } 
        if (Q.containsKey((xU+1) + w * (yU))) {
            var distance = distU
            if (dirU == 1) {
                distance += 1
            } else if (dirU == 0 || dirU == 2) {
                distance += 1001
            } else if (dirU == 3) {
                distance += 2001
            }
            if (distance < Q.getValue((xU+1) + w * (yU))[0]) Q.put((xU+1) + w * (yU), listOf(distance, idU, 1))
        }
        if (Q.containsKey((xU) + w * (yU+1))) {
            var distance = distU
            if (dirU == 2) {
                distance += 1
            } else if (dirU == 1 || dirU == 3) {
                distance += 1001
            } else if (dirU == 0) {
                distance += 2001
            }
            if (distance < Q.getValue((xU) + w * (yU+1))[0]) Q.put((xU) + w * (yU+1), listOf(distance, idU, 2))
        }
        if (Q.containsKey((xU-1) + w * (yU))) {
            var distance = distU
            if (dirU == 3) {
                distance += 1
            } else if (dirU == 0 || dirU == 2) {
                distance += 1001
            } else if (dirU == 1) {
                distance += 2001
            }
            if (distance < Q.getValue((xU-1) + w * (yU))[0]) Q.put((xU-1) + w * (yU), listOf(distance, idU, 3))
        }
         
        j += 1
    }
    
    var endIndex = end

    return Pair(allNodes.getValue(endIndex)[0], allNodes.getValue(endIndex)[2])
}

fun main() {
    var t1 = System.currentTimeMillis()
   
    println("--- Day 16: Reindeer Maze ---")
	
    var puzzleInput = mutableListOf<String>()
	
	File("day2416_puzzle_input.txt").forEachLine {
		puzzleInput.add(it)
	}

    var pI = puzzleInput.joinToString("")

    var width = puzzleInput[0].length
    var height = puzzleInput.size
     
    var deadEnd = true
    while (deadEnd) {
        deadEnd = false
        for (y in 1..height-2) {
            for (x in 1..width-2) {
                if(pI[x + width*y] == '.') {
                    var count = 0
                    if (pI[(x) + width*(y-1)] == '#') count += 1
                    if (pI[(x+1) + width*(y)] == '#') count += 1
                    if (pI[(x) + width*(y+1)] == '#') count += 1
                    if (pI[(x-1) + width*(y)] == '#') count += 1
                    if (count >= 3) {
                        pI = pI.replaceRange(x+width*y, x+width*y+1, "#")
                        deadEnd = true
                    }
                    
                }
            }
        }
    }
        
     

    var startIndex = pI.indexOf("S")
    var endIndex = pI.indexOf("E")
    var dir = 1
    
    var solution1 = maze(pI, width, height, startIndex, endIndex, dir, 1).first
    println("   part1: the lowest score a Reindeer could possibly get is $solution1")
	
    var solution2 = 2  // S and E already counted

    val dur = pI.count {it == '.'}
    var j = 1
    for (i in 0..pI.length-1) {
        if (pI[i] == '.') {
            println("$j out of ${dur}")
            j += 1
            val firstPath = maze(pI, width, height, startIndex, i, dir, 2)
            val firstPathLength = firstPath.first
            val firstPathDir = firstPath.second
            val secondPathLength = maze(pI, width, height, i, endIndex, firstPathDir, 2).first
            if (firstPathLength + secondPathLength == solution1 ) {
                solution2 += 1
            }
        }
    }

    println("   part2: $solution2 tiles are part of at least one of the best paths")

    t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
