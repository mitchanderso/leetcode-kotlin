package LC2024

import java.util.Stack


class `2108`() {
    fun firstPalindrome(words: Array<String>): String {
        for (i in 0 until words.size) {
            if (isPalindrome(words[i])) return words[i]
        }
        return ""
    }

    fun isPalindrome(word: String) : Boolean {
        var left = 0
        var right = word.length - 1

        while (left <= right) {
            if (word[left] != word[right]) return false
            left++
            right--
        }

        return true
    }


}


fun main() {
    val soln = `2108`()
    println(soln.firstPalindrome(arrayOf("abba", "car", "ada", "racecar", "cool")))



}