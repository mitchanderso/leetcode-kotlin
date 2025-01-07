package AOC2024

import utils.Utils.print
import utils.Utils.split
import java.io.File
import kotlin.math.abs

class `Day7` {

    data class Calibration(val target: Long, val nums: List<Long>)

    fun p1(file: File) : Long {
        val calibrations = file.parse()
        return calibrations
            .filter { canBeCalibrated(it.target, 0, 0, it.nums) }
            .fold(0L) { acc: Long, calibration: Calibration ->
                acc + calibration.target
            }

    }

    fun p2(file: File) : Long {
        val calibrations = file.parse()
        return calibrations
            .filter { canBeCalibratedWithConcatenations(it.target, 0, 0, it.nums) }
            .fold(0L) { acc: Long, calibration: Calibration ->
                acc + calibration.target
            }

    }

    fun concatenate(splitPoint: Int, nums: List<Long>) : Long {
        val left = nums.subList(0, splitPoint)
        val right = nums.subList(splitPoint, nums.size)
        return (left+right).joinToString("").toLong()
    }

    fun canBeCalibratedWithConcatenations(target: Long, workingValue: Long, index: Int, nums: List<Long>) : Boolean {
        var possible = false
        for (splitPoint in index until nums.size) {
            val left = nums.subList(0, splitPoint)
            val right = nums.subList(splitPoint, nums.size)

            // Check that the left matches the target
            if (left.toString() == target.toString().substring(0, left.size)) {
                // If we concatenate right and left
                if ((left+right).joinToString("").toLong() == target) {
                    // Can concatenate
                    possible = true
                }

                // Maybe we can get all possibilities and then concatenate
                val rightTarget = target.toString().substring(left.size)
            }
        }
        return false
    }

    fun canBeCalibrated(target: Long, workingValue: Long, index: Int, nums: List<Long>) : Boolean {
        if (index >= nums.size) {
            return workingValue == target
        }
        if (workingValue > target) return false
        // For the first item, always subtract from target
        if (index == 0) {
            return canBeCalibrated(target, workingValue + nums[index], index + 1, nums)
        } else {
            val validAddition = canBeCalibrated(target, workingValue + nums[index], index + 1, nums)
            val validMultiplication = canBeCalibrated(target, workingValue * nums[index], index + 1, nums)
            return validMultiplication || validAddition
        }
    }

    fun File.parse() : List<Calibration> {
        return this.readLines().map {
            val (target, nums) = it.split(":")
            Calibration(target.toLong(), nums.trim().split(" ").map { it.trim().toLong() })
        }
    }

}

fun main() {
    val soln = Day7()
    val file = File(Day7::class.java.getResource("/AOC2024/day7/input.txt").file)

    println(soln.p2(file))
    //println(soln.concatenate(1, listOf(13L, 14L)))

}