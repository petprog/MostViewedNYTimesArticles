package com.petprog.nytimes.mostviewednytimes.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.newyork.nytimes.ui.main.adapter.ArticleListAdapter
import com.newyork.nytimes.utils.NetworkUtils
import com.newyork.nytimes.utils.hide
import com.newyork.nytimes.utils.show
import com.petprog.nytimes.mostviewednytimes.R
import com.petprog.nytimes.mostviewednytimes.databinding.FragmentArticleListBinding
import com.petprog.nytimes.mostviewednytimes.model.Article
import com.petprog.nytimes.mostviewednytimes.model.State
import com.petprog.nytimes.mostviewednytimes.utils.Extension.getColorRes
import com.petprog.nytimes.mostviewednytimes.utils.Extension.showToast
import kotlinx.coroutines.flow.collect
import com.petprog.nytimes.mostviewednytimes.viewmodel.ArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private lateinit var binding: FragmentArticleListBinding

    private val viewModel by viewModels<ArticleListViewModel>()

    private val mAdapter = ArticleListAdapter(this::onItemClicked)

    override fun onStart() {
        super.onStart()
        handleNetworkChanges()
        binding.swipeRefreshLayout.setOnRefreshListener {
            getArticles()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentArticleListBinding.inflate(inflater, container, false)

        initView()
        observePosts()

        return binding.root


    }

    private fun observePosts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articles.collect { state ->
                    when (state) {
                        is State.Loading -> showLoading(true)
                        is State.Success -> {
                            if (state.data.isNotEmpty()) {
                                mAdapter.submitList(state.data.toMutableList())
                                showLoading(false)
                            }
                        }
                        is State.Error -> {
                            requireActivity().showToast(state.message)
                            showLoading(false)
                        }
                    }

                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun getArticles() = viewModel.getArticles()

    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                if (mAdapter.itemCount == 0) getArticles()
                binding.textViewNetworkStatus.text =
                    getString(R.string.text_no_connectivity)
                binding.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(requireActivity().getColorRes(R.color.colorStatusNotConnected))
                }
            } else {
                if (mAdapter.itemCount == 0) getArticles()
                binding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                binding.networkStatusLayout.apply {
                    setBackgroundColor(requireActivity().getColorRes(R.color.colorStatusConnected))
                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }

    private fun initView() {
        binding.run {
            articlesRecyclerView.adapter = mAdapter
            swipeRefreshLayout.setOnRefreshListener {
                getArticles()
            }
        }
    }

    private fun onItemClicked(post: Article) {
        val postId = post.id
        val action =
            ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment(postId)
        findNavController().navigate(action)
    }

    companion object {
        const val ANIMATION_DURATION = 1000L
    }
}