fun fencing(gardenMap: String, w: Int): Int {
    
   val allTiles = MutableList(gardenMap.length) {it}
   var allTilesSorted = mutableListOf(mutableListOf<Int>())
   val h = gardenMap.length / w
   println(h)
       
   //println(allTiles)
   //println(allTilesSorted)
  
   var i = 0 
   while (allTiles.size > 0 ) {
	   var nextTile = allTiles[0]
       var xNextTile = nextTile % w
       var yNextTile = nextTile / w
       var plantOfNextTile = gardenMap[nextTile]
       var regionFound = false
       
       for (region in 0..allTilesSorted.size-1) {
           for (tile in 0..allTilesSorted[region].size-1) {
               var xIt = allTilesSorted[region][tile] % w
               var yIt = allTilesSorted[region][tile] / w
               var plantOfIt = gardenMap[allTilesSorted[region][tile]]
               if (((xNextTile - xIt) <= 1  && (xNextTile - xIt) >= -1) && ((yNextTile - yIt) <= 1 && (yNextTile - yIt) >= -1) && plantOfNextTile == plantOfIt) {
                   allTilesSorted[region].add(nextTile)
                   regionFound = true
                   break
               }
           if (regionFound) break    
           }
       }
       if (!regionFound) {
           var newRegion = mutableListOf<Int>()
           newRegion.add(nextTile)
           allTilesSorted.add(newRegion)
           allTiles.remove(nextTile)
       }
       allTiles.remove(nextTile)
       
       println(allTiles)
   	   println(allTilesSorted)
       i += 1
   }
   
   println(allTiles)
   println(allTilesSorted)
   
   // calc number of fences for every tile
   var result = 0
   for (region in 0..allTilesSorted.size-1) {
       var perimeter = 0
       for (tile in 0..allTilesSorted[region].size-1) {
               var xIt = allTilesSorted[region][tile] % w
               var yIt = allTilesSorted[region][tile] / w
               var plantOfIt = gardenMap[allTilesSorted[region][tile]]
               // check up tile
               if (yIt > 0) {
 					if(gardenMap[xIt + w*(yIt-1)] != plantOfIt) {
                        perimeter += 1
                    } 
               } else {
                   perimeter += 1
               }
               // ceck down tile
               if (yIt < h-1) {
 					if(gardenMap[xIt + w*(yIt+1)] != plantOfIt) {
                        perimeter += 1
                    } 
               } else {
                   perimeter += 1
               }
               // left up tile
               if (xIt > 0) {
 					if(gardenMap[(xIt-1) + w*(yIt)] != plantOfIt) {
                        perimeter += 1
                    } 
               } else {
                   perimeter += 1
               }
               // ceck right tile
               if (xIt < w-1) {
 					if(gardenMap[(xIt+1) + w*(yIt)] != plantOfIt) {
                        perimeter += 1
                    } 
               } else {
                   perimeter += 1
               }
       }
       println("${allTilesSorted[region].size}, $perimeter")
       result += allTilesSorted[region].size * perimeter
   }
    
    return result
}


fun main() {
    println("--- Day 12: Garden Groups ---")
    
    var puzzleInput = listOf("RRRRIICCFF",
"RRRRIICCCF",
"VVRRRCCFFF",
"VVRCCCJFFF",
"VVVVCJJCFE",
"VVIVCCJJEE",
"VVIIICJJEE",
"MIIIIIJJEE",
"MIIISIJEEE",
"MMMISSJEEE"
)
    
    val width = puzzleInput[0].length
            
    println("   the total price of fencing all regions on your map ${fencing(puzzleInput.joinToString(""), width)}")
    //println("   ${fencing(puzzleInput.joinToString(""), width)} different positions could be choosen for an obstruction")
}
