package LC2024

class `350` {
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        var shortPtr = 0
        var longPtr = 0
        val (shorter, longer) = arrayOf(nums1, nums2).sortedBy { it.size }

        shorter.sort()
        longer.sort()

        val ans = mutableListOf<Int>()

        while (shortPtr < shorter.size && longPtr < longer.size) {

            while (longPtr < longer.size && longer[longPtr] < shorter[shortPtr]) {
                longPtr++
            }
            while (longPtr < longer.size && shortPtr < shorter.size && shorter[shortPtr] < longer[longPtr]) {
                shortPtr++
            }

            if (shortPtr >= shorter.size || longPtr >= longer.size) return ans.toIntArray()


            if (shorter[shortPtr] == longer[longPtr]) {
                ans.add(shorter[shortPtr])
                shortPtr++
                longPtr++
            }


        }

        return ans.toIntArray()

    }
}

fun main() {
    val soln = `350`()
    println(soln.intersect(intArrayOf(1,2), intArrayOf(1,1)).toList())
}