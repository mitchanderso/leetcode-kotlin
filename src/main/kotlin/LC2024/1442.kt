package LC2024

class `1442` {
    fun countTriplets(arr: IntArray): Int {


        var count = 0
        for (start in 0 until arr.size) {
            var xorA = 0;
            for (mid in start + 1 until arr.size) {
                xorA = xorA xor arr[mid - 1];
                var xorB = 0;
                for (end in mid until arr.size) {
                    xorB = xorB xor  arr[end];
                    println("$start,$mid,$end  A = $xorA B = $xorB")

                    if (xorA == xorB) {
                        count++
                    }
                }
            }
        }



        return count

    }
}



fun main() {
    val soln = `1442`()
    println(soln.countTriplets(intArrayOf(2,3,1,6,7)))



}