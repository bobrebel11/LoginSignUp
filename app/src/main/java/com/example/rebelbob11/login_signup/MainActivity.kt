package com.example.rebelbob11.login_signup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //Declaring all the variables
    lateinit var email:String
    lateinit var password:String

    //Declaring the firebase variables
    private lateinit var mAuth:FirebaseAuth

    //Decalring shared preferences
    lateinit var sharedPreferences:SharedPreferences




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Intialising mAuth and shared preferences
        mAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("User Info",Context.MODE_PRIVATE)


        get_user()


        //Handling the login button
        btn_login.setOnClickListener {


            //If user info is not saved into shared preferences
            if (!sharedPreferences.contains("EMAIL")){

                //Getting the text from email and password text fields
                email = text_email.text.toString()
                password = text_password.text.toString()

                //Handling the checkbox to save user details
                if (chck_remember.isChecked){
                    remember_user(email,password)

                }

                //Logging the user in
                login_user(email,password)

            }
            else{

                //Calling get user method
                get_user()
            }







        }


        //Handling the signup button
        btn_signup.setOnClickListener {

            //Getting the text from email and password fields
            email = text_email.text.toString()
            password = text_password.text.toString()

            //Calling the sign up user method
            signup_user(email,password)


        }
    }

    private fun get_user() {


        //Getting the saved user info from shared preferences
        sharedPreferences = getSharedPreferences("User Info",Context.MODE_PRIVATE)
        val eml = sharedPreferences.getString("EMAIL"," ")
        val pwd = sharedPreferences.getString("PASSWORD"," ")

        //Logging the user in
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

        //Making the progressba visible
        progressBar.visibility = View.VISIBLE
        btn_login.visibility = View.INVISIBLE

        //Creating a user in firebase
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult>{ task ->

                    //If user creation is successful
                    if (task.isSuccessful){

                        //Making progress bar invisible
                        progressBar.visibility = View.INVISIBLE

                        //Navigating to next activity
                        var userintent = Intent(applicationContext,LoggedInActivity::class.java)
                        userintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(userintent)


                    }
                    else {

                        //Making progress bar invisible
                        progressBar.visibility = View.INVISIBLE
                        btn_login.visibility = View.VISIBLE

                        Toast.makeText(applicationContext,""+task.exception,Toast.LENGTH_SHORT).show()

                    }
                })

        Toast.makeText(applicationContext,"Email: " + email + "\nPassword: "+ password,Toast.LENGTH_SHORT).show()

    }

    private fun login_user(email: String, password: String) {


        Toast.makeText(applicationContext,"Email: " + email + "\nPassword: "+ password,Toast.LENGTH_SHORT).show()

    }
}
