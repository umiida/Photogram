package com.example.mininetworkbyru.ui.add_post

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mininetworkbyru.R
import com.example.mininetworkbyru.ui.profile.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_post_fragment.*
import kotlinx.android.synthetic.main.get_post_item.*
import kotlinx.android.synthetic.main.profile_fragment.*

class AddPostFragment(): Fragment(R.layout.add_post_fragment) {
    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        db.collection("users").document(mAuth.currentUser!!.uid).get()
//            .addOnSuccessListener {
//                tvTitleAddPost.text = it.get("username").toString()
//            }

        btnAddPost.setOnClickListener {
            loading(true)
            if(!etAddPostText.text.isNullOrEmpty()){
                val map: MutableMap<String, Any?> = mutableMapOf()
                map["userId"] = mAuth.currentUser!!.uid
                map["text"] = etAddPostText.text.toString()
                map["like"] = 0
                map["dislike"] = 0
                map["comments"] = arrayListOf<String>()
                map["title"] = tvTitleAddPost.text.toString()
                map["description"] = etDescAddPost.text.toString()

                db.collection("posts").document().set(map)
                    .addOnSuccessListener {
                        loading(false)
                        Toast.makeText(requireContext(), "Your post is published", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener{
                        loading(false)
                        Toast.makeText(requireContext(), it.localizedMessage!!.toString(), Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
    private fun loading(view: Boolean){
        if(view){
            pbAddPost.visibility = View.VISIBLE
        }
        else{
            pbAddPost.visibility = View.GONE
        }
    }
}