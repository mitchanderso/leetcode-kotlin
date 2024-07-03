package LC2024


class `3160`() {
    fun queryResults(limit: Int, queries: Array<IntArray>): IntArray {
        // Colour -> balls of each colour
        val colorToBalls = mutableMapOf<Int, MutableSet<Int>>()
        val ballColor = mutableMapOf<Int, Int>()

        val ans = queries.map { (ball, color) ->
            // Update the list of colors
            if (colorToBalls.containsKey(color)) colorToBalls[color]!!.add(ball)
            else {
                colorToBalls[color] = mutableSetOf(ball)
            }


            // If it changed color from what it was before, we need to remove from the list.....
            val existingColor = ballColor.getOrDefault(ball, -1)
            if (existingColor != color && existingColor != -1) {
                colorToBalls[existingColor]!!.remove(ball)
                if (colorToBalls[existingColor]!!.isEmpty()) {
                    colorToBalls.remove(existingColor)
                }
            }
            ballColor[ball] = color


            colorToBalls.size

        }
        return ans.toIntArray()
    }

}


fun main() {
    val soln = `3160`()
    println(
        soln.queryResults(
            1,
            arrayOf(
                intArrayOf(0,1),
                intArrayOf(0,4),
                intArrayOf(0,4),
                intArrayOf(0,1),
                intArrayOf(1,2),
            )
        ).toList()
    )
}