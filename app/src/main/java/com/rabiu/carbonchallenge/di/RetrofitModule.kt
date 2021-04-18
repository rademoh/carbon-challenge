package com.rabiu.carbonchallenge.di


import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.rabiu.carbonchallenge.BuildConfig
import com.rabiu.carbonchallenge.api.AuthInterceptor
import com.rabiu.carbonchallenge.api.DummyApiService
import com.rabiu.carbonchallenge.helper.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val retrofitModule = module {

    val REQUEST_TIMEOUT = 60

    single {
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }
    }

    single {
        okHttpClient(REQUEST_TIMEOUT,get())
    }


    single {
        createRetrofit(get(), get())
    }

    single {
        provideService(get(), get(), DummyApiService::class.java)
    }

    single {
        Gson()
    }

    single {
        GsonConverterFactory.create(get())
    }

    single {
        NetworkConnectionInterceptor(get())
    }
}

private fun createRetrofit(okhttpClient: OkHttpClient, converterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
}

private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
): T {
    return createRetrofit(okhttpClient, converterFactory).create(clazz)
}

private fun Scope.okHttpClient(REQUEST_TIMEOUT: Int, networkConnectionInterceptor : NetworkConnectionInterceptor): OkHttpClient {

    return OkHttpClient.Builder()
        .addInterceptor(get<HttpLoggingInterceptor>())
        .addInterceptor(networkConnectionInterceptor)
        .addInterceptor(AuthInterceptor(BuildConfig.API_ID))
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .build()
}