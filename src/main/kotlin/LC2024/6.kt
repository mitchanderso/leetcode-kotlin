package LC2024

class `6` {
    fun convert(s: String, numRows: Int): String {
        val array = Array(numRows) { Array(1000) { '*' } }
        if (numRows == 1) return s
        var x = 0
        var y = 0
        var onDiagonal = false
        val ans = java.lang.StringBuilder()
        for (i in 0 until s.length) {
            array[y][x] = s[i]
            if (onDiagonal) {
                x++
                y--
                if (y == 0) onDiagonal = false
            } else {
                y++
            }
            if (y == numRows - 1) {
                onDiagonal = true
            }
        }

        for (yy in 0 until array.size) {
            for (xx in 0 until array[0].size) {
                if (array[yy][xx] != '*') ans.append(array[yy][xx])
            }
            println()
        }

        return ans.toString()
    }
}

fun main() {
    val soln = `6`()
    println(soln.convert("AB", 1))
}