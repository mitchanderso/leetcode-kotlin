package LC2024


class `2486` {

    fun appendCharacters(s: String, t: String): Int {
        var lastSearch = -1
        var tPos = 0
        while (tPos < t.length) {
            var toFind = t[tPos]
            var found = -1
            for (i in lastSearch + 1 until s.length) {
                if (s[i] == toFind) {
                    found = i
                    lastSearch = found
                    tPos++
                    break
                }
            }
            if (found == -1) {
                return t.length - tPos
            }

        }

        return 0
    }


}

fun main() {
    val soln = `2486`()
}