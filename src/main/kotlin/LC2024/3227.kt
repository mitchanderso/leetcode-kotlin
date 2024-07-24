package LC2024

class `3227` {
    fun doesAliceWin(s: String): Boolean {
        var vowelCount = s.count { it in setOf('a', 'e', 'i', 'o', 'u') }
        return vowelCount != 0
    }
}

fun main() {
}