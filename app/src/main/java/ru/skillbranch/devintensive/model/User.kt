package ru.skillbranch.devintensive.model

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User( val id: String,
            var firstName: String?,
            var lastName: String?,
            var avatar: String?,
            var rating:Int = 0,
            var respect: Int = 0,
            val lastVisit: Date? = null,
            var isOnline: Boolean = false){
constructor(id: String, firstName: String?, lastName: String?): this(
    id = id,
    firstName = firstName,
    lastName = lastName,
    avatar = null)

    constructor(id:String): this(id, firstName = "John", lastName = "Doe $id")
    var introBit: String
    init {
        introBit = getIntro()
        println("It's a live.\n" + "${if(lastName === "Doe") "His name is $firstName $lastName" else "And his name $firstName $lastName"}\n" + 
                "${getIntro()}")
        //printMe()
    }

    private fun getIntro()="""
        tram trararam trtaram tram tram
        tram trararam trtaram tram tram
    """.trimIndent()

    fun printMe() = println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnlain: $isOnline
        """.trimIndent())

    companion object Factory{
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            val(firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = "$firstName", lastName = "$lastName")
        }

    }


}

