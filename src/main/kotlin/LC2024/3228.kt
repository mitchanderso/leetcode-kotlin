package LC2024

class `3228` {

    fun maxOperations(s: String): Int {

        var cnt = 0
        var ans = 0
        for (i in 0 until s.length - 1) {
             if (s[i] == '1') {
                cnt++
            }
            if (s[i] == '1' &&  s[i + 1] == '0') {
                ans += cnt
            }
        }
        return ans
    }
    fun maxOperationsSlow(s: String): Int {
        var sb = StringBuilder(s)
        var indexOfOneZero = sb.indexOf("10")
        var moves = 0
        while (indexOfOneZero >= 0) {
            var movingIndex = indexOfOneZero
            while (movingIndex + 1 < sb.length && sb[movingIndex + 1] == '0') {
                sb[movingIndex] = '0'
                sb[movingIndex + 1] = '1'
                movingIndex++
            }

            moves++
            println("After $moves the string is $sb")
            // We only need to start looking from where we moved to
            indexOfOneZero = sb.indexOf("10")
        }
        return moves
    }
}

fun main() {
    val soln = `3228`()
    println(soln.maxOperations("1001101"))
}