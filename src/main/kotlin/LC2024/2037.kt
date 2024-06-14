package LC2024

import kotlin.math.abs

class `2037` {
    fun minMovesToSeat(seats: IntArray, students: IntArray): Int {
        seats.sort()
        students.sort()
        var ans = 0
        for (i in 0 until students.size) {
            ans += abs(seats[i] - students[i])
        }
        return ans
    }
}

fun main() {
    val soln = `2037`()
    println(soln.minMovesToSeat(
        intArrayOf(3,1,5),
        intArrayOf(2,7,4)
    ))

}