package LC2024


class `1122` {

    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        var counts = mutableMapOf<Int, Int>()
        val toRemove = mutableSetOf<Int>()
        arr1.forEach { counts[it] = counts.getOrDefault(it, 0) + 1 }
        var ans = mutableListOf<Int>()
        arr2.forEach { num ->
            repeat( counts[num]!! ) { ans.add(num) }
            toRemove.add(num)
        }
        val extra = arr1.toMutableList()
        extra.removeAll { it in toRemove }
        return (ans + extra.sorted()).toIntArray()
    }


}

fun main() {
    val soln = `1122`()
    println(
        soln.relativeSortArray(
            intArrayOf(2,3,1,3,2,4,6,7,9,2,19),
            intArrayOf(2,1,4,3,9,6)
        ).toList()
    )
}