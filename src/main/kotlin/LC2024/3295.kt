package LC2024

class `3295` {
    fun reportSpam(message: Array<String>, bannedWords: Array<String>): Boolean {
        val bannedWordsSet = bannedWords.toSet()
        var count = 0
        for (i in 0 until message.size) {
            if (message[i] in bannedWordsSet) count++
            if (count >= 2) return true
        }
        return false
    }
}

fun main() {
    val soln = `3295`()
    println(soln.reportSpam(arrayOf("hello","world","leetcode"), arrayOf("world","hello")))
}