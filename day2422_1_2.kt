import java.io.File

fun secrets(in1: List<Int>): Long {
    
    var result = 0L
	
    in1.forEach {
        var secret = it.toLong()
        for (i in 0..1999) {
            // x 64 - mix - prune
            secret = ((secret * 64) xor secret) 
            secret = secret % 16777216
            // / 32 - trunc - mix - prune
            secret = ((32 * (secret / 32) / 32) xor secret)
            secret = secret % 16777216
            // x 2048
            secret = ((secret * 2048) xor secret) 
            secret = secret % 16777216
            //println(secret)
        }
        result += secret
    }
    return result
}

fun main() {
    
     var t1 = System.currentTimeMillis()
    
    println("--- Day 22: Monkey Market ---")
    
    var puzzleInput = mutableListOf<Int>()

    File("day2422_puzzle_input.txt").forEachLine {
        puzzleInput.add(it.toInt())
    }
    
    //puzzleInput = listOf(10)
    
    
    var solution1 = secrets(puzzleInput)

    println("  part1: the sum of the 2000th secret number is $solution1")
    
//    var solution2 = maze(puzzleInput.joinToString(""), width, height, 2)
//    println("   part2: $solution2 tiles are part of at least one of the best paths")
    
  t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
