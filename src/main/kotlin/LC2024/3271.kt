package LC2024

class `3271` {
    fun stringHash(s: String, k: Int): String {
        // Divide in to n/k subsctrings
        val substrings = mutableListOf<String>()
        val howManySubStrings = s.length / k
        val len = s.length / howManySubStrings

        var currentLength = 0
        var sb = java.lang.StringBuilder()
        for (i in 0 until s.length) {
            if (currentLength == len) {
                substrings.add(sb.toString())
                sb = StringBuilder()
                currentLength = 0
            }
            sb.append(s[i])
            currentLength++
        }

        substrings.add(sb.toString())

        val ans = StringBuilder()

        substrings.forEach { substring ->
            val hashedChar = substring.map { it - 'a' }.sum() % 26
            val letter = Char(hashedChar + 97)
            ans.append(letter)
        }

        return ans.toString()
    }
}

fun main() {
    val soln = `3271`()
    println(soln.stringHash("mxz", 3))
}