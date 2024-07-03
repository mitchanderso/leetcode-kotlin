package LC2024


class `864`() {

    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        if (hand.size % groupSize != 0) return false
        val counts = hand.associateWith { num -> hand.count { it == num } }.toSortedMap()

        while (counts.isNotEmpty()) {
            val first = counts.firstKey()
            for (i in 0 until groupSize) {
                if (!counts.containsKey(first + i)) return false
                counts.decrementAndRemove(first + i)
            }

        }

        return true
    }

    fun MutableMap<Int,Int>.decrementAndRemove(key: Int) {
        this[key] = this[key]!! - 1
        if (this[key] == 0) this.remove(key)
    }

}


fun main() {
    val soln = `864`()
    println(soln.isNStraightHand(intArrayOf(1,2,2,3,3,3,4,4,5),3)) // true
//    println(soln.isNStraightHand(intArrayOf(1,2,3,4,5),4)) // false
//    println(soln.isNStraightHand(intArrayOf(3,2,1,2,3,4,3,4,5,9,10,11),3)) // true
//


}