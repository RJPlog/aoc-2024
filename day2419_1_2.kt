import java.io.File

fun LinLay(pattern: String, layout: String, alreadyEval: Map<String, Long>): Long {

    if (pattern.length == 0) return 0
    
    var layPossible = 0L

    var searchPatterns = pattern.split(", ")
        pattern.split(", ").forEach {
            var currPat = it
            if (currPat.length >= layout.length) {
                if (currPat == layout) {
                    layPossible += 1L 
                } 
            } else {
                
                if (layout.take(currPat.length) == currPat) {

                    // again reduce pattern to patterns which can be found in pattern to search
                    var newLay = layout.drop(currPat.length)
                    val reducedAvPat = (pattern.split(", ").filter {newLay.contains(it)}).joinToString(", ")
                    if (alreadyEval.containsKey(newLay)) {
                        layPossible += alreadyEval.getValue(newLay)
                    } else {
                        layPossible += LinLay(reducedAvPat, newLay, alreadyEval)
                    }
                } else {
                    layPossible += 0L
                }  
            }
        }

    return layPossible
}

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
        i += 1
    }

    println("--- Day 19: Linen Layout ---")
    
    var solution1 = 0L
    var solution2 = 0L 
    

    patterns2Create.forEach {
        var patCrea = it
        var alreadyEval = mutableMapOf<String, Long>()

        // idea: create a map containing all substings (beginning from end) to make programm faster
        for (i in patCrea.length-2 downTo 0) {

            // reduce availablePatters to make programm faster
            var pat2Crea = patCrea.drop(i)
            var reducedAvPat = ""
            var reduceList = mutableListOf<String>()

            availablePatterns.split(", ").forEach {
                if (pat2Crea.contains(it)) {
                    reduceList.add(it)
                }
            }
            reducedAvPat = reduceList.joinToString(", ")

            alreadyEval.put(pat2Crea, LinLay(reducedAvPat, pat2Crea, alreadyEval))
        }

        val singleResult = alreadyEval.getValue(patCrea)
        println("$patCrea -> $singleResult")
        if (singleResult >= 1) solution1 += 1
        solution2 += singleResult
    }

    println("  part1: $solution1 designs are possible") 
    
    println("  part2:  you get $solution2 if you add up the number of different ways")
    
    t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
