package com.example.rebelbob11.login_signup

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var email:String
    lateinit var password:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        btn_login.setOnClickListener {

            email = text_email.text.toString()
            password = text_password.text.toString()

            if (chck_remember.isChecked){
                
            }


            login_user(email,password)

        }

        btn_signup.setOnClickListener {

            email = text_email.text.toString()
            password = text_password.text.toString()

            signup_user(email,password)


        }
    }

    private fun signup_user(email: String, password: String) {

        Toast.makeText(applicationContext,"Email: " + email + "\nPassword: "+ password,Toast.LENGTH_SHORT).show()

    }

    private fun login_user(email: String, password: String) {


        Toast.makeText(applicationContext,"Email: " + email + "\nPassword: "+ password,Toast.LENGTH_SHORT).show()

    }
}
