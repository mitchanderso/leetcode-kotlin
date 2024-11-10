package LC2024

class `2275` {
    fun largestCombination(candidates: IntArray): Int {
        val counts = Array<Int>(24) { 0 }
        val binary = candidates.map { Integer.toBinaryString(it).padStart(24, '0') }

        binary.forEach { binaryString ->
            for (i in 0 until binaryString.length) {
                if (binaryString[i] == '1') counts[i]++
            }
        }

        return counts.max()

    }
}

fun main() {
    val soln = `2275`()
    println(soln.largestCombination(intArrayOf(16,17,71,62,12,24,14)))
}