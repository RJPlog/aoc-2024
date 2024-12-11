fun pebbles2(in1: List<String>, in2: Int): Long {
    var stoneMap = mutableMapOf<String,Long>()

    // create a map containing all numbers on stones
    in1.forEach{
        if (stoneMap.containsKey(it)) {
            stoneMap.put(it, stoneMap.getValue(it) + 1)
        } else {
            stoneMap.put(it, 1)
        }
    }

    // iterate through map and transform stones, add up all stones with same number
    for (i in 0.. in2-1) {
        var stoneMapNew = mutableMapOf<String,Long>()

		for ((key,value) in stoneMap) {
            if(key == "0") {
                if (stoneMapNew.containsKey("1")) {
                    stoneMapNew.put("1", stoneMapNew.getValue("1")+stoneMap.getValue("0"))
                } else {
                    stoneMapNew.put("1", stoneMap.getValue("0"))
                }
            } else if (key.length % 2 == 0 ) {
                val firstStone = key.chunked(key.length/2)[0]
                var secondStone = key.chunked(key.length/2)[1]
                while (secondStone.length > 1 && secondStone[0] == '0') {
                    secondStone = secondStone.drop(1)
                }
                if (stoneMapNew.containsKey(firstStone)) {
                    stoneMapNew.put(firstStone, stoneMapNew.getValue(firstStone) + stoneMap.getValue(key))
                } else {
                    stoneMapNew.put(firstStone, stoneMap.getValue(key))
                }
                if (stoneMapNew.containsKey(secondStone)) {
                    stoneMapNew.put(secondStone, stoneMapNew.getValue(secondStone) + stoneMap.getValue(key))
                } else {
                    stoneMapNew.put(secondStone, stoneMap.getValue(key))
                }
            } else {
                var newKey = (key.toLong()*2024).toString()
                if (stoneMapNew.contains(newKey)) {
                    stoneMapNew.put(newKey, stoneMapNew.getValue(newKey) + stoneMap.getValue(key))
                    stoneMap.put(newKey, 0)
                } else {
                    stoneMapNew.put(newKey, stoneMap.getValue(key))
                }
            }
        }
        stoneMap.clear()
        stoneMap.putAll(stoneMapNew)
        stoneMapNew.clear()
    }
    return stoneMap.values.sum()
}


fun main() {
	var t1 = System.currentTimeMillis()

    println("--- Day 11: Plutonian Pebbles ---")

    val puzzleInput = listOf("125", "17")

    val solution1 = pebbles2(puzzleInput, 25)
    println("   after 25 blinks there are ${solution1}")
	
    var solution2 = pebbles2(puzzleInput, 75)
    println("   after 75 blinks there are ${solution2} ")
	
	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
