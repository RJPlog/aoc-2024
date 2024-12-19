fun compute(code: List<Long>, A: Long, B: Long, C: Long): String {
    var output = mutableListOf<Long>()
    
    var A = A
    var B = B
    var C = C
    
    var inst = 0
    
    //println("INI A: $A, B: $B, C: $C, inst: $inst")
    
    var i = 0L
    while (inst < code.size) {
 
        var opcode = code[inst]
        var operand = code[inst+1]
        var comboOp = when (operand) {
            0L -> 0L
            1L -> 1L
            1L -> 2L
            3L -> 3L
            4L -> A
            5L -> B
            6L -> C
            else -> -1L
        }
        //println("$i: do $opcode with op $operand or combo $comboOp")
        when (opcode) {
            0L -> { //adv
                var divisor = 1
                for (i in 1..comboOp) {
                    divisor *= 2
                }
                A = (A / divisor)
                inst += 2
            }
            1L -> { //bxl
                 B = B.xor(operand)
                 inst += 2
            }
            2L -> { //bst
                B = comboOp % 8
                inst += 2
            }
            3L -> { //jnz
            	if (A != 0L) {
                    inst = operand.toInt()
                } else {
                    inst +=2
                }
            }
            4L -> { //bxc
            	B = B.xor(C)
                inst += 2
            }
            5L -> { //out
                output.add(comboOp % 8)
                if (code.take(output.size) != output) return "-1L"
                inst += 2
            }
            6L -> { // bdv
                var divisor = 1
                for (i in 1..comboOp) {
                    divisor *= 2
                }
                B = (A / divisor)
                inst += 2  
            }
            7L -> { // cdv
                var divisor = 1
                for (i in 1..comboOp) {
                    divisor *= 2
                }
                C = (A / divisor)
                inst += 2  
            }
        }
        //println("    A: $A, B: $B, C: $C, inst: $inst")
    i += 1  
    }
    
    return output.joinToString(",")
}


fun main() {
    var t1 = System.currentTimeMillis()

    println("--- Day 17: Chronospatial Computer ---")
    
    var A = 2024L
    var B = 0L
    var C = 0L
    
    var puzzleInput = listOf(0,3,5,4,3,0)

    var code = mutableListOf<Long>()
    puzzleInput.forEach {
        code.add(it.toLong())
    }
    
    
    var solution1 = compute(code, A,B,C)

    println("  part1: you get $solution1") 
    
    var i = 0L
    val codeString = code.joinToString(",")

    while (compute(code, i, 0, 0) != codeString) {
        //if (i % 1000L == 0L) println(i)
        i += 1
    }

    println("  part2:  the lowest positive initial value that causes the program to output a copy of itself is: $i")
    
    t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
