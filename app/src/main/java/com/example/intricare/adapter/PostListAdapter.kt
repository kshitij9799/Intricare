package com.example.intricare.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intricare.R
import com.example.intricare.data.Post
import com.example.intricare.utils.OnItemClickListener


class PostListAdapter(private val listener: OnItemClickListener, private val context: Context, private val posts: List<Post>) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostListAdapter.PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostListAdapter.PostViewHolder, position: Int) {
        Log.d("checkingList", "onBindViewHolder: " + posts[position])
        holder.text.text = posts[position].name
        holder.itemView.setOnClickListener {
            listener.onItemClick(posts[position])
        }
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val text =  view.findViewById<TextView>(R.id.textView)
    }
}