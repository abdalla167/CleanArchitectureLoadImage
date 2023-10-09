package com.example.mealzapplication.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.UnsplashPhoto
import com.example.mealzapplication.AnimalesViewModle
import com.example.mealzapplication.R
import com.example.mealzapplication.adapter.UnsplashPhotoAdapter
import com.example.mealzapplication.adapter.UnsplashPhotoLoadStateAdapter
import com.example.mealzapplication.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) , UnsplashPhotoAdapter.OnItemClickListner   {
    private val viewModel by viewModels<AnimalesViewModle>()

    lateinit var rv: RecyclerView
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)


         rv= view.findViewById(R.id.category_rv)
        var progressb: ProgressBar =view. findViewById(R.id.progress_bar)
        var buttonRetray: Button =view.findViewById(R.id.button_retry)
        var textviewError : TextView =view.findViewById(R.id.text_view_error)

        val adapterUnsplas = UnsplashPhotoAdapter(this)
        rv.apply {

            setHasFixedSize(true)
            this.adapter = adapterUnsplas.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter { adapterUnsplas.retry() },
                footer = UnsplashPhotoLoadStateAdapter { adapterUnsplas.retry() },
            )


        }
        buttonRetray.setOnClickListener {
            adapterUnsplas.retry()
        }

        lifecycleScope.launch {
            viewModel.photo.observe(viewLifecycleOwner) {

                adapterUnsplas.submitData(lifecycle, it)
                adapterUnsplas.addLoadStateListener { loadStat->

                    progressb.isVisible =loadStat.source.refresh is LoadState.Loading
                    rv.isVisible = loadStat.source.refresh is LoadState.NotLoading
                    buttonRetray.isVisible =loadStat.source.refresh is LoadState.Error
                    textviewError.isVisible =loadStat.source.refresh is LoadState.Error

                    //empty view
                    if(loadStat.source.refresh is LoadState.NotLoading  && loadStat.append.endOfPaginationReached && adapterUnsplas.itemCount >1)
                    {
                        rv.isVisible=false
                        textviewError.isVisible=true

                    }
                    else
                    {
                        textviewError.isVisible=false
                    }

                }

            }
        }



        setHasOptionsMenu(true)

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(com.example.mealzapplication.R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(com.example.mealzapplication.R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    rv.scrollToPosition(0)
                    viewModel.searchPhoto(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
    override fun OnItemClick(photo: UnsplashPhoto) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(photo)
        findNavController().navigate(action)
    }

}