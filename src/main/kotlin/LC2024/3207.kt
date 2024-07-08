package LC2024

class `3207` {
    fun maximumPoints(enemyEnergies: IntArray, currentEnergy: Int): Long {
        val sortedEnergy = enemyEnergies.sorted()
        var marked = 0
        var energy = currentEnergy
        var ans = 0L
        while (marked < enemyEnergies.size ) {
            if (energy >= sortedEnergy[0]) {
                val canTake = energy / sortedEnergy[0]
                println("Taking $canTake of the first item which costs ${sortedEnergy[0]}")
                ans += canTake.toLong()
                energy -= (canTake * sortedEnergy[0])
                println("Score is now $ans and our remaining energy is $energy")
            } else if (ans > 0){
                energy += sortedEnergy[sortedEnergy.size - 1 - marked]
                marked++
                println("Unable to take the cheapest, moving the marked from the end, it is now $marked, which means we are reading from index ${sortedEnergy.size - 1 - marked} and our energy is now $energy")
            } else return ans

            println("------")
            println("------")
        }

        return ans
    }
}

fun main() {
    val soln = `3207`()
    println(soln.maximumPoints(
        enemyEnergies = intArrayOf(3,2,2),
        currentEnergy = 2
    ))
}