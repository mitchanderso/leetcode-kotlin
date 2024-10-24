package LC2024

class `239` {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val ans = mutableListOf<Int>()
        val deque = ArrayDeque<Int>()
        for (i in 0 until nums.size) {

            // Remove everything from the start that is no longer in the window
            while (deque.isNotEmpty() && deque.first() < i - k + 1) {
                deque.removeFirstOrNull()
            }

            // Remove everything from the head that is less than the current
            // It can never be the new max
            while (deque.isNotEmpty() && nums[deque.last()] < nums[i]) {
                deque.removeLastOrNull()
            }

            // Add the current value in
            deque.addLast(i)


            if (i >= k - 1) {
                ans.add(nums[deque.first()])
            }
        }

        return ans.toIntArray()
    }
}

fun main() {
    val soln = `239`()
    println(soln.maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 3).toList())
}