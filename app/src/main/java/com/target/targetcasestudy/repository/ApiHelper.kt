package com.target.targetcasestudy.repository

import com.target.targetcasestudy.repository.ApiService

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
}