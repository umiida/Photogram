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

    var onItemClick :(model: Post) -> Unit = {}
    fun setOnItemClicked(onItemClick: (model: Post) -> Unit){
        this.onItemClick = onItemClick
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
        fun populateModel(post: Post) {
            itemView.tvTitleGetPost.text = post.title
            itemView.tvDescGetPost.text = post.description
            itemView.tvTextGetPost.text = post.text

            itemView.setOnClickListener {
                onItemClick.invoke(post)
            }
        }
    }
}
data class Post(
    val title: String = "",
    val description: String = "",
    val text: String = "",
    var id: String = "",
    val like: Int = 0,
    val dislike: Int = 0,
    val userId: String = "",
    var cmt_text: String = ""
)