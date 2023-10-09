package com.example.data.repo

import com.example.data.remot.ApiService
import com.example.domain.entity.UnsplashResponse
import com.example.domain.repo.AnimalesRepo

class AnimalesRepoImpl(private val api:ApiService):AnimalesRepo {
    override suspend fun getDogsFromRemote(
        query: String,
        page: Int,
        per_Page: Int
    ): UnsplashResponse =api.searchPhotos(query,page,per_Page)

//    override suspend fun getAnimalesFromDataBase(limit:Int, offest:Int):List <DataModelAnimalLocal> = database.getPagedList(limit,offest)
//    override suspend fun setDataLocaleRom(listData: List<DataModelAnimalLocal>) {
//
//   for(i in 0..listData.size-1)
//    database.insert(listData.get(i))
//    }


}