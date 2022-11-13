package ru.binnyatoff.weatherappcompose.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.binnyatoff.weatherappcompose.GPS
import ru.binnyatoff.weatherappcompose.data.Api
import ru.binnyatoff.weatherappcompose.data.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(api: Api): Repository {
        return Repository(api)
    }

    @Provides
    @Singleton
    fun provideGPS(@ApplicationContext appContext: Context):GPS{
        return GPS(appContext = appContext)
    }
}