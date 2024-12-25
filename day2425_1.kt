import java.io.File

fun main() {
  var t1 = System.currentTimeMillis()

  println("--- Day 25: Code Chronicle ---")
    
  var keys = mutableListOf<MutableList<Int>>()
  var locks = mutableListOf<MutableList<Int>>()
    
  File("day2425_puzzle_input.txt").forEachLine {
    println(it)
	}

	var solution1 = 1

  println("  part1:  $solution1 unique lock/key pairs fit together")
    
  t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
