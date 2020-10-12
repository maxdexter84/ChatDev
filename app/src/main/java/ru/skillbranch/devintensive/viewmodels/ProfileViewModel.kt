package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.model.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel() {
    private val repository: PreferencesRepository = PreferencesRepository
    private val  profileData = MutableLiveData<Profile>()

    private val nightMode = MutableLiveData<Boolean>()
    fun getNightMode(): LiveData<Boolean> = nightMode

    init {
        Log.d("M_TAG", "ProfileViewModelInit")
        profileData.value = repository.getProfile()
        nightMode.value = repository.getNightMode()
    }

    fun getProfileData(): LiveData<Profile> = profileData

    fun saveProfileData(profile: Profile) {
        repository.saveProfileData(profile)
        profileData.value = profile
    }

    fun saveNightMode(isDark: Boolean){
        PreferencesRepository.saveNightMode(isDark)
        nightMode.value = isDark
    }
}