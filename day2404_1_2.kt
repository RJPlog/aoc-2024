fun ceres(in1: Int): Int {
    // today I only have access to kotlin's playground, so no reading of input file etc..
    // in order not to reveal puzzle data here only test input is shown
    
    val input = listOf("MMMSXXMASM",
					   "MSAMXMSMSA",
					   "AMXSXMAAMM",
					   "MSAMASMSMX",
					   "XMASAMXAMM",
					   "XXAMMXXAMA",
					   "SMSMSASXSS",
					   "SAXAMASAAA",
					   "MAMMMXMMMM",
					   "MXMXAXMASX")
    val width = input[0].length
    val height = input.size
    var result = 0 
    
	// find horizontal 
	input.forEach{
       result += it.windowed(4).filter() {it == "XMAS" || it == "SAMX"}.count()
    }
    
    // find vertical
    for (y in 0..height-4) {
        for (x in 0..width - 1) {
            val testString = input[y][x].toString() + input[y+1][x].toString() + input[y+2][x].toString() + input[y+3][x].toString()
            if (testString == "XMAS" || testString == "SAMX") {
                result += 1
            }
        }
    }
    
    // find diagonal left to right
    for (y in 0..height-4) {
        for (x in 0..width - 4) {
            val testString = input[y][x].toString() + input[y+1][x+1].toString() + input[y+2][x+2].toString() + input[y+3][x+3].toString()
            if (testString == "XMAS" || testString == "SAMX") {
                result += 1
            }
        }
    }   
    
    // find diagonal right to left
    for (y in 0..height-4) {
        for (x in 3..width - 1) {
            val testString = input[y][x].toString() + input[y+1][x-1].toString() + input[y+2][x-2].toString() + input[y+3][x-3].toString()
            if (testString == "XMAS" || testString == "SAMX") {
                result += 1
            }
        }
    }  
    
    // count X-MAS appearance for part 2
    if (in1 == 2) {
        result = 0
        for (y in 1..height-2) {
            for (x in 1..width - 2) {
                val testString1 = input[y-1][x-1].toString() + input[y][x].toString() + input[y+1][x+1].toString()
                val testString2 = input[y-1][x+1].toString() + input[y][x].toString() + input[y+1][x-1].toString()
                if ((testString1 == "MAS" || testString1 == "SAM")  && (testString2 == "MAS" || testString2 == "SAM")) {
                    result += 1
                }
            }
        }          
    }
    return result
}

fun main() {  
    println("--- Day 4: Ceres Search ---")
    println()
    println("    solution part1: XMAS appears ${ceres(1)} times") 
    println("    solution part2: X-MAS appears ${ceres(2)} times") 
}
