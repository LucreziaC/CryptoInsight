package com.lucreziacarena.cryptoinsight.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    /*@Singleton
    @Provides
    fun provideMovieRepository(
        moviesApi: MovieApi,
        trailersApi: TrailerApi
    ): IMovieRepo {
        return MovieRepo(movieDao, moviesApi, trailersApi)
    }*/
}
