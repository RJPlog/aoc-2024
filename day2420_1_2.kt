//  sudo apt-get update && sudo apt-get install kotlin
//  kotlinc day2420_1_2.kt -include-runtime -d day2420_1_2.jar && java -jar day2420_1_2.jar

import java.io.File
import kotlin.math.*

fun cheatRace(puzzleInput: String, w: Int, h: Int, cheat: Int, part: Int): Int {

    var pI = puzzleInput
    val startIndex = pI.indexOf("S")
    val endIndex = pI.indexOf("E")

    // create race track
    var racePath = mutableListOf<Int>()
    racePath.add(startIndex)
    var finishReached = false
    var currentPos = startIndex

    while (!finishReached) {
        val x = currentPos % w
        val y = currentPos / h
        if (pI[(x+1)+w*(y)] == '.') {
            racePath.add((x+1)+w*(y))
            pI = pI.replaceRange((x+1)+w*(y), (x+1)+w*(y)+1, "S")
        } else if (pI[(x)+w*(y+1)] == '.') {
            racePath.add((x)+w*(y+1))
            pI = pI.replaceRange((x)+w*(y+1), (x)+w*(y+1)+1, "S")
        } else if (pI[(x-1)+w*(y)] == '.') {
            racePath.add((x-1)+w*(y))
            pI = pI.replaceRange((x-1)+w*(y), (x-1)+w*(y)+1, "S")
        } else if (pI[(x)+w*(y-1)] == '.') {
            racePath.add((x)+w*(y-1))
            pI = pI.replaceRange((x)+w*(y-1), (x)+w*(y-1)+1, "S")
        } 
        if (!pI.contains(".")) finishReached = true
        currentPos = racePath.last()
    }
    racePath.add(endIndex)

    var result = 0

    // set startpoint for shortcut
    for (i in 0..racePath.size-1) {
        // set endpoint for shortcut
        for (j in i+1..racePath.size-1) {
            // calc manhatten dist of shortcut
            val manDist = abs((racePath[i]%w)  - (racePath[j]%w)) + abs((racePath[i]/w)  - (racePath[j]/w))
            // calc origin dist
            val originDist = j - i

            if (originDist - manDist >= 100 && manDist <= cheat) result += 1
        }
    }
    return result
}
    
fun main() {

    var t1 = System.currentTimeMillis()
   
    println("--- Day 20: Race Condition ---")

    var puzzleInput = mutableListOf<String>()
    File("day2420_puzzle_input.txt").forEachLine {
        puzzleInput.add(it)
    }

    var width = puzzleInput[0].length
    var height = puzzleInput.size
 
    var solution1 = cheatRace(puzzleInput.joinToString(""), width, height, 2, 1)
    println("  part1: $solution1 cheats would save you at least 100 picoseconds")

    var solution2 = cheatRace(puzzleInput.joinToString(""), width, height, 20, 2)
    println("  part2: $solution2 cheats would save you at least 100 picoseconds")

    t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
