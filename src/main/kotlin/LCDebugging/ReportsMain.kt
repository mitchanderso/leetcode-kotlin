package LCDebugging

import java.time.LocalDate

fun main() {
    // Should give you the the last N days worth of timesheet data, and throw an exception if the user hasn't uploaded their days...but something is wrong
    println(
        getLastNDaysOfTimeSheetDataFromEmployee(7, "James Garner").map { it.uuid }
    )

    // Should tell you the index of the first unique character in a string...but something is wrong
    println(firstUniqueCharacterInString("hello")) // 0
    println(firstUniqueCharacterInString("ttoday")) // 2
}




fun firstUniqueCharacterInString(s: String): Int {
    val locs = mutableMapOf<Char, Int>()
    s.forEachIndexed { idx, ch ->
        if (!locs.containsKey(ch)) {
            locs[ch] = idx
        } else {
            locs.remove(ch)
        }
    }

    return locs.values.sorted().first()
}




fun getLastNDaysOfTimeSheetDataFromEmployee(days: Int, creator: String): List<TimeSheetEntry> {
    var daysRemaining = days
    val data = timesheet_data.sortedBy { it.uploadedDate }
        .filter { it.creator == creator }
        .takeWhile {
            daysRemaining--
            daysRemaining >= 0
        }
    return if (data.size < days) throw java.lang.RuntimeException("Unable to gather last $days of reports from $creator") else data
}



data class TimeSheetEntry(val uuid: String, val creator: String, val uploadedDate: LocalDate)

val timesheet_data = mutableListOf(
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 2, 5), uuid = "000-001", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 2, 4), uuid = "000-002", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 2, 3), uuid = "000-003", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 2, 2), uuid = "000-004", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 2, 1), uuid = "000-005", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 1, 30), uuid = "000-006", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 1, 29), uuid = "000-007", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 1, 28), uuid = "000-008", creator = "James Garner"),
    TimeSheetEntry(uploadedDate = LocalDate.of(2024, 1, 27), uuid = "000-009", creator = "James Garner"),
)