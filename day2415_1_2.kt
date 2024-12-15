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
                    val pR = "\\.(.{" + (w-1).toString() + "}O)+.{" + (w-1).toString() + "}@"
    				val pD = pR.toRegex()  //"""\.(.{7}O)+.{7}@""".toRegex()
                    val matchD = pD.find(warehouse)
                    if (matchD != null) {
                    	warehouse = warehouse.replaceRange(x + w*y, x + w*y + 1, ".")
                        warehouse = warehouse.replaceRange(x + w*(y-1), x + w*(y-1) + 1, "@")
                        warehouse = warehouse.replaceRange(x + w*y - (matchD!!.value.length-1), x + w*y - (matchD!!.value.length-1) + 1, "O")
                    }
                }
                //println("up")
            }
            '>' -> {
                if (warehouse[(x+1)+ w*(y)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x+1)+ w*(y), (x+1)+ w*(y)+1, "@")
                } else if (warehouse[(x+1)+ w*(y)] == 'O') {
                    val pR = """@O+\.""".toRegex()
                    val match = pR.find(warehouse)
                    if (match != null) {
                    	warehouse = warehouse.replace(match!!.value, "." + match!!.value.dropLast(1))
                    }
                }
                //println("right")
                }
            'v' -> {
                if (warehouse[(x)+ w*(y+1)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x)+ w*(y+1), (x)+ w*(y+1)+1, "@")
                } else if (warehouse[(x)+ w*(y+1)] == 'O') {
                    val pR = "@(.{" + (w-1).toString() + "}O)+.{" + (w-1).toString() + "}\\."
    				val pD = pR.toRegex()  //"""@(.{7}O)+.{7}\.""".toRegex()
                    val matchD = pD.find(warehouse)
                    if (matchD != null) {
                    	warehouse = warehouse.replaceRange(x + w*y, x + w*y + 1, ".")
                        warehouse = warehouse.replaceRange(x + w*(y+1), x + w*(y+1) + 1, "@")
                        warehouse = warehouse.replaceRange(x + w*y + matchD!!.value.length-1, x + w*y + matchD!!.value.length-1 + 1, "O")
                    }
                }
                //println("down")
                }
            '<' -> {
                //println("left")
                if (warehouse[(x-1)+ w*(y)] == '.') {
                    warehouse = warehouse.replaceRange((x)+ w*(y), (x)+ w*(y)+1, ".")
                    warehouse = warehouse.replaceRange((x-1)+ w*(y), (x-1)+ w*(y)+1, "@")
                } else if (warehouse[(x-1)+ w*(y)] == 'O') {
                    val pR = """\.O+@""".toRegex()
                    val match = pR.find(warehouse)
                    if (match != null) {
                    	warehouse = warehouse.replace(match!!.value, match!!.value.drop(1) + ".")
                    }
                }
            }
        } 

    }
    warehouse.chunked(w).forEach{
            println(it)
        }
        println()
    return -1
}

fun main() {
   
    println("--- Day 15: Warehouse Woes ---")
    
    var puzzleInput1 = listOf("##########",
"#..O..O.O#",
"#......O.#",
"#.OO..O.O#",
"#..O@..O.#",
"#O#..O...#",
"#O..O..O.#",
"#.OO.O.OO#",
"#....O...#",
"##########")
    
    var puzzleInput2 = listOf("<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^",
"vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v",
"><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<",
"<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^",
"^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><",
"^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^",
">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^",
"<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>",
"^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>",
"v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^")
    
    var width = puzzleInput1[0].length
    var height = puzzleInput1.size
    
 
    var solution1 = warehouse(puzzleInput1.joinToString(""), puzzleInput2.joinToString(""), width, height, 1)

    println("  part1: the sum of all boxes' GPS coordinates is $solution1")

    //println("  part2: ")
}
