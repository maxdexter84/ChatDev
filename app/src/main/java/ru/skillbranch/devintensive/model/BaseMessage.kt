package ru.skillbranch.devintensive.model

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat?,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(from: User?, chat: Chat?,date: Date = Date(), type:String = "text", payload: Any?): BaseMessage{
            lastId++
           return when(type){
               "image" -> ImageMessage(from = from,chat = chat,date = date,id = lastId.toString(),image = payload as String)
                else -> TextMessage(from = from,chat = chat,date = date,id = lastId.toString(),text = payload as String)
            }
        }
    }
}