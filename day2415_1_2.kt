fun warehouse(puzzleInput1: String, puzzleInput2:String, w: Int, h: Int, part: Int): Int {
	var warehouse = puzzleInput1
    puzzleInput2.forEach {
        var x = warehouse.indexOf('@') % w
        var y = warehouse.indexOf('@') / w
        
        when (it) {     			
            '^' -> {
                if (warehouse[(x)+ w*(y-1)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x)+ w*(y-1), (x)+ w*(y-1)+1, "@")
                } else if (warehouse[(x)+ w*(y-1)] == 'O') {
                    // x
                }
                println("up")
            }
            '>' -> {
                if (warehouse[(x+1)+ w*(y)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x+1)+ w*(y), (x+1)+ w*(y)+1, "@")
                } else if (warehouse[(x+1)+ w*(y)] == 'O') {
                    // x
                }
                println("right")}
            'v' -> {
                if (warehouse[(x)+ w*(y+1)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x)+ w*(y+1), (x)+ w*(y+1)+1, "@")
                } else if (warehouse[(x-1)+ w*(y)] == 'O') {
                    // x
                }
                println("down")}
            '<' -> {
                println("left")
                if (warehouse[(x-1)+ w*(y)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x-1)+ w*(y), (x-1)+ w*(y)+1, "@")
                } else if (warehouse[(x-1)+ w*(y)] == 'O') {
                    // x
                }
            }
        }
        warehouse.chunked(w).forEach{
            println(it)
        }
        println()
        
        
    }
    return -1
}

fun main() {
   
    println("--- Day 15: Warehouse Woes ---")
    
    var puzzleInput1 = listOf("########",
"#..O.O.#",
"##@.O..#",
"#...O..#",
"#.#.O..#",
"#...O..#",
"#......#",
"########")
    
    var puzzleInput2 = listOf("<^^>>>vv<v>>v<<")
    
    var width = puzzleInput1[0].length
    var height = puzzleInput1.size
    
 
    var solution1 = warehouse(puzzleInput1.joinToString(""), puzzleInput2.joinToString(""), width, height, 1)
    //var solution2 = 0L

    println("  part1: the safety factor after exactly 100 seconds is $solution1")

    //println("  part2: the fewest tokens you would have to spend is $solution2")
}
