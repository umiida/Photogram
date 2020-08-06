package com.example.mininetworkbyru.ui.add_post

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mininetworkbyru.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_post_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.*

class AddPostFragment: Fragment(R.layout.add_post_fragment) {
    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPost.setOnClickListener {
            //loading(true)
            if(!etAddPostText.text.isNullOrEmpty()){
                val map: MutableMap<String, Any?> = mutableMapOf()
                map["userId"] = mAuth?.currentUser!!.uid
                map["text"] = etAddPostText.text.toString()
                map["like"] = 0
                map["dislike"] = 0
                map["comments"] = 0
                //map["data"] =  mAuth?.currentUser!!.providerData
                db.collection("posts").document().set(map)
                    .addOnSuccessListener {
                        //loading(false)
                        Toast.makeText(requireContext(), "Your post is published", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener{
                        //loading(false)
                        Toast.makeText(requireContext(), it.localizedMessage!!.toString(), Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
//    private fun loading(view: Boolean){
//        if(view){
//            pbProfile.visibility = View.VISIBLE
//        }
//        else{
//            pbProfile.visibility = View.GONE
//        }
//        etAddPostText.isEnabled = !view
//        btnAddPost.isEnabled = !view
//    }
}