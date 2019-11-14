package com.sxhardha.slocator

import android.app.Application
import androidx.room.Room
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SlocatorApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@SlocatorApplication))

        bind<HttpLoggingInterceptor>() with singleton {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return@singleton httpLoggingInterceptor
        }

        bind<OkHttpClient>() with singleton {
            OkHttpClient.Builder().addInterceptor(instance<HttpLoggingInterceptor>()).build()
        }

        bind<Retrofit>() with singleton {
            Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/images/")
                .client(instance())
                .addConverterFactory(MoshiConverterFactory.create()).build()
        }

        bind<SomeApiInterface>() with singleton { instance<Retrofit>().create(SomeApiInterface::class.java) }

        bind<CatDatabase>() with singleton {
            Room.databaseBuilder(instance(), CatDatabase::class.java, "cat_db")
                .fallbackToDestructiveMigration()
                .build()
        }

        bind<CatDAO>() with singleton { instance<CatDatabase>().catDao() }

        bind<Picasso>() with singleton { Picasso.get() }

        bind<EntranceRepository>() with singleton { EntranceRepository(instance(), instance()) }

        bind<Dispatchers>() with singleton {
            Dispatchers(
                kotlinx.coroutines.Dispatchers.Main,
                kotlinx.coroutines.Dispatchers.IO
            )
        }

        bind() from provider {
            EntranceVMFactory(
                instance(),
                instance()
            )
        }
    }
}