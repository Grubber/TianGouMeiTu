package com.github.grubber.tiangoumeitu.core.di

import android.content.Context
import com.github.grubber.tiangoumeitu.BuildConfig
import com.github.grubber.tiangoumeitu.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangoumeitu.core.di.qualifier.ForApplication
import com.github.grubber.tiangoumeitu.util.ImageRequestTransformer
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by grubber on 2017/1/7.
 */
@Module
class NetworkModule {
    private val HTTP_DISK_CACHE_SIZE = 20 * 1024 * 1024.toLong()
    private val HTTP_DISK_CACHE_NAME = "http-cache"

    private fun _createOkHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(15000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .writeTimeout(15000L, TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).cache(
                    Cache(File(context.externalCacheDir, HTTP_DISK_CACHE_NAME), HTTP_DISK_CACHE_SIZE)
            )
        } else {
            builder.cache(Cache(File(context.cacheDir, HTTP_DISK_CACHE_NAME), HTTP_DISK_CACHE_SIZE))
        }
        return builder.build()
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(@ForApplication context: Context): OkHttpClient {
        return _createOkHttpClient(context)
    }

    @Provides
    @ApplicationScope
    fun providePicasso(@ForApplication context: Context,
                       okHttpClient: OkHttpClient,
                       imageRequestTransformer: ImageRequestTransformer): Picasso {
        val builder = okHttpClient.newBuilder()
        builder.interceptors().clear()
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))

        val picasso = Picasso.Builder(context)
                .requestTransformer(imageRequestTransformer)
                .downloader(OkHttp3Downloader(builder.build()))
                .listener { picasso, uri, exception ->
                    Timber.e(exception, "Failed to load image: $uri")
                }
                .build()
        return picasso
    }
}