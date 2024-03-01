package com.lucreziacarena.cryptoinsight.di

import com.lucreziacarena.cryptoinsight.network.CryptoApi
import com.lucreziacarena.cryptoinsight.network.CryptoRepo
import com.lucreziacarena.cryptoinsight.network.CryptoRepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCryptoRepository(
        moviesApi: CryptoApi,
    ): CryptoRepoInterface {
        return CryptoRepo(moviesApi)
    }

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Default



}
