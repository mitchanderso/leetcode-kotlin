package LC2024

class `13` {
    fun romanToInt(s: String): Int {
        var idx = 0
        var ans = 0

        val map = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000,

        )

        while (idx < s.length) {
            ans += when {
                s[idx] == 'I' && idx + 1 < s.length && s[idx+1] == 'V' -> {
                    idx++
                    4
                }
                s[idx] == 'I' && idx + 1 < s.length && s[idx+1] == 'X' -> {
                    idx++
                    9
                }
                s[idx] == 'X' && idx + 1 < s.length && s[idx+1] == 'L' -> {
                    idx++
                    40
                }
                s[idx] == 'X' && idx + 1 < s.length && s[idx+1] == 'C' -> {
                    idx++
                    90
                }
                s[idx] == 'C' && idx + 1 < s.length && s[idx+1] == 'D' -> {
                    idx++
                    400
                }
                s[idx] == 'C' && idx + 1 < s.length && s[idx+1] == 'M' -> {
                    idx++
                    900
                }

                else -> map[s[idx]]!!
            }
            idx++
        }

        return ans
    }
}

fun main() {
    val soln = `13`()
    println(soln.romanToInt("MCMXCIV"))
}