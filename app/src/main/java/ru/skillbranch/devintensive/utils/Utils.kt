package ru.skillbranch.devintensive.utils

import java.io.File

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val firstName = fullName?.split(" ")?.getOrNull(0)
        val lastName = fullName?.split(" ")?.getOrNull(1)

        return Pair(firstName, lastName) // встроенный в котлин дата класс
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var name = StringBuilder()
        for (item in payload){
           val i = replaceLetter(item.toString())
            if(i == null) {
                name.append(divider)
            } else name.append(i)
        }
        return name.toString()
    }
   private fun replaceLetter(key: String): String? {
        val file = File("src/main/java/ru/skillbranch/alphabet.txt")
        val list =  file.readLines()
        val map = mutableMapOf<String,String>()
        for (item in list) {
            val(key, value) = item.split(':')
            val(keyUp, valueUp) = item.split(':')
            map.put(key, value)
            map.put(keyUp.toUpperCase(), valueUp.toUpperCase())
        }
        return map[key]
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