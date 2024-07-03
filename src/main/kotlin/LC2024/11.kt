package LC2024

class `11` {
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.size - 1
        var ans = -1;
        while (left <= right) {
            val distance = right - left
            val multiplier = Math.min(height[left], height[right])
            ans = Math.max(ans, multiplier * distance)
            if (height[left] < height[right]) left++ else right--
        }

        return ans
    }




}



fun main() {
    val soln = `11`()

}