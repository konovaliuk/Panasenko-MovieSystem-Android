package com.panvova.moviesystem.di

import com.panvova.moviesystem.data.MovieAuthorizationGateway
import com.panvova.moviesystem.data.MovieSystemAPI
import com.panvova.moviesystem.data.MovieSystemGateway
import com.panvova.moviesystem.domain.MovieAuthorizationGatewayImpl
import com.panvova.moviesystem.domain.MovieSystemGatewayImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieSystemAPI(retrofit: Retrofit): MovieSystemAPI {
        return retrofit.create(MovieSystemAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieAuthorizationGateway(
        movieSystemAPI: MovieSystemAPI,
    ): MovieAuthorizationGateway {
        return MovieAuthorizationGatewayImpl(movieSystemAPI)
    }

    @Singleton
    @Provides
    fun provideMovieSystemGateway(movieSystemAPI: MovieSystemAPI): MovieSystemGateway {
        return MovieSystemGatewayImpl(movieSystemAPI)
    }
}
