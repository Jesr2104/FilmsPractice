package com.justjump.filmscave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.google.firebase.auth.FirebaseAuth
import com.justjump.filmscave.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp(binding)
    }

    private fun setUp(binding: ActivitySignUpBinding) {
        title = "Authentication"

        binding.SignUpButton.setOnClickListener {
            if (binding.EmailField.isNotEmpty() && binding.PasswordField.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.EmailText.text.toString(),
                    binding.PasswordText.text.toString(),
                ).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "The user has been created successfully", Toast.LENGTH_SHORT).show()
                        onBackPressed()
                    } else {
                        Toast.makeText(this, "Could not create", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "You must complete all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}