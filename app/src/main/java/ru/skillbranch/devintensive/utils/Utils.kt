package ru.skillbranch.devintensive.utils

import java.util.*


object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val firstName = fullName?.split(" ")?.getOrNull(0)
        val lastName = fullName?.split(" ")?.getOrNull(1)

        return Pair(firstName, lastName) // встроенный в котлин дата класс
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val name = StringBuilder()
        for (item in payload){
           val i = replaceLetter(item.toString())
            if(i == null) {
                name.append(divider)
            } else name.append(i)
        }
        return name.toString()
    }
   private fun replaceLetter(key: String): String? {
        val map = mutableMapOf<String,String>(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh",
            "ъ" to "",
            "ы" to "i",
                "ь" to "",
       "э" to "e to ",
       "ю" to "yu",
       "я" to "ya")
       val bigLetter = mutableMapOf<String, String>()
        for (item in map) {
            val(key, value) = item
            bigLetter[key.toUpperCase(Locale.ROOT)] = value.toUpperCase(Locale.ROOT)

        }

        return map[key] ?: bigLetter[key]
    }
    fun toInitials(firstName: String?, lastName: String?): String? {
        val a = firstLetterToUpperCase(firstName)
        val b = firstLetterToUpperCase(lastName)
        return if (a == null && b == null) null else (a ?: "") + (b ?: "")
    }

    private fun firstLetterToUpperCase(str: String?): String? {
       val let = when(str) {
            null,""," " -> null
            else -> str.first().toUpperCase().toString()
        }
        return let
    }
}