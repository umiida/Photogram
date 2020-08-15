package com.example.mininetworkbyru.ui.get_post

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mininetworkbyru.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.get_post_fragment.*
import kotlinx.android.synthetic.main.get_post_item.*

class GetPostFragment: Fragment(R.layout.get_post_fragment) {
    private val db = FirebaseFirestore.getInstance()
    private val adapter = GetPostAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcvGetPost.adapter = adapter
        rcvGetPost.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rcvGetPost.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        getAllPosts()

    }
    private fun getAllPosts(){
        val result: MutableList<Post> = mutableListOf()
        db.collection("posts").addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(requireContext(), error.message.toString(), Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            db.collection("posts").get().addOnSuccessListener {
                it.documents.forEach{doc->
                    val model = doc.toObject(Post::class.java)
                    model?.let {
                        result.add(model)
                    }
                }
                adapter.models = result
            }
        }
    }
}