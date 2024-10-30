package com.example.snapfetch.di

import com.example.snapfetch.BuildConfig
import com.example.snapfetch.data.remote.PhotosApi
import com.example.snapfetch.domain.repository.PhotosRepository
import com.example.snapfetch.domain.repository.PhotosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SnapFetchAppModule {

    @Provides
    @Singleton
    fun providePhotosApi(): PhotosApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePhotosRepository(
        photoApi: PhotosApi,
    ): PhotosRepository {
        return PhotosRepositoryImpl(photoApi)
    }

}