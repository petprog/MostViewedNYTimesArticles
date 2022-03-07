package com.petprog.nytimes.mostviewednytimes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.petprog.nytimes.mostviewednytimes.databinding.FragmentArticleDetailBinding
import com.petprog.nytimes.mostviewednytimes.viewmodel.ArticleDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding

    @Inject
    lateinit var viewModelFactory: ArticleDetailsViewModel.ArticleDetailsViewModelFactory


    private val viewModel: ArticleDetailsViewModel by viewModels {
        val args = ArticleDetailFragmentArgs.fromBundle(requireArguments())

        ArticleDetailsViewModel.provideFactory(viewModelFactory, args.articleId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        initArticle()
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initArticle() {
        viewModel.article.observe(this) { article ->
            binding.apply {
                articleTitle.text = article.title
                articleAuthor.text = article.byline
                articleBody.text = article.abstractX
            }
            binding.imageView.load(article.imageUrl)
        }
    }
}