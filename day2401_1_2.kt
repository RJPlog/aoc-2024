import java.io.File
import kotlin.math.*

fun hysteria(in1: Int): Int {
	var groupOne = mutableListOf<Int>()
	var groupTwo = mutableListOf<Int>()
	File("day2501_puzzle_input.txt").forEachLine {
		var line = it.split("   ")
		groupOne.add(line[0].toInt())
		groupTwo.add(line[1].toInt())
	}
	groupOne.sort()
	groupTwo.sort()
	
	var result = 0
	
	for(i in 0..groupOne.size-1) {
		if (in1 == 1) {
			result += abs(groupOne[i] - groupTwo[i])
		} else {
			result += groupOne[i] * groupTwo.count() {it == groupOne[i]}
		}
	}

	return result
}


fun main() {
	var t1 = System.currentTimeMillis()

	var solution1 = hysteria(1)
	var solution2 = hysteria(2)

// print solution for part 1
	println("*******************************")
	println("--- Day 1: Historian Hysteria ---")
	println("*******************************")
	println("Solution for part1")
	println("   $solution1 is the total distance between your lists")
	println()
// print solution for part 2
	println("*******************************")
	println("Solution for part2")
	println("   $solution2 is their similarity score")
	println()

	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
