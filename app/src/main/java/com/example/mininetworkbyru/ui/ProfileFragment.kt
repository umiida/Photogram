package com.example.mininetworkbyru.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mininetworkbyru.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment: Fragment(R.layout.profile_fragment) {
    private val db = FirebaseFirestore.getInstance()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
        btn_save.setOnClickListener {
            val map: MutableMap<String, Any?> = mutableMapOf()
            map["username"] = ed_username.text.toString()
            map["email"] = ed_email.text.toString()
            map["phone"] = ed_phone.text.toString()
            map["information"] = ed_information.text.toString()
            db.collection("users").document(mAuth.currentUser!!.uid).set(map)
                .addOnSuccessListener {
                    Toast.makeText(re   `quireContext(), "Your profile data changed successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{e->
                    Toast.makeText(requireContext(), e.localizedMessage!!.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }
    private fun showData(){
        db.collection("users").document(mAuth.currentUser!!.uid).get()
            .addOnSuccessListener {
                ed_email.setText(it.get("username").toString())
                ed_username.setText(it.get("email").toString())
                ed_phone.setText(it.get("phone_number").toString())
                ed_information.setText(it.get("information").toString())
            }
    }
}