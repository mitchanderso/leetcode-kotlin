package LC2024


class `3143`() {
    data class Point(var x: Long, var y: Long, val c: Char)
    fun maxPointsInsideSquare(points: Array<IntArray>, s: String): Int {
        var pointsList = mutableListOf<Point>()
        points.forEachIndexed { index, (x, y) ->
            pointsList.add(Point(x.toLong(), y.toLong(), s[index]))
        }

        pointsList = pointsList.sortedBy { Math.abs(it.x - 0) + Math.abs(it.y - 0) }.toMutableList()

        var left = 0L
        var right = 10e9.toLong()
        var res = 0L

        while (left <= right) {
            val middle : Long = left + (right - left) / 2

            val corners = mutableListOf(
                Point(0,0,'*'), // Top Right
                Point(0,0,'*'), // Bottom Right
                Point(0,0,'*'), // Bottom Left
                Point(0,0,'*'), // Top Left
            )

            corners.set(middle)
            val countOfCharsInSquare = mutableMapOf<Char, Int>()
            val pointsInsideWithGivenSideLength = pointsList.filter { it.isInSquare(corners) }
            pointsInsideWithGivenSideLength.forEach { countOfCharsInSquare[it.c] = countOfCharsInSquare.getOrDefault(it.c, 0) + 1}


            if (countOfCharsInSquare.hasMoreThan2()) {
                right = middle - 1
            } else {
                res = middle
                left = middle + 1
            }
        }

        var ans = 0
        val corners = mutableListOf(
            Point(0,0,'*'), // Top Right
            Point(0,0,'*'), // Bottom Right
            Point(0,0,'*'), // Bottom Left
            Point(0,0,'*'), // Top Left
        )

        corners.set(res)
        pointsList.forEach {
            if (it.isInSquare(corners)) ans++
        }

        return ans

    }

    fun MutableList<Point>.set(num : Long) {
        this[0].x = num; this[0].y =  num
        this[1].x = num; this[1].y =  -num
        this[2].x = -num; this[2].y = -num
        this[3].x = -num; this[3].y = num
    }




    fun Map<Char, Int>.hasMoreThan2() : Boolean {
        return this.any { (k, v) -> v >= 2 }
    }

    fun Point.isInSquare(corners: List<Point>) : Boolean {
        return (this.x >= corners[3].x &&
                this.x <= corners[0].x  &&
                this.y >= corners[1].y &&
                this.y <= corners[0].y )
    }

}



fun main() {
//    println(`3143`().maxPointsInsideSquare(
//        arrayOf(
//            intArrayOf(-1,-4),
//            intArrayOf(16,-8),
//            intArrayOf(13,-3),
//            intArrayOf(12,0),
//
//        ),
//        "abda"
//    ))
    println(`3143`().maxPointsInsideSquare(
        arrayOf(
            intArrayOf(1,-1)
        ),
        "a"
    ))

}