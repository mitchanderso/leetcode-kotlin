package LC2024

class `1636` {

    fun frequencySort(nums: IntArray): IntArray {
        val numToFrequency = mutableMapOf<Int, Int>()


        nums.forEach { num ->
            numToFrequency[num] = numToFrequency.getOrDefault(num, 0) + 1
        }
        val sorted = numToFrequency.entries.sortedWith (compareBy<Map.Entry<Int, Int>> { it.value  }.thenByDescending { it.key })

        val ans = mutableListOf<Int>()
        sorted.forEach { (num, amt) ->
            repeat(amt) { ans.add(num) }
        }

        return ans.toIntArray()

    }
}

fun main() {
    val soln = `1636`()
    println(soln.frequencySort(intArrayOf(2,3,1,3,2)).toList())
}