package com.example.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.UnsplashPhoto
import com.example.domain.useCase.GetAnimales
import retrofit2.HttpException
import java.io.IOException

class UnsPlashPagingSource(
    private val getAnimales: GetAnimales
   , private val query: String
):PagingSource<Int , UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val posation = params.key ?: 1
        return try {
           val response = getAnimales.invokeDogesPhoto(query ,posation ,params.loadSize )
           val photos = response.results
            LoadResult.Page(data = photos, prevKey = if (posation == 1) null else posation - 1, nextKey = if (photos.isEmpty()) null else posation + 1)
       }catch (e:IOException)
        {
LoadResult.Error(e)

       }catch (e:HttpException)
        {

           LoadResult.Error(e)
       }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        TODO("Not yet implemented")
    }
}