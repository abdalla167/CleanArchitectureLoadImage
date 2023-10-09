package com.example.mealzapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.data.repo.UnsPlashPagingSource
import com.example.domain.entity.DataModelAnimalLocal
import com.example.domain.useCase.GetAnimales
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AnimalesViewModle @Inject constructor (private val getMealUseCase: GetAnimales) : ViewModel()
{


    private val   _photoAnimal  = MutableLiveData(DEFAULT_QUERY)
   // private val   _photoAnimal  =state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    fun setDataLocal(list: List<DataModelAnimalLocal>)
    {

    }


    fun searchPhoto(query: String)
    {
        _photoAnimal.value=query
    }
   val photo=  _photoAnimal.switchMap { queuryString ->
            getSearchResult(queuryString!!).cachedIn(viewModelScope)
    }
    fun getSearchResult(query: String ) =
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    maxSize = 100,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {

                    UnsPlashPagingSource(getMealUseCase, query)
                }

            ).liveData



    companion object {
        private const val CURRENT_QUERY ="CURRENT_QUERY"
        private const val DEFAULT_QUERY = "dogs"
    }
}