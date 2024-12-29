import java.io.File

data class PathX(
    var path: List<Int>,
    var score: Int,
    var lastDir: Char
)

fun maze(puzzleInput: String, w: Int, h: Int, part: Int): Int {
      
    // initialize all necessary variables for Dijkstra
    var Q = mutableMapOf<Int,List<Int>>()  // id -> dist, previous, direction (N = 0, E = 1, S = 2, W = 3)
    var allNodes = mutableMapOf<Int,List<Int>>()
    var startIndex = puzzleInput.indexOf("S")
    for (i in 0..puzzleInput.length-1) {
        if (puzzleInput[i] != '#') {
            var node = listOf(w*h*1000, 0, 10 )
            Q.put(i, node)
        }
    }
    Q.put(startIndex, listOf(0,0,1))
    
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
    
    var endIndex = puzzleInput.indexOf("E")
    
    return allNodes.getValue(endIndex)[0]
}

fun maze2(pI: String, w: Int, h: Int, maxScore: Int): Int {

    var puzzleInput = pI
    var deadEnd = true
    while (deadEnd) {
        deadEnd = false
        for (y in 1..h-2) {
            for (x in 1..w-2) {
                if(puzzleInput[x + w*y] == '.') {
                    var count = 0
                    if (puzzleInput[(x) + w*(y-1)] == '#') count += 1
                    if (puzzleInput[(x+1) + w*(y)] == '#') count += 1
                    if (puzzleInput[(x) + w*(y+1)] == '#') count += 1
                    if (puzzleInput[(x-1) + w*(y)] == '#') count += 1
                    if (count >= 3) {
                        puzzleInput = puzzleInput.replaceRange(x+w*y, x+w*y+1, "#")
                        deadEnd = true
                    }
                    
                }
            }
        }
    }
    
        var startIndex = puzzleInput.indexOf("S")
        var endIndex = puzzleInput.indexOf("E")
        var allPath = mutableListOf<PathX>()
        var path2End = mutableListOf<PathX>()
        allPath.add(PathX(listOf(startIndex), 0, '>'))
        
        var i = 0
        while (!allPath.isEmpty()) {
            i += 1
            var allPathNew = mutableListOf<PathX>()
            allPath.forEach{
                var path = it.path
                var pX = path[it.path.size-1] % w
                var pY = path[it.path.size-1] / w
                var score = it.score
                var dir = it.lastDir
				var newScore = 0
				var newDir = '.'
				
				// create new paths
				if (puzzleInput[(pX) + w * (pY-1)] != '#' && !path.contains((pX)+w*(pY-1))) {
                    var newPath = mutableListOf<Int>()
                    newPath.addAll(path)
                    newPath.add((pX)+w*(pY-1))
                    newDir = '^'

                    if (dir == '^') {
                        newScore = 1
                    } else if (dir == '>' || dir == '<') {
                    	newScore = 1001
                    } else {
                        newScore = 2001
                    }
                    if ((pX) + w * (pY-1) == endIndex && score + newScore <= maxScore) {
                        path2End.add(PathX(newPath, score+newScore, newDir))
                    } else if (score + newScore <= maxScore) {
                    	allPathNew.add(PathX(newPath, score+newScore, newDir))
                    }
                }
                if (puzzleInput[(pX+1) + w * (pY)] != '#' && !path.contains((pX+1)+w*(pY))) {
                    var newPath = mutableListOf<Int>()
                    newPath.addAll(path)
                    newPath.add((pX+1)+w*(pY))
                    newDir = '>'

                    if (dir == '>') {
                    	newScore = 1
                    } else if (dir == '^' || dir == 'v') {
                    	newScore = 1001
                    } else {
                        newScore = 2001
                    }
                    if ((pX+1) + w * (pY) == endIndex && score + newScore <= maxScore) {
                        path2End.add(PathX(newPath, score+newScore, newDir))
                    } else if (score + newScore <= maxScore) {
                    	allPathNew.add(PathX(newPath, score+newScore, newDir))
                    }
                }
                if (puzzleInput[(pX) + w * (pY+1)] != '#' && !path.contains((pX)+w*(pY+1))) {
                    var newPath = mutableListOf<Int>()
                    newPath.addAll(path)
                    newPath.add((pX)+w*(pY+1))
                    newDir = 'v'

                    if (dir == 'v') {
                    	newScore = 1
                    } else if (dir == '>' || dir == '<') {
                    	newScore = 1001
                    } else {
                        newScore = 2001
                    }
                    if ((pX) + w * (pY+1) == endIndex && score + newScore <= maxScore) {
                        path2End.add(PathX(newPath, score+newScore, newDir))
                    } else if (score + newScore <= maxScore) {
                    	allPathNew.add(PathX(newPath, score+newScore, newDir))
                    }
                }
                if (puzzleInput[(pX-1) + w * (pY)] != '#' && !path.contains((pX-1)+w*(pY))) {
                    var newPath = mutableListOf<Int>()
                    newPath.addAll(path)
                    newPath.add((pX-1)+w*(pY))
                    newDir = '<'

                    if (dir == '<') {
                    	newScore = 1
                    } else if (dir == '^' || dir == 'v') {
                    	newScore = 1001
                    } else {
                        newScore = 2001
                    }
                    if ((pX-1) + w * (pY) == endIndex && score + newScore <= maxScore) {
                        path2End.add(PathX(newPath, score+newScore, newDir))
                   } else if (score + newScore <= maxScore) {
                    	allPathNew.add(PathX(newPath, score+newScore, newDir))
                    }
                }
            }
            // exchange pathes
            println("$i   -> ${allPathNew.size}, ${path2End.size}")
            allPath.clear()
            allPath.addAll(allPathNew)
        }
        
        var modPuzzleInput = puzzleInput
    	path2End.forEach {
            it.path.forEach {
                modPuzzleInput = modPuzzleInput.replaceRange(it, it+1, "O")
            }
        }
        
        /* modPuzzleInput.chunked(w){
            println(it)
        } */
    
    return modPuzzleInput.count {it == 'O'}
}


fun main() {
   
    println("--- Day 16: Reindeer Maze ---")
	
    var puzzleInput = mutableListOf<String>()
	
	File("day2416_puzzle_input.txt").forEachLine {
		puzzleInput.add(it)
	}
     
    var width = puzzleInput[0].length
    var height = puzzleInput.size
    
    var solution1 = maze(puzzleInput.joinToString(""), width, height, 1)
    println("  part1: the lowest score a Reindeer could possibly get is $solution1")
	
	var solution2 = maze2(puzzleInput.joinToString(""), width, height, solution1)
    println("   part2: $solution2 tiles are part of at least one of the best paths")
}
