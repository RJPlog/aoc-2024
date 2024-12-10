fun bridgeRepair(in1: List<String>, in2: Int): Long {

	var calibResult = 0L

	in1.forEach {
		val resultExpected = it.split(":")[0].toString().toLong()
		val values = it.split(": ")[1].split(" ").map { it -> it.toString().toLong() }
		var operators = values.dropLast(1).map { it * 0 + 2 }.reduce { a, e -> a * e }

		if (in2 == 2) {
			operators = values.dropLast(1).map { it * 0 + 3 }.reduce { a, e -> a * e }
		}

		var solutionFound = false

		for (i in 0..operators) {
			var singleResult = values[0]

			for (j in 1..values.size - 1) {
				var ops = i.toString(2).padStart(values.size - 1, '0')

				if (in2 == 2) {
					ops = i.toString(3).padStart(values.size - 1, '0')
				}

				if (ops[j - 1] == '0') {
					singleResult += values[j]

				} else if (ops[j - 1] == '1') {
					singleResult *= values[j]
				} else {
					singleResult = (singleResult.toString() + values[j].toString()).toLong()
				}
			}

			if (singleResult == resultExpected && solutionFound == false) {
				calibResult += resultExpected
				solutionFound = true
			}
		}
	}
	return calibResult
}

fun main() {
	
	var t1 = System.currentTimeMillis()
	
	println("--- Day 7: Bridge Repair ---")

	var puzzleInput = listOf(
		"190: 10 19",
		"3267: 81 40 27",
		"83: 17 5",
		"156: 15 6",
		"7290: 6 8 6 15",
		"161011: 16 10 13",
		"192: 17 8 14",
		"21037: 9 7 18 13",
		"292: 11 6 16 20"
	)

	val solution1 = bridgeRepair(puzzleInput, 1)
	val solution2 = bridgeRepair(puzzleInput, 2)

	println("   the total calibration result is ${solution1} ")
	println("   the total calibration result is ${solution2} ")
	
	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
