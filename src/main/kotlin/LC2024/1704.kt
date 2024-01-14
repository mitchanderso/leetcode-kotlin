package LC2024

class `1704` {
    fun halvesAreAlike(s: String): Boolean {
        return s.substring(0, s.length / 2).vowelCount() == s.substring(s.length / 2).vowelCount()
    }

    fun String.vowelCount() = count { it.lowercaseChar() in listOf('a', 'e', 'i', 'o', 'u') }
}

fun main() {
    val soln = `1704`()
    println(soln.halvesAreAlike("textbook"))
}