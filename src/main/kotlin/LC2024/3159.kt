package LC2024


class `3159`() {
    fun occurrencesOfElement(nums: IntArray, queries: IntArray, x: Int): IntArray {
        val occurrences = mutableMapOf<Int, MutableList<Int>>()
        nums.forEachIndexed { index, i ->
            val list = occurrences.getOrDefault(i, mutableListOf())
            list.add(index)
            occurrences[i] = list
        }

        val answers = queries.map {
            val indexList = occurrences.getOrDefault(x, emptyList())
            if (it - 1 > indexList.size - 1) -1
            else {
                indexList[it - 1]
            }
        }

        return answers.toIntArray()
    }

}


fun main() {
    val soln = `3159`()
    println(
        soln.occurrencesOfElement(
            intArrayOf(1,3,1,7),
            intArrayOf(1,3,2,4),
            1
        ).toList()
    )
}