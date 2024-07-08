package LC2024

class `3210` {
    fun getEncryptedString(s: String, k: Int): String {
        return s.mapIndexed { index, c ->
            val skip = (index + k) % s.length
            s[skip]
        }.joinToString("")
    }
}

fun main() {
    val soln = `3210`()
    println(soln.getEncryptedString("oxoq", 4))
}