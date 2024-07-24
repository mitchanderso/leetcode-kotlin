package LC2024

class `3226` {
    fun minChanges(n: Int, k: Int): Int {
        var nn = n
        var kk = k
        var count = 0
        while (nn > 0) {
            val nnBit = nn and 1
            val kkBit = kk and 1
            if (nnBit != kkBit && nnBit == 1) count++
            else if (nnBit != kkBit) return -1

            nn = nn shr 1
            kk = kk shr 1
            if (kk < 0) return count
        }

        return if (kk > 0) -1 else count
    }
}

fun main() {
    val soln = `3226`()
    println(soln.minChanges(11,56))
}