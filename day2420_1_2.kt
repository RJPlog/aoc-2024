import java.io.File

fun cheatRace(puzzleInput: String, w: Int, h: Int, part: Int): Int {
    
    // initialize all necessary variables for Dijkstra
    var Q = mutableMapOf<Int,List<Int>>()  // id -> dist, previous
    var allNodes = mutableMapOf<Int,List<Int>>()
    var startIndex = puzzleInput.indexOf("S")
    var endIndex = puzzleInput.indexOf("E")
    for (i in 0..puzzleInput.length-1) {
        if (puzzleInput[i] != '#') {
            var node = listOf(w*h*1000, 0)
            Q.put(i, node)
        }
    }
    Q.put(startIndex, listOf(0,0))
    
    var j = 0
    while (Q.size > 0 && !allNodes.containsKey(endIndex)) {
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
        j += 1
    }
 
    return allNodes.getValue(endIndex)[0]
}

fun main() {
   
    println("--- Day 20: Race Condition ---")

    var puzzleInput = mutableListOf<String>()

    File("day2420_puzzle_input.txt").forEachLine {
        puzzleInput.add(it)
    }
     
    var width = puzzleInput[0].length
    var height = puzzleInput.size
    
    var solution1 = cheatRace(puzzleInput.joinToString(""), width, height, 1)

    println("  part1: $solution1 cheats would save you at least 100 picoseconds")
}
