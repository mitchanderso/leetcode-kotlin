package LC2024

class `3206` {
    fun numberOfAlternatingGroups(colors: IntArray): Int {
        var ans = 0
        for (i in 1 until colors.size - 1) {
            if (colors[i] != colors[i - 1] && colors[i] != colors[i+1]) {
                ans++
            }
        }

        if (colors[0] != colors[1] && colors[0] != colors[colors.size - 1]) ans++
        if (colors[colors.size - 1] != colors[colors.size - 2] && colors[colors.size - 1] != colors[0]) ans++
        return ans
    }
}

fun main() {
    val soln = `3206`()
    println(soln.numberOfAlternatingGroups(intArrayOf(0,1,0,0,1)))
}