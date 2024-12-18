fun ramRun(puzzleInput: String, w: Int, h: Int, part: Int): Int {
    
    // initialize all necessary variables for Dijkstra
    var Q = mutableMapOf<Int,List<Int>>()  // id -> dist, previous
    var allNodes = mutableMapOf<Int,List<Int>>()
    var startIndex = puzzleInput.indexOf("S")
    for (i in 0..puzzleInput.length-1) {
        if (puzzleInput[i] != '#') {
            var node = listOf(w*h*1000, 0)
            Q.put(i, node)
        }
    }
    Q.put(startIndex, listOf(0,0))
    
   // println(Q)
   // println(allNodes)
    
    var j = 0
    while (Q.size > 0) {
        // take node with shortest distance
        var idU = 0
        var distU = w*h*1000
        for ((key, value) in Q) {
            if (value[0] < distU) {
                idU = key
                distU = value[0]
            }
        }
        allNodes.put(idU, listOf(distU,idU))
        Q.remove(idU)
        
        // for each neigbour of U
        var xU = idU % w
        var yU = idU / w
        if (Q.containsKey((xU) + w * (yU-1))) {
            var distance = distU
                distance += 1
            if (distance < Q.getValue((xU) + w * (yU-1))[0]) Q.put((xU) + w * (yU-1), listOf(distance, idU))
        } 
        if (Q.containsKey((xU+1) + w * (yU))) {
            var distance = distU
                distance += 1
            if (distance < Q.getValue((xU+1) + w * (yU))[0]) Q.put((xU+1) + w * (yU), listOf(distance, idU))
        }
        if (Q.containsKey((xU) + w * (yU+1))) {
            var distance = distU
                distance += 1
            if (distance < Q.getValue((xU) + w * (yU+1))[0]) Q.put((xU) + w * (yU+1), listOf(distance, idU))
        }
        if (Q.containsKey((xU-1) + w * (yU))) {
            var distance = distU
                distance += 1
            if (distance < Q.getValue((xU-1) + w * (yU))[0]) Q.put((xU-1) + w * (yU), listOf(distance, idU))
        }
  //      println()
 //   println(Q)
 //   println(allNodes) 
        j += 1
    }
    
    var endIndex = puzzleInput.indexOf("E")
    
    return allNodes.getValue(endIndex)[0]
}


fun main() {
   
    println("--- Day 18: RAM Run ---")
    
    var puzzleInput = listOf("5,4",
"4,2",
"4,5",
"3,0",
"2,1",
"6,3",
"2,4",
"1,5",
"0,6",
"3,3",
"2,6",
"5,1")
     
    var w = 6 // 70
    var h = 6  // 70
    
    var ramSpace = ""
    
    for (y in 0..h+2) {
        for (x in 0..w+2) {
         	if (x == 0 || x == w+2 || y == 0 || y == h+2) {
                ramSpace +="#"
            } else if (x == 1 && y == 1) {
                ramSpace += "S"
            } else if (x == w+1 && y == h+1) {
                ramSpace += "E"
             } else{
                var coord = (x-1).toString() + "," + (y-1).toString()
                if (puzzleInput.contains(coord)) {
                    ramSpace += "#"
                } else {
                  ramSpace +="."                  
                }

            }   
        }
    }

    ramSpace.chunked(w+2+1) {
        println(it)
    }
    println()
    if (false) {
        for (y in 0..h+2) {
            for (x in 0..w+2) {
                print(ramSpace[x + (w+3)*y])   
            }
            println()
        }
        println()
    }
    
    for (y in 1..h+1) {
        for (x in 1..w+1) {
            if ( ramSpace[(x) + (w+3)*(y-1)] == '#' && ramSpace[(x+1) + (w+3)*(y)] == '#' && ramSpace[(x) + (w+3)*(y+1)] == '#' && ramSpace[(x-1) + (w+3)*(y)] == '#' ) {
                ramSpace = ramSpace.replaceRange((x) + (w+3)*(y), (x) + (w+3)*(y)+1, "#")
            }
        }
    }
        ramSpace.chunked(w+2+1) {
        println(it)
    }
    
    
    
    var solution1 = ramRun(ramSpace, w+2+1, h+2+1, 1)

    println("  part1: the lowest score a Reindeer could possibly get is $solution1")
}
