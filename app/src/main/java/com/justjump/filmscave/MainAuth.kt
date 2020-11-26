package com.justjump.filmscave

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import com.google.firebase.auth.FirebaseAuth
import com.justjump.filmscave.databinding.ActivityAuthBinding

class MainAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //load the theme
        setTheme(R.style.Theme_FilmsCave)

        super.onCreate(savedInstanceState)
        val binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.SignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        setUp(binding)
        session(binding)
    }

    override fun onStart(){
        super.onStart()

        //val binding = ActivityAuthBinding.inflate(layoutInflater)
        //binding.loginLayout.visibility = View.VISIBLE
    }

    private fun session(binding: ActivityAuthBinding) {
        //we save the credentials of the login
        val sPreferences = getSharedPreferences(
            getString(R.string.prefs_login),
            Context.MODE_PRIVATE
        )
        val email = sPreferences.getString("email", null)

        if (email != null){
            //binding.loginLayout.visibility = View.INVISIBLE
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("EmailUser", email)
            startActivity(intent)
        }
    }

    private fun setUp(binding: ActivityAuthBinding) {
        title = "Authentication"

        binding.LogInButton.setOnClickListener {
            if (binding.EmailField.isNotEmpty() && binding.PasswordField.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.EmailText.text.toString(),
                    binding.PasswordText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("EmailUser", binding.EmailText.text.toString())
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "The username and password are incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(this, "You must complete all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}