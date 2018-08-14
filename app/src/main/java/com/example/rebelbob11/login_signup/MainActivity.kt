package com.example.rebelbob11.login_signup

import android.content.Context
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

    lateinit var sharedPreferences:SharedPreferences




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    sharedPreferences = getSharedPreferences("User Info",Context.MODE_PRIVATE)


        btn_login.setOnClickListener {

            if (!sharedPreferences.contains("EMAIL")){
                email = text_email.text.toString()
                password = text_password.text.toString()

                if (chck_remember.isChecked){
                    remember_user(email,password)

                }

                login_user(email,password)

            }
            else{
                get_user()
            }







        }

        btn_signup.setOnClickListener {

            email = text_email.text.toString()
            password = text_password.text.toString()

            signup_user(email,password)


        }
    }

    private fun get_user() {



        sharedPreferences = getSharedPreferences("User Info",Context.MODE_PRIVATE)
        val eml = sharedPreferences.getString("EMAIL"," ")
        val pwd = sharedPreferences.getString("PASSWORD"," ")

        login_user(eml,pwd);




    }

    private fun remember_user(email: String, password: String) {

        sharedPreferences = getSharedPreferences("User Info",Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putString("EMAIL",email)
        editor.putString("PASSWORD",password)
        editor.apply()

    }

    private fun signup_user(email: String, password: String) {

        Toast.makeText(applicationContext,"Email: " + email + "\nPassword: "+ password,Toast.LENGTH_SHORT).show()

    }

    private fun login_user(email: String, password: String) {


        Toast.makeText(applicationContext,"Email: " + email + "\nPassword: "+ password,Toast.LENGTH_SHORT).show()

    }
}
