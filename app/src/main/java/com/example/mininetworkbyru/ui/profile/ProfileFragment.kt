package com.example.mininetworkbyru.ui.profile

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
        btnSaveProfile.setOnClickListener {
            loading(true)
            val map: MutableMap<String, Any?> = mutableMapOf()
            map["username"] = etUsernameProfile.text.toString()
            map["email"] = etEmailProfile.text.toString()
            map["phone"] = etPhoneProfile.text.toString()
            map["information"] = etInfoProfile.text.toString()
            db.collection("users").document(mAuth.currentUser!!.uid).set(map)
                .addOnSuccessListener {
                    loading(false)
                    Toast.makeText(requireContext(), "Your profile data changed successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{e->
                    loading(false)
                    Toast.makeText(requireContext(), e.localizedMessage!!.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }
    private fun showData(){
        db.collection("users").document(mAuth.currentUser!!.uid).get()
            .addOnSuccessListener {
                etEmailProfile.setText(it.get("email").toString())
                etUsernameProfile.setText(it.get("username").toString())
                etPhoneProfile.setText(it.get("phone").toString())
                etInfoProfile.setText(it.get("information").toString())
            }
    }
    private fun loading(view: Boolean){
        if(view){
            pbProfile.visibility = View.VISIBLE
        }
        else{
            pbProfile.visibility = View.GONE
        }
        etUsernameProfile.isEnabled = !view
        etEmailProfile.isEnabled = !view
        etPhoneProfile.isEnabled = !view
        etInfoProfile.isEnabled = !view
    }
}