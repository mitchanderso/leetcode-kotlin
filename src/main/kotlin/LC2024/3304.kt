package LC2024

class `3304` {
    fun kthCharacter(k: Int): Char {
        var string = "a"
        while (string.length < k + 1) {
            val converted = convert(string)
            string += converted
        }
        return string[k - 1]
    }

    fun convert(word: String) : String {
        return word.map {
            val idx = it - 'a' + 1
            val newPosition = idx % 26
            Char(97 + newPosition)
        }.joinToString("")
    }
}

fun main() {
    val soln = `3304`()
    println(soln.kthCharacter(5))
}