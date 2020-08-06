package com.example.mininetworkbyru.ui.sign_up

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mininetworkbyru.R
import com.example.mininetworkbyru.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        btnRegister.setOnClickListener {
            loading(true)
            if (etUsernameRegister.text.isNotEmpty() && etPasswordRegister.text.isNotEmpty()) {
                mAuth.createUserWithEmailAndPassword(
                    etUsernameRegister.text.toString(),
                    etPasswordRegister.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            loading(false)
                            val user = mAuth.currentUser
                            updateUI(user)
                        } else {
                            loading(false)
                            Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT)
                                .show()
                            updateUI(null)
                        }
                    }
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loading(view: Boolean) {
        if (view) {
            pbRegister.visibility = View.VISIBLE
        } else {
            pbRegister.visibility = View.GONE
        }
    }
}

