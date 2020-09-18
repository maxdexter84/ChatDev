package ru.skillbranch.devintensive.extension

fun String.truncate(num: Int = 16): String {
    var result: String = ""
    if (this.length >= num) {
        result = this.substring(0..num).trim().plus("...")
        if (result.last() == ' ') result.trim().plus("...")
    }
    return result
}