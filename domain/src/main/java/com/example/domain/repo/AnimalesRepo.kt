package com.example.domain.repo

import com.example.domain.entity.DataModelAnimalLocal
import com.example.domain.entity.UnsplashResponse

interface AnimalesRepo {

  //all function that i need from repo
  suspend  fun getDogsFromRemote( query: String, page:Int,per_Page:Int): UnsplashResponse
  //get and set local
//  suspend fun getAnimalesFromDataBase(limit:Int, offest:Int):List<DataModelAnimalLocal>
//  suspend fun setDataLocaleRom(listData:List<DataModelAnimalLocal>)



}