package com.forcetower.scheduler.injection

import android.content.Context
import androidx.room.Room
import com.forcetower.scheduler.BaseApplication
import com.forcetower.scheduler.core.database.EventDB
import com.forcetower.scheduler.core.service.EventAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
    fun provideEventAPI(client: OkHttpClient): EventAPI {
        return Retrofit.Builder()
            .client(client)
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