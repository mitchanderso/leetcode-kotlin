package LC2024

import java.lang.Math.max


class `3169` {

    fun countDays(days: Int, meetings: Array<IntArray>): Int {
        meetings.sortWith( compareBy<IntArray> { it[0] }.thenBy { it[1] } )
        var ans = 0
        var current = 1
        var previous = 0

        while (current < meetings.size) {
            if (meetings[current][0] <= meetings[previous][0] && meetings[current][1] <= meetings[previous][1]){
                meetings[current] = intArrayOf(-1, -1)
                current++
            }
            else if (meetings[current][1] <= meetings[previous][1] && meetings[current][0] <= meetings[previous][1]) {
                meetings[current] = intArrayOf(-1, -1)
                current++
            }
            else if (meetings[current][0] <= meetings[previous][1]) {

                if (meetings[current][1] > meetings[previous][1]) {
                    meetings[previous][1] = meetings[current][1]
                    meetings[current] = intArrayOf(-1, -1)
                } else {
                    val (t1, t2) = meetings[current]
                    meetings[previous][1] = max(meetings[current][0], meetings[previous][1])
                    meetings[current] = intArrayOf(meetings[previous][1] + 1, t2)
                    previous = current
                }


                current++
            }
            else {
                previous = current
                current++
            }

            //ans += ((meetings[previous][1] - meetings[previous][0]) + 1)


        }
        var meetingDays = 0
        meetings.forEach { (start, end) ->
            if (start != -1) {
                meetingDays += (end - start) + 1
            }
        }

        return days - meetingDays
    }


}

fun main() {
    val soln = `3169`()
    println(soln.countDays(8,
        arrayOf(
            intArrayOf(2,5),
            intArrayOf(3,4),
            intArrayOf(4,8),
            intArrayOf(3,8),
        )
        )) // 1
}