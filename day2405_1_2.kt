fun fixOrder(in1: List<String>): List<String> {
  	val ruleset = listOf ("47|53",
"97|13",
"97|61",
"97|47",
"75|29",
"61|13",
"75|53",
"29|13",
"97|29",
"53|29",
"61|53",
"97|53",
"61|29",
"47|13",
"75|47",
"97|75",
"47|61",
"75|61",
"47|29",
"75|13",
"53|13") 
    
    val fixedUpdate = mutableListOf<String>()
    val resortingUpdate = in1.toMutableList()
    
    while (resortingUpdate.size > 0) {
        resortingUpdate.forEach {
            var pageBehind = false
            val currentPage = it
            ruleset.forEach {
                if (currentPage == it.split("|")[0] && resortingUpdate.contains(it.split("|")[1])) {
            		pageBehind = true
                }
            }
            
         if (!pageBehind) {
             fixedUpdate.add(currentPage)
         }
        }
        val resortingUpdateNew = mutableListOf<String>()
        resortingUpdate.forEach{
            if (!fixedUpdate.contains(it)) {
                resortingUpdateNew.add(it)
            }
        }
        resortingUpdate.clear()
        resortingUpdate.addAll(resortingUpdateNew)
        resortingUpdateNew.clear()
    }
   return fixedUpdate
}

fun checkUpdate(in1: List<String>): Boolean {
    val currentUpdate = in1
  	val ruleset = listOf ("47|53",
"97|13",
"97|61",
"97|47",
"75|29",
"61|13",
"75|53",
"29|13",
"97|29",
"53|29",
"61|53",
"97|53",
"61|29",
"47|13",
"75|47",
"97|75",
"47|61",
"75|61",
"47|29",
"75|13",
"53|13")  
        
    var rightOrder = true
        ruleset.forEach{
            val previousPage = it.split("|")[0]
            val followingPage = it.split("|")[1]
            if (currentUpdate.contains(previousPage) && currentUpdate.contains(followingPage)) {
                if (currentUpdate.indexOf(previousPage) > currentUpdate.indexOf(followingPage)) {
                    rightOrder = false
                }
        	}
        }
        return rightOrder
}

fun printQueue(in1: Int): Int {	
    var result = 0
    val updates = listOf("75,47,61,53,29",
"97,61,53,29,13",
"75,29,13",
"75,97,47,61,53",
"61,13,29",
"97,13,75,29,47")
	updates.forEach {
        val currentUpdate = it.split(",").toList()
        
        if (checkUpdate(currentUpdate)) {
            if (in1 == 1) {
            	result += currentUpdate[currentUpdate.size/2].toInt()
            }
        }  else {
            if (in1 == 2) {
                val currentUpdateFixed = fixOrder(currentUpdate)
                result += currentUpdateFixed[currentUpdateFixed.size/2].toInt()
            }
        }                  
    }
        return result   
}

fun main() {
	println("--- Day 5: Print Queue ---")
	println("  you will get ${printQueue(1)} if you update the correctly orderd updates")
  println("  you will get ${printQueue(2)} if you update the wrong orderd updates")
}
