package LC2024

class `1497` {
    fun canArrange(arr: IntArray, k: Int): Boolean {

        val countOfModuloFrequency = mutableMapOf<Int, Int>()
        arr.forEach { num ->
            var remainder = num % k
            if (remainder < 0) remainder += k
            val complement = k - remainder
            countOfModuloFrequency[complement] = countOfModuloFrequency.getOrDefault(complement, 0) + 1
        }

        for (i in 1 .. k / 2) {
            if (countOfModuloFrequency[i] != countOfModuloFrequency[k - i]) {
                return false
            }
        }

        if (countOfModuloFrequency.containsKey(0) && countOfModuloFrequency[0]!! % 2 != 0) return false

        return true

    }
}

fun main() {
    val soln = `1497`()
    println(soln.canArrange(intArrayOf(3,8,7,2), 10))
}