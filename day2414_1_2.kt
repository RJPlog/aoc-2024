fun redoubt(puzzleInput: String, w: Int, h: Int, n: Int, part: Int): Int {
	var q1 = 0
    var q2 = 0
    var q3 = 0
    var q4 = 0
    
    // part 2 
    var robots = mutableListOf<Int>()
    
    puzzleInput.split(";").forEach{
        val instruction = it.split(",")
        val px = instruction[0].substringAfter("=").toString().toInt()
        val py = instruction[1].substringBefore(" ").toString().toInt()
        val vx = instruction[1].substringAfter("=").toString().toInt()
        val vy = instruction[2].toString().toInt().toString().toInt()
        
        //print("$px, $py, $vx, $vy")
       
        
        
       
        var p100x = (px + vx * n) % w
        if (p100x < 0) p100x = w+p100x
        var p100y = (py + vy * n) % h
        if (p100y < 0) p100y = h+p100y
        
        if (part == 2) robots.add(p100x + w*p100y)
        
        //println("   -> $p100x, $p100y")
         
        if (p100x < w/2 && p100y < h/2) q1 += 1
        if (p100x > w/2 && p100y < h/2) q2 += 1
        if (p100x < w/2 && p100y > h/2) q3 += 1
        if (p100x > w/2 && p100y > h/2) q4 += 1
    }
    
    if (part == 2) {
        for (y in 0..h-1) {
            for (x in 0..w-1) {
                if (robots.contains(x + w*y)) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
	}	
    
    return q1*q2*q3*q4
}

fun main() {
   
    println("--- Day 14: Restroom Redoubt ---")
    
    var puzzleInput = listOf("p=0,4 v=3,-3",
"p=6,3 v=-1,-3",
"p=10,3 v=-1,2",
"p=2,0 v=2,-1",
"p=0,0 v=1,3",
"p=3,0 v=-2,-2",
"p=7,6 v=-1,-3",
"p=3,0 v=-1,-2",
"p=9,3 v=2,3",
"p=7,3 v=-1,2",
"p=2,4 v=2,-3",
"p=9,5 v=-3,-3")
    
    
    val width = 11  //  11  - 101
    val height = 7    //  7  - 103 
        
    var solution1 = redoubt(puzzleInput.joinToString(";"), width, height, 100, 1)
    var solution2 = 0

    println("  part1: the safety factor after exactly 100 seconds is $solution1")
    
  //for (i in 0..1000) {
  //      var solution2 = redoubt(puzzleInput.joinToString(";"), width, height, i, 2)
  //  }
    //println("  part2: the fewest tokens you would have to spend is $solution2")
}
