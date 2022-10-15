package com.rogernkosi.absaassessment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rogernkosi.absaassessment.commons.Constants.BASE_URL
import com.rogernkosi.absaassessment.data.remote.ApiClient
import com.rogernkosi.absaassessment.data.remote.repository.TimelinesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttp() :OkHttpClient{
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient) : ApiClient{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun providesTimelinesRepository(impl: TimelinesRepositoryImpl) : TimelinesRepositoryImpl = impl

}