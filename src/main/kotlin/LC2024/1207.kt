package LC2024

class `1207` {
    fun uniqueOccurrences(arr: IntArray): Boolean {
        val map = mutableMapOf<Int, Int>()
        arr.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        return map.values.toSet().size == map.keys.size
    }


}

fun main() {
    val soln = `1207`()
    println(soln.uniqueOccurrences(intArrayOf(1,2,2,1,1,3)))
    println(soln.uniqueOccurrences(intArrayOf(1,2)))
    println(soln.uniqueOccurrences(intArrayOf(-3,0,1,-3,1,1,1,-3,10,0)))
}