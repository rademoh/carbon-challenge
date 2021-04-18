package com.rabiu.carbonchallenge.di

import com.rabiu.carbonchallenge.data.AppDatabase
import com.rabiu.carbonchallenge.repository.UserRepository
import com.rabiu.carbonchallenge.repository.UsersRemoteData
import org.koin.dsl.module


val repositoryModule = module {

    single {
        UserRepository(get(),get())
    }

    single {
        get<AppDatabase>().usersDao()
    }

    single {
        AppDatabase.getInstance(get())
    }

    single {

        UsersRemoteData(get())
    }
}