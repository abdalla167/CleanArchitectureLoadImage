package com.example.domain.useCase

import com.example.domain.entity.DataModelAnimalLocal
import com.example.domain.repo.AnimalesRepo

class GetAnimales (private val animalesRepo: AnimalesRepo)
{

    suspend   fun invokeDogesPhoto(query: String, page:Int,per_Page:Int)=animalesRepo.getDogsFromRemote(query,page,per_Page)
//    suspend   fun invokeDogesPhotolocal(limit:Int, offest:Int)=animalesRepo.getAnimalesFromDataBase(limit,offest)
//    suspend   fun invokeAnimalesSetDataBase(listAnimla:List<DataModelAnimalLocal>)=animalesRepo.setDataLocaleRom(listAnimla)



}