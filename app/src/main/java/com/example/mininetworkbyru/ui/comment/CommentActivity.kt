package com.example.mininetworkbyru.ui.comment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mininetworkbyru.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity() {

    val adapter = CommentAdapter()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        val id = intent.getStringExtra("comId") ?: ""

        rcvComment.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rcvComment.adapter = adapter

        getComment(id)
    }

    fun getComment(id: String) {
        db.collection("posts").document(id).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    it.get("comments")?.let { comment ->
                        adapter.models = comment as List<Map<String, String>>
                    }
                    if(adapter.models.isNotEmpty()){
                        tvCommentEmpty.visibility = View.GONE
                        rcvComment.visibility = View.VISIBLE
                    }
                    else{
                        rcvComment.visibility = View.GONE
                        tvCommentEmpty.visibility = View.VISIBLE
                    }
                }
//                if (it.exists()) {
//                    it.get("comments")?.let { comment ->
//                        adapter.models = comment as List<String>
//                    }
                //           }
//                if (it.exists()){
//                    if(it.get("comments") != null){
//                        adapter.models = it.get("comments") as List<String>
//                        tvCommentEmpty.visibility = View.GONE
//                        rcvComment.visibility = View.VISIBLE
//                    }}
//
//                    else{
//                        rcvComment.visibility = View.GONE
//                        tvCommentEmpty.visibility = View.VISIBLE
//                    }
//                }


            }
    }}


