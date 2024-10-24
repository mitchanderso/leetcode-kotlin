package LC2024

class `2938` {
    fun minimumSteps(s: String): Long {
        var spotToMoveTo = s.length - 1
        while (spotToMoveTo >= 0 && s[spotToMoveTo] == '1') {
            spotToMoveTo--
        }
        if (spotToMoveTo == 0) return 0L
        var ans = 0L

        for (i in spotToMoveTo - 1 downTo 0) {
            if (s[i] == '1') {
                // We need to move it all the way to the back
                // then move the back
                var distance = spotToMoveTo - i
                ans += distance
                spotToMoveTo -= 1
            }
        }

        return ans
    }
}

fun main() {
    val soln = `2938`()
    println(soln.minimumSteps("0001111"))
}