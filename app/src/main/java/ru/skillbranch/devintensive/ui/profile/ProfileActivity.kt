package ru.skillbranch.devintensive.ui.profile
import android.annotation.SuppressLint
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil

import ru.skillbranch.devintensive.R

import ru.skillbranch.devintensive.databinding.ActivityProfileBinding
import ru.skillbranch.devintensive.model.Profile
import ru.skillbranch.devintensive.utils.Utils
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel
import androidx.lifecycle.ViewModelProvider as ViewModelProvider


const val IS_EDIT_MODE = "edit mode"
class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding
    private var isDarkTheme = false
   var isEditMode: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        initViewModel()
        initView(savedInstanceState)
        observeTheme()
        setTheme()

    }

    private fun setTheme() {
        binding.btnSwitchTheme.setOnClickListener {
            isDarkTheme = !isDarkTheme
            viewModel.saveNightMode(isDarkTheme)
            recreate()
        }
    }

    private fun observeTheme() {
        viewModel.getNightMode().observe(this, {
            isDarkTheme = it
            if (isDarkTheme) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, { updateUI(it) })
    }

    private fun updateUI(profile: Profile?) {
        profile?.also {
            binding.tvRank.text = it.rank
            binding.tvRespect.text = it.respect.toString()
            binding.etRepository.setText(it.repository)
            binding.etFirstName.setText(it.firstName)
            binding.etLastName.setText(it.lastName)
            binding.etAbout.setText(it.about)
            binding.tvNickName.text = it.nickName
            binding.tvRating.text = it.rating.toString()
        }
    }

    private fun initView(savedInstanceState: Bundle?) {
        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE) ?: false
        showCurrentMode(isEditMode)
        isEdit(isEditMode)
        binding.btnEdit.setOnClickListener {
            isEditMode = !isEditMode
            isEdit(isEditMode)
            showCurrentMode(isEditMode)
            if (!isEditMode) {
                saveProfileInfo()
            }
        }
    }

    private fun showCurrentMode(editMode: Boolean) {
        binding.wrRepository.isFocusable = editMode
        //binding.itRepositoryLinc.isEnabled = editMode
        binding.wrAbout.isFocusable = editMode
       // binding.tiAboutMe.isEnabled = editMode
        binding.tiFirstName.isFocusable = editMode
        binding.tiFirstName.isEnabled = editMode
        binding.tiLastName.isFocusable = editMode
        binding.tiLastName.isEnabled = editMode
        binding.icEye.visibility = if (editMode) View.GONE else View.VISIBLE
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun isEdit(edit: Boolean) {
        with(binding.btnEdit) {
            val filter: ColorFilter? = if(edit){
                PorterDuffColorFilter(resources.getColor(R.color.color_accent, theme), PorterDuff.Mode.SRC_IN)
            }else {
                null
            }
            val icon = if(edit) {
                resources.getDrawable(R.drawable.ic_baseline_save_24,theme)
            } else {resources.getDrawable(R.drawable.ic_baseline_edit_black24,theme)}

            background.colorFilter = filter
            setImageDrawable(icon)

        }
    }


    private fun saveProfileInfo() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val nickname = Utils.transliteration("$firstName $lastName")
        Profile(
            nickName = nickname,
            firstName = firstName,
            lastName = lastName,
            about = binding.etAbout.text.toString(),
            repository = binding.etRepository.text.toString()
        ).let{ viewModel.saveProfileData(it) }
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }




}