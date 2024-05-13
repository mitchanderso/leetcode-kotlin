package LC2024

import java.lang.Math.min
import kotlin.system.measureTimeMillis


class `279`() {
    fun perfectSquares(n: Int): Int {
        val perfectSquares = (1 .. 100).map { it * it }
        println(perfectSquares)
        return numSquaresRec(n, MutableList(10001) { -1 })
        //return psRecur(n, mutableMapOf<Int, Int>(0 to 0))
    }

    fun numSquaresRec(n: Int, memo: MutableList<Int>) : Int
    {
        println("fn($n)")
        if (n <= 3)
            return n

        if (memo[n] != -1) {
            println("memo $n")
            return memo[n]
        }


        var localMin = Int.MAX_VALUE
        for (i in 1 until 10000) {
            if (i * i > n) break
            localMin = min(localMin, numSquaresRec(n - i * i, memo) + 1)
        }

        memo[n] = localMin
        return memo[n]
    }



    fun psRecur(i: Int, memo: MutableMap<Int, Int>)  : Int{
        if (i < 0) return Int.MAX_VALUE
        if (i == 0) return 0
        if (memo.containsKey(i)) return memo[i]!!


        var min = Int.MAX_VALUE
        var j = 1
        while (j * j <= i) {
            val using = psRecur(i - (j * j), memo)
            min = min(min, using + 1)
            j = j + 1
        }

        memo[i] = min
        return min
    }


}


fun main() {
    val soln = `279`()
//    println( measureTimeMillis {  println(soln.perfectSquares(6255)) } )
    println( measureTimeMillis {  println(soln.perfectSquares(6254)) } )




}