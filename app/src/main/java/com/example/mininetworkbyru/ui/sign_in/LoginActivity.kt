package com.example.mininetworkbyru.ui.sign_in

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mininetworkbyru.R
import com.example.mininetworkbyru.ui.MainActivity
import com.example.mininetworkbyru.ui.sign_up.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            loadingView(true)
            if (etUsernameLogin.text.isNotEmpty() && etPasswordLogin.text.isNotEmpty()) {
                mAuth.signInWithEmailAndPassword(
                    etUsernameLogin.text.toString(),
                    etPasswordLogin.text.toString()
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
        tvRegisterLogin.setOnClickListener {
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
            pbLogin.visibility = View.VISIBLE
        } else {
            pbLogin.visibility = View.GONE
        }
    }
}
