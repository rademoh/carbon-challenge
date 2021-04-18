package com.rabiu.carbonchallenge.di

import com.rabiu.carbonchallenge.ui.userDetail.UserDetailViewModel
import com.rabiu.carbonchallenge.ui.userList.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {

        MainViewModel(get())
    }


    viewModel {

        UserDetailViewModel(get())
    }
}