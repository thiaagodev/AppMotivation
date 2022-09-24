package com.thiaagodev.motivation

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.thiaagodev.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUserName()

        binding.buttonNewWord.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_word) {
            TODO("")
        }
    }

    private fun handleUserName() {
        val userName = SecurityPreferences(this).getString("USER_NAME")
        binding.textHello.text = "Olá, $userName!"
    }
}