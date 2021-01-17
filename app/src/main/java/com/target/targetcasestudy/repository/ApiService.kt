package com.target.targetcasestudy.repository

import com.target.targetcasestudy.data.ApiData
import retrofit2.http.GET

interface ApiService {

    @GET("deals")
    suspend fun getUsers(): ApiData

}