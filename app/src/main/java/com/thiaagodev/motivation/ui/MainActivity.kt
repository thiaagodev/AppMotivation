package com.thiaagodev.motivation.ui

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

        handleUserName()
        handlePhrase()

        binding.buttonNewWord.setOnClickListener(this)
        binding.imageAllInclusive.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_new_word -> {
                handlePhrase()
            }
            in listOf(R.id.image_all_inclusive, R.id.image_happy, R.id.image_sunny) -> {
                handleFilter(view.id)
            }
        }
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

    private fun handleUserName() {
        val userName = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textHello.text = "Olá, $userName!"
    }
}