package LC2024



class `884` {
    fun uncommonFromSentences(s1: String, s2: String): Array<String> {
        val x = (s1.split(" ") + s2.split(" "))
        val counts = mutableMapOf<String, Int>()
        x.forEach { word -> counts[word] = counts.getOrDefault(word, 0) + 1 }
        return counts.filter { (k, v) -> v == 1 }.keys.toTypedArray()
    }
}

fun main() {
    val soln = `884`()
    println(soln.uncommonFromSentences("this apple is sweet", "this apple is sour"))
}