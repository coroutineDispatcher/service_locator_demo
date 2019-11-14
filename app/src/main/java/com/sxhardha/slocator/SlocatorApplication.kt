package com.sxhardha.slocator

import android.app.Application
import androidx.room.Room
import com.squareup.picasso.Picasso
import com.sxhardha.slocator.database.CatDatabase
import com.sxhardha.slocator.model.CoroutineDispatchers
import com.sxhardha.slocator.network.SomeApiInterface
import com.sxhardha.slocator.ui.CatAdapter
import com.sxhardha.slocator.ui.EntranceFragment
import com.sxhardha.slocator.ui.EntranceFragmentViewModel
import com.sxhardha.slocator.ui.EntranceRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SlocatorApplication : Application() {

    private val mainModule = module {

        single {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return@single httpLoggingInterceptor
        }

        single {
            OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build()
        }

        single {
            Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/images/")
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create()).build()
        }

        single { get<Retrofit>().create(SomeApiInterface::class.java) }

        single {
            Room.databaseBuilder(get(), CatDatabase::class.java, "cat_db")
                .fallbackToDestructiveMigration()
                .build()
        }

        single {
            get<CatDatabase>().catDao()
        }

        single {
            Picasso.get()
        }

        single {
            CoroutineDispatchers(
                Dispatchers.Main,
                Dispatchers.IO
            )
        }

        factory {
            EntranceRepository(get(), get())
        }

        viewModel {
            EntranceFragmentViewModel(get(), get())
        }

        scope(named<EntranceFragment>()) {
            scoped { CatAdapter(get()) }
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SlocatorApplication)
            modules(mainModule)
        }
    }
}