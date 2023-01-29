package com.jsaddour.orangebanktest.di

import com.jsaddour.orangebanktest.data.remote.BankService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteClientModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder().apply {
        baseUrl("https://run.mocky.io/v3/")
        addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType()))
    }.build()

    @Singleton
    @Provides
    fun provideBankService(retrofit: Retrofit) = retrofit.create(BankService::class.java)

}