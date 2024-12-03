import java.io.File
import kotlin.math.*

fun safeReport(): Int {	
	var result = 0
	File("day2402_puzzle_input.txt").forEachLine {
		var line = it.split(" ").windowed(2).map() {it[1].toInt() - it[0].toInt()}
		if (line.filter {it !in listOf(1,2,3)}.count() == 0) {
			result += 1
		}
		if (line.filter {it !in listOf(-1,-2,-3)}.count() == 0) {
			result += 1
		}	
	} 
	return result
}

fun safeReportMod(): Int {
	var result = 0
	File("day2502_puzzle_input.txt").forEachLine {
		var line = it.split(" ").map() {it.toInt()}
		for (i in 0..line.size-1) {
			var lineMod = mutableListOf<Int>()
			lineMod.addAll(line)
			lineMod.removeAt(i)
			var gradients = lineMod.windowed(2).map() {it[1].toInt() - it[0].toInt()}
			if (gradients.filter {it !in listOf(1,2,3)}.count() == 0) {
				result += 1
				break
			}
			if (gradients.filter {it !in listOf(-1,-2,-3)}.count() == 0) {
				result += 1
				break
			}
		}	
	} 
	return result
}

fun main() {
	var t1 = System.currentTimeMillis()

	var solution1 = safeReport()
	var solution2 = safeReportMod()

// print solution for part 1
	println("*******************************")
	println("--- Day 2: Red-Nosed Reports ---")
	println("*******************************")
	println("Solution for part1")
	println("   $solution1 reports are safe")
	println()
// print solution for part 2
	println("*******************************")
	println("Solution for part2")
	println("   $solution2 reports are safe")
	println()

	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
