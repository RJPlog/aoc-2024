fun keypadCon(pins: List<String>): Int {
    
    var result = 0
    
    var n2d = mutableMapOf<String,List<String>>()
    n2d.put("AA", listOf("A"))
    n2d.put("A0", listOf("<A"))
    n2d.put("02", listOf("^A"))
    n2d.put("29", listOf("^^>A"))  // !
    n2d.put("9A", listOf("vvvA"))
    n2d.put("A9", listOf("^^^A"))
    n2d.put("98", listOf("<A"))
    n2d.put("80", listOf("vvvA"))
    n2d.put("0A", listOf(">A"))
    n2d.put("A1", listOf("^<<A"))  // !
    n2d.put("17", listOf("^^A"))
    n2d.put("79", listOf(">>A"))
    n2d.put("A4", listOf("^^<<A"))  // !
    n2d.put("45", listOf(">A"))
    n2d.put("56", listOf(">A"))
    n2d.put("6A", listOf("vvA"))  
    n2d.put("A3", listOf("^A"))
    n2d.put("37", listOf("^^<<A", "<<^^A", "<^<^", "^<^<^"))  // there is a differenz between ^^<< and <<^^  -> follow that up. There is even a strange thing that <<^^ makes 57 as a min
    
    var n2n = mutableMapOf<String,String>()
    n2n.put("AA", "A")
    n2n.put("A<", "v<<A")
    n2n.put("<A", ">>^A")
    n2n.put("A^", "<A")
    n2n.put("^A",">A")
    n2n.put("^^","A")
    n2n.put("^>",">vA")
    n2n.put(">A","^A")
    n2n.put("Av","v<A")
    n2n.put("vv","A")
    n2n.put("vA",">^A")
    n2n.put("v<", "<A")
    n2n.put("<<", "A")
    n2n.put("A>", "vA")
    n2n.put(">>", "A")
    n2n.put(">^", "^<A")
    n2n.put(">v", "<A")
    n2n.put("^<", "v<A")
    n2n.put("<^", ">^A")

    
    pins.forEach {
        var startPin = "A"
        var pin = it
        var firstSeq = mutableListOf<String>()
        firstSeq.add("")
        println("pin: $pin")
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
        println("firstSeq: $firstSeq, startPin: $startPin")
        
        /*
        for (i in 0..1) {
            var nextSeq = ""
            // repeat with num2num pads
            
                firstSeq.forEach {
                    var move = startPin + it.toString()
                    if (n2n.containsKey(move)) {
                     	nextSeq += n2n.getValue(move)
                        //println("move: $move, $nextSeq")
                    } else {
                        println("   $move missing in n2n map")
                    }
                    startPin = it.toString()
                }
            firstSeq = nextSeq
            println("$i: i-te transformation: $firstSeq")
        }
        
        println("pin $pin: -> ${firstSeq.length}, pinvalue: ${pin.filter {it.isDigit()}.toInt()}")
        result += firstSeq.length * pin.filter {it.isDigit()}.toInt()
        */
    }
    
    return result
}

fun main() {
    
     var t1 = System.currentTimeMillis()
    
   
    println("--- Day 21: Keypad Conundrum ---")
    
    var puzzleInput = listOf("029A", "980A", "179A", "456A", "379A")
    
    puzzleInput = listOf("379A")
    
    
    var solution1 = keypadCon(puzzleInput)

    println("  part1: the sum of the complexities is $solution1")
    
//    var solution2 = maze(puzzleInput.joinToString(""), width, height, 2)
//    println("   part2: $solution2 tiles are part of at least one of the best paths")
    
  t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
