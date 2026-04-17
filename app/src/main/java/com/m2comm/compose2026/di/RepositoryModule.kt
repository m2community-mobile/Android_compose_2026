package com.m2comm.compose2026.di

import com.m2comm.compose2026.api.ApiRepository
import com.m2comm.compose2026.api.ApiRepositoryImpl
import com.m2comm.compose2026.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Provides
    @Singleton
    fun provideApiRepository(apiService: ApiService) : ApiRepository = ApiRepositoryImpl(apiService)


}