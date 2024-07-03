package LC2024

class `39` {

    val ans = mutableListOf<MutableList<Int>>()
    val seen = mutableMapOf<List<Int>, Boolean>()
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val sorted = candidates.sortedDescending().toIntArray()

        combinationSumR(sorted, target, 0, mutableListOf())
        return ans
    }

    fun combinationSumR(candidates: IntArray, remaining: Int, pos: Int, workingList: MutableList<Int>) {
        if (remaining < 0) return
        if (remaining == 0 && !seen.containsKey(workingList.toMutableList())) {
            ans.add(workingList.toMutableList())
            seen[workingList.toMutableList()] = true
            return
        }

        for (i in pos until candidates.size) {
            workingList.add(candidates[i])
            combinationSumR(candidates, remaining - candidates[i], i, workingList)
            workingList.removeAt(workingList.size - 1)
        }
    }

}



fun main() {
    val soln = `39`()
    println(soln.combinationSum(intArrayOf(2,3,5), 8))



}