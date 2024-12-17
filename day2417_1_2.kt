fun compute(code: List<Int>, A: Int, B: Int, C: Int): String {
    var output = mutableListOf<Int>()
    
    var A = A
    var B = B
    var C = C
    
    var inst = 0
    
    println("INI A: $A, B: $B, C: $C, inst: $inst")
    
    var i = 0
    while (inst < code.size) {
 
        var opcode = code[inst]
        var operand = code[inst+1]
        var comboOp = when (operand) {
            0 -> 0
            1 -> 1
            1 -> 2
            3 -> 3
            4 -> A
            5 -> B
            6 -> C
            else -> -1
        }
        println("$i: do $opcode with op $operand or combo $comboOp")
        when (opcode) {
            0 -> { //adv
                var divisor = 1
                for (i in 1..comboOp) {
                    divisor *= 2
                }
                A = (A / divisor)
                inst += 2
            }
            1 -> { //bxl
                 B = B.xor(operand)
                 inst += 2
            }
            2 -> { //bst
                B = comboOp % 8
                inst += 2
            }
            3 -> { //jnz
            	if (A != 0) {
                    inst = operand
                } else {
                    inst +=2
                }
            }
            4 -> { //bxc
            	B = B.xor(C)
                inst += 2
            }
            5 -> { //out
                output.add(comboOp % 8)
                inst += 2
            }
            6 -> { // bdv
                var divisor = 1
                for (i in 1..comboOp) {
                    divisor *= 2
                }
                B = (A / divisor)
                inst += 2  
            }
            7 -> { // cdv
                var divisor = 1
                for (i in 1..comboOp) {
                    divisor *= 2
                }
                C = (A / divisor)
                inst += 2  
            }
        }
        println("    A: $A, B: $B, C: $C, inst: $inst")
    i += 1  
    }
    
    return output.joinToString(",")
}


fun main() {
    
    println("--- Day 17: Chronospatial Computer ---")
    
    var A = 729
    var B = 0
    var C = 0
    
    var code = listOf(0,1,5,4,3,0)
    
    
    var solution1 = compute(code, A,B,C)

    println("  part1: you get $solution1") 

}
