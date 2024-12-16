fun maze(puzzleInput: String, w: Int, h: Int, part: Int): Int {
      
    // initialize all necessary variables for Dijkstra
    var Q = mutableMapOf<Int,List<Int>>()  // id -> dist, previous, direction (N = 0, E = 1, S = 2, W = 3)
    var allNodes = mutableMapOf<Int,List<Int>>()
    var startIndex = puzzleInput.indexOf("S")
    for (i in 0..puzzleInput.length-1) {
        if (puzzleInput[i] != '#') {
            var node = listOf(w*h*1000, 0, 10 )
            Q.put(i, node)
        }
    }
    Q.put(startIndex, listOf(0,0,1))
    
    var j = 0
    while (Q.size > 0) {
        // take node with shortest distance
        var idU = 0
        var distU = w*h*1000
        var dirU = 10
        for ((key, value) in Q) {
            if (value[0] < distU) {
                idU = key
                distU = value[0]
                dirU = value[2]
            }
        }
        allNodes.put(idU, listOf(distU,idU,dirU))
        Q.remove(idU)
        
        // for each neigbour of U
        var xU = idU % w
        var yU = idU / w
        if (Q.containsKey((xU) + w * (yU-1))) {
            var distance = distU
            if (dirU == 0) {
                distance += 1
            } else if (dirU == 1 || dirU == 3) {
                distance += 1001
            } else if (dirU == 2) {
                distance += 2001
            }
            Q.put((xU) + w * (yU-1), listOf(distance, idU, 0))
        } 
        if (Q.containsKey((xU+1) + w * (yU))) {
            var distance = distU
            if (dirU == 1) {
                distance += 1
            } else if (dirU == 0 || dirU == 2) {
                distance += 1001
            } else if (dirU == 3) {
                distance += 2001
            }
            Q.put((xU+1) + w * (yU), listOf(distance, idU, 1))
        }
        if (Q.containsKey((xU) + w * (yU+1))) {
            var distance = distU
            if (dirU == 2) {
                distance += 1
            } else if (dirU == 1 || dirU == 3) {
                distance += 1001
            } else if (dirU == 0) {
                distance += 2001
            }
            Q.put((xU) + w * (yU+1), listOf(distance, idU, 2))
        }
        if (Q.containsKey((xU-1) + w * (yU))) {
            var distance = distU
            if (dirU == 3) {
                distance += 1
            } else if (dirU == 0 || dirU == 2) {
                distance += 1001
            } else if (dirU == 1) {
                distance += 2001
            }
            Q.put((xU-1) + w * (yU), listOf(distance, idU, 3))
        }
         
        j += 1
    }
    
    var endIndex = puzzleInput.indexOf("E")
    
    return allNodes.getValue(endIndex)[0]
}


fun main() {
   
    println("--- Day 16: Reindeer Maze ---")
    
    var puzzleInput = listOf("###############",
"#.......#....E#",
"#.#.###.#.###.#",
"#.....#.#...#.#",
"#.###.#####.#.#",
"#.#.#.......#.#",
"#.#.#####.###.#",
"#...........#.#",
"###.#.#####.#.#",
"#...#.....#.#.#",
"#.#.#.###.#.#.#",
"#.....#...#.#.#",
"#.###.#.#.#.#.#",
"#S..#.....#...#",
"###############")
    
    
 puzzleInput = listOf("#################",
"#...#...#...#..E#",
"#.#.#.#.#.#.#.#.#",
"#.#.#.#...#...#.#",
"#.#.#.#.###.#.#.#",
"#...#.#.#.....#.#",
"#.#.#.#.#.#####.#",
"#.#...#.#.#.....#",
"#.#.#####.#.###.#",
"#.#.#.......#...#",
"#.#.###.#####.###",
"#.#.#...#.....#.#",
"#.#.#.#####.###.#",
"#.#.#.........#.#",
"#.#.#.#########.#",
"#S#.............#",
"#################")   
    
    var width = puzzleInput[0].length
    var height = puzzleInput.size
    
    var solution1 = maze(puzzleInput.joinToString(""), width, height, 1)

    println("  part1: the lowest score a Reindeer could possibly get is $solution1")
}
