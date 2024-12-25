import java.io.File

fun main() {
  var t1 = System.currentTimeMillis()

  println("--- Day 25: Code Chronicle ---")
    
  var keys = mutableListOf<String>()
  var locks = mutableListOf<String>()
    
  var scematic = ""
  File("day2425_puzzle_input.txt").forEachLine {
    if (it == "") {
      scematic = ""
    } else {
      scematic += it
    }

    if (scematic.length == 35 ) {
      if (scematic[0] == '#') locks.add(scematic)
      if (scematic[0] == '.') keys.add(scematic)
    }
	} 

  var solution1 = 0

  locks.forEach {
    var lock = it
    keys.forEach {
      var key = it
      var overlap = false 
      for (i in 0..key.length-1) {
        if (key[i] == '#' && lock[i] == '#') overlap = true
      }
      if (!overlap) solution1 += 1
    }
  }

  println("  part1:  $solution1 unique lock/key pairs fit together")
    
  t1 = System.currentTimeMillis() - t1
	println("puzzle solved in ${t1} ms")
}
