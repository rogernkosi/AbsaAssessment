package com.rogernkosi.absaassessment.di

import com.rogernkosi.absaassessment.commons.Constants.BASE_URL
import com.rogernkosi.absaassessment.data.remote.ApiClient
import com.rogernkosi.absaassessment.data.remote.repository.TimelinesRepositoryImpl
import com.rogernkosi.absaassessment.domain.repository.TimelinesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.header("accept", "application/json")
                requestBuilder.header("Accept-Encoding", "gzip")
                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): ApiClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun providesTimelinesRepository(impl: TimelinesRepositoryImpl): TimelinesRepository = impl

    @Provides
    @Singleton
    fun providesDateTimeFormatter() : DateTimeFormatter{
        return DateTimeFormatterBuilder().toFormatter()
    }

}