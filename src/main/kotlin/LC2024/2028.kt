package LC2024

class `2028` {
    fun missingRolls(rolls: IntArray, mean: Int, n: Int): IntArray {
        val sum = rolls.sum()
        val totalRolls = n + rolls.size
        var remainder = (mean * totalRolls) - sum

        val ans = mutableListOf<Int>()
        // We must use N missing slots
        // and it must add up to the remainder
        if (n * 6 < remainder) return intArrayOf()
        for (i in 0 until n) {
            val slotsLeft = n - i
            if (remainder == 0) return intArrayOf()
            if (slotsLeft * 6 <= remainder) {
                ans.add(6)
                remainder -= 6
            } else if (slotsLeft * 5 <= remainder) {
                ans.add(5)
                remainder -= 5
            } else if (slotsLeft * 4 <= remainder) {
                ans.add(4)
                remainder -= 4
            } else if (slotsLeft * 3 <= remainder) {
                ans.add(3)
                remainder -= 3
            } else if (slotsLeft * 2 <= remainder) {
                ans.add(2)
                remainder -= 2
            } else if (slotsLeft * 1 <= remainder) {
                ans.add(1)
                remainder -= 1
            } else return intArrayOf()

        }
        return ans.toIntArray()
    }
}

fun main() {
    val soln = `2028`()
    println(soln.missingRolls(intArrayOf(4,2,2,5,4,5,4,5,3,3,6,1,2,4,2,1,6,5,4,2,3,4,2,3,3,5,4,1,4,4,5,3,6,1,5,2,3,3,6,1,6,4,1,3), 2, 53).toList())
}