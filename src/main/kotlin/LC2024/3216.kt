package LC2024

class `3216` {
    fun getSmallestString(s: String): String {
        val sb = StringBuilder(s)
        for (i in 1 until sb.length) {
            val d1 = sb[i - 1].digitToInt()
            val d2 = sb[i].digitToInt()
            if (d1 % 2 == d2 % 2 &&
                d2 < d1) {
                val temp = sb[i - 1]
                sb[i - 1] = sb[i]
                sb[i] = temp
                return sb.toString()
            }
        }

        return sb.toString()
    }
}

fun main() {
    val soln = `3216`()
    println(soln.getSmallestString("45320"))
}