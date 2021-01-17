package com.target.targetcasestudy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.target.targetcasestudy.repository.ApiHelper
import com.target.targetcasestudy.repository.PostRepository
import com.target.targetcasestudy.repository.RetrofitBuilder
import com.target.targetcasestudy.utils.Resource
import kotlinx.coroutines.Dispatchers

class PostViewModel(application: Application) : AndroidViewModel(application){
   // val allPost: LiveData<List<Product>>
    private val repository: PostRepository = PostRepository(ApiHelper(RetrofitBuilder.apiService))




    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
