import java.io.File

fun secrets(in1: List<Int>, part: Int = 0): Long {
    
    var result = 0L

    var overallPriceList = mutableListOf<String>()
	
    in1.forEach {
        var secret = it.toLong()
        var priceList = ""
        priceList += secret.toString().takeLast(1)
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
            var price = secret.toString().takeLast(1)
            priceList += price
        }
        overallPriceList.add(priceList)
        result += secret
    }

    if (part == 1) {
        return result
    }
    
    var bananas = 0
    for (m in -9..9) {
        for ( n in -9..9) {
            for (o in -9..9) {
                for (p in -9..9) {
                    var currentBananas = 0
                    overallPriceList.forEach {
                        var currentPriceList = it
                        var patternFound = false
                        currentPriceList.windowed(5) {
                            if (it[1].toInt() - it[0].toInt() == m ) {
                                if (it[2].toInt() - it[1].toInt() == n ) {
                                    if (it[3].toInt() - it[2].toInt() == o ) {
                                        if (it[4].toInt() - it[3].toInt() == p ) {
                                            if (!patternFound) {
                                                currentBananas += it[4].toString().toInt()
                                                patternFound = true
                                                println("$m$n$o$p: $currentBananas")
                                            }
                                            
                                        }
                                    }
                                }
                            }
                        }
                    }
                    println("$m $n $o $p max: $bananas currentBanans: $currentBananas")
                    if (currentBananas > bananas) bananas = currentBananas
                }
            }
        }
    }



    result= bananas.toLong()

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
    
    
    var solution1 = secrets(puzzleInput, 1)

    println("  part1: the sum of the 2000th secret number is $solution1")
    
    var solution2 = secrets(puzzleInput, 2)
    println("   part2: the most bananas you can get is $solution2")
    
  t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
