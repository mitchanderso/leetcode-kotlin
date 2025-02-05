package LC2025

class `1790` {
    fun areAlmostEqual(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false
        val differenceIndices = mutableListOf<Int>()
        for (i in 0 until s1.length) {
            if (s1[i] != s2[i]) differenceIndices.add(i)
        }
        if (differenceIndices.size == 0) return true
        if (differenceIndices.size != 2) return false
        if (mutableListOf(s1[differenceIndices[0]], s1[differenceIndices[1]]).sorted() == mutableListOf(s2[differenceIndices[0]], s2[differenceIndices[1]]).sorted()) return true
        return false
    }
}

fun main() {
    val soln = `1790`()
    println(soln.areAlmostEqual("aa", "ac"))


}