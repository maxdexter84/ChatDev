package ru.skillbranch.devintensive.model

class UserView(
    val id: String,
    val fullName: String,
    val nickName: String,
    var avatar: String? = null,
    var status: String = "offline",
    val initials: String?

) {
    fun printMe() {
       println("""
    id: 
    fullName: 
    nickName: 
    avatar: 
    status: 
    initials: 
       """.trimIndent())
    }
}