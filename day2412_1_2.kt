fun fencing(gardenMap: String, w: Int): Int {
    
   val allTiles = MutableList(gardenMap.length) {it}
   //println(allTiles.size)
   var allTilesSorted = mutableListOf(mutableListOf<Int>())
   var allTSLetter = mutableListOf(mutableListOf<Char>())
   val h = gardenMap.length / w
   //println(h)
       
   //println(allTiles)
   //println(allTilesSorted)
  
   var j = 0 
   while (allTiles.size > 0 ) {
       var regionFound = false
       var indexOfRegionFound = 0
       for (i in 0..allTiles.size-1) {
           var nextTile = allTiles[i]
           var xNextTile = nextTile % w
           var yNextTile = nextTile / w
           var plantOfNextTile = gardenMap[nextTile]



           for (region in 0..allTilesSorted.size-1) {
               for (tile in 0..allTilesSorted[region].size-1) {
                   var xIt = allTilesSorted[region][tile] % w
                   var yIt = allTilesSorted[region][tile] / w
                   var plantOfIt = gardenMap[allTilesSorted[region][tile]]
                   // siehe nach unten, könnte das diagonale Felder zu einer Region zusammenfassen? -> Abfrage separieren auf u,d,l,r?
                   if (((xNextTile - xIt) <= 1  && (xNextTile - xIt) >= -1) && ((yNextTile - yIt) <= 1 && (yNextTile - yIt) >= -1) && plantOfNextTile == plantOfIt) {
                       allTilesSorted[region].add(nextTile)
                       allTSLetter[region].add(gardenMap[nextTile])
                       regionFound = true
                       indexOfRegionFound = i
                       break
                   }
               if (regionFound) break    
               }
           }
           if (regionFound) break
       } 
       
       //println("j: $j, $indexOfRegionFound, ${allTiles.size}")
       if (regionFound) {
            allTiles.remove(allTiles[indexOfRegionFound])
       } else {
           var newRegion = mutableListOf<Int>()
           newRegion.add(allTiles[0])
           allTilesSorted.add(newRegion)
           var newLetter = mutableListOf<Char>()
           newLetter.add(gardenMap[allTiles[0]])
           allTSLetter.add(newLetter)
           allTiles.remove(allTiles[0])
       }
       
     //  println(allTiles)
   	 //  println(allTilesSorted)
       j += 1
       println(j)
   }
   
   //println(allTiles)
   //println(allTilesSorted)
   //println(allTSLetter)
   
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
       //println("${allTilesSorted[region].size}, $perimeter")
       
       // debug
       if (false) {
           allTilesSorted.forEach{
               it.forEach {
                   print(gardenMap[it])
               }
               println()
           }
       }
       //        
       result += allTilesSorted[region].size * perimeter
   }
    
    // debug -> calculate back out allTileSorted and allTSLetter and compare to input
    var gardenMapReconstructed =  "-".repeat(gardenMap.length)
    for (i in 0..allTilesSorted.size-1) {
        for (j in 0..allTilesSorted[i].size-1) {
            gardenMapReconstructed = gardenMapReconstructed.replaceRange(allTilesSorted[i][j],allTilesSorted[i][j]+1, allTSLetter[i][j].toString())
        }
    }
    if (gardenMapReconstructed == gardenMap) {
        println("reconstruction successfully managed")
    }
    return result
}


fun main() {
   
    println("--- Day 12: Garden Groups ---")
    
    var puzzleInput = listOf("AAAA",
"BBCD",
"BBCC",
"EEEC")
    
 puzzleInput = listOf("OOOOO",
"OXOXO",
"OOOOO",
"OXOXO",
"OOOOO")   

   puzzleInput = listOf("RRRRIICCFF",
"RRRRIICCCF",
"VVRRRCCFFF",
"VVRCCCJFFF",
"VVVVCJJCFE",
"VVIVCCJJEE",
"VVIIICJJEE",
"MIIIIIJJEE",
"MIIISIJEEE",
"MMMISSJEEE") 


    
    val width = puzzleInput[0].length
            
    println("   the total price of fencing all regions on your map ${fencing(puzzleInput.joinToString(""), width)}")
    //println("   ${fencing(puzzleInput.joinToString(""), width)} different positions could be choosen for an obstruction")
}
