package com.example.assignmentrxjava.presentation.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentrxjava.R
import com.example.assignmentrxjava.databinding.ListCardBinding
import com.example.assignmentrxjava.data.entity.Post

class PostListAdapter(private var postList: List<Post>, val context: Context) : RecyclerView.Adapter<PostListAdapter.ListHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    inner class ListHolder(var bindingV: ListCardBinding) : RecyclerView.ViewHolder(bindingV.root)

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun updateList(newList: List<Post>) {
        postList = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val postView = ListCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return ListHolder(postView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val post = postList[position]
        val view = holder.bindingV

        view.postId.text = post.id.toString()
        view.postTitle.text = post.title
        view.postBody.text = post.body
        if (post.isFav) {
            view.cardView.background = AppCompatResources.getDrawable(context, R.color.peach)
        } else {
            view.cardView.background = AppCompatResources.getDrawable(context, R.color.white)
        }
        view.cardView.setOnClickListener {
            onItemClickListener.onItemClick(post.id)
        }
    }
}
