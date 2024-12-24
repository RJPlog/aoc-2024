import java.io.File

fun crossedWires(gates: List<String>): String {
	// sum = (xi XOR yi) XOR Ci-1
	// Ci = (xi AND yi) OR (ci-1 AND (xi XOR yi)

	var wrongCon = mutableListOf<String>()

	var sigAList = mutableListOf<String>()
	var sigBList = mutableListOf<String>()

	// fill sigA and Sig B list
	for (i in 1..44) {
		var x = "x" + i.toString().padStart(2, '0')
		var y = "y" + i.toString().padStart(2, '0')

		var out: String
		gates.forEach {
			if (it.contains(x + " XOR " + y) || it.contains(y + " XOR " + x)) {
				out = it.substringAfter("-> ")
				if (!sigAList.contains(out)) sigAList.add(out)
			}
			if (it.contains(x + " AND " + y) || it.contains(y + " AND " + x)) {
				out = it.substringAfter("-> ")
				if (!sigBList.contains(out)) sigBList.add(out)
			}
		}
	}

	for (i in 1..44) {
		var x = "x" + i.toString().padStart(2, '0')
		var y = "y" + i.toString().padStart(2, '0')

		var out: String

		gates.forEach {
			// if xn xor yn are input, output is not able to be zn (at least for all after lsb
			if (it.contains(x + " XOR " + y) || it.contains(y + " XOR " + x)) {
				out = it.substringAfter("-> ")
				if (out.contains("z")) {
					if (!wrongCon.contains(out)) wrongCon.add(out)
				}
			}
			// zn can only be output of an XOR element (not valid for last element!
			if (it.contains(" AND ") || it.contains(" OR ")) {
				out = it.substringAfter("-> ")
				if (out.contains("z") && out != "z45") {
					if (!wrongCon.contains(out)) wrongCon.add(out)
				}
			}
			// output of an XOR with no input xn or yn is not allowed to be other than zn -->> hier weitermachen.
			if (!it.contains("x") && it.contains("XOR")) {
				out = it.substringAfter("-> ")
				if (!out.contains("z")) {
					if (!wrongCon.contains(out)) wrongCon.add(out)
				}
			}
			// any input of XOR is not allowed to be SigB
			if (it.contains("XOR") && it.substringBefore("->").contains(sigBList[i - 1])) {
				if (!wrongCon.contains(sigBList[i - 1])) wrongCon.add(sigBList[i - 1])
			}
			// any input of OR is not allowed to be SigA
			if (it.contains(" OR") && it.substringBefore("->").contains(sigAList[i - 1])) {
				if (!wrongCon.contains(sigAList[i - 1])) wrongCon.add(sigAList[i - 1])
			}
		}
	}
	return wrongCon.sorted().joinToString(",")
}

fun main() {
	var t1 = System.currentTimeMillis()

	println("--- Day 24: Crossed Wires ---")

	var lines = mutableListOf<String>()
	var gates = mutableListOf<String>()

	File("day2424_puzzle_input.txt").forEachLine {
		if (it.contains("->")) {
			gates.add(it)
		} else if (it.contains(":")) {
			lines.add(it)
		}
	}

	// calc solution for part two before modifing input for part one
	var solution2 = crossedWires(gates)

	// calc part one 			
	while (!gates.isEmpty()) {
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

		network.split(",").forEach {
			if (it.substringBefore(" ->") == "0") {
				lines.add(it.split(" -> ")[1] + ": " + "0")
			} else if (it.substringBefore(" ->") == "1") {
				lines.add(it.split(" -> ")[1] + ": " + "1")
			} else {
				gates.add(it)
			}
		}
	}

	var solution1 = lines.filter { it[0] == 'z' }.sortedDescending().map { it.takeLast(1) }.joinToString("").toLong(2)

	println("  part1: the system output is $solution1")

	println("  part2: these are the wrong connected wires: $solution2")

	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
