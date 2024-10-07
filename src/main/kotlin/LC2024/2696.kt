package LC2024

class `2696` {
    fun minLength(s: String): Int {
        var hasRemovable = true
        var sb = s.toMutableList()
        while (hasRemovable) {
            val removal = mutableListOf<Int>()
            for (i in 1 until sb.size) {
                if ((sb[i - 1] == 'A' && sb[i] == 'B') || (sb[i - 1] == 'C' && sb[i] == 'D')) {
                    removal.add(i - 1)
                    removal.add(i)
                }
            }
            hasRemovable = removal.isNotEmpty()
            val newsb = mutableListOf<Char>()
            for (i in 0 until sb.size) {
                if (i !in removal) newsb.add(sb[i])
            }
            sb = newsb
        }

        return sb.size
    }
}

fun main() {
    val soln = `2696`()
    println(soln.minLength("ABFCACDB"))
}