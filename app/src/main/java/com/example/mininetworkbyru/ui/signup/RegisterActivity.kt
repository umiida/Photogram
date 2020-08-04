package com.example.mininetworkbyru.ui.signup

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
        btn_register.setOnClickListener {
            loading(true)
            if (etr_username.text.isNotEmpty() && etr_password.text.isNotEmpty()) {
                mAuth.createUserWithEmailAndPassword(
                    etr_username.text.toString(),
                    etr_password.text.toString()
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
            pbr_loading.visibility = View.VISIBLE
        } else {
            pbr_loading.visibility = View.GONE
        }
//        etr_username.isEnabled = !view
//        et_password.isEnabled = !view
//        btn_register.isEnabled = !view
    }
}

