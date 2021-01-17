package com.target.targetcasestudy.repository

class PostRepository(private val apiHelper: ApiHelper)  {

    suspend fun getUsers() = apiHelper.getUsers()

}
