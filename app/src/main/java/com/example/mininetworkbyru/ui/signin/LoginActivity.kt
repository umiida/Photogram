package com.example.mininetworkbyru.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mininetworkbyru.R
import com.example.mininetworkbyru.ui.MainActivity
import com.example.mininetworkbyru.ui.signup.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        btn_login.setOnClickListener {
            loadingView(true)
            if (et_username.text.isNotEmpty() && et_password.text.isNotEmpty()) {
                mAuth.signInWithEmailAndPassword(
                    et_username.text.toString(),
                    et_password.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            loadingView(false)
                            val currentUser = mAuth.currentUser
                            updateUI(currentUser)
                        } else {
                            loadingView(false)
                            Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
        tv_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }


    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadingView(loading: Boolean) {
        if (loading) {
            pb_loading.visibility = View.VISIBLE
        } else {
            pb_loading.visibility = View.GONE
        }
//        et_username.isEnabled = !loading
//        et_password.isEnabled = loading
//        btn_login.isEnabled = loading
//        tv_register.isEnabled = loading
    }
}
