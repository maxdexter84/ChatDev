package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.model.Profile


object PreferencesRepository {
    private const val NICKNAME = "NICKNAME"
    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val RANK = "RANK"
    private const val RESPECT = "RESPECT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RATING = "RATING"

    private const val DARK_THEME = "DARK_THEME"

    private val prefs: SharedPreferences by lazy {
        val context = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(context)
    }
    fun getProfile(): Profile {
        return Profile(
            prefs.getString(NICKNAME, " ") ?: " ",
            prefs.getString(RANK, " ") ?: " ",
            prefs.getString(FIRST_NAME, " ") ?: " ",
            prefs.getString(LAST_NAME, " " ) ?: " ",
            prefs.getString(ABOUT, " ") ?: " ",
            prefs.getString(REPOSITORY, " ") ?: " ",
            prefs.getInt(RATING, 0),
            prefs.getInt(RESPECT, 0)
        )
    }

    fun getNightMode(): Boolean {
        return prefs.getBoolean(DARK_THEME, false)
    }

    fun saveProfileData(profile: Profile) {
        profile.apply {
            putValue(NICKNAME to nickName)
            putValue(RANK to rank)
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(ABOUT to about)
            putValue(RESPECT to respect)
            putValue(REPOSITORY to repository)
            putValue(RATING to rating)
        }
    }

   private fun putValue(pair: Pair<String, Any>) = prefs.edit().apply{
        val key = pair.first
        val value = pair.second
        when(value) {
            is String -> putString(key,value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitives type")
        }
    }.apply()

    fun saveNightMode(isDark: Boolean){
        putValue(DARK_THEME to isDark)
    }
}