package LC2024

class `2678` {
    fun countSeniors(details: Array<String>): Int {
        return details.count { "${it[11]}${it[12]}".toInt() > 60}
    }
}

fun main() {
    val soln = `75`()
    println(soln.sortColors(intArrayOf(2,0,2,1,1,0)))
}