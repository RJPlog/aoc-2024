
fun main() {

	var t1 = System.currentTimeMillis()

	println("--- Day 24: Crossed Wires ---")

	var puzzleInput = mutableListOf<String>()

	var lines = mutableListOf<String>()
	var gates = mutableListOf<String>()
	
	File("day2424_puzzle_input.txt").forEachLine {
		if (it.contains("->")) {
			 gates.add(it)
		} else if (it.contains(":")) {
			lines.add(it)
		}
	}
	
	

	println(lines)
	println(gates)
	
	while (!gates.isEmpty()) {
		println()
		var network = gates.joinToString(",")
		lines.forEach {
			network = network.replace(it.split(": ")[0], it.split(": ")[1])
		}
		network = network.replace("1 AND 1", "1")
		network = network.replace("1 AND 0", "0")
		network = network.replace("0 AND 1", "0")
		network = network.replace("0 AND 0", "0")
		network = network.replace("1 OR 1", "1")
		network = network.replace("1 OR 0", "1")
		network = network.replace("0 OR 1", "1")
		network = network.replace("0 OR 0", "0")
		network = network.replace("1 XOR 1", "0")
		network = network.replace("1 XOR 0", "1")
		network = network.replace("0 XOR 1", "1")
		network = network.replace("0 XOR 0", "0")

		gates.clear()
		lines.clear()
	
		network.split(",").forEach {
			if (it.substringBefore(" ->") == "0") {
				lines.add(it.split(" -> ")[1] + ": " + "0")
			}  else if (it.substringBefore(" ->") == "1") {
				lines.add(it.split(" -> ")[1] + ": " + "1")
			}else {
				gates.add(it)
			}
		}
		
		println(network)
		println(lines)
		println(gates)
	}
		
	var solution1 = lines.sortedDescending().map {it.takeLast(1)}.joinToString("").toLong(2)
	println("  part1: the system output is $solution1")

//	var solution2 = lanParty2(puzzleInput)
//	println("   part2: the system output is $solution2")

	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
