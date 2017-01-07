package com.github.grubber.tiangoumeitu.core.di

import com.github.grubber.tiangoumeitu.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangoumeitu.data.api.BASE_ENDPOINT
import com.github.grubber.tiangoumeitu.data.api.service.GalleryService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by grubber on 2017/1/7.
 */
@Module
class ApiModule {
    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @ApplicationScope
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    @ApplicationScope
    fun provideGalleryService(retrofit: Retrofit): GalleryService {
        return retrofit.create(GalleryService::class.java)
    }
}