package LC2024

import java.lang.Math.min
import java.util.PriorityQueue
import java.util.Stack


class `2402`() {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {

        if (n == 1) return 0

        val cc = compareBy<IntArray> ( {it[0]}, {it[1]} )
        val sortedMeetings = meetings.sortedWith(cc)

        var assigned = 0
        val roomCounts = mutableMapOf<Int, Int>()
        val roomPq = PriorityQueue<Room>(compareBy ({ it.freeAt }, { it.n }))
        for (i in 0 until n) roomPq.offer(Room(i, 0))

        while (assigned < meetings.size) {
            val (currentMeetingStart, currentMeetingEnd) = sortedMeetings[assigned]
            if (roomPq.peek().freeAt <= currentMeetingStart) {
                val popped = mutableListOf<Room>()

                while (roomPq.isNotEmpty() && roomPq.peek().freeAt <= currentMeetingStart) {
                    popped.add(roomPq.poll())
                }
                val usedRoom = popped.sortedBy { it.n }[0]
                popped.remove(usedRoom)
                val freeAt = usedRoom.freeAt + (currentMeetingEnd - currentMeetingStart) + (currentMeetingStart - usedRoom.freeAt)
                println("Meeting $currentMeetingStart,$currentMeetingEnd starting in room ${usedRoom.n}, it wont be free until $freeAt")
                roomCounts[usedRoom.n] = roomCounts.getOrDefault(usedRoom.n, 0 ) + 1
                roomPq.offer(Room(usedRoom.n, freeAt))
                popped.forEach { roomPq.offer(it) }
            } else {
                // Get the earleist free room

                val usedRoom = roomPq.poll()
                // Jump forward in time
                val waiting = usedRoom.freeAt - currentMeetingStart
                val newStart = currentMeetingStart + waiting
                val newEnd = currentMeetingEnd + waiting
                val freeAt = usedRoom.freeAt + (currentMeetingEnd - currentMeetingStart)
                println("Meeting $currentMeetingStart,$currentMeetingEnd was delayed by $waiting starting in room ${usedRoom.n}, it wont be free until $freeAt")
                roomCounts[usedRoom.n] = roomCounts.getOrDefault(usedRoom.n, 0 ) + 1
                roomPq.offer(Room(usedRoom.n, freeAt))

            }
            assigned++
        }

        val sortedCounts = roomCounts.map { it.key to it.value }.sortedByDescending { it.second }
        return if (sortedCounts.size > 1 && sortedCounts[0].second == sortedCounts[1].second) {
            min(sortedCounts[0].first, sortedCounts[0].second)
        } else return sortedCounts[0].first

    }

    data class Room(val n: Int, val freeAt: Int)


}


fun main() {
    val soln = `2402`()

    val n = 43261596
    val binaryString = Integer.toBinaryString(n)

    val padded = binaryString.padStart(32, '0')
    Integer.parseUnsignedInt(padded.reversed(),2)

    println(soln.mostBooked(4, arrayOf(
        intArrayOf(18,19),
        intArrayOf(3,12),
        intArrayOf(17,19),
        intArrayOf(2,13),
        intArrayOf(7,10),
    )))


    // Answer is 0

//    println(soln.mostBooked(2, arrayOf(
//        intArrayOf(0,1)
//    )))

//    println(soln.mostBooked(2, arrayOf(
//        intArrayOf(0,10),
//        intArrayOf(1,5),
//        intArrayOf(2,7),
//        intArrayOf(3,4),
//    )))
//
//
//
//    println(soln.mostBooked(2, arrayOf(
//        intArrayOf(1,20),
//        intArrayOf(2,10),
//        intArrayOf(3,5),
//        intArrayOf(4,9),
//        intArrayOf(6,8),
//    )))



}