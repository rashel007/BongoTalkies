package com.rashel.myapplication.di

import android.app.Application
import android.content.Context
import com.rashel.myapplication.data.remote.TmdbApiService
import com.rashel.myapplication.data.repository.MovieRepositoryImpl
import com.rashel.myapplication.domain.repository.MovieRepository
import com.rashel.myapplication.presentation.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context) = MyApp()

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: TmdbApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }
}