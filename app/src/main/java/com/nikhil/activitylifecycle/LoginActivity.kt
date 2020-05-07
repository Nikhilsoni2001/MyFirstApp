package com.nikhil.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtForgot: TextView
    private lateinit var txtRegister: TextView
    private val validMobile = "0123456789"
    private val validpassword = arrayOf("thanos","hulk","captain","thor","theAvengers")
    lateinit var sharedPrefenences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefenences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        val isLoggedIn = sharedPrefenences.getBoolean("isLoggedIn",false)
            setContentView(R.layout.activity_login)

        if (isLoggedIn) {
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            startActivity(intent)
            finish()
        }


        title = "Log In"

        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtForgot = findViewById(R.id.txtForgot)
        txtRegister = findViewById(R.id.txtRegister)

        btnLogin.setOnClickListener(this)                                                       //"this" for method created below
    }

    override fun onClick(v: View?) {            //onclick Action

        val mobile = etMobileNumber.text.toString()
        val password = etPassword.text.toString()

        val nameOfAvenger : String


        val intent = Intent(this@LoginActivity, AvengersActivity::class.java)               //creating a bridge

        if( mobile == validMobile) {
            when (password) {
               validpassword[0] -> {
                nameOfAvenger = "Thanos"
                savePreferences(nameOfAvenger)
                intent.putExtra("Name", nameOfAvenger)
                startActivity(intent)                                                               //passing in start activity
            }
                validpassword[1] -> {
                    nameOfAvenger = "Hulk"
                    savePreferences(nameOfAvenger)
                    intent.putExtra("Name", nameOfAvenger)
                    startActivity(intent)                                                               //passing in start activity
                }

               validpassword[2] -> {
                    nameOfAvenger = "Captain America"
                   savePreferences(nameOfAvenger)
                    intent.putExtra("Name", nameOfAvenger)
                    startActivity(intent)                                                               //passing in start activity
                }

             validpassword[3] ->    {
                nameOfAvenger = "Thor"
                 savePreferences(nameOfAvenger)
                intent.putExtra("Name", nameOfAvenger)
                startActivity(intent)                                                               //passing in start activity
            }

            validpassword[4] ->    {
                nameOfAvenger = "The Avengers"
                savePreferences(nameOfAvenger)
                intent.putExtra("Name", nameOfAvenger)
                startActivity(intent)                                                               //passing in start activity
            } else -> Toast.makeText(this@LoginActivity, "Incorrect Password", Toast.LENGTH_LONG).show()     //to show a toast
        }
        } else
            Toast.makeText(this@LoginActivity, "Incorrect Credentials", Toast.LENGTH_LONG).show()     //to show a toast

    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savePreferences(title: String) {
        sharedPrefenences.edit().putBoolean("isLoggedIn",true).apply()
        sharedPrefenences.edit().putString("Title",title).apply()
    }
}
