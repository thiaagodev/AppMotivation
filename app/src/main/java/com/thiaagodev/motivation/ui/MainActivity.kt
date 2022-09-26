package com.thiaagodev.motivation.ui

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.thiaagodev.motivation.infra.MotivationConstants
import com.thiaagodev.motivation.R
import com.thiaagodev.motivation.data.Mock
import com.thiaagodev.motivation.infra.SecurityPreferences
import com.thiaagodev.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL
    private val mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handlePhrase()

        setListeners()
    }

    override fun onResume() {
        super.onResume()
        showUserName()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_new_word -> {
                handlePhrase()
            }
            in listOf(R.id.image_all_inclusive, R.id.image_happy, R.id.image_sunny) -> {
                handleFilter(view.id)
            }
            R.id.text_hello -> {
                startActivity(Intent(this, UserActivity::class.java))
            }
        }
    }

    private fun setListeners() {
        binding.buttonNewWord.setOnClickListener(this)
        binding.imageAllInclusive.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textHello.setOnClickListener(this)
    }

    private fun handlePhrase() {
        binding.textWord.text = mock.getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {
        binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.purple_dark))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.purple_dark))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.purple_dark))

        when (id) {
            R.id.image_all_inclusive -> {
                binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun showUserName() {
        val userName = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textHello.text = "${getString(R.string.hello)}, $userName!"
    }
}