package com.example.mininetworkbyru.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mininetworkbyru.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db.collection("users").document(mAuth.currentUser?.uid!!).get()
            .addOnCompleteListener {
                //Log.d("tekseriw", it.result.toString())
                if (it.isSuccessful && !it.result?.exists()!!) {
                    val map: MutableMap<String, Any?> = mutableMapOf()
                    map["email"] = mAuth.currentUser?.email
                    db.collection("users").document(mAuth.currentUser?.uid!!).set(map)
                        .addOnSuccessListener {
                            Log.d("paydalaniwshi", "User has been added successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.d("paydalaniwshi", e.localizedMessage!!.toString())
                        }
                }
                bnv_main.setOnNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.profile -> {
                            val fragment: Fragment =
                                ProfileFragment()
                            changeFr(fragment,
                                R.id.frl_layout
                            )
                            return@setOnNavigationItemSelectedListener true
                        }
                        else -> return@setOnNavigationItemSelectedListener false
                    }
                }
            }
    }

    private fun changeFr(fragment: Fragment, container: Int) {
        supportFragmentManager.beginTransaction().replace(container, fragment).commit()

    }
}



