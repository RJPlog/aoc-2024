fun diskFragmenter(): Long {
	var puzzleInput = "2333133121414131402"
	
	if (puzzleInput.length % 2 != 0) {
		puzzleInput += "0"
	}

	// transform represenation of blocks
	var id = 0
	var transformedBlock = mutableListOf<Int>()

	puzzleInput.chunked(2).forEach {
		repeat(it[0].toString().toInt()) {
			transformedBlock.add(id)
		}

		id += 1

		repeat(it[1].toString().toInt()) {
			transformedBlock.add(-1)
		}
	}

	// swap blocks

	while (transformedBlock.contains(-1)) {
		while (transformedBlock.last() == -1) {
			transformedBlock.removeAt(transformedBlock.size - 1)
		}

		transformedBlock[transformedBlock.indexOfFirst { it == -1 }] = transformedBlock.last()
		transformedBlock.removeAt(transformedBlock.size - 1)

		while (transformedBlock.last() == -1) {
			transformedBlock.removeAt(transformedBlock.size - 1)
		}
	}

	// calc checksum
	var result: Long = 0L

	for (i in 0..transformedBlock.size - 1) {
		if (transformedBlock[i] != -1) {
			result += i * transformedBlock[i].toLong()
		}
	}

	return result
}

fun diskFragmenter2(): Long {
	var puzzleInput = "2333133121414131402"
	
	if (puzzleInput.length % 2 != 0) {
		puzzleInput += "0"
	}

	// transform represenation of blocks
	var id = 0
	var transformedBlock = mutableListOf<Int>()

	puzzleInput.chunked(2).forEach {
		repeat(it[0].toString().toInt()) {
			transformedBlock.add(id)
		}

		id += 1

		repeat(it[1].toString().toInt()) {
			transformedBlock.add(-1)
		}
	}

	// move blocks
	for (i in id - 1 downTo 0) {
		var blockSize = transformedBlock.count() { it == i }
		var deleteIndex = transformedBlock.indexOf(i)

		for (j in 0..transformedBlock.size - 1 - blockSize) {
			if (transformedBlock.subList(j, j + blockSize).count() { it == -1 } == blockSize) {
				if (j < deleteIndex) {
					for (k in 0..blockSize - 1) {
						transformedBlock[j + k] = i
						transformedBlock[deleteIndex + k] = -1
					}
				}
				break
			}
		}
	}

	// calc checksum
	var result: Long = 0L
	for (i in 0..transformedBlock.size - 1) {
		if (transformedBlock[i] != -1) {
			result += i * transformedBlock[i].toLong()
		}
	}
	return result
}


fun main() {	
	var t1 = System.currentTimeMillis()

	var solution1 = diskFragmenter()
	var solution2 = diskFragmenter2()

// print solution for part 1
	println("*******************************")
	println("--- Day 9: Disk Fragmenter ---")
	println("*******************************")
	println("Solution for part1")
	println("   the checksum for part 1 is $solution1")
	println()
// print solution for part 2
	println("*******************************")
	println("Solution for part2")
	println("   the checksum for part 2 is $solution2")
	println()

	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
