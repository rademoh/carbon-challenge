package com.rabiu.carbonchallenge.ui.userDetail


import androidx.lifecycle.MutableLiveData
import com.rabiu.carbonchallenge.data.entities.User
import com.rabiu.carbonchallenge.repository.UserRepository
import com.rabiu.carbonchallenge.ui.common.BaseViewModel

class UserDetailViewModel constructor(private val userRepository: UserRepository): BaseViewModel() {

     val user = MutableLiveData<User>()
     fun getUserDetail(userId : String) = userRepository.getUserDetail(userId);

    fun bindUI(it: User) {
      user.postValue(it)
    }
}