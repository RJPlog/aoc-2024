import java.io.File
import kotlin.math.*

fun MullItOver(): Int {	
	var result = 0
	File("day2403_puzzle_input.txt").forEachLine {
		val pattern = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
		val matchResults = pattern.findAll(it)
		matchResults.forEach {
			val(mul1,mul2) = it.destructured
			result += mul1.toInt() * mul2.toInt()
		}
	} 
	return result
}

fun MullItOver2(): Int {	
	var result = 0
	var memory = ""
	File("day2403_puzzle_input.txt").forEachLine {
		memory += it
	}

	val substrings = ("do()" + memory + "don't()").split("do()")
		substrings.forEach{
			val b = it.substringBefore("don't()")
			println(b)
			val pattern = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
			val matchResults = pattern.findAll(b)
			matchResults.forEach {
				val(mul1,mul2) = it.destructured
					result += mul1.toInt() * mul2.toInt()
			}
		}
	return result
}

fun main() {
	var t1 = System.currentTimeMillis()

	var solution1 = MullItOver()
	var solution2 = MullItOver2()

// print solution for part 1
	println("*******************************")
	println("--- Day 3: Mull It Over ---")
	println("*******************************")
	println("Solution for part1")
	println("   you get $solution1 if you add up all of the results of the multiplications")
	println()
// print solution for part 2
	println("*******************************")
	println("Solution for part2")
	println("   you get $solution2 if you add up all of the results of just the enabled multiplications")   // 70553436 is to high
	println()

	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
