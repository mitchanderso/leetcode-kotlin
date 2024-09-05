package LC2024

class `2022` {
    fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> {
        if (m * n < original.size) return emptyArray()
        var row = 0
        var col = 0
        val ans = Array(m) { IntArray (n) { -1 } }

        for (i in 0 until original.size) {
            if (i % n == 0 && i != 0) {
                row++
                col = 0
            }
            ans[row][col] = original[i]
            col++
        }

        return ans
    }
}

fun main() {
    val soln = `2022`()
    println(soln.construct2DArray(intArrayOf(1,2),1,1))
}