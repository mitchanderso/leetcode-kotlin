package LC2024

class `273` {

    val lowMap = mutableMapOf(0 to "Zero", 1 to "One", 2 to "Two", 3 to "Three", 4 to "Four", 5 to "Five", 6 to "Six", 7 to "Seven", 8 to "Eight", 9 to "Nine", 10 to "Ten", 11 to "Eleven", 12 to "Twelve",
    13 to "Thirteen", 14 to "Fourteen", 15 to "Fifteen", 16 to "Sixteen", 17 to "Seventeen", 18 to "Eighteen", 19 to "Nineteen")
    val twoDigitMap = mutableMapOf(2 to "Twenty", 3 to "Thirty", 4 to "Forty", 5 to "Fifty", 6 to "Sixty", 7 to "Seventy", 8 to "Eighty", 9 to "Ninety")

    val fig = listOf("", "Thousand", "Million", "Billion")

    fun numberToWords(num: Int): String {

        val list = mutableListOf<String>()
        val str = num.toString()
        var figDex = 0
        var sb = java.lang.StringBuilder()
        for (i in str.length - 1 downTo 0) {

            sb.append(str[i])

            if (sb.length == 3 || i == 0) {
                list.add(0, threeDigit(sb.reverse().toString(),figDex, i > 0))
                sb = StringBuilder()
                figDex++
            }
        }

        return list.filter { it.isNotEmpty() }.joinToString(" ").trimEnd { it.isWhitespace() }

    }

    fun threeDigit(num: String, figDex: Int, leading: Boolean) : String {
        val numStripped = num.toInt().toString()
        if (num.toInt() == 0 && leading) return ""
        if (num.toInt() < 20  ) {
            return (lowMap[numStripped.toInt()]!!  + " " +  fig[figDex]).trimEnd { it.isWhitespace() }
        }
        else if (num.toInt() < 100) {
            return (twoDigitMap[numStripped[0].digitToInt()]!! + " " + (if (numStripped[1].digitToInt() > 0) lowMap[numStripped[1].digitToInt()] + " " else "") + fig[figDex]).trimEnd { it.isWhitespace() }
        }
        val secondPart = threeDigit("${num[1]}${num[2]}", 0, true)
        val sp = if (secondPart.length > 0 ) " " + secondPart else secondPart
        return (lowMap[num[0].digitToInt()]!! + " " + "Hundred" + sp + " " + fig[figDex]).trimEnd { it.isWhitespace() }
    }
}

fun main() {
    val soln = `273`()
    println(soln.numberToWords(1000010))
}