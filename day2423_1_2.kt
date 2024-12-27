import java.io.File

fun lanParty(pI: List<String>, part: Int = 0): Int {

	var posPC = mutableListOf<String>()

	pI.forEach {
		var pc = it.split("-")
		if (!posPC.contains(pc[0])) {
			posPC.add(pc[0])
		}
		if (!posPC.contains(pc[1])) {
			posPC.add(pc[1])
		}

	}

	var intercon = mutableListOf<List<String>>()

	for (i in 0..posPC.size - 1) {
		var pc1 = posPC[i]
		for (j in i..posPC.size - 1) {
			var pc2 = posPC[j]
			for (k in j..posPC.size - 1) {
				var pc3 = posPC[k]
				if (pc1[0] == 't' || pc2[0] == 't' || pc3[0] == 't') {
					println("$i, $j, $k")
					if ((pI.contains(pc1 + "-" + pc2) || pI.contains(pc2 + "-" + pc1)) && (pI.contains(pc2 + "-" + pc3) || pI.contains(
							pc3 + "-" + pc2
						)) && (pI.contains(pc1 + "-" + pc3) || pI.contains(pc3 + "-" + pc1))
					) {
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
	return intercon.size
}

fun lanParty2(pI: List<String>, part: Int = 2): String {

	// ----------- step 1 -----------------
	// create list with one2one connections alphabethically sorted
	// create list with all ids of pc

	var one2oneList = mutableListOf<List<String>>()
	var pcID = mutableListOf<String>()
	pI.forEach {
		var ids = it.split("-")
		var one2one = listOf(ids[0], ids[1]).sorted()
		one2oneList.add(one2one)
		if (!pcID.contains(ids[0])) pcID.add(ids[0])
		if (!pcID.contains(ids[1])) pcID.add(ids[1])
	}
	one2oneList.sortBy { it[0] }
	pcID.sort()

	//println(one2oneList)
	//println(pcID)
	//println()

	//------------- step 2------------------
	// create list with lan connetions (initalvalue: copy of one to one connections)
	// set passwd to first element of that list
	var allLans = mutableListOf<List<String>>()
	allLans.addAll(one2oneList)
	var passwd = allLans[0].joinToString(",")

	// iterate trough all lan networks and try if you can add another pc
	var n = 0
	while (allLans.size > 0) {
		var allLansNew = mutableListOf<List<String>>()
		allLans.forEach {
			var currentLan = it

			// iterate through pcID list and check if current id is connected to all the IDs of the current lan
			var nextLetter = pcID.indexOf(currentLan[currentLan.size - 1])
			if (nextLetter + 1 < pcID.size - 1)
				for (i in nextLetter + 1..pcID.size - 1) { //startpoint can be switchet to the id after the last id of current lan
					var connection = true
					currentLan.forEach {
						if (!one2oneList.contains(listOf(it, pcID[i]))) {
							connection = false
						}
					}
					// add new Lan to list
					if (connection) {
						var addPC = mutableListOf<String>()
						addPC.addAll(currentLan)
						addPC.add(pcID[i])
						allLansNew.add(addPC)
					}

				}


		}
		// do all the clear allLans ...
		allLans.clear()
		allLans.addAll(allLansNew)
		allLansNew.clear()
		if (allLans.size > 0) passwd = allLans[0].joinToString(",")
		println("$n: ${allLans.size}, passwd: $passwd")
		n += 1
	}

	return passwd
}

fun lanParty3(pI: List<String>, part: Int = 2): String {

	// ----------- step 1 -----------------
	// create list with one2one connections alphabethically sorted
	// create list with all ids of pc

	var one2oneList = mutableListOf<List<String>>()
	var one2oneListBinary = mutableListOf<String>()
	var pcID = mutableListOf<String>()
	pI.forEach {
		var ids = it.split("-")
		var one2one = listOf(ids[0], ids[1]).sorted()
		one2oneList.add(one2one)
		one2oneListBinary.add(one2one.joinToString("-"))				// try out Peter's hint
		if (!pcID.contains(ids[0])) pcID.add(ids[0])
		if (!pcID.contains(ids[1])) pcID.add(ids[1])
	}
	one2oneList.sortBy { it[0] }
	one2oneListBinary.sort()											// try out Peter's hint
	pcID.sort()

	//println(one2oneList)
	//println(pcID)
	//println()

	//------------- step 2------------------
	// create list with lan connetions (initalvalue: copy of one to one connections)
	// set passwd to first element of that list
	var allLans = mutableListOf<List<String>>()
	allLans.addAll(one2oneList)
	var passwd = allLans[0].joinToString(",")

	// iterate trough all lan networks and try if you can add another pc
	var n = 0
	while (allLans.size > 0) {
		var allLansNew = mutableListOf<List<String>>()
		allLans.forEach {
			var currentLan = it

			// iterate through pcID list and check if current id is connected to all the IDs of the current lan
			var nextLetter = pcID.indexOf(currentLan[currentLan.size - 1])
			if (nextLetter + 1 < pcID.size - 1)
				for (i in nextLetter + 1..pcID.size - 1) { //startpoint can be switchet to the id after the last id of current lan
					var connection = true
					currentLan.forEach {
						// try out Peter's hint
						if (one2oneListBinary.binarySearch(listOf(it, pcID[i]).sorted().joinToString("-")) < 0) {
							connection = false
						}
						//if (!one2oneList.contains(listOf(it, pcID[i]))) {
						//	connection = false
						//}
					}
					// add new Lan to list
					if (connection) {
						var addPC = mutableListOf<String>()
						addPC.addAll(currentLan)
						addPC.add(pcID[i])
						allLansNew.add(addPC)
					}

				}


		}
		// do all the clear allLans ...
		allLans.clear()
		allLans.addAll(allLansNew)
		allLansNew.clear()
		if (allLans.size > 0) passwd = allLans[0].joinToString(",")
		println("$n: ${allLans.size}, passwd: $passwd")
		n += 1
	}

	return passwd
}



fun main() {

	var t1 = System.currentTimeMillis()

	println("--- Day 23: LAN Party ---")

	var puzzleInput = mutableListOf<String>()

	File("day2423_puzzle_input.txt").forEachLine {
		puzzleInput.add(it)
	}

//	var solution1 = lanParty(puzzleInput)
//	println("  part1: $solution1 contain at least one computer with a name that starts with t")

	var solution2 = lanParty3(puzzleInput)
	println("   part2: the password to get into the LAN party is >>$solution2<<")

	t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
