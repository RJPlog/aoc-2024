fun fencing(gardenMap: String, w: Int, part: Int): Int {
    
    // sort all tiles into regions
    val allTiles = MutableList(gardenMap.length) {it}
    var allTilesSorted = mutableListOf(mutableListOf<Int>())
    var allTSLetter = mutableListOf(mutableListOf<Char>())
    val h = gardenMap.length / w
   
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
                    if (((xNextTile - xIt) <= 1  && (xNextTile - xIt) >= -1) && ((yNextTile - yIt) <= 1 && (yNextTile - yIt) >= -1) && ((xNextTile -xIt) == 0 || (yNextTile - yIt) == 0 ) && plantOfNextTile == plantOfIt) {
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
        
        j += 1
    }
    
    // calc number of fences for every tile
    var result = 0
    var result2 = 0
    for (region in 0..allTilesSorted.size-1) {
        var perimeter = 0
        
        // part 2
        var edge = 0
        
        for (tile in 0..allTilesSorted[region].size-1) {
                var xIt = allTilesSorted[region][tile] % w
                var yIt = allTilesSorted[region][tile] / w
                var plantOfIt = gardenMap[allTilesSorted[region][tile]]
            
            // part 2
            var perimeterU = false
            var perimeterD = false
            var perimeterL = false
            var perimeterR = false
            var diagUR = false
            var diagUL = false
            var diagDR = false
            var diagDL = false
            
                // check up tile
                if (yIt > 0) {
                      if(gardenMap[xIt + w*(yIt-1)] != plantOfIt) {
                         perimeter += 1
                         perimeterU = true
                     } 
                } else {
                    perimeter += 1
                    perimeterU = true
                }
                // ceck down til
                if (yIt < h-1) {
                      if(gardenMap[xIt + w*(yIt+1)] != plantOfIt) {
                         perimeter += 1
                         perimeterD = true
                     } 
                } else {
                    perimeter += 1
                    perimeterD = true
                }
                // ceck left tile
                if (xIt > 0) {
                      if(gardenMap[(xIt-1) + w*(yIt)] != plantOfIt) {
                         perimeter += 1
                         perimeterL = true
                     } 
                } else {
                    perimeter += 1
                    perimeterL = true
                }
                // ceck right tile
                if (xIt < w-1) {
                      if(gardenMap[(xIt+1) + w*(yIt)] != plantOfIt) {
                         perimeter += 1
                         perimeterR = true
                     } 
                } else {
                    perimeter += 1
                    perimeterR = true
                }
                // ceck diagonal tiles (part2)
                if (xIt < w-1  && yIt > 0) {
                    if (gardenMap[(xIt+1) + w*(yIt - 1)] != plantOfIt) diagUR = true
                }
                if (xIt > 0 && yIt > 0) {
                    if (gardenMap[(xIt-1) + w*(yIt - 1)] != plantOfIt) diagUL = true
                }
                if (xIt < w-1 && yIt < h-1) {
                    if (gardenMap[(xIt+1) + w*(yIt + 1)] != plantOfIt) diagDR = true
                }
                if (xIt > 0 && yIt < h-1) {
                    if (gardenMap[(xIt-1) + w*(yIt + 1)] != plantOfIt) diagDL = true
                }
                
            if (perimeterU && perimeterR) edge +=1
            if (perimeterU && perimeterL) edge +=1
            if (perimeterD && perimeterR) edge +=1
            if (perimeterD && perimeterL) edge +=1
            if (!perimeterU && !perimeterL && diagUL) edge += 1
            if (!perimeterU && !perimeterR && diagUR) edge += 1
            if (!perimeterD && !perimeterL && diagDL) edge += 1
            if (!perimeterD && !perimeterR && diagDR) edge += 1   
        }
 
        result += allTilesSorted[region].size * perimeter
        result2 += allTilesSorted[region].size * edge
    }
     
    if (part == 1) {
        return result
            } else {
        return result2 
    }
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
 "MMMISSJEEE") 
 
val width = puzzleInput[0].length
             
println("  part1: the total price of fencing all regions on your map ${fencing(puzzleInput.joinToString(""), width, 1)}")
println("  part2: the total price of fencing all regions on your map ${fencing(puzzleInput.joinToString(""), width, 2)}")
}
