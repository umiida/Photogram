package com.example.mininetworkbyru.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mininetworkbyru.R
import com.example.mininetworkbyru.ui.get_post.Post
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    var models: List<Map<String, String>> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    inner class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(comment: Map<String, String>){
            itemView.tvCommentUI.text = comment["cmt_username"]
            itemView.tvCommentTI.text = comment["cmt_text"]
        }
    }
}