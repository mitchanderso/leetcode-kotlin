package LC2024

class `3280` {
    fun convertDateToBinary(date: String): String {
        val parts = date.split("-")
        val binary = parts.map { Integer.toBinaryString(it.toInt()) }
        return binary.joinToString("-")

    }
}

fun main() {
    val soln = `3280`()
    println(soln.convertDateToBinary("1900-01-01"))
}