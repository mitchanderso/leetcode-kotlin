package LC2024

class `78` {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        result.add(emptyList())
        for (i in nums.indices) {
            val tempList = result.toList()
            tempList.forEach { list ->
                result.add(list.toMutableList() + nums[i])
            }
        }
        return result
    }


}



fun main() {
    val soln = `78`()




}