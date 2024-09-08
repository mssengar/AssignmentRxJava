package com.example.assignmentrxjava.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentrxjava.R
import com.example.assignmentrxjava.data.entity.Post
import com.example.assignmentrxjava.databinding.FragmentPostListBinding
import com.example.assignmentrxjava.presentation.adapter.OnItemClickListener
import com.example.assignmentrxjava.presentation.adapter.PostListAdapter
import com.example.assignmentrxjava.presentation.state.StatePostList
import com.example.assignmentrxjava.presentation.viewmodel.PostListViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostListFragment : Fragment() {

    private val postsViewModel by viewModels<PostListViewModel>()

    private lateinit var postListAdapter: PostListAdapter

    private lateinit var binding: FragmentPostListBinding

    private var posts: List<Post> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostListBinding.bind(view)

        findNavController().clearBackStack(R.id.loginFragment)
        val mContext = requireContext()
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.posts)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.favourites)))

        postListAdapter = PostListAdapter(listOf(), mContext)
        postListAdapter.setOnItemClickListener(object : OnItemClickListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(position: Int) {
                if (binding.tabLayout.selectedTabPosition == 0) {
                    posts.single { it.id == position }.apply {
                        isFav = !isFav
                        postsViewModel.updateFavInDb(this)
                    }
                    postListAdapter.notifyDataSetChanged()
                }
            }
        })
        binding.postsRV.adapter = postListAdapter
        binding.postsRV.layoutManager = LinearLayoutManager(mContext)

        postsViewModel.statePostsList.observe(viewLifecycleOwner) { postListState ->
            when (postListState) {
                is StatePostList.Loading -> {
                    // handling loader
                }

                is StatePostList.Success -> {
                    posts = postListState.postList
                    postListAdapter.updateList(posts)
                    postListAdapter.notifyDataSetChanged()
                }

                is StatePostList.Failure -> {
                    val message = postListState.message
//                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val list = if (tab?.position == 0) {
                    posts
                } else {
                    posts.filter { it.isFav }
                }
                list.let {
                    postListAdapter.updateList(list)
                    postListAdapter.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

}