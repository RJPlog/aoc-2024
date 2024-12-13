fun contraption(instructionA: String, instructionB: String, price: String): Long {
    var AX = instructionA.split(",")[0].substringAfter("+").toLong()
    var AY = instructionA.split(",")[1].substringAfter("+").toLong()
    
    var BX = instructionB.split(",")[0].substringAfter("+").toLong()
    var BY = instructionB.split(",")[1].substringAfter("+").toLong()
    
    var PX = price.split(",")[0].substringAfter("=").toLong()
    var PY = price.split(",")[1].substringAfter("=").toLong()
    
    var result = 3L*100 + 1*100 + 1
    
    for (a in 0..99) {
        for (b in 0..99) {
            if (AX * a + BX * b == PX && AY * a + BY * b == PY  ) {
                if (a * 3L + b * 1 < result) result = a * 3L + b * 1
            }
        }
    }
    
    if (result < 3*100 + 1*100 + 1) {
        return result
    } else {
        return 0
    }
}

fun main() {
   
    println("--- Day 13: Claw Contraption ---")
    
    var puzzleInput = listOf("Button A: X+94, Y+34",
"Button B: X+22, Y+67",
"Prize: X=8400, Y=5400",
"",
"Button A: X+26, Y+66",
"Button B: X+67, Y+21",
"Prize: X=12748, Y=12176",
"",
"Button A: X+17, Y+86",
"Button B: X+84, Y+37",
"Prize: X=7870, Y=6450",
"",
"Button A: X+69, Y+23",
"Button B: X+27, Y+71",
"Prize: X=18641, Y=10279")
    
    
    puzzleInput = listOf()

	var solution1 = 0L    
    
	puzzleInput.chunked(4){
    	solution1 += contraption(it[0], it[1], it[2])
	}

    println("  part1: the fewest tokens you would have to spend is $solution1")
}
