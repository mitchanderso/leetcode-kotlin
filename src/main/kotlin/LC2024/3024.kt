package LC2024

class `3024` {
    fun triangleType(nums: IntArray): String {
        val (s1,s2,s3) = nums
        if (s1 + s2 < s3 && s1 + s3 < s2 && s2 + s3 < s1) return "none"
        if (s1 == s2 && s2 == s3) return "equilateral"
        if (s1 != s2 && s2 != s3 && s1 != s3) return "scalene"
        return "isosceles"
    }
}

fun main() {
    val soln = `3024`()
    println(soln.triangleType(intArrayOf(3,3,3)))
}