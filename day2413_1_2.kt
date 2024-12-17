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

fun contraption2(instructionA: String, instructionB: String, price: String): Long {

    var AX = instructionA.split(",")[0].substringAfter("+").toLong()
    var AY = instructionA.split(",")[1].substringAfter("+").toLong()
    
    var BX = instructionB.split(",")[0].substringAfter("+").toLong()
    var BY = instructionB.split(",")[1].substringAfter("+").toLong()
    
    var PX = price.split(",")[0].substringAfter("=").toLong() + 10000000000000
    var PY = price.split(",")[1].substringAfter("=").toLong() + 10000000000000
    
    // get PX with whatever it costs
    var a = PX / AX
    var b = 0L
    var PXcurrent = a * AX
    
    var i  = 0
    while (PX - PXcurrent != 0L) {
    //println("PX: $PX, PXcurrent: $PXcurrent Diff: ${PX -PXcurrent}, a: $a, b: $b")
        if (PX- PXcurrent > 0) {
            b += 1
        } else {
            a -= 1
        }
    PXcurrent = a * AX + b* BX
        i += 1
        if (i > 100000) {
            //println("    schon im ersten Schuß nichts gefunden")
            return 0
        }
    }
    //println("Dff ${PX-PXcurrent}, a: $a, b: $b")
    
    // now adjust PY by exchanging blocks with common mulitple
    // this idea seems not to work, since it is not possible to get a solution even with the first example. 
    var fA = 1
    var fB = 1
    while (AX * fA != BX * fB) {
        if (BX * fB < AX * fA) {
            fB += 1
        } else {
            fA += 1
        }
    }
    //println("fA $fA, fB: $fB")
    
    var PYcurrent = a * AY + b * BY
    var learnSign = 0

    var PYminus3 = 1L
    var PYminus2 = 2L
    var PYminus1 = 3L

    var j = 0
    var learnLength = (PYcurrent-PY).toString().length- 4
    if (learnLength <= 0) learnLength = 0
    var learn = "1".padEnd(learnLength,'0').toLong()
    while (PYcurrent != PY) {
        if (PYcurrent > PY && AY*fA > BY*fB) learnSign = 1  // share AY must be reduced
        if (PYcurrent > PY && AY*fA < BY*fB) learnSign = -1 // share BY must be reduced
        if (PYcurrent < PY && AY*fA > BY*fB) learnSign = -1  // share AY must be increased
        if (PYcurrent < PY && AY*fA < BY*fB) learnSign = 1  // share BY must be increased 
        a -= fA * learn * learnSign
        b += fB * learn * learnSign
        
        PYminus3 = PYminus2
        PYminus2 = PYminus1
        PYminus1 = PYcurrent
        PYcurrent = a* AY + b * BY
        //println(" learn: $learn  diff PY: ${PY - PYcurrent} diff PX: ${PX - (a * AX + b* BX)}, a: $a, b: $b")
        if (PYcurrent == PYminus2 && PYminus1 == PYminus3) {
            //println("   sorry, no price")
            return 0
        }
        j += 1
        learnLength = (PYcurrent-PY).toString().length- 4
        if (learnLength <= 0) learnLength = 0
        learn = "1".padEnd(learnLength,'0').toLong()
    }
    //println("   diff PY: ${PY - (a * AY + b* BY)} diff PX: ${PX - (a * AX + b* BX)}  -> you have to spent ${a * 3L + b* 1L}")
    //println("$PYcurrent, $PYminus1, $PYminus2, $PYminus3")
    
    
    //println()
	return a * 3L + b* 1L
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
    

    var solution1 = 0L    
    
	puzzleInput.chunked(4){
    	solution1 += contraption(it[0], it[1], it[2])
	}

    println("  part1: the fewest tokens you would have to spend is $solution1")


	var solution2 = 0L    
    
	puzzleInput.chunked(4){
        //println(it[0])
        //println(it[1])
        //println(it[2])
    	solution2 += contraption2(it[0], it[1], it[2])
        //println()
	}

    println("  part2: the fewest tokens you would have to spend is $solution2")
}
