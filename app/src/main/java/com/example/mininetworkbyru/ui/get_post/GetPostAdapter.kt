package com.example.mininetworkbyru.ui.get_post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mininetworkbyru.R
import kotlinx.android.synthetic.main.get_post_item.view.*

class GetPostAdapter : RecyclerView.Adapter<GetPostAdapter.GetPostViewHolder>() {

    var models: List<Post> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetPostViewHolder {
        val itemViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.get_post_item, parent, false)
        return GetPostViewHolder(itemViewHolder)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: GetPostViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    inner class GetPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModel(user: Post) {
            itemView.tvTitleGetPost.text = user.title
            itemView.tvDescGetPost.text = user.description
            itemView.etTextGetPost.setText(user.text)
        }
    }

}

data class Post(
    val title: String = "",
    val description: String = "",
    val text: String = "",
    val like: Int = 0,
    val dislike: Int = 0,
    val userId: String = ""
)