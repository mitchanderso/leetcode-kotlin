package LC2024

class `2597` {
    var subsets = mutableListOf<MutableList<Int>>()
    fun beautifulSubsets(nums: IntArray, k: Int): Int {
        dfs(nums.also { it.sort() }, k, 0, mutableListOf())
        println(subsets)
        return subsets.size
    }

    fun dfs(nums: IntArray, k: Int, pos: Int, workingList: MutableList<Int>) {
        if (pos >= nums.size) {
            if (workingList.isNotEmpty()) {
                subsets.add(workingList.toMutableList())
            }
            return
        }

        // Take
        val addingIn = nums[pos]
        val dontWant = addingIn + k
        if (!workingList.contains(dontWant)) {
            workingList.add(addingIn)
            dfs(nums, k, pos + 1, workingList)
            workingList.removeAt(workingList.size - 1)
        }


        // Dont take
        dfs(nums, k, pos + 1, workingList)
    }


}



fun main() {
    val soln = `2597`()

    println(soln.beautifulSubsets(
        intArrayOf(2,4,6), 2
    ))




}