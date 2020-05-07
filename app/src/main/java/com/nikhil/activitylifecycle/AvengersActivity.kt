package com.nikhil.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AvengersActivity : AppCompatActivity() {

    var titleName: String? = "The Avengers"
    lateinit var txtMessage: EditText
    lateinit var btnSend: Button
    lateinit var btnLogOut: Button
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setContentView(R.layout.activity_avengers)

        titleName = sharedPreferences.getString("Title","The Avengers") as String

        title = titleName

        txtMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        btnLogOut = findViewById(R.id.btnLogOut)


        btnSend.setOnClickListener(){
            val intent = Intent(this@AvengersActivity, MessageActivity :: class.java)
            val message = txtMessage.text.toString()
            intent.putExtra("Message",message)
            startActivity(intent)
        }

        btnLogOut.setOnClickListener{
            val intent = Intent(this@AvengersActivity, LoginActivity::class.java)
            sharedPreferences.edit().clear().apply()
            startActivity(intent)
        }

    }


}
