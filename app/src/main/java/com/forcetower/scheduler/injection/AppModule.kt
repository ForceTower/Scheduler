package com.forcetower.scheduler.injection

import android.content.Context
import androidx.room.Room
import com.forcetower.scheduler.BaseApplication
import com.forcetower.scheduler.core.database.EventDB
import com.forcetower.scheduler.core.service.EventAPI
import com.forcetower.scheduler.core.service.GsonAdapters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
object AppModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: BaseApplication): Context {
        return application
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(ZonedDateTime::class.java, GsonAdapters.ZONED_TO_STRING)
            .create()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideEventAPI(client: OkHttpClient, gson: Gson): EventAPI {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://unes.herokuapp.com/api/")
            .build()
            .create(EventAPI::class.java)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideExecutors(): Executor {
        return Executors.newFixedThreadPool(4)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideEventDatabase(context: Context): EventDB {
        return Room.databaseBuilder(context, EventDB::class.java, "event.db")
            .build()
    }
}