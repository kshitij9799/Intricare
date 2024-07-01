package com.example.intricare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intricare.MainActivity.Companion.postList
import com.example.intricare.adapter.PostListAdapter
import com.example.intricare.data.Message
import com.example.intricare.data.Post
import com.example.intricare.utils.OnItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Response

class HomeFragment : Fragment(), OnItemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(context)
        if (postList == null ){
            recyclerView.visibility = View.GONE
            Toast.makeText(context, "No item to show", Toast.LENGTH_SHORT).show()
        } else {
            recyclerView.adapter = context?.let {
                PostListAdapter(
                    this@HomeFragment,
                    it, postList!!
                )
            }
        }

        return view
    }

    private fun getData(recyclerView: RecyclerView) : List<Post>? {
        var list: List<Post> ?= null
        try {
            lateinit var res: Response<Message>
            CoroutineScope(Dispatchers.IO).launch {
                runBlocking {
                    res = Api.retrofitService.getPostOffices().execute()
                }
                if (res.isSuccessful) {
                    val data = res.body()!!
                    list = data.postOffice
                    withContext(Dispatchers.Main) {

                    }
                }
            }
        } catch (e: Exception) {
            println("output1221 is $e")
        }
        return list
    }

    override fun onItemClick(post: Post) {
        CoroutineScope(Dispatchers.Main).launch {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_holder, DetailsFragment(post))
                addToBackStack(null)
                commit()
            }
        }
    }
}