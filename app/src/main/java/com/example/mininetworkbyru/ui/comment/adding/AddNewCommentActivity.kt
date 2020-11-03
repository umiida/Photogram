package com.example.mininetworkbyru.ui.comment.adding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mininetworkbyru.R
import com.example.mininetworkbyru.ui.get_post.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_new_comment.*

class AddNewCommentActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val mAuth = FirebaseAuth.getInstance()
    var postId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_comment)
        postId = intent.getStringExtra("postId") ?: ""

        btnAddNewCmt.setOnClickListener {
            pbAddNewCmt.visibility = View.VISIBLE
            if(!etAddNewCmt.text.isNullOrEmpty()){
                db.collection("posts").document(postId).get()
                    .addOnSuccessListener {
                        if(it.exists()){
                            val post = it.toObject(Post:: class.java)
                            var username = ""
                            db.collection("users").document(mAuth.currentUser!!.uid).get()
                                .addOnSuccessListener {user ->
                                username = user.get("username").toString()
                            }
                            post?.comments?.toMutableList()?.add(mapOf(username to "username"))
                            post?.comments?.toMutableList()?.add(mapOf(etAddNewCmt.text.toString() to "cmt_text"))
                            db.collection("posts").document(postId).update("comments", post?.comments)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener{
                                    Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
                                }
                                .addOnCompleteListener {
                                    pbAddNewCmt.visibility = View.GONE
                                    finish()
                                }

                        }
                    }
            }
        }
    }
}
