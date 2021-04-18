package com.rabiu.carbonchallenge.ui.userList

import com.rabiu.carbonchallenge.repository.UserRepository
import com.rabiu.carbonchallenge.ui.common.BaseViewModel

class MainViewModel constructor(private val userRepository: UserRepository): BaseViewModel() {

    var limit = 50
    fun usersList() = userRepository.fetchUsers(limit)
}