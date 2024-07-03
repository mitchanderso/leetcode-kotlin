package LC2024


class `1404`() {
    fun numSteps(s: String): Int {
        var num = s.toMutableList()
        var ans = 0
        while ( !num.isOne()) {
            if (num.odd()) {
                // Find the last 0, change everything to the right to 1's
                val idxOfLast0 = num.indexOfLast { it == '0' }
                if (idxOfLast0 < 0) {
                    num.add(0, '1')
                    for (i in 1 until num.size) num[i] = '0'
                } else {
                    num[idxOfLast0] = '1'
                    for (i in idxOfLast0 + 1 until num.size) num[i] = '0'
                }


                println("Odd, add1: Number is now $num")
            } else {
                num.removeAt(num.size - 1)
                println("Even, divide by 2: Number is now $num")
            }
            ans++
        }


        return ans
    }

    private fun MutableList<Char>.isOne(): Boolean {
        return this.size == 1
    }

    fun List<Char>.odd() : Boolean {
        return this.last() == '1'
    }


}




fun main() {
    val soln = `1404`()
    println( soln.numSteps("1101"))




}