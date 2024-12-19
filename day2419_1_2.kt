import java.io.File

fun LinLay(pattern: String, layout: String, count: Int = 0): Int {
    
    var insert = " ".repeat(count)

    var layPossible = 0

    pattern.split(", ").forEach {
        var currPat = it
        //println("$insert run with $currPat, $layout,  ${currPat.length}, ${layout.length}")
        if (currPat.length >= layout.length) {
            if (currPat == layout) {
                layPossible += 1
            } 
        } else {
            //println("$insert    else : ${layout.take(currPat.length)} vs $currPat")
            if (layout.take(currPat.length) == currPat) {
              //  println( "$insert     next level: ${layout.drop(currPat.length)}")
                layPossible += LinLay(pattern, layout.drop(currPat.length), count+4)
            } else {
                layPossible += 0
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
    
    var solution1 = 0 
    
    patterns2Create.forEach {
        println("$it -> ${LinLay(availablePatterns, it)}")
        if (LinLay(availablePatterns, it, 0) >= 1) solution1 += 1
    }

    println("  part1: $solution1 designs are possible") 
    
    //println("  part2:  i")
    
    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
