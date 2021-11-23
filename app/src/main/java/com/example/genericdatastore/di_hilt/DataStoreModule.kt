package com.example.genericdatastore.di_hilt

import com.example.genericdatastore.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun injectMainRepository() : MainRepository = MainRepository()
}