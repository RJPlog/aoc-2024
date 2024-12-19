import java.io.File

fun main() {
    var t1 = System.currentTimeMillis()

    var i = 0
    var availablePatterns = ""
    var patterns2Create = mutableListOf<String>()
    File("day2419_puzzle_input.txt").forEachLine {
        if (i == 0) {
            availablePatterns = it
        } else if (i > 1) {
            patterns2Create.add(it)
        }
        i += 0
    }

    println(availablePatterns)
    println(patterns2Create)

    println("--- Day 19: Linen Layout ---")
    
    

    println("  part1: designs are possible") 
    
    
    //println("  part2:  i")
    
    t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
