package com.thiaagodev.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.thiaagodev.motivation.infra.MotivationConstants
import com.thiaagodev.motivation.R
import com.thiaagodev.motivation.infra.SecurityPreferences
import com.thiaagodev.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonSaveName.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save_name) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()


        if (name != "") {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}