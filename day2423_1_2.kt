fun lanParty(pI: List<String>, part: Int = 0): Int {
    
    var posPC = mutableListOf<String>()
	
    pI.forEach{
        var pc = it.split("-") 
            if (!posPC.contains(pc[0])) {
                posPC.add(pc[0])
            }
            if (!posPC.contains(pc[1])) {
                posPC.add(pc[1])
            }
        
    }
    //println(posPC)
    
    var intercon = mutableListOf<List<String>>()
	for (i in 0..posPC.size-1) {
        var pc1 = posPC[i]
        for (j in i..posPC.size-1) {
            var pc2 = posPC[j]
            for (k in j..posPC.size-1) {
                var pc3 = posPC[k]
                if (pc1[0] == 't' || pc2[0] == 't' || pc3[0] == 't') {

                    if ((pI.contains(pc1+"-"+pc2) || pI.contains(pc2+"-"+pc1)) && (pI.contains(pc2+"-"+pc3) || pI.contains(pc3+"-"+pc2)) && (pI.contains(pc1+"-"+pc3) || pI.contains(pc3+"-"+pc1))) {
                       //println("$pc1, $pc2, $pc3")
                       var con = listOf(pc1, pc2, pc3).sorted()
                       if (!intercon.contains(con)) {
                           intercon.add(con)
                       }
                    }
       
                }
            }
        }
    }
    //println(intercon)
    
    return intercon.size
}

fun main() {
    
     var t1 = System.currentTimeMillis()
    
    println("--- Day 23: LAN Party ---")
    
    var puzzleInput = listOf("kh-tc",
"qp-kh",
"de-cg",
"ka-co",
"yn-aq",
"qp-ub",
"cg-tb",
"vc-aq",
"tb-ka",
"wh-tc",
"yn-cg",
"kh-ub",
"ta-co",
"de-co",
"tc-td",
"tb-wq",
"wh-td",
"ta-ka",
"td-qp",
"aq-cg",
"wq-ub",
"ub-vc",
"de-ta",
"wq-aq",
"wq-vc",
"wh-yn",
"ka-de",
"kh-ta",
"co-tc",
"wh-qp",
"tb-vc",
"td-yn")
    
    
    var solution1 = lanParty(puzzleInput)

    println("  part1: $solution1 contain at least one computer with a name that starts with t")
    
//    var solution2 = maze(puzzleInput.joinToString(""), width, height, 2)
//    println("   part2: $solution2 tiles are part of at least one of the best paths")
    
  t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
