package com.example.bookingapp.repository

import com.example.bookingapp.network.UserApiService
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
    fun providesRepository(apiService: UserApiService): ImpRepository = UserRepository(apiService)
}
