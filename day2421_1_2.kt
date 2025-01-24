fun transN2N(pin: String): List<String> {
    var n2n = mutableMapOf<String,List<String>>()
    n2n.put("AA", listOf("A"))
    n2n.put("A<", listOf("v<<A", "<v<A"))
    n2n.put("<A", listOf(">>^A", ">^>A"))
    n2n.put("A^", listOf("<A"))
    n2n.put("^A", listOf(">A"))
    n2n.put("^^", listOf("A"))
    n2n.put("^>", listOf(">vA", "v>A"))
    n2n.put(">A", listOf("^A"))
    n2n.put("Av", listOf("v<A","<vA"))
    n2n.put("vv", listOf("A"))
    n2n.put("vA", listOf(">^A", "^>A"))
    n2n.put("v<", listOf("<A"))
    n2n.put("<<", listOf("A"))
    n2n.put("A>", listOf("vA"))
    n2n.put(">>", listOf("A"))
    n2n.put(">^", listOf("^<A", "<^A"))
    n2n.put(">v", listOf("<A"))
    n2n.put("^<", listOf("v<A"))
    n2n.put("<^", listOf(">^A"))
    n2n.put("<v", listOf(">A"))
    n2n.put("^v", listOf("vA"))
    n2n.put("v>", listOf(">A"))
    n2n.put("<>", listOf(">>A"))
    n2n.put("><", listOf("<<A"))
    n2n.put("v^", listOf("^A"))

    var startPin = "A"
    var firstSeq = mutableListOf<String>()
    firstSeq.add("")

    pin.forEach {
        var firstSeqNew = mutableListOf<String>()
        var move = startPin + it.toString()
        if (n2n.containsKey(move)) {
            n2n.getValue(move).forEach {
                for (j in 0..firstSeq.size-1) {
                    firstSeqNew.add(firstSeq[j] + it)
                }
            }
        } else {
            println("   $move missing in n2n map")
        }
        firstSeq.clear()
        firstSeq.addAll(firstSeqNew)
        startPin = it.toString()
    }
    return firstSeq
}


fun transN2D(pin: String, start: String = "A"): List<String> {
    var n2d = mutableMapOf<String,List<String>>()
    n2d.put("AA", listOf("A"))
    n2d.put("A0", listOf("<A"))
    n2d.put("02", listOf("^A"))
    n2d.put("29", listOf("^^>A", "^>^A", ">^^A")) 
    n2d.put("9A", listOf("vvvA"))
    n2d.put("A9", listOf("^^^A"))
    n2d.put("98", listOf("<A"))
    n2d.put("80", listOf("vvvA"))
    n2d.put("0A", listOf(">A"))
    n2d.put("A1", listOf("^<<A", "<^<A"))  
    n2d.put("17", listOf("^^A"))
    n2d.put("79", listOf(">>A"))
    n2d.put("A4", listOf("^^<<A", "^<^<A", "<^^>A", "<^^<A")) 
    n2d.put("45", listOf(">A"))
    n2d.put("56", listOf(">A"))
    n2d.put("6A", listOf("vvA"))  
    n2d.put("A3", listOf("^A"))
    n2d.put("37", listOf("^^<<A", "<<^^A", "<^<^A", "^<<^A"))  
    n2d.put("A8", listOf("^^^<A", "<^^^A", "^<^^A", "^^<^A"))
    n2d.put("03", listOf(">^A", "^>A"))
    n2d.put("3A", listOf("vA"))
    n2d.put("A5", listOf("^^<A", "<^^A", "^<^A"))
    n2d.put("52", listOf("vA"))
    n2d.put("28", listOf("^^A"))
    n2d.put("8A", listOf(">vvvA", "v>vvA", "vv>vA", "vvv>A"))
    n2d.put("58", listOf("^A"))
    n2d.put("86", listOf(">vA", "v>A"))
    n2d.put("34", listOf("^<<A", "<^<A", "<<^A"))
    n2d.put("41", listOf("vA"))
    n2d.put("1A", listOf(">>vA", ">v>A"))
    n2d.put("31", listOf("<<A"))
    n2d.put("19", listOf(">>^^A", ">^>^A", ">^^>A", "^^>>A"))

    var startPin = start
    var firstSeq = mutableListOf<String>()
    firstSeq.add("")

    pin.forEach {
        var firstSeqNew = mutableListOf<String>()
        var move = startPin + it.toString()
        if (n2d.containsKey(move)) {
            n2d.getValue(move).forEach {
            for (j in 0..firstSeq.size-1) {
                firstSeqNew.add(firstSeq[j] + it)
            }
        }         
        } else {
            println("   $move missing in n2d map")
        }
        firstSeq.clear()
        firstSeq.addAll(firstSeqNew)
        startPin = it.toString()
    } 
    return firstSeq
}

fun keypadCon(pins: List<String>, rep: Int): Int {
    
    var result = 0
    var possibleSequencesNth = mutableListOf<String>()
    var possibleSequencesNplusOneth = mutableListOf<String>()
    
    pins.forEach {
        var intermediateResult = 0
        var start = "A"
        it.forEach{
        //println(it)
        possibleSequencesNth.addAll(transN2D(it.toString(), start))
        start = it.toString()    
        for (i in 1..rep) {
            possibleSequencesNplusOneth.clear()
            possibleSequencesNth.forEach{
                possibleSequencesNplusOneth.addAll(transN2N(it))
            }
            possibleSequencesNth.clear()
            possibleSequencesNth.addAll(possibleSequencesNplusOneth)
            //println("$i th run: ${possibleSequencesNth.size}")
        }

        var minSeq = possibleSequencesNplusOneth[0].length
        possibleSequencesNplusOneth.forEach {
            if (it.length < minSeq) {
            minSeq = it.length
            }
        }
        //println(minSeqVerb)
        //result += minSeq * it.filter {it.isDigit()}.toInt()
        intermediateResult += minSeq
        possibleSequencesNth.clear()
        possibleSequencesNplusOneth.clear()
        }
        //println(result)
        result += intermediateResult *it.filter {it.isDigit()}.toInt()
    } 

    return result
}

fun main() {
    
var t1 = System.currentTimeMillis()
    
println("--- Day 21: Keypad Conundrum ---")
    
var puzzleInput = listOf("029A", "980A", "179A", "456A", "379A")
    puzzleInput = listOf("803A", "528A", "586A", "341A", "319A")

        
var solution1 = keypadCon(puzzleInput, 2)

println("  part1: the sum of the complexities is $solution1")   

//    var solution2 = maze(puzzleInput.joinToString(""), width, height, 2)
//    println("   part2: the sum of the complexities is $solution2")
    
t1 = System.currentTimeMillis() - t1
println("puzzle solved in ${t1} ms")
}
