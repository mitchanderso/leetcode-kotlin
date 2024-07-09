package LC2024

class `3208` {
    fun numberOfAlternatingGroups(colors: IntArray, k: Int): Int {
        var start = 0
        var end = 1
        var last = colors[start]
        var run = 1
        var ans = 0

        while (end <= colors.size + k && start < colors.size) {
            if (last == colors[end  % colors.size]) {
                start = end
                if (start >= colors.size) return ans
                end = (end + 1)
                last = colors[start]
                run = 1
            } else if (last != colors[end  % colors.size]) {
                run++
                last = colors[end % colors.size]
                if (run >= k) {
                    ans++
                    start++
                    run--
                }
                end = (end + 1)
            }


        }

        return ans
    }
}

fun main() {
    val soln = `3208`()
    println(soln.numberOfAlternatingGroups(
        intArrayOf(0,1,0,0,1,0,1), k = 6
    ))
}