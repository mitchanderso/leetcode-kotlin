package LC2024

class `238` {
    fun productExceptSelf(nums: IntArray): IntArray {
        val prefixMultiply = mutableListOf<Int>().apply { add(0) }
        val suffixMultiply = mutableListOf<Int>().apply { add(0) }


        var prev = 1

        for (i in 1 until nums.size) {
            prefixMultiply.add(prev * nums[i - 1])
            prev = prefixMultiply[i]
        }

        prev = 1
        for (i in nums.size - 1 downTo 1) {
            suffixMultiply.add(0, prev * nums[i])
            prev = suffixMultiply[0]
        }

        suffixMultiply[suffixMultiply.size - 1] = 1
        prefixMultiply[0] = 1

        return prefixMultiply.mapIndexed { index, i ->
            i * suffixMultiply[index]
        }.toIntArray()

    }
}

fun main() {
    val soln = `238`()
    println(soln.productExceptSelf(intArrayOf(-1,1,0,-3,3)).toList())
}