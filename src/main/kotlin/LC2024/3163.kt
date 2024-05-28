package LC2024


class `3163`() {
    fun compressedString(word: String): String {
        val ans = StringBuilder()

        var mutableWord = word
        var i = 0
        while (i < word.length) {
            val currentChar = mutableWord[i]
            var j = 0
            while (j < 9 && j + i < mutableWord.length && mutableWord[j + i] == currentChar) {
                j++
            }
            i += j
            ans.append("$j$currentChar")
        }

        return ans.toString()
    }

}


fun main() {
    val soln = `3163`()

    println(soln.compressedString("abcde"))


}