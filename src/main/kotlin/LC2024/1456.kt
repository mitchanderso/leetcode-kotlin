package LC2024

import java.lang.Math.max

class `1456` {

    val vowels = setOf('a','e','i','o','u')
    fun maxVowels(s: String, k: Int): Int {

        var start = 0
        var end = k - 1
        var count = s.substring(0, k).count { it in vowels }
        var max = count
        while (end < s.length - 1) {
            // Remove the first char
            if (s[start] in vowels) count--
            start++
            end++
            // Add the last char
            if (s[end] in vowels) count++
            max = max(count, max)
        }

        return max
    }
}



fun main() {
    val soln = `1456`()
    println(soln.maxVowels("abciii", 3))
}