package LC2024

class `131` {
    val ans = mutableListOf<MutableList<String>>()
    fun partition(s: String): List<List<String>> {
        partitionR(s, 0, mutableListOf())
        return ans
    }

    fun partitionR(s: String, pos: Int, workingList: MutableList<String>) {
        if (pos >= s.length) {
            println("The split is $workingList")
            ans.add(workingList.toMutableList())
            return
        }

        for (i in pos until s.length) {
            if (isPalin(s, pos, i)) {
                workingList.add(s.substring(pos, i + 1))
                partitionR(s, i + 1, workingList)
                workingList.removeAt(workingList.size - 1)
            }
        }

    }

    fun isPalin(s: String, i: Int, j: Int) : Boolean {
        var ii = i
        var jj = j
        while (ii <= jj) {
            if (s[ii] != s[jj]) return false
            ii++
            jj--
        }
        return true
    }

}



fun main() {
    val soln = `131`()
    println(soln.partition("aab"))



}